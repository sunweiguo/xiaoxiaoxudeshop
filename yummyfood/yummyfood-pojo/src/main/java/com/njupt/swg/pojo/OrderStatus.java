package com.njupt.swg.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "order_status")
public class OrderStatus {
    /**
     * 订单ID;对应订单表的主键id
     */
    @Id
    @Column(name = "order_id")
    private String orderId;

    /**
     * 订单状态
     */
    @Column(name = "order_status")
    private Integer orderStatus;

    /**
     * 订单创建时间;对应[10:待付款]状态
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 支付成功时间;对应[20:已付款，待发货]状态
     */
    @Column(name = "pay_time")
    private Date payTime;

    /**
     * 发货时间;对应[30：已发货，待收货]状态
     */
    @Column(name = "deliver_time")
    private Date deliverTime;

    /**
     * 交易成功时间;对应[40：交易成功]状态
     */
    @Column(name = "success_time")
    private Date successTime;

    /**
     * 交易关闭时间;对应[50：交易关闭]状态
     */
    @Column(name = "close_time")
    private Date closeTime;

    /**
     * 留言时间;用户在交易成功后的留言时间
     */
    @Column(name = "comment_time")
    private Date commentTime;

    /**
     * 获取订单ID;对应订单表的主键id
     *
     * @return order_id - 订单ID;对应订单表的主键id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置订单ID;对应订单表的主键id
     *
     * @param orderId 订单ID;对应订单表的主键id
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取订单状态
     *
     * @return order_status - 订单状态
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置订单状态
     *
     * @param orderStatus 订单状态
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 获取订单创建时间;对应[10:待付款]状态
     *
     * @return created_time - 订单创建时间;对应[10:待付款]状态
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置订单创建时间;对应[10:待付款]状态
     *
     * @param createdTime 订单创建时间;对应[10:待付款]状态
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 获取支付成功时间;对应[20:已付款，待发货]状态
     *
     * @return pay_time - 支付成功时间;对应[20:已付款，待发货]状态
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * 设置支付成功时间;对应[20:已付款，待发货]状态
     *
     * @param payTime 支付成功时间;对应[20:已付款，待发货]状态
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * 获取发货时间;对应[30：已发货，待收货]状态
     *
     * @return deliver_time - 发货时间;对应[30：已发货，待收货]状态
     */
    public Date getDeliverTime() {
        return deliverTime;
    }

    /**
     * 设置发货时间;对应[30：已发货，待收货]状态
     *
     * @param deliverTime 发货时间;对应[30：已发货，待收货]状态
     */
    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }

    /**
     * 获取交易成功时间;对应[40：交易成功]状态
     *
     * @return success_time - 交易成功时间;对应[40：交易成功]状态
     */
    public Date getSuccessTime() {
        return successTime;
    }

    /**
     * 设置交易成功时间;对应[40：交易成功]状态
     *
     * @param successTime 交易成功时间;对应[40：交易成功]状态
     */
    public void setSuccessTime(Date successTime) {
        this.successTime = successTime;
    }

    /**
     * 获取交易关闭时间;对应[50：交易关闭]状态
     *
     * @return close_time - 交易关闭时间;对应[50：交易关闭]状态
     */
    public Date getCloseTime() {
        return closeTime;
    }

    /**
     * 设置交易关闭时间;对应[50：交易关闭]状态
     *
     * @param closeTime 交易关闭时间;对应[50：交易关闭]状态
     */
    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    /**
     * 获取留言时间;用户在交易成功后的留言时间
     *
     * @return comment_time - 留言时间;用户在交易成功后的留言时间
     */
    public Date getCommentTime() {
        return commentTime;
    }

    /**
     * 设置留言时间;用户在交易成功后的留言时间
     *
     * @param commentTime 留言时间;用户在交易成功后的留言时间
     */
    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }
}