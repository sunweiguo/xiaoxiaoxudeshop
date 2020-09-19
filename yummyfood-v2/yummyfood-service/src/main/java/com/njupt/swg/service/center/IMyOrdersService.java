package com.njupt.swg.service.center;

import com.njupt.swg.utils.PagedGridResult;
import com.njupt.swg.vo.OrderStatusCountsVO;

/**
 * @Author swg.
 * @Date 2020/9/19 16:37
 * @CONTACT 317758022@qq.com
 * @DESC
 */
public interface IMyOrdersService {

    OrderStatusCountsVO getOrderStatusCounts(String userId);

    PagedGridResult getOrdersTrend(String userId, Integer page, Integer pageSize);
}
