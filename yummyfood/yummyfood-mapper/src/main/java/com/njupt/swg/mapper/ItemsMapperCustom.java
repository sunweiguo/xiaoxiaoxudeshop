package com.njupt.swg.mapper;

import com.njupt.swg.vo.ItemCommentVO;
import com.njupt.swg.vo.SearchItemsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemsMapperCustom{
    List<ItemCommentVO> queryItemComments(@Param("itemId") String itemId, @Param("level") Integer level);

    List<SearchItemsVO> searchItems(@Param("keywords") String keywords, @Param("sort") String sort);

    List<SearchItemsVO> searchCatItems(@Param("catId")Integer catId,@Param("sort") String sort);
}