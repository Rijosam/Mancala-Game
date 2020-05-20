package com.assignments.mancala.mancalagameservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MancalaGameServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(MancalaGameServiceApplication.class, args);
	}

}
