package com.njupt.swg.vo;

import lombok.Data;

/**
 * @Author swg.
 * @Date 2020/3/28 22:16
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Data
public class CartItemVO {
    private String itemId;
    private String itemImgUrl;
    private String itemName;
    private String specId;
    private String specName;
    private Integer buyCounts;
    private Integer priceDiscount;
    private Integer priceNormal;
}
