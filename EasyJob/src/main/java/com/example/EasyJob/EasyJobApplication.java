package com.example.EasyJob;

import jakarta.xml.bind.SchemaOutputResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EasyJobApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyJobApplication.class, args);

		System.out.println("http://localhost:8008/bitznomad/swagger");
	}

}
