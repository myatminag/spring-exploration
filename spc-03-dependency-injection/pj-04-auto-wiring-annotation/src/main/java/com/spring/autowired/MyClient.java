package com.spring.autowired;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MyClient {

	private final MyService service; // Dependency

	public void showMessage() {
		System.out.printf(service.message());
	}
}
