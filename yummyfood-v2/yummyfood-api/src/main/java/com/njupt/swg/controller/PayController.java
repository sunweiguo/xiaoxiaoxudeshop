package com.njupt.swg.controller;

import com.njupt.swg.utils.CommonJsonResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("goAlipay")
    public CommonJsonResult pay(@RequestParam("userId") String userId, @RequestParam("orderId") String orderId){
        log.info("传递进来的userID为：{}，orderId为：{}",userId,orderId);

        return CommonJsonResult.ok("http://xiaozhao.oursnail.cn/1442902-66a45c92a322d462.gif");
    }


}
