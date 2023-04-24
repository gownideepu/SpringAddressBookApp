package com.bridgelabz.AddressBookApp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@Slf4j
public class AddressBookAppApplication {

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(AddressBookAppApplication.class, args);
		log.info("Application was successfully started",context.getEnvironment().getProperty("spring.datasource.username"));
	}
}
