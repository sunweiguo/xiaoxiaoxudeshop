package com.njupt.swg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
public class YummyFoodApplication {
    public static void main(String[] args) {
        SpringApplication.run(YummyFoodApplication.class,args);
    }
}
