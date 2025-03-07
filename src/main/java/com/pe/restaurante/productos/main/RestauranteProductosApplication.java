package com.pe.restaurante.productos.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan(basePackages = "com.pe")
@EntityScan("com.pe.restaurante.productos.model")
@EnableJpaRepositories(basePackages = "com.pe.restaurante.productos.model.dao")
public class RestauranteProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestauranteProductosApplication.class, args);
	}

}
