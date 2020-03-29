package com.njupt.swg.service;

import com.njupt.swg.pojo.Items;
import com.njupt.swg.pojo.ItemsImg;
import com.njupt.swg.pojo.ItemsParam;
import com.njupt.swg.pojo.ItemsSpec;
import com.njupt.swg.utils.PagedGridResult;
import com.njupt.swg.vo.CartItemVO;
import com.njupt.swg.vo.CommentLevelCountsVO;

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
    //查询商品的好、中、差评数量
    CommentLevelCountsVO queryCommentCounts(String itemId);
    //分页展示商品的评价列表
    PagedGridResult queryPagedComments(String itemId, Integer level, Integer page, Integer pageSize);
    //搜索商品列表
    PagedGridResult searchItems(String keywords, String sort, Integer page, Integer pageSize);
    //携带三级分类搜索商品列表
    PagedGridResult searchCatItems(Integer catId, String sort, Integer page, Integer pageSize);

    List<CartItemVO> refreshCartItems(List<String> itemSpecIdList);

    //根据id获取单个商品规格对象
    ItemsSpec quertyItemSepcById(String specId);

    //根据商品ID获取商品主图URL
    String queryItemMainImgUrlById(String itemId);

    //扣除库存
    void decreaseItemSpecStock(String specId,Integer buyCounts);
}
