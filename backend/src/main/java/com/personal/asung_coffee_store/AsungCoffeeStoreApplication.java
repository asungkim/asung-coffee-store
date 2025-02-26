package com.personal.asung_coffee_store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AsungCoffeeStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsungCoffeeStoreApplication.class, args);
	}

}
