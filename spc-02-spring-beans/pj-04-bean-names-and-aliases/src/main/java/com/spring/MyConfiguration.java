package com.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.spring.beans.MySerivce;
import com.spring.beans.MyServiceOne;

@Configuration
@ComponentScan(basePackages = "com.spring.beans")
public class MyConfiguration {

	@Bean(name = { "myService", "defaultService" })
	MySerivce myService() {
		return new MyServiceOne();
	}
}
