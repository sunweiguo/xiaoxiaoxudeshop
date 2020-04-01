package com.njupt.swg.service.impl.center;

import com.njupt.swg.bo.center.CenterUserBO;
import com.njupt.swg.mapper.UsersMapper;
import com.njupt.swg.pojo.Users;
import com.njupt.swg.service.center.ICenterUserService;
import com.njupt.swg.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author swg.
 * @Date 2020/3/31 21:45
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Service
@Slf4j
public class CenterUserServiceImpl implements ICenterUserService {
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserById(String userId) {
        Users user = usersMapper.selectByPrimaryKey(userId);
        user.setPassword("");
        return user;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users update(String userId,CenterUserBO centerUserBO) {
        Users res = usersMapper.selectByPrimaryKey(userId);

        Users updateUser = new Users();
        BeanUtils.copyProperties(centerUserBO,updateUser);

        updateUser.setId(userId);
        updateUser.setCreatedTime(new Date());
        updateUser.setPassword(res.getPassword());
        usersMapper.updateByPrimaryKeySelective(updateUser);
        return queryUserById(userId);
    }

    @Override
    public Users updateFaceUrl(String userId, String faceUrl) {
        Users user = usersMapper.selectByPrimaryKey(userId);
        user.setFace(faceUrl);
        usersMapper.updateByPrimaryKeySelective(user);
        return queryUserById(userId);
    }
}
