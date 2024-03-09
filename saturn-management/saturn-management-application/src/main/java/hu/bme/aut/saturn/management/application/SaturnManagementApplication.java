package hu.bme.aut.saturn.management.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "hu.bme.aut.saturn.management")
public class SaturnManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaturnManagementApplication.class, args);
	}

}
