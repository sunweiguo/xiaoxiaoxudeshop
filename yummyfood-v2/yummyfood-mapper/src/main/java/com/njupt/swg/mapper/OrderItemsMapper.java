package com.njupt.swg.mapper;

import com.njupt.my.mapper.MyMapper.MyMapper;
import com.njupt.swg.pojo.OrderItems;
import com.njupt.swg.vo.MySubOrderItemVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderItemsMapper extends MyMapper<OrderItems> {
    List<MySubOrderItemVO> getOrderItemsByOrderId(@Param("orderNo") String orderNo);
}