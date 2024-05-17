package it.juan.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class DogRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DogRestApiApplication.class, args);
	}

}
