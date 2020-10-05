package com.njupt.swg.mapper;

import com.njupt.swg.pojo.OrderStatus;
import com.njupt.swg.vo.MyOrdersVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author swg.
 * @Date 2020/9/19 16:41
 * @CONTACT 317758022@qq.com
 * @DESC
 */
public interface OrdersMapperCustom {

    //查询个人中心首页的订单的四种状态的数量
    Integer getMyOrderStatusCounts(@Param("paramsMap") Map<String, Object> map);

    //首页的订单去向
    List<OrderStatus> getMyOrderTrend(@Param("paramsMap") Map<String, Object> map);

    List<MyOrdersVO> queryMyOrders(@Param("userId")String userId, @Param("orderStatus") Integer orderStatus);
}
