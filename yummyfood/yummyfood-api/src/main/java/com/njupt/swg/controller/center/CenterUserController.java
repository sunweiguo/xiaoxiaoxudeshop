package com.njupt.swg.controller.center;

import com.njupt.swg.bo.center.CenterUserBO;
import com.njupt.swg.pojo.Users;
import com.njupt.swg.service.center.ICenterUserService;
import com.njupt.swg.utils.CommonJsonResult;
import com.njupt.swg.utils.CookieUtils;
import com.njupt.swg.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author swg.
 * @Date 2020/3/31 21:43
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Api(value = "个人中心-用户信息接口",tags = "个人中心-用户信息接口")
@RestController
@RequestMapping("userInfo")
public class CenterUserController {
    @Autowired
    private ICenterUserService centerUserService;

    @ApiOperation(value = "更新用户信息",notes = "更新用户信息",httpMethod = "POST")
    @PostMapping("update")
    public CommonJsonResult update(
            @ApiParam(name = "userId",value = "用户ID",required = true)
            @RequestParam String userId,
            @RequestBody @Valid CenterUserBO centerUserBO,
            BindingResult bindingResult,
            HttpServletRequest request, HttpServletResponse response){
        if(bindingResult.hasErrors()){
            Map<String,String> errorMap = getErrors(bindingResult);
            return CommonJsonResult.errorMap(errorMap);
        }
        Users updateUser = centerUserService.update(userId,centerUserBO);
        updateUser = setNullProperty(updateUser);
        CookieUtils.setCookie(request,response,"user", JsonUtils.objectToJson(updateUser),true);
        //TODO：增加令牌TOKEN，整合redis
        return CommonJsonResult.ok(updateUser);
    }

    private Map<String,String> getErrors(BindingResult bindingResult){
        Map<String,String> map = new HashMap<>();
        List<FieldError> errorList = bindingResult.getFieldErrors();
        for(FieldError error : errorList){
            map.put(error.getField(),error.getDefaultMessage());
        }
        return map;
    }

    private Users setNullProperty(Users userResult){
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
        return userResult;
    }

}
