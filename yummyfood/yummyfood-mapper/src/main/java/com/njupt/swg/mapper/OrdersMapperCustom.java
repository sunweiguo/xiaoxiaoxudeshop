package com.njupt.swg.mapper;

import com.njupt.swg.vo.MyOrdersVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author swg.
 * @Date 2020/4/4 20:57
 * @CONTACT 317758022@qq.com
 * @DESC
 */
public interface OrdersMapperCustom {
    List<MyOrdersVO> queryMyOrders(@Param("userId")String userId, @Param("orderStatus") Integer orderStatus);
}
