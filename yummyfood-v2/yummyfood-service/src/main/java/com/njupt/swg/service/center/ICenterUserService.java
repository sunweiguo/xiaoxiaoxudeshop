package com.njupt.swg.service.center;

import com.njupt.swg.bo.CenterUserBO;
import com.njupt.swg.pojo.Users;

/**
 * @Author swg.
 * @Date 2020/9/19 15:57
 * @CONTACT 317758022@qq.com
 * @DESC
 */
public interface ICenterUserService {

    //更新用户的头像
    Users updateFaceUrl(String userId, String faceUrl);

    //更新用户的基本信息
    Users update(String userId, CenterUserBO centerUserBO);
}
