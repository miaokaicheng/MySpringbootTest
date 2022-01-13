package com.mm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Description swagger配置
 * @Author MKC
 * @Date 2021/12/28
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {
    @Bean
    public Docket buildDocket() {
        //3.x版本使用OAS_30，2.x版本使用SWAGGER_2
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(buildApiInf())
                //enable是否启动swagger,如果为false,那么浏览器中无法访问swagger
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mm.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo buildApiInf() {
        return new ApiInfoBuilder()
                .title("系统RESTful API文档")
                .contact(new Contact("MKC", "https://github.com/miaokaicheng", "1052313933@qq.com"))
                .version("1.0")
                .build();
    }
}
