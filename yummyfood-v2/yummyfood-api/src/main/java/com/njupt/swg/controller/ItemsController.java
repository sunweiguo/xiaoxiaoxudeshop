package com.njupt.swg.controller;

import com.njupt.swg.pojo.Items;
import com.njupt.swg.pojo.ItemsImg;
import com.njupt.swg.pojo.ItemsParam;
import com.njupt.swg.pojo.ItemsSpec;
import com.njupt.swg.service.IItemService;
import com.njupt.swg.utils.CommonJsonResult;
import com.njupt.swg.vo.ItemInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author swg.
 * @Date 2020/5/2 15:16
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Api(value = "商品相关接口",tags = "商品相关接口")
@RestController
@RequestMapping("/items")
@Slf4j
public class ItemsController {
    @Autowired
    private IItemService itemService;

    @ApiOperation(value = "根据商品ID获取商品详情",notes = "根据商品ID获取商品详情",httpMethod = "GET")
    @GetMapping("/info/{itemId}")
    public CommonJsonResult info(
            @ApiParam(name = "itemId",value = "商品ID",required = true)
            @PathVariable String itemId){
        if(StringUtils.isBlank(itemId)){
            return CommonJsonResult.errorMsg("");
        }
        Items item = itemService.queryItemById(itemId);
        List<ItemsImg> itemsImgList = itemService.queryItemImgList(itemId);
        List<ItemsSpec> itemsSpecList = itemService.queryItemSpecList(itemId);
        ItemsParam itemsParam = itemService.queryItemParam(itemId);
        ItemInfoVO itemInfoVO = new ItemInfoVO();
        itemInfoVO.setItem(item);
        itemInfoVO.setItemImgList(itemsImgList);
        itemInfoVO.setItemSpecList(itemsSpecList);
        itemInfoVO.setItemParams(itemsParam);
        return CommonJsonResult.ok(itemInfoVO);
    }
}
