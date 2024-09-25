package com.spring.events;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.spring.events.listeners", "com.spring.events.publishers" })
public class ApplicationConfig {

}
