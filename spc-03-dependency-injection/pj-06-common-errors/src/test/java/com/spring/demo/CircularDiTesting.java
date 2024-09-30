package com.spring.demo;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CircularDiTesting {

	@Test
	void demo() {
		
		try (var context = new AnnotationConfigApplicationContext("com.spring.circular")) {
			
		}
	}
}
