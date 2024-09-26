package com.spring.beans;

import org.springframework.stereotype.Component;

@Component
public class MyService {

	public String message() {
		return "Hello From Annotation Base Bean";
	}
}
