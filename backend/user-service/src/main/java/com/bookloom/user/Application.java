package com.bookloom.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

/**
 * The main entry point for the Spring Boot application.
 *
 * This class is annotated with {@link SpringBootApplication} which denotes that it is the primary Spring Boot configuration class.
 * It also enables MongoDB repositories through the {@link EnableMongoRepositories} annotation.
 */
@SpringBootApplication
@EnableMongoRepositories
public class Application {

	/**
	 * The main method serves as the entry point of the Spring Boot application.
	 *
	 * It invokes {@link SpringApplication#run(Class, String...)} with the current class and command-line arguments.
	 *
	 * @param args command-line arguments passed during the application startup.
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
