package com.njupt.swg.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "items_comments")
public class ItemsComments {
    /**
     * id主键
     */
    @Id
    private String id;

    /**
     * 用户id 用户名须脱敏
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 商品id
     */
    @Column(name = "item_id")
    private String itemId;

    /**
     * 商品名称
     */
    @Column(name = "item_name")
    private String itemName;

    /**
     * 商品规格id 可为空
     */
    @Column(name = "item_spec_id")
    private String itemSpecId;

    /**
     * 规格名称 可为空
     */
    @Column(name = "sepc_name")
    private String sepcName;

    /**
     * 评价等级 1：好评 2：中评 3：差评
     */
    @Column(name = "comment_level")
    private Integer commentLevel;

    /**
     * 评价内容
     */
    private String content;

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
     * 获取id主键
     *
     * @return id - id主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id主键
     *
     * @param id id主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取用户id 用户名须脱敏
     *
     * @return user_id - 用户id 用户名须脱敏
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id 用户名须脱敏
     *
     * @param userId 用户id 用户名须脱敏
     */
    public void setUserId(String userId) {
        this.userId = userId;
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
     * 获取商品规格id 可为空
     *
     * @return item_spec_id - 商品规格id 可为空
     */
    public String getItemSpecId() {
        return itemSpecId;
    }

    /**
     * 设置商品规格id 可为空
     *
     * @param itemSpecId 商品规格id 可为空
     */
    public void setItemSpecId(String itemSpecId) {
        this.itemSpecId = itemSpecId;
    }

    /**
     * 获取规格名称 可为空
     *
     * @return sepc_name - 规格名称 可为空
     */
    public String getSepcName() {
        return sepcName;
    }

    /**
     * 设置规格名称 可为空
     *
     * @param sepcName 规格名称 可为空
     */
    public void setSepcName(String sepcName) {
        this.sepcName = sepcName;
    }

    /**
     * 获取评价等级 1：好评 2：中评 3：差评
     *
     * @return comment_level - 评价等级 1：好评 2：中评 3：差评
     */
    public Integer getCommentLevel() {
        return commentLevel;
    }

    /**
     * 设置评价等级 1：好评 2：中评 3：差评
     *
     * @param commentLevel 评价等级 1：好评 2：中评 3：差评
     */
    public void setCommentLevel(Integer commentLevel) {
        this.commentLevel = commentLevel;
    }

    /**
     * 获取评价内容
     *
     * @return content - 评价内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置评价内容
     *
     * @param content 评价内容
     */
    public void setContent(String content) {
        this.content = content;
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