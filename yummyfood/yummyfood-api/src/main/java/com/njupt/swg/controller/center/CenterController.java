package com.njupt.swg.controller.center;

import com.njupt.swg.service.center.ICenterUserService;
import com.njupt.swg.utils.CommonJsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author swg.
 * @Date 2020/3/31 21:43
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Api(value = "用户中心展示的相关接口",tags = "用户中心展示的相关接口")
@RestController
@RequestMapping("center")
public class CenterController {
    @Autowired
    private ICenterUserService centerUserService;

    @ApiOperation(value = "获取用户信息",notes = "获取用户信息",httpMethod = "GET")
    @GetMapping("userInfo")
    public CommonJsonResult userInfo(
            @ApiParam(name = "userId",value = "用户ID",required = true)
            @RequestParam String userId){
        return CommonJsonResult.ok(centerUserService.queryUserById(userId));
    }

}
