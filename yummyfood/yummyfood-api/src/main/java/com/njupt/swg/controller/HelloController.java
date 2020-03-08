package com.njupt.swg.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author swg.
 * @Date 2020/3/8 19:24
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello(){
        return "hello world";
    }

}
