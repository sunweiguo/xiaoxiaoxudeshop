package com.njupt.swg.service;

import com.njupt.swg.bo.UserBO;
import com.njupt.swg.pojo.Users;

/**
 * @Author swg.
 * @Date 2020/3/11 22:20
 * @CONTACT 317758022@qq.com
 * @DESC
 */
public interface IUserService {
    boolean queryUsernameIsExist(String username);

//    用户注册
    Users createUser(UserBO userBO);
}
