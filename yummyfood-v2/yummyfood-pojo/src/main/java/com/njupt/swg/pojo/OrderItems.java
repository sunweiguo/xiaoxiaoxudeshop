package com.njupt.swg.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
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
}