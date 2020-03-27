package com.njupt.swg.vo;

import lombok.Data;

/**
 * @Author swg.
 * @Date 2020/3/27 22:30
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Data
public class SearchItemsVO {
    private String itemName;
    private String itemId;
    private Integer sellCounts;
    private String imgUrl;
    private Integer price;
}
