package com.njupt.swg.bo;

import lombok.Data;

/**
 * @Author swg.
 * @Date 2020/3/28 23:27
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Data
public class AddressNewAddBO {
    private String addressId;
    private String userId;
    private String receiver;
    private String mobile;
    private String province;
    private String city;
    private String district;
    private String detail;
}
