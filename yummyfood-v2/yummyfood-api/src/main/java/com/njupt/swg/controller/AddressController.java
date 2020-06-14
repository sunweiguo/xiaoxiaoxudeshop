package com.njupt.swg.controller;

import com.njupt.swg.bo.AddressNewAddBO;
import com.njupt.swg.service.IAddressService;
import com.njupt.swg.utils.CommonJsonResult;
import com.njupt.swg.utils.MobileEmailUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author swg.
 * @Date 2020/6/14 11:21
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Api(value = "收货地址相关接口",tags = "收货地址相关接口")
@RestController
@RequestMapping("address")
public class AddressController {

    @Autowired
    private IAddressService addressService;

    @ApiOperation(value = "根据用户ID查询收货地址列表",notes = "根据用户ID查询收货地址列表",httpMethod = "POST")
    @PostMapping("/list")
    public CommonJsonResult list(
            @ApiParam(name = "userId",value = "用户ID",required = true)
            @RequestParam String userId){
        //TODO 横向越权的问题，应该对userId做判断是否为当前登录的userId
        if(StringUtils.isBlank(userId)){
            return CommonJsonResult.errorMsg("用户ID不能为空");
        }
        return CommonJsonResult.ok(addressService.getAllUserAddressedByUserId(userId));
    }

    @ApiOperation(value = "新增收货地址",notes = "新增收货地址",httpMethod = "POST")
    @PostMapping("/add")
    public CommonJsonResult add(
            @ApiParam(name = "addressNewAddBO",value = "收货地址实体")
            @RequestBody AddressNewAddBO addressNewAddBO){
        //TODO 横向越权的问题，应该对userId做判断是否为当前登录的userId
        if(StringUtils.isBlank(addressNewAddBO.getUserId())){
            return CommonJsonResult.errorMsg("用户ID不能为空");
        }
        //后端校验新增地址的一些参数是否符合条件
        CommonJsonResult checkRes = checkAddress(addressNewAddBO);
        if (checkRes.getStatus() != 200) {
            return checkRes;
        }
        return addressService.createNewAddress(addressNewAddBO);
    }

    private CommonJsonResult checkAddress(AddressNewAddBO addressBO) {
        String receiver = addressBO.getReceiver();
        if (StringUtils.isBlank(receiver)) {
            return CommonJsonResult.errorMsg("收货人不能为空");
        }
        if (receiver.length() > 12) {
            return CommonJsonResult.errorMsg("收货人姓名不能太长");
        }

        String mobile = addressBO.getMobile();
        if (StringUtils.isBlank(mobile)) {
            return CommonJsonResult.errorMsg("收货人手机号不能为空");
        }
        if (mobile.length() != 11) {
            return CommonJsonResult.errorMsg("收货人手机号长度不正确");
        }
        boolean isMobileOk = MobileEmailUtils.checkMobileIsOk(mobile);
        if (!isMobileOk) {
            return CommonJsonResult.errorMsg("收货人手机号格式不正确");
        }

        String province = addressBO.getProvince();
        String city = addressBO.getCity();
        String district = addressBO.getDistrict();
        String detail = addressBO.getDetail();
        if (StringUtils.isBlank(province) ||
                StringUtils.isBlank(city) ||
                StringUtils.isBlank(district) ||
                StringUtils.isBlank(detail)) {
            return CommonJsonResult.errorMsg("收货地址信息不能为空");
        }

        return CommonJsonResult.ok();
    }

    @ApiOperation(value = "更新收货地址",notes = "更新收货地址",httpMethod = "POST")
    @PostMapping("/update")
    public CommonJsonResult update(
            @ApiParam(name = "addressNewAddBO",value = "收货地址实体")
            @RequestBody AddressNewAddBO addressNewAddBO){
        //TODO 横向越权的问题，应该对userId做判断是否为当前登录的userId
        if(StringUtils.isBlank(addressNewAddBO.getUserId())){
            return CommonJsonResult.errorMsg("用户ID不能为空");
        }
        //后端校验新增地址的一些参数是否符合条件
        CommonJsonResult checkRes = checkAddress(addressNewAddBO);
        if (checkRes.getStatus() != 200) {
            return checkRes;
        }
        return addressService.createNewAddress(addressNewAddBO);
    }

    @ApiOperation(value = "删除收货地址",notes = "删除收货地址",httpMethod = "POST")
    @PostMapping("/delete")
    public CommonJsonResult delete(
            @ApiParam(name = "userId",value = "用户ID",required = true)
            @RequestParam String  userId,
            @ApiParam(name = "addressId",value = "收货地址ID",required = true)
            @RequestParam String  addressId){
        //TODO 横向越权的问题，应该对userId做判断是否为当前登录的userId
        if(StringUtils.isBlank(userId) || StringUtils.isBlank(addressId)){
            return CommonJsonResult.errorMsg("");
        }
        return addressService.deleteAddress(userId,addressId);
    }

    @ApiOperation(value = "设置默认地址",notes = "设置默认地址",httpMethod = "POST")
    @PostMapping("/setDefalut")
    public CommonJsonResult setDefalut(
            @ApiParam(name = "userId",value = "用户ID",required = true)
            @RequestParam String  userId,
            @ApiParam(name = "addressId",value = "收货地址ID",required = true)
            @RequestParam String  addressId){
        //TODO 横向越权的问题，应该对userId做判断是否为当前登录的userId
        if(StringUtils.isBlank(userId) || StringUtils.isBlank(addressId)){
            return CommonJsonResult.errorMsg("");
        }
        return addressService.setDefalutAddress(userId,addressId);
    }

}
