package com.njupt.swg.service.impl;

import com.njupt.swg.mapper.UsersMapper;
import com.njupt.swg.pojo.Users;
import com.njupt.swg.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author swg.
 * @Date 2020/4/11 23:09
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Service
public class TestServiceImpl implements ITestService {
    @Autowired
    private UsersMapper usersMapper;


    @Override
    public String sayHello(String userId) {
        Users userRes = usersMapper.selectByPrimaryKey(userId);
        if(userRes == null){
            return "你的朋友消失了~";
        }
        return "Hello World：" + userRes.getUsername();
    }
}
