package com.sp.demo;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloBeanTest {

	@Test
	void test() {

		try (var context = new AnnotationConfigApplicationContext("com.sp.demo")) {

			var bean = context.getBean(HelloBean.class);
			System.out.println(bean.sayHello());
		}
	}
}
