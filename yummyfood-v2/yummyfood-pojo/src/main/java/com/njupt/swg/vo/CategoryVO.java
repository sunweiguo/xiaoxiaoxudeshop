package com.njupt.swg.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author swg.
 * @Date 2020/5/1 15:40
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
