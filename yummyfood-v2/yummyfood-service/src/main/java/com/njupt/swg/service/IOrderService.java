package com.njupt.swg.service;

import com.njupt.swg.pojo.OrderItems;
import com.njupt.swg.pojo.Orders;
import com.njupt.swg.vo.SubmitOrderVO;

import java.util.List;

/**
 * @Author swg.
 * @Date 2020/6/29 21:06
 * @CONTACT 317758022@qq.com
 * @DESC
 */
public interface IOrderService {
    //创建订单相关信息
    String createOrder(SubmitOrderVO submitOrderVO);

    int closeOrder();

    //根据工单号查询工单主表记录
    Orders selectByOrderNo(String orderNo);

    List<OrderItems> getByOrderNoUserId(String orderNo);
}
