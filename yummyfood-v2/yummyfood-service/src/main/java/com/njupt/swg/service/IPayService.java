package com.njupt.swg.service;

import com.njupt.swg.utils.CommonJsonResult;

import java.util.Map;

/**
 * @Author swg.
 * @Date 2020/7/26 17:24
 * @CONTACT 317758022@qq.com
 * @DESC
 */
public interface IPayService {
    CommonJsonResult pay(String userId,String orderNo);

    CommonJsonResult aliCallback(Map<String, String> params);
}
