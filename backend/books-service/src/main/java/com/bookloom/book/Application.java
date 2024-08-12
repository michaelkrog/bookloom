package com.bookloom.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * The main entry point for the Book Service.
 */
@SpringBootApplication(scanBasePackages = {"com.bookloom.shared", "com.bookloom.book"})
@EnableMongoRepositories
public class Application {


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
