package com.spring.demo;

public class MyService {

	private String name;

	public MyService(String name) {
		super();
		this.name = name;
	}

	public String getMessage() {
		return "Hello Spring From %s".formatted(name);
	}
}
