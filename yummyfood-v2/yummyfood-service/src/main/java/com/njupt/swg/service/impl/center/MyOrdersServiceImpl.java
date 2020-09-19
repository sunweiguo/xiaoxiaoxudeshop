package com.njupt.swg.service.impl.center;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njupt.swg.constants.Constants;
import com.njupt.swg.enums.YesOrNo;
import com.njupt.swg.mapper.OrdersMapperCustom;
import com.njupt.swg.pojo.OrderStatus;
import com.njupt.swg.service.center.IMyOrdersService;
import com.njupt.swg.utils.PagedGridResult;
import com.njupt.swg.vo.OrderStatusCountsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author swg.
 * @Date 2020/9/19 16:38
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Service
public class MyOrdersServiceImpl implements IMyOrdersService {

    @Autowired
    private OrdersMapperCustom ordersMapperCustom;

    @Override
    public OrderStatusCountsVO getOrderStatusCounts(String userId) {
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);

        //待付款
        map.put("orderStatus", Constants.OrderStatusEnum.NO_PAY.getCode());
        int waitPayCounts = ordersMapperCustom.getMyOrderStatusCounts(map);

        //已付款、待发货
        map.put("orderStatus", Constants.OrderStatusEnum.PAID.getCode());
        int waitDeliverCounts = ordersMapperCustom.getMyOrderStatusCounts(map);

        //已发货、待收货
        map.put("orderStatus", Constants.OrderStatusEnum.SHIPPED.getCode());
        int waitReceiveCounts = ordersMapperCustom.getMyOrderStatusCounts(map);

        //已完成、待评价
        map.put("orderStatus", Constants.OrderStatusEnum.ORDER_SUCCESS.getCode());
        map.put("isComment", YesOrNo.NO.type);
        int waitCommentCounts = ordersMapperCustom.getMyOrderStatusCounts(map);

        OrderStatusCountsVO countsVO = new OrderStatusCountsVO(waitPayCounts,
                waitDeliverCounts,
                waitReceiveCounts,
                waitCommentCounts);
        return countsVO;
    }

    @Override
    public PagedGridResult getOrdersTrend(String userId, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        PageHelper.startPage(page, pageSize);
        List<OrderStatus> list = ordersMapperCustom.getMyOrderTrend(map);

        return setterPagedGrid(list, page);
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
