package com.njupt.swg.vo;

import lombok.Data;

/**
 * @Author swg.
 * @Date 2020/3/29 15:56
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Data
public class SubmitOrderVO {
    private String userId;
    private String itemSpecIds;
    private String addressId;
    private Integer payMethod;
    private String leftMsg;
}
