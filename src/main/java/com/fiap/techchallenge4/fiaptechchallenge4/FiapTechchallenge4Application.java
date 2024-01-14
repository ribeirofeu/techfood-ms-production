package com.fiap.techchallenge4.fiaptechchallenge4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class FiapTechchallenge4Application {

	public static void main(String[] args) {
		SpringApplication.run(FiapTechchallenge4Application.class, args);
	}

}
