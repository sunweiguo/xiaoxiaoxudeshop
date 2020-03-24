package com.njupt.swg.mapper;

import com.njupt.swg.vo.CategoryVO;
import com.njupt.swg.vo.NewItemsVO;

import java.util.List;

public interface CategoryMapperCustom{
    List<CategoryVO> getSubCatList(Integer rootCatId);
    List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId);
}