package com.njupt.swg.service.impl.center;

import com.njupt.swg.bo.CenterUserBO;
import com.njupt.swg.mapper.UsersMapper;
import com.njupt.swg.pojo.Users;
import com.njupt.swg.service.IUserService;
import com.njupt.swg.service.center.ICenterUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author swg.
 * @Date 2020/9/19 15:57
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Service
public class CenterUserServiceImpl implements ICenterUserService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public Users updateFaceUrl(String userId, String faceUrl) {
        Users users = usersMapper.selectByPrimaryKey(userId);
        users.setFace(faceUrl);
        usersMapper.updateByPrimaryKeySelective(users);
        return users;
    }

    @Override
    public Users update(String userId, CenterUserBO centerUserBO) {
        Users dbuser = usersMapper.selectByPrimaryKey(userId);
        Users updateUser = new Users();
        BeanUtils.copyProperties(centerUserBO,updateUser);
        updateUser.setId(userId);
        updateUser.setUpdatedTime(new Date());
        updateUser.setCreatedTime(dbuser.getCreatedTime());
        //由于前端不更新密码，所以记得把数据库里面的老密码拿出来，不要更新之后密码字段为空了
        updateUser.setPassword(dbuser.getPassword());
        updateUser.setFace(dbuser.getFace());
        usersMapper.updateByPrimaryKeySelective(updateUser);
        return updateUser;
    }
}
