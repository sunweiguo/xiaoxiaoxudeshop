package com.njupt.swg.service;

import com.njupt.swg.pojo.Users;
import com.njupt.swg.bo.UserBO;
import com.njupt.swg.utils.CommonJsonResult;

/**
 * @Author swg.
 * @Date 2020/4/19 17:09
 * @CONTACT 317758022@qq.com
 * @DESC
 */
public interface IUserService {
    //判断用户名是否存在，存在则为true
    boolean queryUsernameIsExist(String username);

    //注册新用户
    Users registNewUser(UserBO userBO);

    //根据用户名密码查询用户是否存在
    Users queryUserForLogin(String username, String password);

    CommonJsonResult queryUserById(String userId);
}
