package com.njupt.swg.service.impl;

import com.njupt.swg.mapper.ItemsImgMapper;
import com.njupt.swg.mapper.ItemsMapper;
import com.njupt.swg.mapper.ItemsParamMapper;
import com.njupt.swg.mapper.ItemsSpecMapper;
import com.njupt.swg.pojo.Items;
import com.njupt.swg.pojo.ItemsImg;
import com.njupt.swg.pojo.ItemsParam;
import com.njupt.swg.pojo.ItemsSpec;
import com.njupt.swg.service.IItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author swg.
 * @Date 2020/5/2 15:03
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Service
@Slf4j
public class ItemServiceImpl implements IItemService {
    @Autowired
    private ItemsMapper itemsMapper;
    @Autowired
    private ItemsImgMapper itemsImgMapper;
    @Autowired
    private ItemsParamMapper itemsParamMapper;
    @Autowired
    private ItemsSpecMapper itemsSpecMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Items queryItemById(String itemId) {
        return itemsMapper.selectByPrimaryKey(itemId);
    }

    @Override
    public List<ItemsImg> queryItemImgList(String itemId) {
        Example example = new Example(ItemsImg.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId",itemId);
        List<ItemsImg> itemsImgList = itemsImgMapper.selectByExample(example);
        log.info("根据商品ID:{}获取此商品下的所有图片数据:{}",itemId,itemsImgList);
        return itemsImgList;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsSpec> queryItemSpecList(String itemId) {
        Example example = new Example(ItemsSpec.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId",itemId);
        List<ItemsSpec> itemsSpecList = itemsSpecMapper.selectByExample(example);
        log.info("根据商品ID:{}获取此商品下的所有规格数据:{}",itemId,itemsSpecList);
        return itemsSpecList;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsParam queryItemParam(String itemId) {
        Example example = new Example(ItemsSpec.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId",itemId);
        ItemsParam itemsParam = itemsParamMapper.selectOneByExample(example);
        log.info("根据商品ID:{}获取此商品参数数据:{}",itemId,itemsParam);
        return itemsParam;
    }
}
