package com.njupt.swg.mapper;

import com.njupt.my.mapper.MyMapper.MyMapper;
import com.njupt.swg.pojo.ItemsComments;
import com.njupt.swg.vo.MyCommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author swg.
 * @Date 2020/10/5 19:17
 * @CONTACT 317758022@qq.com
 * @DESC
 */
public interface ItemsCommentsMapperCustom extends MyMapper<ItemsComments> {
    List<MyCommentVO> queryMyComments(@Param("paramsMap") Map<String, Object> map);

    void saveComments(Map<String, Object> map);
}
