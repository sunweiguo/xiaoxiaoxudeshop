package com.njupt.swg.service.impl;

import com.njupt.swg.mapper.CarouselMapper;
import com.njupt.swg.pojo.Carousel;
import com.njupt.swg.service.ICarouselService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author swg.
 * @Date 2020/3/23 21:17
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Service
@Slf4j
public class CarouselServiceImpl implements ICarouselService {
    @Autowired
    private CarouselMapper carouselMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Carousel> queryAll(Integer isShow) {
        log.info("查询轮播图数据列表开始，传入的参数isShow为：{}",isShow);
        Example example = new Example(Carousel.class);
        //按照sort字段排序
        example.orderBy("sort").asc();
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isShow",isShow);
        List<Carousel> carouselList = carouselMapper.selectByExample(example);
        log.info("查询轮播图数据列表结束，查询到的数据为：{}",carouselList);
        return carouselList;
    }
}
