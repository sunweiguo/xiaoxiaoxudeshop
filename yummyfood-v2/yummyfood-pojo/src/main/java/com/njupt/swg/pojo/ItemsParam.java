package com.njupt.swg.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "items_param")
public class ItemsParam {
    /**
     * 商品参数id
     */
    @Id
    private String id;

    /**
     * 商品外键id
     */
    @Column(name = "item_id")
    private String itemId;

    /**
     * 产地 产地，例：中国江苏
     */
    @Column(name = "produc_place")
    private String producPlace;

    /**
     * 保质期 保质期，例：180天
     */
    @Column(name = "foot_period")
    private String footPeriod;

    /**
     * 品牌名 品牌名，例：三只大灰狼
     */
    private String brand;

    /**
     * 生产厂名 生产厂名，例：大灰狼工厂
     */
    @Column(name = "factory_name")
    private String factoryName;

    /**
     * 生产厂址 生产厂址，例：大灰狼生产基地
     */
    @Column(name = "factory_address")
    private String factoryAddress;

    /**
     * 包装方式 包装方式，例：袋装
     */
    @Column(name = "packaging_method")
    private String packagingMethod;

    /**
     * 规格重量 规格重量，例：35g
     */
    private String weight;

    /**
     * 存储方法 存储方法，例：常温5~25°
     */
    @Column(name = "storage_method")
    private String storageMethod;

    /**
     * 食用方式 食用方式，例：开袋即食
     */
    @Column(name = "eat_method")
    private String eatMethod;

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
}