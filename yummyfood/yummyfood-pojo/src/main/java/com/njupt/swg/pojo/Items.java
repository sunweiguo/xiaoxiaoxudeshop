package com.njupt.swg.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
public class Items {
    /**
     * 商品主键id
     */
    @Id
    private String id;

    /**
     * 商品名称 商品名称
     */
    @Column(name = "item_name")
    private String itemName;

    /**
     * 分类外键id 分类id
     */
    @Column(name = "cat_id")
    private Integer catId;

    /**
     * 一级分类外键id
     */
    @Column(name = "root_cat_id")
    private Integer rootCatId;

    /**
     * 累计销售 累计销售
     */
    @Column(name = "sell_counts")
    private Integer sellCounts;

    /**
     * 上下架状态 上下架状态,1:上架 2:下架
     */
    @Column(name = "on_off_status")
    private Integer onOffStatus;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 更新时间
     */
    @Column(name = "updated_time")
    private Date updatedTime;

    /**
     * 商品内容 商品内容
     */
    private String content;
}