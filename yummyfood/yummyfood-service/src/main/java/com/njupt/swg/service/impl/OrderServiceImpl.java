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
 * @Date 2020/3/29 16:06
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
        newOrder.setId(orderId);
        newOrder.setUserId(userId);
        UserAddress userAddress = addressService.getSingleAddressByUserIdAndAddressId(userId,addressId);
        newOrder.setReceiverName(userAddress.getReceiver());
        newOrder.setReceiverMobile(userAddress.getMobile());
        newOrder.setReceiverAddress(userAddress.getProvince() +"|"+
                userAddress.getCity() +"|"+userAddress.getDistrict()+"|"+userAddress.getDetail());
        newOrder.setPostAmount(postAmount);
        newOrder.setPayMethod(payMethod);
        newOrder.setLeftMsg(leftMsg);
        newOrder.setIsComment(YesOrNo.NO.type);
        newOrder.setIsDelete(YesOrNo.NO.type);
        newOrder.setCreatedTime(new Date());
        newOrder.setUpdatedTime(new Date());


        //2.循环根据itemSpecIds保存商品信息表
        String[] itemSpecIdArr = itemSpecIds.split(",");
        Integer totalAmount = 0;
        Integer realPayAmount = 0;
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
            String imgUrl = itemService.queryItemMainImgUrlById(itemId);

            //2.3 循环保存子订单数据到数据库
            String subOrderId = sid.nextShort();
            OrderItems subOrderItem = new OrderItems();
            subOrderItem.setId(subOrderId);
            subOrderItem.setOrderId(orderId);
            subOrderItem.setItemId(itemId);
            subOrderItem.setItemName(items.getItemName());
            subOrderItem.setItemImg(imgUrl);
            subOrderItem.setBuyCounts(buyCounts);
            subOrderItem.setItemSpecId(itemSpecId);
            subOrderItem.setItemSpecName(itemsSpec.getName());
            subOrderItem.setPrice(itemsSpec.getPriceDiscount());
            orderItemsMapper.insert(subOrderItem);

            //2.4 商品规格表中需要扣除库存
            itemService.decreaseItemSpecStock(itemSpecId,buyCounts);
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
