package com.njupt.swg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author swg.
 * @Date 2020/3/8 19:21
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@SpringBootApplication
//扫描mybatis通用mapper所在的包
@MapperScan(basePackages = "com.njupt.swg.mapper")
//扫描所有包和相关组件包
@ComponentScan(basePackages = {"com.njupt.swg","org.n3r.idworker"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
