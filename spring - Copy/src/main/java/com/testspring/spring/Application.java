package com.testspring.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// this below annotation is used to tell that is has other dependencies and then it will other dependencies
@SpringBootApplication

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
