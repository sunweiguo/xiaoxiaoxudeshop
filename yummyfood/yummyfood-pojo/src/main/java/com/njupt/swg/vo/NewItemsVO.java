package com.njupt.swg.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author swg.
 * @Date 2020/3/24 22:26
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Getter
@Setter
public class NewItemsVO {
    private String rootCatId;
    private String rootCatName;
    private String slogan;
    private String catImage;
    private String bgColor;
    private List<SimpleItemVO> simpleItemList;
}
