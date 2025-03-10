package com.pe.restaurante.productos.ws.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pe.restaurante.productos.dto.ProductoDTO;
import com.pe.restaurante.productos.service.impl.ProductoServiceImpl;

@RestController
@RequestMapping("/productos")
public class ProductoRestService {

	@Autowired
	private ProductoServiceImpl productoService;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductoRestService.class);

	@PostMapping("/t/registro")
	public ResponseEntity<Object> registrarProducto(@RequestBody ProductoDTO productoDTO ) {
		logger.info("Invoca endpoint registro");
		return productoService.crearProducto(productoDTO);
	}

}
