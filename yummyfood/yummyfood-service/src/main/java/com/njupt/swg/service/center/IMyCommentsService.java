package com.njupt.swg.service.center;

import com.njupt.swg.bo.center.OrderItemsCommentBO;
import com.njupt.swg.pojo.OrderItems;
import com.njupt.swg.utils.PagedGridResult;

import java.util.List;

/**
 * @Author swg.
 * @Date 2020/4/4 22:42
 * @CONTACT 317758022@qq.com
 * @DESC
 */
public interface IMyCommentsService {
    List<OrderItems> queryPendingComment(String orderId);

    void saveComments(String orderId, String userId, List<OrderItemsCommentBO> commentList);

    PagedGridResult queryMyComments(String userId, Integer page, Integer pageSize);
}
