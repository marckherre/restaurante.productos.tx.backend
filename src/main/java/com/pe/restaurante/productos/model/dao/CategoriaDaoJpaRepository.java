package com.pe.restaurante.productos.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pe.restaurante.productos.model.Categoria;


public interface CategoriaDaoJpaRepository extends JpaRepository<Categoria, Long> {
}

