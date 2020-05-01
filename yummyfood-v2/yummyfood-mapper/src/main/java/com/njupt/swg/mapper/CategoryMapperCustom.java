package com.njupt.swg.mapper;

import com.njupt.my.mapper.MyMapper.MyMapper;
import com.njupt.swg.pojo.Category;
import com.njupt.swg.vo.CategoryVO;

import java.util.List;

/**
 * @Author swg.
 * @Date 2020/5/1 15:39
 * @CONTACT 317758022@qq.com
 * @DESC
 */
public interface CategoryMapperCustom extends MyMapper<Category> {
    List<CategoryVO> getSubCatList(Integer rootCatId);
}
