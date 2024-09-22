package com.sp.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sp.ioc.bean.AnnotatedBean;

public class HelloBeanTest {

	@Test
	void test() {

		try (var context = new AnnotationConfigApplicationContext(ApplicationConfig.class)) {

			var bean = context.getBean(HelloBean.class);
			System.out.println(bean.sayHello());

			var annotatedBean = context.getBean(AnnotatedBean.class);
			System.out.println(annotatedBean.sayHello());
		}
	}
}
