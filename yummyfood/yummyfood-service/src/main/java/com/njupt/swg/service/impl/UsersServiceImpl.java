package com.njupt.swg.service.impl;

import com.njupt.swg.mapper.UsersMapper;
import com.njupt.swg.pojo.Users;
import com.njupt.swg.service.IUsersService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author swg.
 * @Date 2020/3/11 22:22
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Service
public class UsersServiceImpl implements IUsersService {
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public Users getUserById(String id) {
        return usersMapper.selectByPrimaryKey(id);
    }
}
