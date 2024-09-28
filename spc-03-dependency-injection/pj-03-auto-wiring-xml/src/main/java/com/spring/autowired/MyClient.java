package com.spring.autowired;

public class MyClient {

	private MyService service;

	public void setService(MyService service) {
		this.service = service;
	}

	public void showMessage() {
		System.out.printf(service.message());
	}
}
