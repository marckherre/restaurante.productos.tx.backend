package com.pe.restaurante.productos.tx.backend.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "com.pe.restaurante")
public class RestauranteProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestauranteProductosApplication.class, args);
	}

}
