package com.spring.circular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanA {

	@Autowired
	private BeanB bean;
	
	public void doSomething() {
		bean.sayHello();
	}

	public void sayHello() {
		System.out.println("Hello From Bean A");
	}
}
