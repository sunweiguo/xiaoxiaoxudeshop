package com.njupt.swg;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @Author swg.
 * @Date 2020/10/7 16:20
 * @CONTACT 317758022@qq.com
 * @DESC
 */
public class WarStarterYummyFoodApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 指向Application这个Springboot启动类
        return builder.sources(YummyFoodApplication.class);
    }
}
