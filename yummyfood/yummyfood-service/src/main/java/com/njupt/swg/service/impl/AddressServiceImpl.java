package com.njupt.swg.service.impl;

import com.njupt.swg.bo.AddressNewAddBO;
import com.njupt.swg.mapper.UserAddressMapper;
import com.njupt.swg.pojo.UserAddress;
import com.njupt.swg.service.IAddressService;
import com.njupt.swg.utils.CommonJsonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @Author swg.
 * @Date 2020/3/28 23:21
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Service
@Slf4j
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private UserAddressMapper userAddressMapper;
    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<UserAddress> getAllUserAddressedByUserId(String userId) {
        Example example = new Example(UserAddress.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);
        return userAddressMapper.selectByExample(example);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public CommonJsonResult createNewAddress(AddressNewAddBO addressNewAddBO) {
        if(StringUtils.isBlank(addressNewAddBO.getAddressId())){
            //没有地址ID表示新增
            UserAddress userAddress = new UserAddress();
            BeanUtils.copyProperties(addressNewAddBO,userAddress);
            userAddress.setId(sid.nextShort());
            //查询这个用户下有没有地址信息，没有的话，第一个地址设置为默认地址
            Example example = new Example(UserAddress.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("userId",addressNewAddBO.getUserId());
            List<UserAddress> userAddressList = userAddressMapper.selectByExample(example);
            if(!CollectionUtils.isEmpty(userAddressList)){
                //已经存在地址了，那么普通的非默认地址即可
                userAddress.setIsDefault(0);
            }else{
                //如果这个用户下之前没有任何地址，那么第一个地址为默认地址
                userAddress.setIsDefault(1);
            }
            userAddress.setCreatedTime(new Date());
            userAddress.setUpdatedTime(new Date());
            userAddressMapper.insert(userAddress);
        }else{
            //表示更新
            UserAddress userAddress = userAddressMapper.selectByPrimaryKey(addressNewAddBO.getAddressId());
            BeanUtils.copyProperties(addressNewAddBO,userAddress);
            userAddress.setUpdatedTime(new Date());
            userAddressMapper.updateByPrimaryKey(userAddress);
        }
        return CommonJsonResult.ok();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public CommonJsonResult deleteAddress(String userId, String addressId) {
        Example example = new Example(UserAddress.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);
        criteria.andEqualTo("id",addressId);
        userAddressMapper.deleteByExample(example);
        return CommonJsonResult.ok();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public CommonJsonResult setDefalutAddress(String userId, String addressId) {
        //1、判断该用户下有没有收货地址，没有则直接结束
        Example example = new Example(UserAddress.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);
        criteria.andEqualTo("id",addressId);
        UserAddress userAddress = userAddressMapper.selectOneByExample(example);
        if(userAddress == null){
            log.warn("查询不到相关地址信息，用户ID为：{},收货地址ID为：{}",userId,addressId);
            return CommonJsonResult.errorMsg("查询不到地址信息");
        }
        example = new Example(UserAddress.class);
        criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);
        List<UserAddress> userAddressList = userAddressMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(userAddressList)){
            return CommonJsonResult.ok("");
        }
        //2、判断该用户下有没有默认地址，有默认地址的话，那么判断是否id一致，一致则结束
        for(UserAddress ua:userAddressList){
            if(ua.getIsDefault() == 1 && ua.getId().equals(addressId)){
                log.info("已经是默认地址，不需要重复设置，用户ID为：{},收货地址ID为：{}",userId,addressId);
                return CommonJsonResult.ok("");
            }else if(ua.getIsDefault() == 1 && !ua.getId().equals(addressId)){
                //3、有默认地址，但是id不一致，那么修改之前的id状态为非默认地址状态，此地址设置为默认地址状态
                ua.setIsDefault(0);
                userAddressMapper.updateByPrimaryKey(ua);
                userAddress.setIsDefault(1);
                userAddressMapper.updateByPrimaryKey(userAddress);
                return CommonJsonResult.ok();
            }
        }
        //4、没有，则将这一个地址置为默认状态
        userAddress.setIsDefault(1);
        userAddressMapper.updateByPrimaryKey(userAddress);
        return CommonJsonResult.ok();
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public UserAddress getSingleAddressByUserIdAndAddressId(String userId, String addressId) {
        UserAddress singleAddress = new UserAddress();
        singleAddress.setUserId(userId);
        singleAddress.setId(addressId);
        return userAddressMapper.selectOne(singleAddress);
    }
}
