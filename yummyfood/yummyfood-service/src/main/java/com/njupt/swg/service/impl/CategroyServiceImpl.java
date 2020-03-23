package com.njupt.swg.service.impl;

import com.njupt.swg.mapper.CategoryMapper;
import com.njupt.swg.mapper.CategoryMapperCustom;
import com.njupt.swg.pojo.Category;
import com.njupt.swg.service.ICategroyService;
import com.njupt.swg.vo.CategoryVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author swg.
 * @Date 2020/3/23 21:39
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Service
@Slf4j
public class CategroyServiceImpl implements ICategroyService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private CategoryMapperCustom categoryMapperCustom;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Category> queryAllRootLevelCat() {
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        //type为1的为root分类
        criteria.andEqualTo("type",1);
        List<Category> categoryList = categoryMapper.selectByExample(example);
        return categoryList;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<CategoryVO> getSubCatList(Integer rootCatId) {
        return categoryMapperCustom.getSubCatList(rootCatId);
    }
}
