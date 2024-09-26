package com.spring.beans;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UsingBeanNames {

	@Test
	void demo() {

		try (var context = new GenericXmlApplicationContext("classpath:beans.xml")) {

			var factory = context.getBeanFactory();
			var beanNames = factory.getBeanDefinitionNames();

			for (var name : beanNames) {
				System.out.println(name);
				
				var aliases = factory.getAliases(name);
				
				for (var alias : aliases) {
					System.out.printf("Alias of %s is %s%n".formatted(name, alias));
				}
			}
		}
	}
}
