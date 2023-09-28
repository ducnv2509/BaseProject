package lab.base.config;

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

@EnableSwagger2
@Configuration
public class Swagger2Config {
    @Value("DATN_FALL2022")
    private String applicationName;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("lab.base.controller"))
                .paths(PathSelectors.any())
                .build()
//                .securitySchemes(Collections.singletonList(apiKey()))
//                .securityContexts(securityContext(
//                        "/api/accounts/**"
//                        ))
                .apiInfo(apiEndPointsInfo());
    }

//    @Bean
//    public SecurityConfiguration security() {
//        return SecurityConfigurationBuilder.builder()
//                .build();
//    }
//
//    private List<SecurityContext> securityContext(String... antPatterns) {
//        return Arrays.stream(antPatterns).map(antPattern -> SecurityContext.builder().securityReferences(defaultAuth())
//                .forPaths(PathSelectors.ant(antPattern)).build()).collect(Collectors.toList());
//    }
//
//    private List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope = new AuthorizationScope(
//                "global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return Collections.singletonList(new SecurityReference("JWT",
//                authorizationScopes));
//    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title(applicationName)
                .version("1.0.0")
                .build();
    }

//    private ApiKey apiKey() {
//        return new ApiKey("JWT", "Authorization", "header");
//    }
}