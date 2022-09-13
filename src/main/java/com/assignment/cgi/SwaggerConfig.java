package com.assignment.cgi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        // NOTE: set the package of your application to avoid showing the spring-boot default services in the swagger documentation
        List<VendorExtension> vendorExtensions = new ArrayList<>();

        return new Docket(DocumentationType.SWAGGER_2)

                .apiInfo(new ApiInfo("Web-API For Words Count Occurrence", "In text analysis, one is often interested in which words occur most" + "often in a mass of text. To make it easier for those who work with" + "this, A small Web-API that takes a mass of" + "text as input and returns the 10 most frequent words along with" + "the frequency.\n", "0.0.1", "", new Contact("Prashant Borkar", "https://prashantborkar.io", "pfborkar@gmail.com"), "Logical Support by Prashant", "", vendorExtensions)).select().apis(RequestHandlerSelectors.basePackage("com.assignment.cgi")).paths(PathSelectors.any()).build();
    }
}