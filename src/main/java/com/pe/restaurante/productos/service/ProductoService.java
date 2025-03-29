package com.pe.restaurante.productos.service;


import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.pe.restaurante.productos.dto.ProductoDTO;

public interface ProductoService {
	ResponseEntity<Object> crearProducto(ProductoDTO  productoDTO );
	ResponseEntity<Object> obtenerTodosProductos();
	ResponseEntity<Object> actualizarProductoParcial(Long id, Map<String, Object> campos);

}
