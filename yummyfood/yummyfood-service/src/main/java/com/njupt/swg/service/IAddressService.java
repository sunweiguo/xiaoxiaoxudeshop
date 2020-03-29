package com.njupt.swg.service;

import com.njupt.swg.bo.AddressNewAddBO;
import com.njupt.swg.pojo.UserAddress;
import com.njupt.swg.utils.CommonJsonResult;

import java.util.List;

/**
 * @Author swg.
 * @Date 2020/3/28 23:20
 * @CONTACT 317758022@qq.com
 * @DESC
 */
public interface IAddressService {
    List<UserAddress> getAllUserAddressedByUserId(String userId);

    CommonJsonResult createNewAddress(AddressNewAddBO addressNewAddBO);

    CommonJsonResult deleteAddress(String userId, String addressId);

    CommonJsonResult setDefalutAddress(String userId, String addressId);

    UserAddress getSingleAddressByUserIdAndAddressId(String userId,String addressId);
}
