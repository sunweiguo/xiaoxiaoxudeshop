package com.njupt.swg.service;

import com.njupt.swg.vo.SubmitOrderVO;

/**
 * @Author swg.
 * @Date 2020/3/29 16:06
 * @CONTACT 317758022@qq.com
 * @DESC
 */
public interface IOrderService {
    //创建订单相关信息
    String createOrder(SubmitOrderVO submitOrderVO);

    //关闭超时未支付订单
    int closeOrder();
}
