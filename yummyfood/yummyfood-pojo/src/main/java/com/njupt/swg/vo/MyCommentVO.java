package com.njupt.swg.vo;

import lombok.Data;

import java.util.Date;

@Data
public class MyCommentVO {
    private String commentId;
    private String content;
    private Date createdTime;
    private String itemId;
    private String itemName;
    private String specName;
    private String itemImg;
}