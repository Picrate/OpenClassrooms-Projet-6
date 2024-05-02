package com.openclassrooms.mddapi.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@EnableSwagger2
@Import(SpringDataRestConfiguration.class)
public class SpringFoxConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.openclassrooms.mddapi.controller"))
                .paths(PathSelectors.ant("/api/*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "MDD REST API",
                "ORION MDD Project API.",
                "MVP",
                "Terms of service",
                new Contact("Patrice Allary", "www.orion.com", "patrice.allary@orion.com"),
                "License of API", "API license URL", Collections.emptyList());
    }

}
