package com.njupt.swg.controller;

import com.njupt.swg.bo.ShopcartBO;
import com.njupt.swg.utils.CommonJsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author swg.
 * @Date 2020/5/17 13:25
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Api(value = "购物车",tags = "购物车相关接口")
@RestController
@RequestMapping("/shopcart")
@Slf4j
public class ShopcartController {

    @ApiOperation(value = "添加商品到购物车",notes = "添加商品到购物车",httpMethod = "POST")
    @PostMapping(value = "/add")
    public CommonJsonResult add(
            @ApiParam(name = "userId",value = "用户ID",required = true)
            @RequestParam String userId,
            @ApiParam(name = "shopcartBO",value = "前端传过来的购物车信息",required = true)
            @RequestBody ShopcartBO shopcartBO,
            HttpServletRequest request,
            HttpServletResponse response){
        if(StringUtils.isBlank(userId)){
            return CommonJsonResult.errorMsg("用户未登录的情况下，后端啥都不处理，前端的cookie进行记录即可");
        }
        log.info("前端传过来的购物车数据为：{}",shopcartBO);
        //TODO 前端用户在登录的情况下添加商品到购物车里，会同步在后端往redis中添加缓存
        return CommonJsonResult.ok();
    }

    @ApiOperation(value = "移除购物车里面的商品",notes = "移除购物车里面的商品",httpMethod = "POST")
    @PostMapping(value = "/del")
    public CommonJsonResult del(
            @ApiParam(name = "userId",value = "用户ID",required = true)
            @RequestParam String userId,
            @ApiParam(name = "itemSpecId",value = "前端移除的商品规格ID",required = true)
            @RequestParam String itemSpecId,
            HttpServletRequest request,
            HttpServletResponse response){
        if(StringUtils.isBlank(userId) || StringUtils.isBlank(itemSpecId)){
            return CommonJsonResult.errorMsg("用户未登录的情况下，后端啥都不处理，前端的cookie进行记录即可");
        }
        log.info("前端移除购物车商品的时候传来的用户ID为：{}，商品规格ID为：{}",userId,itemSpecId);
        //TODO 前端用户在登录的情况下购物车里移除商品，会同步在后端往redis中更新缓存
        return CommonJsonResult.ok();
    }


}
