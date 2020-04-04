package com.njupt.swg.mapper;

import com.njupt.swg.pojo.OrderStatus;
import com.njupt.swg.vo.MyOrdersVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author swg.
 * @Date 2020/4/4 20:57
 * @CONTACT 317758022@qq.com
 * @DESC
 */
public interface OrdersMapperCustom {
    List<MyOrdersVO> queryMyOrders(@Param("userId")String userId, @Param("orderStatus") Integer orderStatus);

    Integer getMyOrderStatusCounts(@Param("paramsMap") Map<String, Object> map);

    List<OrderStatus> getMyOrderTrend(@Param("paramsMap") Map<String, Object> map);
}
