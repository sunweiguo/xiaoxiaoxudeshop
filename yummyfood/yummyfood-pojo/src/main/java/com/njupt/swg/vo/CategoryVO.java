package com.njupt.swg.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author swg.
 * @Date 2020/3/23 22:22
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Getter
@Setter
public class CategoryVO {
    private Integer id;
    private String name;
    private Integer type;
    private String fatherId;
    private List<SubCategoryVO> subCatList;
}
