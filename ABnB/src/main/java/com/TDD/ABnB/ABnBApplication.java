package com.TDD.ABnB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ABnBApplication {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {    return new BCryptPasswordEncoder(); }

	public static void main(String[] args) {
		SpringApplication.run(ABnBApplication.class, args);

	}

}

