package com.njupt.swg.vo;

import lombok.Data;

/**
 * @Author swg.
 * @Date 2020/9/19 16:40
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Data
public class MySubOrderItemVO {
    private String itemId;
    private String itemName;
    private String itemImg;
    private String itemSpecId;
    private String itemSpecName;
    private Integer buyCounts;
    private Integer price;
}
