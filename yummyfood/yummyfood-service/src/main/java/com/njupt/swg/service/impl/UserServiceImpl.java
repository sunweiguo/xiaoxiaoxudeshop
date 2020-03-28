package com.njupt.swg.service.impl;

import com.njupt.swg.bo.UserBO;
import com.njupt.swg.enums.Sex;
import com.njupt.swg.mapper.UsersMapper;
import com.njupt.swg.pojo.Users;
import com.njupt.swg.service.IUserService;
import com.njupt.swg.utils.DateUtil;
import com.njupt.swg.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * @Author swg.
 * @Date 2020/3/11 22:22
 * @CONTACT 317758022@qq.com
 * @DESC 用户相关逻辑
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {
        log.info("根据{}查询用户名是否存在",username);
        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andEqualTo("username",username);
        Users result = usersMapper.selectOneByExample(userExample);
        return result != null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users createUser(UserBO userBO) {
        Users user = new Users();
        String userid = sid.nextShort();
        user.setId(userid);
        user.setUsername(userBO.getUsername());
        try {
            user.setPassword(MD5Utils.getMD5Str(userBO.getPassword()));
        } catch (Exception e) {
            log.error("创建用户-生成密码失败",e.getMessage());
        }
        user.setNickname(userBO.getUsername());
//        默认头像
        user.setFace("http://bloghello.oursnail.cn/avatar.png");
        user.setBirthday(DateUtil.stringToDate("1900-01-01"));
        user.setSex(Sex.secret.type);
        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());
        log.info("注册用的数据为：{}",user);
        usersMapper.insertSelective(user);
        return user;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users queryUserForLogin(String username, String password) {
        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andEqualTo("username",username);
        try {
            userCriteria.andEqualTo("password",MD5Utils.getMD5Str(password));
        } catch (Exception e) {
            log.error("MD5加密有错误",e.getMessage());
        }
        Users result = usersMapper.selectOneByExample(userExample);
        return result;
    }
}
