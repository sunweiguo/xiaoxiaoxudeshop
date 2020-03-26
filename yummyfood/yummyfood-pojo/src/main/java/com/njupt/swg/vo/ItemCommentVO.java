package com.njupt.swg.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author swg.
 * @Date 2020/3/26 21:25
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Data
public class ItemCommentVO {
    private Integer commentLevel;
    private String content;
    private String  specName;
    private Date createTime;
    private String userFace;
    private String  nickname;
}
