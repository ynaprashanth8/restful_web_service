package com.myspace.rest.webservices.restfulwebservices;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//Config 
//Enable Swagger
//this will enable the class as a spring bean 
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	 public static final Contact DEFAULT_CONTACT = new Contact("Prashanth", "", "");
	 public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Api Documentation", "Api Documentation", "1.0", "urn:tos",
	          DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");

	private static final Set<String> DEFAULT_PRODUCERS_AND_CONSUMERS = new HashSet<String>(Arrays.asList("application/json","application/xml"));
	//http://localhost:8080/swagger-ui.html
	//Bean - Docket
	@Bean 
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO).produces(DEFAULT_PRODUCERS_AND_CONSUMERS).consumes(DEFAULT_PRODUCERS_AND_CONSUMERS);
	}

}
