package com.njupt.swg.mapper;

import com.njupt.swg.vo.ItemCommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemsMapperCustom{
    List<ItemCommentVO> queryItemComments(@Param("itemId") String itemId, @Param("level") Integer level);
}