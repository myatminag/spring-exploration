package com.spring.beans;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.MyConfiguration;

public class JavaBasedConfigBeanTest {

	@Test
	void demo() {
		
		try (var context = new AnnotationConfigApplicationContext(MyConfiguration.class)) {
			
			var service = context.getBean(MyService.class);
			System.out.println(service.message());
		}
	}
}
