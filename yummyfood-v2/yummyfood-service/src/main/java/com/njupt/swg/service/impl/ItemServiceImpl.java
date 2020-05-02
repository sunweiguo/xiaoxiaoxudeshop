package com.njupt.swg.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njupt.swg.enums.CommentLevel;
import com.njupt.swg.mapper.*;
import com.njupt.swg.pojo.*;
import com.njupt.swg.service.IItemService;
import com.njupt.swg.utils.DesensitizationUtil;
import com.njupt.swg.utils.PagedGridResult;
import com.njupt.swg.vo.CommentLevelCountsVO;
import com.njupt.swg.vo.ItemCommentVO;
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
    @Autowired
    private ItemsCommentsMapper itemsCommentsMapper;
    @Autowired
    private ItemsMapperCustom itemsMapperCustom;

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

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public CommentLevelCountsVO queryCommentCounts(String itemId) {
        Integer goodCounts = getCommentsCounts(itemId, CommentLevel.GOOD.type);
        Integer commonCounts = getCommentsCounts(itemId,CommentLevel.NORMAL.type);
        Integer badCounts = getCommentsCounts(itemId,CommentLevel.BAD.type);
        Integer totalCounts = goodCounts+commonCounts+badCounts;
        log.info("查询商品：{}下的评论一共有{}条，其中有{}条好评，{}条中评，{}条差评",itemId,totalCounts,goodCounts,commonCounts,badCounts);
        return new CommentLevelCountsVO(totalCounts,goodCounts,commonCounts,badCounts);
    }

    private Integer getCommentsCounts(String itemId,Integer level){
        ItemsComments condition = new ItemsComments();
        condition.setItemId(itemId);
        if(level != null){
            condition.setCommentLevel(level);
        }
        return itemsCommentsMapper.selectCount(condition);
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryPagedComments(String itemId,
                                              Integer level,
                                              Integer page,
                                              Integer pageSize) {

        PageHelper.startPage(page, pageSize);
        List<ItemCommentVO> list = itemsMapperCustom.queryItemComments(itemId,level);
        log.info("查询商品:{}和评价等级为{}的评论分页数据为：{}",itemId,level,list);
        return setterPagedGrid(list,page);
    }

    private PagedGridResult setterPagedGrid(List<?> list,Integer page){
        //包含佷多的分页的数据，需要反馈给前端
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        //当前页
        grid.setPage(page);
        //总记录数
        grid.setRecords(pageList.getTotal());
        //每行显示的内容
        grid.setRows(pageList.getList());
        //总页数
        grid.setTotal(pageList.getPages());
        return grid;
    }
}
