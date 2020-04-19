package com.njupt.swg.controller;

import com.njupt.swg.pojo.Users;
import com.njupt.swg.service.IUserService;
import com.njupt.swg.utils.CommonJsonResult;
import com.njupt.swg.bo.UserBO;
import com.njupt.swg.utils.CookieUtils;
import com.njupt.swg.utils.JsonUtils;
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
 * @DESC
 */
@RestController
@RequestMapping("/passport")
public class PassportController extends BaseController{
    @Autowired
    private IUserService userService;

    @PostMapping("/regist")
    public CommonJsonResult regist(@RequestBody @Valid UserBO userBO,
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
        userResult = setNullProperty(userResult);
        CookieUtils.setCookie(request,response, "user",
                JsonUtils.objectToJson(userResult),true);
        //TODO 生成用户TOKEN，存入redis
        //TODO 同步购物车数据
        return CommonJsonResult.ok();
    }

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
