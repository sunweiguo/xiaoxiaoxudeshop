package com.njupt.swg.controller;

import com.njupt.swg.service.IUserService;
import com.njupt.swg.utils.CommonJsonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author swg.
 * @Date 2020/3/8 19:24
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@RestController
@RequestMapping("passport")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("usernameIsExist")
    public CommonJsonResult usernameIsExist(@RequestParam String username){
        if(StringUtils.isBlank(username)){
            return CommonJsonResult.errorMsg("用户名不能为空");
        }
        if(userService.queryUsernameIsExist(username)){
            return CommonJsonResult.errorMsg("用户名已存在");
        }
        return CommonJsonResult.ok();
    }

}
