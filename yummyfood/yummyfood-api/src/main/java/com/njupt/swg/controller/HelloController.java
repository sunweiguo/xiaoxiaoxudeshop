package com.njupt.swg.controller;

import com.njupt.swg.pojo.Users;
import com.njupt.swg.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author swg.
 * @Date 2020/3/8 19:24
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@RestController
public class HelloController {
    @Autowired
    private IUsersService usersService;

    @GetMapping("/hello/{id}")
    public Users hello(@PathVariable String id){
        return usersService.getUserById(id);
    }

}
