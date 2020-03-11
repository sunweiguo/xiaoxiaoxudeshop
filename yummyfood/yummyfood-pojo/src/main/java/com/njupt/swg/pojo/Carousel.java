package com.njupt.swg.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class Carousel {
    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 图片 图片地址
     */
    @Column(name = "image_url")
    private String imageUrl;

    /**
     * 背景色
     */
    @Column(name = "background_color")
    private String backgroundColor;

    /**
     * 商品id 商品id
     */
    @Column(name = "item_id")
    private String itemId;

    /**
     * 商品分类id 商品分类id
     */
    @Column(name = "cat_id")
    private String catId;

    /**
     * 轮播图类型 轮播图类型，用于判断，可以根据商品id或者分类进行页面跳转，1：商品 2：分类
     */
    private Integer type;

    /**
     * 轮播图展示顺序
     */
    private Integer sort;

    /**
     * 是否展示
     */
    @Column(name = "is_show")
    private Integer isShow;

    /**
     * 创建时间 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间 更新
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取图片 图片地址
     *
     * @return image_url - 图片 图片地址
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 设置图片 图片地址
     *
     * @param imageUrl 图片 图片地址
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * 获取背景色
     *
     * @return background_color - 背景色
     */
    public String getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * 设置背景色
     *
     * @param backgroundColor 背景色
     */
    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * 获取商品id 商品id
     *
     * @return item_id - 商品id 商品id
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * 设置商品id 商品id
     *
     * @param itemId 商品id 商品id
     */
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    /**
     * 获取商品分类id 商品分类id
     *
     * @return cat_id - 商品分类id 商品分类id
     */
    public String getCatId() {
        return catId;
    }

    /**
     * 设置商品分类id 商品分类id
     *
     * @param catId 商品分类id 商品分类id
     */
    public void setCatId(String catId) {
        this.catId = catId;
    }

    /**
     * 获取轮播图类型 轮播图类型，用于判断，可以根据商品id或者分类进行页面跳转，1：商品 2：分类
     *
     * @return type - 轮播图类型 轮播图类型，用于判断，可以根据商品id或者分类进行页面跳转，1：商品 2：分类
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置轮播图类型 轮播图类型，用于判断，可以根据商品id或者分类进行页面跳转，1：商品 2：分类
     *
     * @param type 轮播图类型 轮播图类型，用于判断，可以根据商品id或者分类进行页面跳转，1：商品 2：分类
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取轮播图展示顺序
     *
     * @return sort - 轮播图展示顺序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置轮播图展示顺序
     *
     * @param sort 轮播图展示顺序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取是否展示
     *
     * @return is_show - 是否展示
     */
    public Integer getIsShow() {
        return isShow;
    }

    /**
     * 设置是否展示
     *
     * @param isShow 是否展示
     */
    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    /**
     * 获取创建时间 创建时间
     *
     * @return create_time - 创建时间 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间 创建时间
     *
     * @param createTime 创建时间 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间 更新
     *
     * @return update_time - 更新时间 更新
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间 更新
     *
     * @param updateTime 更新时间 更新
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}