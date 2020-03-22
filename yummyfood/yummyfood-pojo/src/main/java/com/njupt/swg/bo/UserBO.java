package com.njupt.swg.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "注册用户对象BO",description = "从客户端，由用户传入的数据封装在此实体中")
public class UserBO {
    @ApiModelProperty(value = "注册的用户名",name = "username",example = "fossi",required = true)
    private String username;
    @ApiModelProperty(value = "注册的密码",name = "password",example = "123456",required = true)
    private String password;
    @ApiModelProperty(value = "注册的确认密码",name = "confirmPassword",example = "123456")
    private String confirmPassword;
}
