package com.njupt.swg.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "items_spec")
public class ItemsSpec {
    /**
     * 商品规格id
     */
    @Id
    private String id;

    /**
     * 商品外键id
     */
    @Column(name = "item_id")
    private String itemId;

    /**
     * 规格名称
     */
    private String name;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 折扣力度
     */
    private BigDecimal discounts;

    /**
     * 优惠价
     */
    @Column(name = "price_discount")
    private Integer priceDiscount;

    /**
     * 原价
     */
    @Column(name = "price_normal")
    private Integer priceNormal;

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
     * 获取商品规格id
     *
     * @return id - 商品规格id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置商品规格id
     *
     * @param id 商品规格id
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
     * 获取规格名称
     *
     * @return name - 规格名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置规格名称
     *
     * @param name 规格名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取库存
     *
     * @return stock - 库存
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * 设置库存
     *
     * @param stock 库存
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * 获取折扣力度
     *
     * @return discounts - 折扣力度
     */
    public BigDecimal getDiscounts() {
        return discounts;
    }

    /**
     * 设置折扣力度
     *
     * @param discounts 折扣力度
     */
    public void setDiscounts(BigDecimal discounts) {
        this.discounts = discounts;
    }

    /**
     * 获取优惠价
     *
     * @return price_discount - 优惠价
     */
    public Integer getPriceDiscount() {
        return priceDiscount;
    }

    /**
     * 设置优惠价
     *
     * @param priceDiscount 优惠价
     */
    public void setPriceDiscount(Integer priceDiscount) {
        this.priceDiscount = priceDiscount;
    }

    /**
     * 获取原价
     *
     * @return price_normal - 原价
     */
    public Integer getPriceNormal() {
        return priceNormal;
    }

    /**
     * 设置原价
     *
     * @param priceNormal 原价
     */
    public void setPriceNormal(Integer priceNormal) {
        this.priceNormal = priceNormal;
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