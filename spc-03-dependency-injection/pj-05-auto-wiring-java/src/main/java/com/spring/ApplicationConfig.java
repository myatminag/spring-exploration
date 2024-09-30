package com.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.demo.MyClient;
import com.spring.demo.MyService;

@Configuration
public class ApplicationConfig {

	@Bean
	MyService myService() {
		return new MyService();
	}
	
	@Bean
	MyClient myClient() {
		return new MyClient();
	}
}
