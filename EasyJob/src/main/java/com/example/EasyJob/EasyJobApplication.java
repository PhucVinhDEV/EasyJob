package com.example.EasyJob;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EasyJobApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyJobApplication.class, args);

		System.out.println("http://localhost:8888/bitznomad/swagger");
	}

}
