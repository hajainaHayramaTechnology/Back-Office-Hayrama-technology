package com.Hayrama;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.Hayrama.repository")
public class BackOfficeHayramaTechnologyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackOfficeHayramaTechnologyApplication.class, args);
	}

}
