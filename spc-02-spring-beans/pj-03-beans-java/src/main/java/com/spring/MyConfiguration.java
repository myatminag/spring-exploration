package com.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.beans.MyService;

@Configuration
public class MyConfiguration {

	@Bean({
		"service1",
		"myService",
		"otherService"
	})
	static MyService service() {
		return new MyService();
	}
}
