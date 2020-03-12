package com.dhbw.todoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class TodoServiceApplication {

	/**
	 * Main method starts the whole application.
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(TodoServiceApplication.class, args);
	}

}
