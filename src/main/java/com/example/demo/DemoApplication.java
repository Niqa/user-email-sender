package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Configuration
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("Aplikacja testowa");
	}

	@Bean
	public RestTemplate restTemplate (RestTemplateBuilder builder){
		return builder.build(); //co to robi?
	}

	@Bean
	public ResourceBundleMessageSource messageSource(){
		ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
		resourceBundleMessageSource.setBasename("massages");
		return resourceBundleMessageSource; //co to robi?
	}
}
