package com.njupt.swg.service.impl;

import com.njupt.swg.mapper.CategoryMapper;
import com.njupt.swg.mapper.CategoryMapperCustom;
import com.njupt.swg.pojo.Category;
import com.njupt.swg.service.ICategroyService;
import com.njupt.swg.utils.CommonJsonResult;
import com.njupt.swg.vo.CategoryVO;
import com.njupt.swg.vo.NewItemsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author swg.
 * @Date 2020/5/1 15:20
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
        criteria.andEqualTo("type",1);
        List<Category> categoryList = categoryMapper.selectByExample(example);
        log.info("查询所有一级分类结束，查询到的一级根分类数据为：{}",categoryList);
        return categoryList;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<CategoryVO> getSubCatList(Integer rootCatId) {
        List<CategoryVO> res = categoryMapperCustom.getSubCatList(rootCatId);
        log.info("查询一级分类:{}下的所有二级、三级分类数据结束：{}",rootCatId,res);
        return res;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId) {
        List<NewItemsVO> res = categoryMapperCustom.getSixNewItemsLazy(rootCatId);
        log.info("查询一级分类:{}下的最新六个商品数据结束：{}",rootCatId,res);
        return res;
    }
}
