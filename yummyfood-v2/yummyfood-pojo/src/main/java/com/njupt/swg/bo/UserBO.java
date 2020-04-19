package com.njupt.swg.bo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @Author swg.
 * @Date 2020/4/19 16:47
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Data
public class UserBO {
    @NotBlank(message = "注册的用户名不能为空哦")
    private String username;
    @NotBlank(message = "注册的密码不能为空哦")
    @Length(min = 6,max = 20)
    private String password;
    @NotBlank(message = "注册的确认密码不能为空哦")
    @Length(min = 6,max = 20)
    private String confirmPassword;
}
