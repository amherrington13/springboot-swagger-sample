package com.swagger.sample;

import com.google.common.base.Predicate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.system.ApplicationPidFileWriter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.AuthorizationScopeBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static com.google.common.base.Predicates.or;
import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableAutoConfiguration
@EnableSwagger2
@SpringBootApplication
public class Application implements CommandLineRunner {


	/********************************
	 * 	Swagger using MangoFactory
	 */
//	private SpringSwaggerConfig springSwaggerConfig;
//
//	@Autowired
//	public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
//		this.springSwaggerConfig = springSwaggerConfig;
//	}
//
//	@Bean
//	public SwaggerSpringMvcPlugin customImplementation() {
//		return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
//				//This info will be used in Swagger. See realisation of ApiInfo for more details.
//				.apiInfo(new ApiInfo(
//						"SmartMe education API",
//						"This app is for education, training purpose. It represents model of landlords and apartments for rent",
//						null,
//						null,
//						null,
//						null
//				))
//				//Here we disable auto generating of responses for REST-endpoints
//				.useDefaultResponseMessages(false)
//				//Here we specify URI patterns which will be included in Swagger docs. Use regex for this purpose.
//				.includePatterns(".*");
//	}
	
   @Bean
	public Docket swaggerApi() {
	   AuthorizationScope[] authScopes = new AuthorizationScope[1];
	   authScopes[0] = new AuthorizationScopeBuilder()
			   .scope("read")
			   .description("read access")
			   .build();
	   SecurityReference securityReference = SecurityReference.builder()
			   .reference("test")
			   .scopes(authScopes)
			   .build();

	   ArrayList<SecurityContext> securityContexts = newArrayList(SecurityContext.builder().securityReferences
			   (newArrayList(securityReference)).build());

		return new Docket(DocumentationType.SWAGGER_2)
				.securitySchemes(newArrayList(apiKey()))
				.securityContexts(securityContexts)
				.groupName("swagger-message-api")
				.select()
				.paths(swaggerPaths())
				.build()
	   			.apiInfo(apiInfo());

	}

	@Bean
	SecurityScheme apiKey() {
		return new ApiKey("api_key", "api_key", "header");
	}

    //can separate into multiple groups if wanted
//	@Bean
//	public Docket helloApi() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.groupName("hello-world-api")
//				.apiInfo(apiInfo())
//				.select()
//				.paths(helloPaths())
//				.build();
//	}

	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SpringBoot Swagger Sample")
                .description("A sample SpringBoot app that uses Swagger for documentation.")
                .build();
    }

    private Predicate<String> swaggerPaths() {
        return or(
                regex("/welcome.*"),
                regex("/hello.*")
        );
    }


	
	public static void main(String[] args) throws InterruptedException {
		SpringApplication springApplication = new SpringApplication(Application.class);
		springApplication.addListeners(new ApplicationPidFileWriter("springboot-swagger-sample.pid"));
		springApplication.run(args);
	}
	
	@Override
	public void run(String... args) throws Exception {
	}


}
