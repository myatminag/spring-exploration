package com.spring.demo;

import org.springframework.stereotype.Component;

import jakarta.annotation.Priority;

@Priority(2)
@Component
public class MyService2 implements MyService {

	private String name;

	public MyService2() {
		name = "Service 2";
	}

	@Override
	public String getMessage() {
		return "Hello Spring From %s".formatted(name);
	}
}
