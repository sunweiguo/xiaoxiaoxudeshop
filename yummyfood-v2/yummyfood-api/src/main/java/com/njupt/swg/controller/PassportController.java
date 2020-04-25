package com.njupt.swg.controller;

import com.njupt.swg.pojo.Users;
import com.njupt.swg.service.IUserService;
import com.njupt.swg.utils.CommonJsonResult;
import com.njupt.swg.bo.UserBO;
import com.njupt.swg.utils.CookieUtils;
import com.njupt.swg.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

/**
 * @Author swg.
 * @Date 2020/4/19 16:42
 * @CONTACT 317758022@qq.com
 * @DESC 门户用户相关的基本接口：注册、登录、注销、查询用户名是否存在
 */
@Api(value = "门户用户基本操作相关接口",tags = "门户用户基本操作相关接口")
@RestController
@RequestMapping("/passport")
public class PassportController extends BaseController{
    @Autowired
    private IUserService userService;

    @ApiOperation(value = "用户注册",notes = "用户注册",httpMethod = "POST")
    @PostMapping("/regist")
    public CommonJsonResult regist(@ApiParam(name = "UserBO",value = "用户注册实体",required = true)
                                   @RequestBody @Valid UserBO userBO,
                                   BindingResult br,
                                   HttpServletRequest request,
                                   HttpServletResponse response){
        //1、校验是否为空以及密码长度
        if(br.hasErrors()){
            Map<String,String> errorMap = getErrors(br);
            return CommonJsonResult.errorMap(errorMap);
        }
        //2、用户名是否存在
        if(userService.queryUsernameIsExist(userBO.getUsername())){
            return CommonJsonResult.errorMsg("用户名已存在，请换一个试试吧");
        }
        //3、校验两次密码是否一致
        if(!userBO.getPassword().equals(userBO.getConfirmPassword())){
            return CommonJsonResult.errorMsg("两次密码不一致");
        }
        //4、实现注册
        Users userResult = userService.registNewUser(userBO);
        //5、记录cookie
        userResult = setNullProperty(userResult);//去除敏感信息
        CookieUtils.setCookie(request,response, "user",
                JsonUtils.objectToJson(userResult),true);
        //TODO 生成用户TOKEN，存入redis
        //TODO 同步购物车数据
        return CommonJsonResult.ok();
    }

    @ApiOperation(value = "用户名是否存在",notes = "用户名是否存在",httpMethod = "GET")
    @GetMapping("usernameIsExist")
    public CommonJsonResult usernameIsExist(@ApiParam(name = "username",value = "用户名",required = true)
                                            @RequestParam String username){
        if(StringUtils.isBlank(username)){
            return CommonJsonResult.errorMsg("用户名不能为空");
        }
        if(userService.queryUsernameIsExist(username)){
            return CommonJsonResult.errorMsg("用户名已存在");
        }
        return CommonJsonResult.ok();
    }

    @ApiOperation(value = "用户登录",notes = "用户登录",httpMethod = "POST")
    @PostMapping("/login")
    public CommonJsonResult login(@ApiParam(name = "UserBO",value = "用户登录实体",required = true)
                                  @RequestBody UserBO userBO,
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

    @ApiOperation(value = "用户退出登录",notes = "用户退出登录",httpMethod = "POST")
    @PostMapping("/logout")
    public CommonJsonResult logout(@ApiParam(name = "userId",value = "用户ID",required = true)
                                   @RequestParam String userId,
                                   HttpServletRequest request,
                                   HttpServletResponse response){
        //TODO 为了防止非法用户注销其他用户，所以这里需要做下判断是否为本人进行注销操作的
        CookieUtils.deleteCookie(request,response,"user");
        //删除购物车的cookie
        CookieUtils.deleteCookie(request,response,"shopcart");
//        TODO 清除分布式会话中的用户数据
        return CommonJsonResult.ok();
    }

}
