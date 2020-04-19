package com.njupt.swg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Author swg.
 * @Date 2020/4/19 14:02
 * @CONTACT 317758022@qq.com
 * @DESC 跨域的一些设置，本地调试的时候，前端项目是直接用tomcat运行的，用的8080端口，后端项目用的8088端口，需要解决跨域问题
 */
@Configuration
public class CorsConfig {
    public CorsConfig(){

    }

    @Bean
    public CorsFilter corsFilter(){
//        1、添加cors配置信息
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:8080");
//      设置允许客户端携带一些cookie等信息过来
        config.setAllowCredentials(true);
//        设置允许请求的方式
        config.addAllowedMethod("*");
//        设置允许的header
        config.addAllowedHeader("*");

//        2、为url添加映射路径
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
//        configo配置信息适用所有的路由
        corsSource.registerCorsConfiguration("/**",config);

        return new CorsFilter(corsSource);
    }
}
