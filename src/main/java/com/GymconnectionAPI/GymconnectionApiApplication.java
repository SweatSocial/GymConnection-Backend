package com.GymconnectionAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"src/main/java/com/GymconnectionAPI/controller"})
public class GymconnectionApiApplication {
	public static String PORT = System.getenv("PORT");
	public static void main(String[] args) {
		SpringApplication.run(GymconnectionApiApplication.class, args);
	}

}
