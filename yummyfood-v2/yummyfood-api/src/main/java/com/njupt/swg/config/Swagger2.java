package com.njupt.swg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author swg.
 * @Date 2020/4/25 13:24
 * @CONTACT 317758022@qq.com
 * @DESC 访问此路径即可访问：http://localhost:8088/swagger-ui.html（官方路径）
 * 更好看的样式，访问：http://localhost:8088/doc.html
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)//指定api类型位swagger2
                .apiInfo(apiInfo())//定义api文档汇总信息
                .select().apis(RequestHandlerSelectors.basePackage("com.njupt.swg.controller"))//指定controller包位置
                .paths(PathSelectors.any())//所有controller类都包含进来
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("yummyfood接口API")
                .contact(new Contact("foosi","https://sunweiguo.github.io","317758022@qq.com"))
                .description("YummyFood商城的API文档")
                .version("1.0")
                .termsOfServiceUrl("https://sunweiguo.github.io")
                .build();
    }
}
