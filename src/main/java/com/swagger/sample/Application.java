package com.swagger.sample;

import static springfox.documentation.builders.PathSelectors.regex;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.system.ApplicationPidFileWriter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableAutoConfiguration
@EnableSwagger2
@SpringBootApplication
public class Application implements CommandLineRunner {
	
	   @Bean
	    public Docket newsApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .groupName("swaggerSample")
	                .apiInfo(apiInfo())
	                .select()
	                .build();
	    }
	     
	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("SpringBoot Swagger Sample")
	                .description("A sample SpringBoot app that uses Swagger for documentation.")
	                .build();
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
