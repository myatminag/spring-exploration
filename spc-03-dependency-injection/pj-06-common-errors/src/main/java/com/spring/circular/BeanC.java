package com.spring.circular;

import org.springframework.stereotype.Component;

@Component
public class BeanC {

	private BeanA bean;

	public BeanC(BeanA bean) {
		super();
		this.bean = bean;
	}
	
	public void doSomething() {
		bean.sayHello();
	}

	public void sayHello() {
		System.out.println("Hello From Bean C");
	}

}
