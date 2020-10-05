package com.njupt.swg.service.center;

import com.njupt.swg.bo.OrderItemsCommentBO;
import com.njupt.swg.pojo.OrderItems;
import com.njupt.swg.utils.PagedGridResult;

import java.util.List;

/**
 * @Author swg.
 * @Date 2020/10/5 19:15
 * @CONTACT 317758022@qq.com
 * @DESC
 */
public interface IMyCommentsService {
    PagedGridResult queryMyComments(String userId, Integer page, Integer pageSize);

    List<OrderItems> queryPendingComment(String orderId);

    void saveComments(String orderId, String userId, List<OrderItemsCommentBO> commentList);
}
