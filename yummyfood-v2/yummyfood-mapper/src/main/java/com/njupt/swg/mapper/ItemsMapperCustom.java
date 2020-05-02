package com.njupt.swg.mapper;

import com.njupt.swg.vo.ItemCommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author swg.
 * @Date 2020/5/2 16:24
 * @CONTACT 317758022@qq.com
 * @DESC
 */
public interface ItemsMapperCustom {
    List<ItemCommentVO> queryItemComments(@Param("itemId") String itemId, @Param("level") Integer level);
}
