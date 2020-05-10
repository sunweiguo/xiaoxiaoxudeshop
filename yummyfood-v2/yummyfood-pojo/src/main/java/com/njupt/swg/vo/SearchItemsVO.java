package com.njupt.swg.vo;

import lombok.Data;

/**
 * @Author swg.
 * @Date 2020/5/10 15:27
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
