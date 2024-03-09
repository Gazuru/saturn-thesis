package hu.bme.aut.saturn.education.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "hu.bme.aut.saturn.education")
public class SaturnEducationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaturnEducationApplication.class, args);
	}

}
