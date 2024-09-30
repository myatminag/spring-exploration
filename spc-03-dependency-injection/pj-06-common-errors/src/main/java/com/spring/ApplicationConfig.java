package com.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.spring.demo.MyClient;

@Configuration
@ComponentScan(basePackages = "com.spring.demo")
public class ApplicationConfig {

	@Bean
	MyClient myClient() {
		return new MyClient();
	}
}
