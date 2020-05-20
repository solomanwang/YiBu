package com.yibu.web.config;

import com.wang.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@EnableSwagger2
@Configuration
public class Swagger2 {

    @Value("${swagger.show:false}")
    private boolean swaggerShow;
    @Value("${spring.application.name:API}")
    private String appName;

    @Bean
    public Docket createRestApi() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        List<Parameter> params = new ArrayList<>();
        parameterBuilder.name(Constants.HEADER_PARAM_LANGUAGE)
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .order(-1)
                .build();
        params.add(parameterBuilder.build());
        log.info("swaggerShow:{}", swaggerShow);
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerShow)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build()
                .globalOperationParameters(params);
    }

    private ApiInfo apiInfo() {
        String description = "参数需要传token的接口，也可以按【Bearer + 空格 + acess_token】放到header中的Authorization里。";
        description += "由于access_token的时效比较短(8小时)，所以过期后不要马上让用户去重新登录，而是调用refreshToken的方法获得新的access_token。另外退出的时候也推荐调用删除token的方法";
        return new ApiInfoBuilder()
                .title(appName)
                .description(description)
                .version("1.0")
                .build();
    }

}
