package com.njupt.swg.service;

import com.njupt.swg.pojo.Users;
import com.njupt.swg.bo.UserBO;

/**
 * @Author swg.
 * @Date 2020/4/19 17:09
 * @CONTACT 317758022@qq.com
 * @DESC
 */
public interface IUserService {
    boolean queryUsernameIsExist(String username);

    Users registNewUser(UserBO userBO);
}
