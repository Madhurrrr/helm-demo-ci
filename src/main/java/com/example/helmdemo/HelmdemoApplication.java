package com.example.helmdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class HelmdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelmdemoApplication.class, args);
	}

	@RestController
	@RequestMapping("/api")
	public class HelmdemoController {
		@Value("${testkey}")
		private String mySecretProperty;
		@GetMapping("/hello")
		public String sayHello() {
			return "Hello World from helm !";
		}
		@GetMapping("/vault")
		public String returnSecret() { return mySecretProperty; }
	}

}
