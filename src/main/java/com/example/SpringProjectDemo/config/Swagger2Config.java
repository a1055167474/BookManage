package com.example.SpringProjectDemo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author qinzhibin
 * @description swagger2配置类
 * @date 2021/3/25
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    private static final Logger logger = LoggerFactory.getLogger(Swagger2Config.class);


    //是否开启 swagger-ui 功能，默认为false
    //swagger 访问地址  http://localhost:7723/swagger-ui.html#!
    @Value("${swagger.enable:false}")
    private Boolean enable;

    @Bean
    public Docket createRestApi() {
        logger.info("-------------------swagger启动状态（true为开启，false为关闭）------------------- {}",enable);
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .pathMapping("/")
                .apiInfo(apiInfo())
                .select()
                //需要Swagger描述的接口包路径，如果不想某接口暴露，可在接口上加@ApiIgnore注解
                .apis(RequestHandlerSelectors.basePackage("com.example.SpringProjectDemo.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    //配置在线文档的基本信息
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SpringProjectDemo项目")
                .description("使基于SpringBoot的后端开发变得简单")
                .version("1.0")
                .build();
    }


}
