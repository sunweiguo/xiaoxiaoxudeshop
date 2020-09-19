package com.njupt.swg.vo;

import lombok.*;

/**
 * @Author swg.
 * @Date 2020/9/19 16:47
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusCountsVO {

    private Integer waitPayCounts;
    private Integer waitDeliverCounts;
    private Integer waitReceiveCounts;
    private Integer waitCommentCounts;

}