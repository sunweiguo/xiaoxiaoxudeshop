package com.njupt.swg.controller.center;

import com.njupt.swg.service.IUserService;
import com.njupt.swg.utils.CommonJsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author swg.
 * @Date 2020/9/19 12:11
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Api(value = "用户中心展示的相关接口",tags = "用户中心展示的相关接口")
@RestController
@RequestMapping("center")
public class CenterController {

    @Autowired
    private IUserService centerUserService;

    @ApiOperation(value = "获取用户信息",notes = "获取用户信息",httpMethod = "GET")
    @GetMapping("userInfo")
    public CommonJsonResult userInfo(
            @ApiParam(name = "userId",value = "用户ID",required = true)
            @RequestParam String userId){
        //TODO 只能查询自己的用户信息，因此这里需要判断userid是否是当前登录用户
        return centerUserService.queryUserById(userId);
    }

}
