package com.hv.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Technically, this is a Spring Configuration class.
 * The annotation @SpringBootApplication enables the Spring Context and 
 * all the startup magic of Spring Boot.
 * 
 * @author harshul.varshney
 *
 */
@SpringBootApplication
public class UploadMultipleFilesApplication {

	public static void main(String[] args) {
		SpringApplication.run(UploadMultipleFilesApplication.class, args);
	}
}
