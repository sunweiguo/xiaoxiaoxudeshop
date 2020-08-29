package com.njupt.swg.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "pay_info")
public class PayInfo {
    @Id
    private String id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 订单号
     */
    @Column(name = "order_no")
    private Long orderNo;

    /**
     * 支付平台:1-支付宝,2-微信
     */
    @Column(name = "pay_platform")
    private Integer payPlatform;

    /**
     * 支付宝支付流水号
     */
    @Column(name = "platform_number")
    private String platformNumber;

    /**
     * 支付宝支付状态
     */
    @Column(name = "platform_status")
    private String platformStatus;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取订单号
     *
     * @return order_no - 订单号
     */
    public Long getOrderNo() {
        return orderNo;
    }

    /**
     * 设置订单号
     *
     * @param orderNo 订单号
     */
    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取支付平台:1-支付宝,2-微信
     *
     * @return pay_platform - 支付平台:1-支付宝,2-微信
     */
    public Integer getPayPlatform() {
        return payPlatform;
    }

    /**
     * 设置支付平台:1-支付宝,2-微信
     *
     * @param payPlatform 支付平台:1-支付宝,2-微信
     */
    public void setPayPlatform(Integer payPlatform) {
        this.payPlatform = payPlatform;
    }

    /**
     * 获取支付宝支付流水号
     *
     * @return platform_number - 支付宝支付流水号
     */
    public String getPlatformNumber() {
        return platformNumber;
    }

    /**
     * 设置支付宝支付流水号
     *
     * @param platformNumber 支付宝支付流水号
     */
    public void setPlatformNumber(String platformNumber) {
        this.platformNumber = platformNumber;
    }

    /**
     * 获取支付宝支付状态
     *
     * @return platform_status - 支付宝支付状态
     */
    public String getPlatformStatus() {
        return platformStatus;
    }

    /**
     * 设置支付宝支付状态
     *
     * @param platformStatus 支付宝支付状态
     */
    public void setPlatformStatus(String platformStatus) {
        this.platformStatus = platformStatus;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}