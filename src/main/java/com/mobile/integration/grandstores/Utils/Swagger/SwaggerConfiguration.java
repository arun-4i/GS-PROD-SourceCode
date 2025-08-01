package com.mobile.integration.grandstores.Utils.Swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

  public static final String AUTHORIZATION_HEADER = "Authorization";

//   private ApiInfo apiInfo() {
//     return new ApiInfo("My REST API",
//         "Some custom description of API.",
//         "1.0",
//         "Terms of service",
//         new Contact("Sallo Szrajbman", "www.baeldung.com", "salloszraj@gmail.com"),
//         "License of API",
//         "API license URL");
//   }

    @Bean
    public ApiInfo metaData() {
        final ApiInfoBuilder builder = new ApiInfoBuilder();
        builder.title("GrandStore APi").version("1.0")
        .description("List of GrandStore APIs");
        return builder.build();
        }


  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(metaData())
        .securityContexts(Arrays.asList(securityContext()))
        .securitySchemes(Arrays.asList(apiKey()))
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build();
  }

  private ApiKey apiKey() {
    return new ApiKey("Authorization", AUTHORIZATION_HEADER, "header");
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder()
        .securityReferences(defaultAuth())
        .build();
  }

  List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope
        = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return Arrays.asList(new SecurityReference("Authorization", authorizationScopes));
  }

}


// <!-- Swagger 1 -->
// <dependency>
//     <groupId>io.springfox</groupId>
//     <artifactId>springfox-swagger2</artifactId>
//     <version>2.6.1</version>
// </dependency>
// <!-- Swagger 2 -->
// <dependency>
//     <groupId>io.springfox</groupId>
//     <artifactId>springfox-swagger-ui</artifactId>
//     <version>2.6.1</version>
// </dependency>