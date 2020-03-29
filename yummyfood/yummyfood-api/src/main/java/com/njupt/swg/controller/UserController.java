package com.njupt.swg.controller;

import com.njupt.swg.bo.UserBO;
import com.njupt.swg.pojo.Users;
import com.njupt.swg.service.IUserService;
import com.njupt.swg.utils.CommonJsonResult;
import com.njupt.swg.utils.CookieUtils;
import com.njupt.swg.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author swg.
 * @Date 2020/3/8 19:24
 * @CONTACT 317758022@qq.com
 * @DESC
 */
//@ApiIgnore//（swagger文档忽略这个接口显示）
@Api(value = "用户登录注册等操作相关接口",tags = "用户登录注册相关接口")
@RestController
@RequestMapping("passport")
public class UserController {
    @Autowired
    private IUserService userService;

    @ApiOperation(value = "用户名是否存在",notes = "用户名是否存在",httpMethod = "GET")
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

    @ApiOperation(value = "用户注册",notes = "用户注册",httpMethod = "POST")
    @PostMapping("/regist")
    public CommonJsonResult regist(@RequestBody UserBO userBO,
                                  HttpServletRequest request,
                                   HttpServletResponse response) {
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
        Users userResult = userService.createUser(userBO);

//        6、设置cookie
        userResult = setNullProperty(userResult);
        CookieUtils.setCookie(request,response, "user",
                JsonUtils.objectToJson(userResult),true);

        //TODO 生成用户TOKEN，存入redis
        //TODO 同步购物车数据
        return CommonJsonResult.ok();
    }


    @ApiOperation(value = "用户登录",notes = "用户登录",httpMethod = "POST")
    @PostMapping("/login")
    public CommonJsonResult login(@RequestBody UserBO userBO,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        String username = userBO.getUsername();
        String password = userBO.getPassword();
//        1、判断用户名和密码不能为空
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return CommonJsonResult.errorMsg("用户名或密码不能为空");
        }
//        2、实现登录
        Users userResult = userService.queryUserForLogin(username,password);
        if(userResult == null){
            return CommonJsonResult.errorMsg("用户名或密码不正确");
        }
//        3、去除一些敏感信息返回给前端
        userResult = setNullProperty(userResult);
//        4、设置cookie
        CookieUtils.setCookie(request,response, "user",
                                JsonUtils.objectToJson(userResult),true);

        //TODO 生成用户TOKEN，存入redis
        //TODO 同步购物车数据
        return CommonJsonResult.ok(userResult);
    }

    private Users setNullProperty(Users userResult){
        userResult.setPassword(null);
        userResult.setRealname(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
        return userResult;
    }


    @ApiOperation(value = "用户退出登录",notes = "用户退出登录",httpMethod = "POST")
    @PostMapping("/logout")
    public CommonJsonResult logout(@RequestParam String userId,
                                   HttpServletRequest request,
                                   HttpServletResponse response){
        CookieUtils.deleteCookie(request,response,"user");
        //删除购物车的cookie
        CookieUtils.deleteCookie(request,response,"shopcart");
//        TODO 1、清空购物车
//        TODO 2、清除分布式会话中的用户数据
        return CommonJsonResult.ok();
    }
}
