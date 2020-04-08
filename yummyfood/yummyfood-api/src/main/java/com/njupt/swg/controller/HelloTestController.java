package com.njupt.swg.controller;

import com.njupt.swg.utils.CommonJsonResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @Author swg.
 * @Date 2020/4/8 22:12
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@ApiIgnore
@Api(value = "测试",tags = "测试")
@RestController
@RequestMapping("test")
public class HelloTestController {

    @RequestMapping("hello")
    public CommonJsonResult hello(){
        return CommonJsonResult.ok();
    }
}
