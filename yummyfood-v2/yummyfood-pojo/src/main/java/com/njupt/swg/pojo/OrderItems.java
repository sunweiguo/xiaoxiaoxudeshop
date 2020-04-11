package com.njupt.swg.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "order_items")
public class OrderItems {
    /**
     * 主键id
     */
    @Id
    private String id;

    /**
     * 归属订单id
     */
    @Column(name = "order_id")
    private String orderId;

    /**
     * 商品id
     */
    @Column(name = "item_id")
    private String itemId;

    /**
     * 商品图片
     */
    @Column(name = "item_img")
    private String itemImg;

    /**
     * 商品名称
     */
    @Column(name = "item_name")
    private String itemName;

    /**
     * 规格id
     */
    @Column(name = "item_spec_id")
    private String itemSpecId;

    /**
     * 规格名称
     */
    @Column(name = "item_spec_name")
    private String itemSpecName;

    /**
     * 成交价格
     */
    private Integer price;

    /**
     * 购买数量
     */
    @Column(name = "buy_counts")
    private Integer buyCounts;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取归属订单id
     *
     * @return order_id - 归属订单id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置归属订单id
     *
     * @param orderId 归属订单id
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取商品id
     *
     * @return item_id - 商品id
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * 设置商品id
     *
     * @param itemId 商品id
     */
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    /**
     * 获取商品图片
     *
     * @return item_img - 商品图片
     */
    public String getItemImg() {
        return itemImg;
    }

    /**
     * 设置商品图片
     *
     * @param itemImg 商品图片
     */
    public void setItemImg(String itemImg) {
        this.itemImg = itemImg;
    }

    /**
     * 获取商品名称
     *
     * @return item_name - 商品名称
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 设置商品名称
     *
     * @param itemName 商品名称
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * 获取规格id
     *
     * @return item_spec_id - 规格id
     */
    public String getItemSpecId() {
        return itemSpecId;
    }

    /**
     * 设置规格id
     *
     * @param itemSpecId 规格id
     */
    public void setItemSpecId(String itemSpecId) {
        this.itemSpecId = itemSpecId;
    }

    /**
     * 获取规格名称
     *
     * @return item_spec_name - 规格名称
     */
    public String getItemSpecName() {
        return itemSpecName;
    }

    /**
     * 设置规格名称
     *
     * @param itemSpecName 规格名称
     */
    public void setItemSpecName(String itemSpecName) {
        this.itemSpecName = itemSpecName;
    }

    /**
     * 获取成交价格
     *
     * @return price - 成交价格
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * 设置成交价格
     *
     * @param price 成交价格
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * 获取购买数量
     *
     * @return buy_counts - 购买数量
     */
    public Integer getBuyCounts() {
        return buyCounts;
    }

    /**
     * 设置购买数量
     *
     * @param buyCounts 购买数量
     */
    public void setBuyCounts(Integer buyCounts) {
        this.buyCounts = buyCounts;
    }
}