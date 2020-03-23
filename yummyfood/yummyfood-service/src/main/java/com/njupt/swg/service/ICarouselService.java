package com.njupt.swg.service;

import com.njupt.swg.pojo.Carousel;

import java.util.List;

/**
 * @Author swg.
 * @Date 2020/3/23 21:16
 * @CONTACT 317758022@qq.com
 * @DESC
 */
public interface ICarouselService {
    //查询所有轮播图列表
    List<Carousel> queryAll(Integer isShow);
}
