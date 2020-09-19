package com.njupt.swg.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author swg.
 * @Date 2020/9/19 16:40
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Data
public class MyOrdersVO {
    private String orderId;
    private Date createdTime;
    private Integer payMethod;
    private Integer realPayAmount;
    private Integer postAmount;
    private Integer orderStatus;
    private Integer isComment;
    private List<MySubOrderItemVO> subOrderItemList;
}
