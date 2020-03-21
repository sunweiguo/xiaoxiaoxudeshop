package com.njupt.swg.controller;

import com.njupt.swg.bo.UserBO;
import com.njupt.swg.service.IUserService;
import com.njupt.swg.utils.CommonJsonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/regist")
    public CommonJsonResult regist(@RequestBody UserBO userBO) {
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPassword = userBO.getConfirmPassword();
//        1、判断用户名和密码不能为空
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(confirmPassword)){
            return CommonJsonResult.errorMsg("用户名或密码不能为空");
        }
//        2、查询用户名是否存在
        if(userService.queryUsernameIsExist(username)){
            return CommonJsonResult.errorMsg("用户名已存在");
        }
//        3、密码长度不能少于6位
        if(password.length() < 6){
            return CommonJsonResult.errorMsg("密码长度不能少于6位");
        }

//        4、判断两次密码是否一致
        if(!password.equals(confirmPassword)){
            return CommonJsonResult.errorMsg("两次密码输入不一致");
        }

//        5、实现注册
        userService.createUser(userBO);
        return CommonJsonResult.ok();
    }

}
