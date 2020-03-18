package com.njupt.swg.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
public class Orders {
    /**
     * 订单主键;同时也是订单编号
     */
    @Id
    private String id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 收货人快照
     */
    @Column(name = "receiver_name")
    private String receiverName;

    /**
     * 收货人手机号快照
     */
    @Column(name = "receiver_mobile")
    private String receiverMobile;

    /**
     * 收货地址快照
     */
    @Column(name = "receiver_address")
    private String receiverAddress;

    /**
     * 订单总价格
     */
    @Column(name = "total_amount")
    private Integer totalAmount;

    /**
     * 实际支付总价格
     */
    @Column(name = "real_pay_amount")
    private Integer realPayAmount;

    /**
     * 邮费;默认可以为零，代表包邮
     */
    @Column(name = "post_amount")
    private Integer postAmount;

    /**
     * 支付方式
     */
    @Column(name = "pay_method")
    private Integer payMethod;

    /**
     * 买家留言
     */
    @Column(name = "left_msg")
    private String leftMsg;

    /**
     * 扩展字段
     */
    private String extand;

    /**
     * 买家是否评价;1：已评价，0：未评价
     */
    @Column(name = "is_comment")
    private Integer isComment;

    /**
     * 逻辑删除状态;1: 删除 0:未删除
     */
    @Column(name = "is_delete")
    private Integer isDelete;

    /**
     * 创建时间（成交时间）
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 更新时间
     */
    @Column(name = "updated_time")
    private Date updatedTime;
}