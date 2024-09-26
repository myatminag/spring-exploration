package com.spring.beans;

import org.springframework.stereotype.Component;

@Component
public class MyServiceOne implements MySerivce {

	@Override
	public String message() {
		return "Hello From My Service One";
	}

}
