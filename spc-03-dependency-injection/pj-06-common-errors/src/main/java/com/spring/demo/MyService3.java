package com.spring.demo;

import org.springframework.stereotype.Component;

import jakarta.annotation.Priority;

@Priority(3)
@Component
public class MyService3 implements MyService {

	private String name;

	public MyService3() {
		name = "Service 3";
	}

	@Override
	public String getMessage() {
		return "Hello Spring From %s".formatted(name);
	}
}
