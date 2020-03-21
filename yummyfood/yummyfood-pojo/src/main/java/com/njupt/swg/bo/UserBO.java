package com.njupt.swg.bo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author swg.
 * @Date 2020/3/21 15:09
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Getter
@Setter
public class UserBO {
    private String username;
    private String password;
    private String confirmPassword;
}
