package com.njupt.swg.bo;

import lombok.Data;

/**
 * @Author swg.
 * @Date 2020/10/5 20:13
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Data
public class OrderItemsCommentBO {

    private String commentId;
    private String itemId;
    private String itemName;
    private String itemSpecId;
    private String itemSpecName;
    private Integer commentLevel;
    private String content;
}
