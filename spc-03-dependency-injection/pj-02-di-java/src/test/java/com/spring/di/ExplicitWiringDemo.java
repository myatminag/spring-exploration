package com.spring.di;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.ApplicationConfig;

public class ExplicitWiringDemo {

	@Test
	void demo() {

		try (var context = new AnnotationConfigApplicationContext(ApplicationConfig.class)) {

			var client1 = context.getBean("client1", MyClient.class);
			client1.showMessage();

			var client2 = context.getBean("client2", MyClient.class);
			client2.showMessage();
		}
	}
}
