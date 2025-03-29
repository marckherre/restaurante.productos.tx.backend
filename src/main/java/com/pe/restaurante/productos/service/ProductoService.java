package com.pe.restaurante.productos.service;


import org.springframework.http.ResponseEntity;

import com.pe.restaurante.productos.dto.ProductoDTO;

public interface ProductoService {
	ResponseEntity<Object> crearProducto(ProductoDTO  productoDTO );
	ResponseEntity<Object> obtenerTodosProductos();
}
