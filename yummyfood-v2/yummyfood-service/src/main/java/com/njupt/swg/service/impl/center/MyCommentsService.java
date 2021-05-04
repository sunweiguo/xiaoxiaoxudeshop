package com.njupt.swg.service.impl.center;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njupt.swg.bo.OrderItemsCommentBO;
import com.njupt.swg.enums.YesOrNo;
import com.njupt.swg.mapper.ItemsCommentsMapperCustom;
import com.njupt.swg.mapper.OrderItemsMapper;
import com.njupt.swg.mapper.OrderStatusMapper;
import com.njupt.swg.mapper.OrdersMapper;
import com.njupt.swg.pojo.OrderItems;
import com.njupt.swg.pojo.OrderStatus;
import com.njupt.swg.pojo.Orders;
import com.njupt.swg.pojo.Users;
import com.njupt.swg.service.IUserService;
import com.njupt.swg.service.center.IMyCommentsService;
import com.njupt.swg.utils.PagedGridResult;
import com.njupt.swg.vo.MyCommentVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author swg.
 * @Date 2020/10/5 19:15
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Service
@Slf4j
public class MyCommentsService implements IMyCommentsService {

    @Autowired
    public OrderItemsMapper orderItemsMapper;

    @Autowired
    public OrdersMapper ordersMapper;

    @Autowired
    public OrderStatusMapper orderStatusMapper;

    @Autowired
    public ItemsCommentsMapperCustom itemsCommentsMapperCustom;

    @Autowired
    public IUserService userService;

    @Autowired
    private Sid sid;

    @Override
    public PagedGridResult queryMyComments(String userId, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        PageHelper.startPage(page, pageSize);
        List<MyCommentVO> list = itemsCommentsMapperCustom.queryMyComments(map);

        return setterPagedGrid(list, page);
    }

    @Override
    public List<OrderItems> queryPendingComment(String orderId) {
        OrderItems query = new OrderItems();
        query.setOrderId(orderId);
        return orderItemsMapper.select(query);
    }

    @Override
    public void saveComments(String orderId, String userId, List<OrderItemsCommentBO> commentList) {
        // 1. 保存评价 items_comments
        for (OrderItemsCommentBO oic : commentList) {
            oic.setCommentId(sid.nextShort());
        }
        Users currentCommentUser = (Users) userService.queryUserById(userId).getData();
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("userFakeNickname",desensitizedName(currentCommentUser.getUsername()));
        map.put("userFace",currentCommentUser.getFace());
        map.put("commentList", commentList);
        itemsCommentsMapperCustom.saveComments(map);

        // 2. 修改订单表改已评价 orders
        Orders order = new Orders();
        order.setId(orderId);
        order.setIsComment(YesOrNo.YES.type);
        ordersMapper.updateByPrimaryKeySelective(order);

        // 3. 修改订单状态表的留言时间 order_status
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setCommentTime(new Date());
        orderStatusMapper.updateByPrimaryKeySelective(orderStatus);
    }

    private static String desensitizedName(String fullName){
        String name = fullName;
        if (StringUtils.isNotBlank(fullName)) {
            name = StringUtils.left(fullName, 1);
            return StringUtils.rightPad(name, StringUtils.length(fullName), "*");
        }
        return name;
    }

    private PagedGridResult setterPagedGrid(List<?> list, Integer page){
        //包含佷多的分页的数据，需要反馈给前端
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        //当前页
        grid.setPage(page);
        //总记录数
        grid.setRecords(pageList.getTotal());
        //每行显示的内容
        grid.setRows(pageList.getList());
        //总页数
        grid.setTotal(pageList.getPages());
        return grid;
    }
}
