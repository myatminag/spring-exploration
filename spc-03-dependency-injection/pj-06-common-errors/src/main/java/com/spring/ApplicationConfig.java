package com.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.demo.MyClient;
import com.spring.demo.MyService;

@Configuration
public class ApplicationConfig {

	@Bean(name = { "service1", "service" })
	MyService service1() {
		return new MyService("Service 1");
	}

	@Bean
	MyService service2() {
		return new MyService("Service 2");
	}

	@Bean
	MyService service3() {
		return new MyService("Service 3");
	}

	@Bean
	MyClient myClient() {
		return new MyClient();
	}
}
