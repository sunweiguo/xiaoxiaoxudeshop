package com.njupt.swg.pojo;

import java.util.Date;
import javax.persistence.*;

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

    /**
     * 获取商品参数id
     *
     * @return id - 商品参数id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置商品参数id
     *
     * @param id 商品参数id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取商品外键id
     *
     * @return item_id - 商品外键id
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * 设置商品外键id
     *
     * @param itemId 商品外键id
     */
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    /**
     * 获取产地 产地，例：中国江苏
     *
     * @return produc_place - 产地 产地，例：中国江苏
     */
    public String getProducPlace() {
        return producPlace;
    }

    /**
     * 设置产地 产地，例：中国江苏
     *
     * @param producPlace 产地 产地，例：中国江苏
     */
    public void setProducPlace(String producPlace) {
        this.producPlace = producPlace;
    }

    /**
     * 获取保质期 保质期，例：180天
     *
     * @return foot_period - 保质期 保质期，例：180天
     */
    public String getFootPeriod() {
        return footPeriod;
    }

    /**
     * 设置保质期 保质期，例：180天
     *
     * @param footPeriod 保质期 保质期，例：180天
     */
    public void setFootPeriod(String footPeriod) {
        this.footPeriod = footPeriod;
    }

    /**
     * 获取品牌名 品牌名，例：三只大灰狼
     *
     * @return brand - 品牌名 品牌名，例：三只大灰狼
     */
    public String getBrand() {
        return brand;
    }

    /**
     * 设置品牌名 品牌名，例：三只大灰狼
     *
     * @param brand 品牌名 品牌名，例：三只大灰狼
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * 获取生产厂名 生产厂名，例：大灰狼工厂
     *
     * @return factory_name - 生产厂名 生产厂名，例：大灰狼工厂
     */
    public String getFactoryName() {
        return factoryName;
    }

    /**
     * 设置生产厂名 生产厂名，例：大灰狼工厂
     *
     * @param factoryName 生产厂名 生产厂名，例：大灰狼工厂
     */
    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    /**
     * 获取生产厂址 生产厂址，例：大灰狼生产基地
     *
     * @return factory_address - 生产厂址 生产厂址，例：大灰狼生产基地
     */
    public String getFactoryAddress() {
        return factoryAddress;
    }

    /**
     * 设置生产厂址 生产厂址，例：大灰狼生产基地
     *
     * @param factoryAddress 生产厂址 生产厂址，例：大灰狼生产基地
     */
    public void setFactoryAddress(String factoryAddress) {
        this.factoryAddress = factoryAddress;
    }

    /**
     * 获取包装方式 包装方式，例：袋装
     *
     * @return packaging_method - 包装方式 包装方式，例：袋装
     */
    public String getPackagingMethod() {
        return packagingMethod;
    }

    /**
     * 设置包装方式 包装方式，例：袋装
     *
     * @param packagingMethod 包装方式 包装方式，例：袋装
     */
    public void setPackagingMethod(String packagingMethod) {
        this.packagingMethod = packagingMethod;
    }

    /**
     * 获取规格重量 规格重量，例：35g
     *
     * @return weight - 规格重量 规格重量，例：35g
     */
    public String getWeight() {
        return weight;
    }

    /**
     * 设置规格重量 规格重量，例：35g
     *
     * @param weight 规格重量 规格重量，例：35g
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     * 获取存储方法 存储方法，例：常温5~25°
     *
     * @return storage_method - 存储方法 存储方法，例：常温5~25°
     */
    public String getStorageMethod() {
        return storageMethod;
    }

    /**
     * 设置存储方法 存储方法，例：常温5~25°
     *
     * @param storageMethod 存储方法 存储方法，例：常温5~25°
     */
    public void setStorageMethod(String storageMethod) {
        this.storageMethod = storageMethod;
    }

    /**
     * 获取食用方式 食用方式，例：开袋即食
     *
     * @return eat_method - 食用方式 食用方式，例：开袋即食
     */
    public String getEatMethod() {
        return eatMethod;
    }

    /**
     * 设置食用方式 食用方式，例：开袋即食
     *
     * @param eatMethod 食用方式 食用方式，例：开袋即食
     */
    public void setEatMethod(String eatMethod) {
        this.eatMethod = eatMethod;
    }

    /**
     * 获取创建时间
     *
     * @return created_time - 创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置创建时间
     *
     * @param createdTime 创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 获取更新时间
     *
     * @return updated_time - 更新时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * 设置更新时间
     *
     * @param updatedTime 更新时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}