package com.njupt.swg.service;

import com.njupt.swg.pojo.Items;
import com.njupt.swg.pojo.ItemsImg;
import com.njupt.swg.pojo.ItemsParam;
import com.njupt.swg.pojo.ItemsSpec;

import java.util.List;

/**
 * @Author swg.
 * @Date 2020/3/25 21:07
 * @CONTACT 317758022@qq.com
 * @DESC
 */
public interface IItemService {
    Items queryItemById(String itemId);
    //根据商品ID查询商品图片列表
    List<ItemsImg> queryItemImgList(String itemId);
    //根据商品ID查询商品规格列表
    List<ItemsSpec> queryItemSpecList(String itemId);
    //根据商品ID查询商品参数
    ItemsParam queryItemParam(String itemId);
}
