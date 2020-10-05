package com.njupt.swg.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author swg.
 * @Date 2020/10/5 19:16
 * @CONTACT 317758022@qq.com
 * @DESC
 */
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
