package com.njupt.swg.controller;

import com.njupt.swg.enums.PayMethod;
import com.njupt.swg.service.IOrderService;
import com.njupt.swg.utils.CommonJsonResult;
import com.njupt.swg.utils.CookieUtils;
import com.njupt.swg.vo.SubmitOrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author swg.
 * @Date 2020/3/29 15:54
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Api(value = "订单相关接口",tags = "订单相关接口")
@RestController
@RequestMapping("orders")
@Slf4j
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @ApiOperation(value = "创建订单",notes = "创建订单",httpMethod = "POST")
    @PostMapping("create")
    public CommonJsonResult create(
            @ApiParam(name = "submitOrderVO",value = "创建订单的VO",required = true)
            @RequestBody SubmitOrderVO submitOrderVO,
            HttpServletRequest request,
            HttpServletResponse response){
        if(!submitOrderVO.getPayMethod().equals(PayMethod.ALIPAY.type)
                && !submitOrderVO.getPayMethod().equals(PayMethod.WEIXIN.type)){
            return CommonJsonResult.errorMsg("支付方式不支持哦");
        }
        log.info("用户从前端传递过来的订单信息为：{}",submitOrderVO);
        //1. 创建订单：订单主表、订单子表、订单状态表
        String orderid = null;
        try {
            orderid = orderService.createOrder(submitOrderVO);
        }catch (RuntimeException re){
            return CommonJsonResult.errorMsg(re.getMessage());
        }
        //2. 创建订单后，移除购物车中已结算的商品
//        TODO：整合redis后移除购物车已结算的商品，并同步到前端cookie
        CookieUtils.setCookie(request,response,"shopcart","",true);
        //3. TODO 支付
        return CommonJsonResult.ok(orderid);
    }

}
