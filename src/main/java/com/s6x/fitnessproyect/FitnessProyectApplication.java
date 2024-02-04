package com.s6x.fitnessproyect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FitnessProyectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitnessProyectApplication.class, args);
	}

}
