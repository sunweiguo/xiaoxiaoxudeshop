package com.njupt.swg.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "user_address")
public class UserAddress {
    /**
     * 地址主键id
     */
    @Id
    private String id;

    /**
     * 关联用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 收件人姓名
     */
    private String receiver;

    /**
     * 收件人手机号
     */
    private String mobile;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区县
     */
    private String district;

    /**
     * 详细地址
     */
    private String detail;

    /**
     * 扩展字段
     */
    private String extand;

    /**
     * 是否默认地址
     */
    @Column(name = "is_default")
    private Integer isDefault;

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
     * 获取地址主键id
     *
     * @return id - 地址主键id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置地址主键id
     *
     * @param id 地址主键id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取关联用户id
     *
     * @return user_id - 关联用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置关联用户id
     *
     * @param userId 关联用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取收件人姓名
     *
     * @return receiver - 收件人姓名
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * 设置收件人姓名
     *
     * @param receiver 收件人姓名
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    /**
     * 获取收件人手机号
     *
     * @return mobile - 收件人手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置收件人手机号
     *
     * @param mobile 收件人手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取省份
     *
     * @return province - 省份
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省份
     *
     * @param province 省份
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取城市
     *
     * @return city - 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市
     *
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取区县
     *
     * @return district - 区县
     */
    public String getDistrict() {
        return district;
    }

    /**
     * 设置区县
     *
     * @param district 区县
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * 获取详细地址
     *
     * @return detail - 详细地址
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 设置详细地址
     *
     * @param detail 详细地址
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * 获取扩展字段
     *
     * @return extand - 扩展字段
     */
    public String getExtand() {
        return extand;
    }

    /**
     * 设置扩展字段
     *
     * @param extand 扩展字段
     */
    public void setExtand(String extand) {
        this.extand = extand;
    }

    /**
     * 获取是否默认地址
     *
     * @return is_default - 是否默认地址
     */
    public Integer getIsDefault() {
        return isDefault;
    }

    /**
     * 设置是否默认地址
     *
     * @param isDefault 是否默认地址
     */
    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
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