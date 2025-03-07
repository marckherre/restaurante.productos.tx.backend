package com.pe.restaurante.productos.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pe.restaurante.productos.model.Categoria;
import com.pe.restaurante.productos.model.dao.CategoriaDaoJpaRepository;
import com.pe.restaurante.productos.service.CategoriaService;

import jakarta.annotation.PostConstruct;

@Service
public class CategoriaServiceImpl implements CategoriaService {
	
	  @Autowired
	    private CategoriaDaoJpaRepository categoriaDaoJpaRepository;
	  
	    @PostConstruct
	    public void initCategorias() {
	        if (categoriaDaoJpaRepository.count() == 0) {
	            List<Categoria> categorias = List.of(
	                new Categoria(null, "Bebidas", "1", LocalDateTime.now(), LocalDateTime.now()),
	                new Categoria(null, "Entradas", "1", LocalDateTime.now(), LocalDateTime.now()),
	                new Categoria(null, "Platos de Fondo", "1", LocalDateTime.now(), LocalDateTime.now()),
	                new Categoria(null, "Postres", "1", LocalDateTime.now(), LocalDateTime.now())
	            );
	            categoriaDaoJpaRepository.saveAll(categorias);
	        }
	    }

}
