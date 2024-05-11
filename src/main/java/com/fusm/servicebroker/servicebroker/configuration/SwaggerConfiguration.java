package com.fusm.servicebroker.servicebroker.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(msInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fusm.servicebroker"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo msInfo() {
        return new ApiInfoBuilder()
                .title("FUSM: Microservice for application flow")
                .description("Demo for microservice which filters requests")
                .version("0.1")
                .build();
    }
}
