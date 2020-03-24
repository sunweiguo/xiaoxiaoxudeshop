package com.njupt.swg.service;

import com.njupt.swg.pojo.Category;
import com.njupt.swg.vo.NewItemsVO;

import java.util.List;

/**
 * @Author swg.
 * @Date 2020/3/23 21:39
 * @CONTACT 317758022@qq.com
 * @DESC
 */
public interface ICategroyService {
    List<Category> queryAllRootLevelCat();
    //根据一级分类ID查询子分类
    List getSubCatList(Integer rootCatId);
    //查询一级分类下的六条最新商品数据
    List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId);
}
