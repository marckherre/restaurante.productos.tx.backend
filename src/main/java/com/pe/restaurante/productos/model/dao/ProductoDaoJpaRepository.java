package com.pe.restaurante.productos.model.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pe.restaurante.productos.model.Producto;

public interface ProductoDaoJpaRepository extends JpaRepository<Producto, Long> {
	 Optional<Producto> findByNombre(String nombre);
}
