package com.spring.autowired;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoWiringDemo {

	@Test
	void demo() {
		
		try (var context = new AnnotationConfigApplicationContext("com.spring")) {
			
			var bean = context.getBean(MyClient.class);
			bean.showMessage();
		}
	}
}
