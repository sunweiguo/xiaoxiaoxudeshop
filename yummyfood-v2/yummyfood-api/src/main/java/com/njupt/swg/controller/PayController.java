package com.njupt.swg.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.demo.trade.config.Configs;
import com.google.common.collect.Maps;
import com.njupt.swg.constants.Constants;
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
import java.util.Iterator;
import java.util.Map;


/**
 * @Author swg.
 * @Date 2020/7/5 16:47
 * @CONTACT 317758022@qq.com
 * @DESC 支付宝支付接口、异步回调接口
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

    @ApiOperation(value = "接受支付宝异步回调",notes = "接受支付宝异步回调")
    @PostMapping("alipay_callback")
    public Object alipayCallback(HttpServletRequest request){
        Map<String,String> params = Maps.newHashMap();

//        1、解析参数
        Map requestParams = request.getParameterMap();
        for(Iterator iter = requestParams.keySet().iterator(); iter.hasNext();){
            String name = (String)iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for(int i = 0 ; i <values.length;i++){
                valueStr = (i == values.length -1)?valueStr + values[i]:valueStr + values[i]+",";
            }
            params.put(name,valueStr);
        }
        log.info("支付宝回调,sign:{},trade_status:{},参数:{}",params.get("sign"),params.get("trade_status"),params.toString());

        //非常重要,验证回调的正确性,是不是支付宝发的.并且呢还要避免重复通知.

        params.remove("sign_type");
        try {
            boolean alipayRSACheckedV2 = AlipaySignature.rsaCheckV2(params, Configs.getAlipayPublicKey(),"utf-8",Configs.getSignType());

            if(!alipayRSACheckedV2){
                return CommonJsonResult.errorMsg("非法请求,验证不通过,再恶意请求我就报警找网警了");
            }
        } catch (AlipayApiException e) {
            log.error("支付宝验证回调异常",e);
            return Constants.AlipayCallback.RESPONSE_FAILED;
        }

        CommonJsonResult result = paySerivce.aliCallback(params);
        if(result.isSuccess()){
            return Constants.AlipayCallback.RESPONSE_SUCCESS;
        }
        return Constants.AlipayCallback.RESPONSE_FAILED;
    }


}
