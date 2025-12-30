package com.accenture.SpringBootWebRest.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

/*import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;*/

//@EnableOpenApi
@Configuration
public class SwaggerConfig {
	/*@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(
                        "com.accenture.SpringBootWebRest"))
                .paths(PathSelectors.any())
                .build();
    }
	
	private ApiInfo apiInfo() {
	    return new ApiInfo(
	            "Job App APIs",
	            "By Rivu Chowdhury",
	            "1.0",
	            null,
	            new Contact("Rivu", "", ""),
	            null,
	            null,
	            Collections.emptyList()
	            
	    );
	}*/
	@Bean
    public OpenAPI myCustomConfig() {
		return new OpenAPI()
				.info(
				new Info().title("Job App Api's")
				.description("By Rivu Chowdhury")
		       )
				.servers(Arrays.asList(
                        new Server().url("http://localhost:8080").description("Local instance 1"),
                        new Server().url("http://localhost:8082").description("Local instance 2")
                ));
	}

}
