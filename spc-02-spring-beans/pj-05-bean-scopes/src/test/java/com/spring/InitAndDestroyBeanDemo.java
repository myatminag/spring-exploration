package com.spring;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.beans.InitializableBean;

public class InitAndDestroyBeanDemo {

	@Test
	void demo() {

		try (var context = new AnnotationConfigApplicationContext("com.spring.beans")) {

			var bean = context.getBean(InitializableBean.class);

			assertNotNull(bean);
		}
	}
}
