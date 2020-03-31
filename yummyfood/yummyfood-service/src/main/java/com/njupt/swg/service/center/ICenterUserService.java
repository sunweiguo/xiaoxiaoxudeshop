package com.njupt.swg.service.center;

import com.njupt.swg.bo.center.CenterUserBO;
import com.njupt.swg.pojo.Users;

/**
 * @Author swg.
 * @Date 2020/3/31 21:45
 * @CONTACT 317758022@qq.com
 * @DESC
 */
public interface ICenterUserService {
    Users queryUserById(String userId);

    Users update(String userId,CenterUserBO centerUserBO);
}
