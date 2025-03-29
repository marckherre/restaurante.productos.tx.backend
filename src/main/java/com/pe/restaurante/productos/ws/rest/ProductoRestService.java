package com.pe.restaurante.productos.ws.rest;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/e/listar")
	public ResponseEntity<Object> obtenerTodosClientes(){
		logger.info("Invoca endpoint consulta");
		return productoService.obtenerTodosProductos();
	}
	
	@PatchMapping("/t/actualizar/{id}")
	public ResponseEntity<Object> actualizarParcialProducto(@PathVariable("id") Long id, @RequestBody Map<String, Object> campos) {
    logger.info("Invoca endpoint PATCH para actualización parcial");
	    return productoService.actualizarProductoParcial(id, campos);
	}
	
	@DeleteMapping("/t/eliminar/{id}")
	public ResponseEntity<Object> eliminarProducto(@PathVariable("id") Long id) {
	    logger.info("Invoca endpoint eliminar producto");
	    return productoService.eliminarProducto(id);
	}

}
