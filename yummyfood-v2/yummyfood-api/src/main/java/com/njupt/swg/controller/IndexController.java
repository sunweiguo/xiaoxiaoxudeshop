package com.njupt.swg.controller;

import com.njupt.swg.enums.YesOrNo;
import com.njupt.swg.service.ICarouselService;
import com.njupt.swg.service.ICategroyService;
import com.njupt.swg.utils.CommonJsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author swg.
 * @Date 2020/5/1 15:07
 * @CONTACT 317758022@qq.com
 * @DESC 门户首页相关的接口
 */
@Api(value = "首页",tags = "首页展示的相关接口")
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private ICategroyService categroyService;
    @Autowired
    private ICarouselService carouselService;


    @ApiOperation(value = "获取首页商品根分类列表",notes = "获取首页商品根分类列表",httpMethod = "GET")
    @GetMapping("cats")
    public CommonJsonResult cats(){
        return CommonJsonResult.ok(categroyService.queryAllRootLevelCat());
    }

    @ApiOperation(value = "获取首页商品子分类",notes = "获取首页商品子分类",httpMethod = "GET")
    @GetMapping("subCat/{rootCatId}")
    public CommonJsonResult subCat(
            @ApiParam(name = "rootCatId",value = "一级分类ID",required = true)
            @PathVariable Integer rootCatId){
        if(rootCatId == null){
            return CommonJsonResult.errorMsg("");
        }
        return CommonJsonResult.ok(categroyService.getSubCatList(rootCatId));
    }

    @ApiOperation(value = "获取首页轮播图列表",notes = "获取首页轮播图列表",httpMethod = "GET")
    @GetMapping("carousel")
    public CommonJsonResult carousel(){
        return CommonJsonResult.ok(carouselService.queryAll(YesOrNo.YES.type));
    }

    @ApiOperation(value = "获取每个一级分类下的最新6条商品数据",notes = "获取每个一级分类下的最新6条商品数据",httpMethod = "GET")
    @GetMapping("sixNewItems/{rootCatId}")
    public CommonJsonResult sixNewItems(
            @ApiParam(name = "rootCatId",value = "一级分类ID",required = true)
            @PathVariable Integer rootCatId){
        if(rootCatId == null){
            return CommonJsonResult.errorMsg("");
        }
        return CommonJsonResult.ok(categroyService.getSixNewItemsLazy(rootCatId));
    }

}
