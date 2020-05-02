package com.njupt.swg.vo;

import lombok.Data;

/**
 * @Author swg.
 * @Date 2020/5/2 16:10
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Data
public class CommentLevelCountsVO {
    private Integer totalCounts;
    private Integer goodCounts;
    private Integer normalCounts;
    private Integer badCounts;

    public CommentLevelCountsVO(Integer totalCounts, Integer goodCounts, Integer normalCounts, Integer badCounts) {
        this.totalCounts = totalCounts;
        this.goodCounts = goodCounts;
        this.normalCounts = normalCounts;
        this.badCounts = badCounts;
    }
}
