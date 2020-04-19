package com.njupt.swg.controller;

import com.njupt.swg.pojo.Users;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author swg.
 * @Date 2020/4/19 16:58
 * @CONTACT 317758022@qq.com
 * @DESC
 */
public class BaseController {

    public Map<String,String> getErrors(BindingResult bindingResult){
        Map<String,String> map = new HashMap<>();
        List<FieldError> errorList = bindingResult.getFieldErrors();
        for(FieldError error : errorList){
            map.put(error.getField(),error.getDefaultMessage());
        }
        return map;
    }

    public Users setNullProperty(Users userResult){
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
        return userResult;
    }

}
