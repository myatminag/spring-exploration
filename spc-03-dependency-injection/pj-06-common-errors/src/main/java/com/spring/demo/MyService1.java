package com.spring.demo;

import org.springframework.stereotype.Component;

import jakarta.annotation.Priority;

@Priority(1)
@Component
public class MyService1 implements MyService {

	private String name;

	public MyService1() {
		name = "Service 1";
	}

	@Override
	public String getMessage() {
		return "Hello Spring From %s".formatted(name);
	}
}
