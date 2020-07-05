package com.njupt.swg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author swg.
 * @Date 2020/4/11 23:26
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@SpringBootApplication
//扫描mybatis接口包
@MapperScan(basePackages = "com.njupt.swg.mapper")
@ComponentScan(basePackages = {"com.njupt.swg","org.n3r.idworker"})
//开启定时任务
//@EnableScheduling
public class YummyFoodApplication {
    public static void main(String[] args) {
        SpringApplication.run(YummyFoodApplication.class,args);
    }
}
