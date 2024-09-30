package com.spring.demo;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.ApplicationConfig;

public class JavaBasedConfig {

	@Test
	void demo() {
		
		try (var context = new AnnotationConfigApplicationContext(ApplicationConfig.class)) {
			
			var client = context.getBean(MyClient.class);
			client.showMessage();
		}
	}
}
