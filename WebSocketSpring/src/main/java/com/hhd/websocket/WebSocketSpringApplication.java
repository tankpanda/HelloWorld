package com.hhd.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.hhd.websocket"})
public class WebSocketSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebSocketSpringApplication.class, args);
	}

}
