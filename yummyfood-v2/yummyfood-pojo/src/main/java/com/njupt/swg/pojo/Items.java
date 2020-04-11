package com.njupt.swg.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

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

    /**
     * 获取商品主键id
     *
     * @return id - 商品主键id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置商品主键id
     *
     * @param id 商品主键id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取商品名称 商品名称
     *
     * @return item_name - 商品名称 商品名称
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 设置商品名称 商品名称
     *
     * @param itemName 商品名称 商品名称
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * 获取分类外键id 分类id
     *
     * @return cat_id - 分类外键id 分类id
     */
    public Integer getCatId() {
        return catId;
    }

    /**
     * 设置分类外键id 分类id
     *
     * @param catId 分类外键id 分类id
     */
    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    /**
     * 获取一级分类外键id
     *
     * @return root_cat_id - 一级分类外键id
     */
    public Integer getRootCatId() {
        return rootCatId;
    }

    /**
     * 设置一级分类外键id
     *
     * @param rootCatId 一级分类外键id
     */
    public void setRootCatId(Integer rootCatId) {
        this.rootCatId = rootCatId;
    }

    /**
     * 获取累计销售 累计销售
     *
     * @return sell_counts - 累计销售 累计销售
     */
    public Integer getSellCounts() {
        return sellCounts;
    }

    /**
     * 设置累计销售 累计销售
     *
     * @param sellCounts 累计销售 累计销售
     */
    public void setSellCounts(Integer sellCounts) {
        this.sellCounts = sellCounts;
    }

    /**
     * 获取上下架状态 上下架状态,1:上架 2:下架
     *
     * @return on_off_status - 上下架状态 上下架状态,1:上架 2:下架
     */
    public Integer getOnOffStatus() {
        return onOffStatus;
    }

    /**
     * 设置上下架状态 上下架状态,1:上架 2:下架
     *
     * @param onOffStatus 上下架状态 上下架状态,1:上架 2:下架
     */
    public void setOnOffStatus(Integer onOffStatus) {
        this.onOffStatus = onOffStatus;
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

    /**
     * 获取商品内容 商品内容
     *
     * @return content - 商品内容 商品内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置商品内容 商品内容
     *
     * @param content 商品内容 商品内容
     */
    public void setContent(String content) {
        this.content = content;
    }
}