package com.njupt.swg.bo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author swg.
 * @Date 2020/3/28 21:04
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Getter
@Setter
public class ShopcartBO {
    private String itemId;
    private String itemImgUrl;
    private String itemName;
    private String specId;
    private String specName;
    private Integer buyCounts;
    private String priceDiscount;
    private String priceNormal;
}
