package com.spring.beans;

public class MyServiceFactory {

	public MyService myService() {
		return new MyService("From Instance Factory Method");
	}
}
