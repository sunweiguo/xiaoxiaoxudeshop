package com.njupt.swg.controller;

import com.njupt.swg.service.IPayService;
import com.njupt.swg.utils.CommonJsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author swg.
 * @Date 2020/7/5 16:47
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Api(value = "支付相关接口",tags = "支付相关接口")
@RestController
@RequestMapping("payment")
@Slf4j
public class PayController {

    @Autowired
    private IPayService paySerivce;

    @ApiOperation(value = "支付宝支付",notes = "支付宝支付",httpMethod = "POST")
    @PostMapping("goAlipay")
    public CommonJsonResult pay(
            @ApiParam(name = "userId",value = "用户ID",required = true)
            @RequestParam("userId") String userId,
            @ApiParam(name = "orderId",value = "订单号",required = true)
            @RequestParam("orderId") String orderId){
        log.info("传递进来的userID为：{}，orderId为：{}",userId,orderId);
//        TODO：1、横向越权判断
//    2、预下单，生成支付二维码图片
        return paySerivce.pay(userId,orderId);
    }


}
