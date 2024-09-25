package com.spring.events;

import java.time.LocalDateTime;

public record MyEvent(String name, LocalDateTime publishedAt) {

	public MyEvent(String message) {
		this(message, LocalDateTime.now());
	}
}
