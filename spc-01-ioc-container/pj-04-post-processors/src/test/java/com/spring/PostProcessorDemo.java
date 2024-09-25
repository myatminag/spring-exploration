package com.spring;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.spring.beans.MyBean;

public class PostProcessorDemo {

	@Test
	void test() {
		
		try (var context = new GenericXmlApplicationContext("classpath:/application.xml")) {
			
			var myBean = context.getBean(MyBean.class);
			System.out.println(myBean.getValue());
		}
	}
}
