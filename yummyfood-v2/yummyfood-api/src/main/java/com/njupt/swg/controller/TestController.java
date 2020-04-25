package com.njupt.swg.controller;

import com.njupt.swg.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @Author swg.
 * @Date 2020/4/11 23:12
 * @CONTACT 317758022@qq.com
 * @DESC
 */
//@ApiIgnore              //（swagger文档忽略这个接口显示）
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ITestService testService;

    @RequestMapping("hello")
    public String hello(@RequestParam(name = "userId") String userId){
        return testService.sayHello(userId);
    }

}
