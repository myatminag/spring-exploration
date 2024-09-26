package com.spring.beans;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationBaseConfigBeanTest {

	@Test
	void demo() {
		
		try (var context = new AnnotationConfigApplicationContext("com.spring.beans")) {
			
			var service = context.getBean(MyService.class);
			System.out.println(service.message());
		}
	}
}
