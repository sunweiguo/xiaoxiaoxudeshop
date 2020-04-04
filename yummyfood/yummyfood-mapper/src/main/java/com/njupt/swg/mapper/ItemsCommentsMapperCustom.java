package com.njupt.swg.mapper;

import com.njupt.my.mapper.MyMapper.MyMapper;
import com.njupt.swg.pojo.ItemsComments;
import com.njupt.swg.vo.MyCommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsCommentsMapperCustom extends MyMapper<ItemsComments> {

    void saveComments(Map<String, Object> map);

    List<MyCommentVO> queryMyComments(@Param("paramsMap") Map<String, Object> map);

}