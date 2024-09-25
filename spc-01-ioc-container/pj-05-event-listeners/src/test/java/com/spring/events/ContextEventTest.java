package com.spring.events;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.events.publishers.MyEventPublisher;

public class ContextEventTest {

	@Test
	void demo() {

		try (var context = new AnnotationConfigApplicationContext(ApplicationConfig.class)) {

			var publisher = context.getBean(MyEventPublisher.class);
			publisher.publish(new MyEvent("My first event"));
			publisher.publish(new MyEvent("My second event"));
		}
	}
}
