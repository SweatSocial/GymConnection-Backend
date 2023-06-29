package com.GymconnectionAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GymconnectionApiApplication {
	public String PORT = System.getenv("PORT");
	public static void main(String[] args) {
		SpringApplication.run(GymconnectionApiApplication.class, args);
	}

}
