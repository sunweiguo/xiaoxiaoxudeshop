package com.njupt.swg.service.impl;

import com.njupt.swg.enums.OrderStatusEnum;
import com.njupt.swg.enums.YesOrNo;
import com.njupt.swg.mapper.OrderItemsMapper;
import com.njupt.swg.mapper.OrderStatusMapper;
import com.njupt.swg.mapper.OrdersMapper;
import com.njupt.swg.pojo.*;
import com.njupt.swg.service.IAddressService;
import com.njupt.swg.service.IOrderService;
import com.njupt.swg.utils.DateUtil;
import com.njupt.swg.vo.SubmitOrderVO;
import lombok.extern.slf4j.Slf4j;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author swg.
 * @Date 2020/6/29 21:06
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrderItemsMapper orderItemsMapper;
    @Autowired
    private OrderStatusMapper orderStatusMapper;
    @Autowired
    private Sid sid;
    @Autowired
    private IAddressService addressService;
    @Autowired
    private ItemServiceImpl itemService;


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public String createOrder(SubmitOrderVO submitOrderVO) {
        String userId = submitOrderVO.getUserId();
        String addressId = submitOrderVO.getAddressId();
        String itemSpecIds = submitOrderVO.getItemSpecIds();
        Integer payMethod = submitOrderVO.getPayMethod();
        String leftMsg = submitOrderVO.getLeftMsg();
        //本系统默认包邮，邮费为0
        Integer postAmount = 0;

        //1.创建订单表：订单主表
        Orders newOrder = new Orders();
        String orderId = sid.nextShort();
        newOrder.setId(orderId);//主键
        newOrder.setUserId(userId);//用户ID
        UserAddress userAddress = addressService.getSingleAddressByUserIdAndAddressId(userId,addressId);//根据用户ID和地址ID获取地址对象
        newOrder.setReceiverName(userAddress.getReceiver());//收货人姓名
        newOrder.setReceiverMobile(userAddress.getMobile());//收货人手机号码
        newOrder.setReceiverAddress(userAddress.getProvince() +"|"+
                userAddress.getCity() +"|"+userAddress.getDistrict()+"|"+userAddress.getDetail());//收货人地址
        newOrder.setPostAmount(postAmount);//邮费默认包邮为0
        newOrder.setPayMethod(payMethod);//支付方式
        newOrder.setLeftMsg(leftMsg);//买家留言
        newOrder.setIsComment(YesOrNo.NO.type);//买家是否评价
        newOrder.setIsDelete(YesOrNo.NO.type);//订单是否为删除状态
        newOrder.setCreatedTime(new Date());//订单创建时间
        newOrder.setUpdatedTime(new Date());//订单更新时间


        //2.循环根据itemSpecIds保存商品信息表
        String[] itemSpecIdArr = itemSpecIds.split(",");
        Integer totalAmount = 0;//订单总价格
        Integer realPayAmount = 0;//实际支付的总价格
        for(String itemSpecId : itemSpecIdArr){
            //2.1 根据规格id获取规格相关信息，主要是一个价格
            ItemsSpec itemsSpec = itemService.quertyItemSepcById(itemSpecId);
            //TODO 整合redis后，商品购买的数量从redis中获取，这里暂时先乘以1
            int buyCounts = 1;
            totalAmount += itemsSpec.getPriceNormal() * buyCounts;
            realPayAmount += itemsSpec.getPriceDiscount() * buyCounts;

            //2.2 根据商品id获取商品信息和商品图片
            String itemId = itemsSpec.getItemId();
            Items items = itemService.queryItemById(itemId);
            String imgUrl = itemService.queryItemMainImgUrlById(itemId);//商品主图

            //2.3 循环保存子订单数据到数据库
            String subOrderId = sid.nextShort();
            OrderItems subOrderItem = new OrderItems();
            subOrderItem.setId(subOrderId);//订单商品分表的主键
            subOrderItem.setOrderId(orderId);//主订单表的主键，跟订单主表关联起来
            subOrderItem.setItemId(itemId);//商品ID
            subOrderItem.setItemName(items.getItemName());//商品名称
            subOrderItem.setItemImg(imgUrl);//主图url
            subOrderItem.setBuyCounts(buyCounts);//数量，暂定为1，后面从redis中取出实际的数量
            subOrderItem.setItemSpecId(itemSpecId);//商品规格ID
            subOrderItem.setItemSpecName(itemsSpec.getName());//商品规格名称
            subOrderItem.setPrice(itemsSpec.getPriceDiscount());//成交价格
            orderItemsMapper.insert(subOrderItem);

            //2.4 商品规格表中需要扣除库存
            itemService.decreaseItemSpecStock(itemSpecId,buyCounts);//扣除数据库中库存
        }

        newOrder.setTotalAmount(totalAmount);
        newOrder.setRealPayAmount(realPayAmount);
        ordersMapper.insert(newOrder);

        //3.保存订单状态表
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setOrderStatus(OrderStatusEnum.WAIT_PAY.type);
        orderStatus.setCreatedTime(new Date());
        orderStatusMapper.insert(orderStatus);

        //前端要用这个orderid
        return orderId;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int closeOrder() {
        //查询所有未付款订单，判断时间是否超时
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderStatus(OrderStatusEnum.WAIT_PAY.type);
        List<OrderStatus> orderStatusList = orderStatusMapper.select(orderStatus);
        int count = 0;
        for(OrderStatus os:orderStatusList){
            Date createTime = os.getCreatedTime();
            int days = DateUtil.daysBetween(createTime,new Date());
            if(days >= 1){
                //超过一天则关闭订单
                count++;
                doCloseOrder(os.getOrderId());
            }
        }
        return count;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    void doCloseOrder(String orderId) {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderStatus(OrderStatusEnum.CLOSE.type);
        orderStatus.setOrderId(orderId);
        orderStatus.setCloseTime(new Date());
        orderStatusMapper.updateByPrimaryKeySelective(orderStatus);
    }
}
