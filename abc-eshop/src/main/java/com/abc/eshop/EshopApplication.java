/**
 * 
 * Author Syed Thameem
 * 
 * File Name: EshopApplication
 * File Description: Spring Boot start class for E-shopping Application.
 * 
 */
package com.abc.eshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class EshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(EshopApplication.class, args);
	}
}