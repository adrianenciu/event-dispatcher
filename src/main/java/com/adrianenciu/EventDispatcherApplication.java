package com.adrianenciu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class EventDispatcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventDispatcherApplication.class, args);
	}
}
