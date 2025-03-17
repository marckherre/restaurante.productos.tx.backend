package com.pe.restaurante.productos.model.dao;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pe.restaurante.productos.model.Producto;

public interface ProductoDaoJpaRepository extends JpaRepository<Producto, Long> {
	 Optional<Producto> findByNombre(String nombre);
	 List<Producto> findAll();
}
