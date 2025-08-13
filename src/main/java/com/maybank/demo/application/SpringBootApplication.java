package com.maybank.demo.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@org.springframework.boot.autoconfigure.SpringBootApplication
@ComponentScan(basePackages = "com.maybank.demo")
@EnableJpaRepositories(basePackages = "com.maybank.demo.repository", entityManagerFactoryRef = "entityManagerFactory")
@EntityScan("com.maybank.demo.entity")
public class SpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApplication.class, args);
	}

}
