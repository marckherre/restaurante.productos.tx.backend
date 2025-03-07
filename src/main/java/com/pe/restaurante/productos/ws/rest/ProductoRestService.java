package com.pe.restaurante.productos.ws.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pe.restaurante.productos.dto.ProductoDTO;
import com.pe.restaurante.productos.model.Producto;
import com.pe.restaurante.productos.service.impl.ProductoServiceImpl;

@RestController
@RequestMapping("/productos")
public class ProductoRestService {
	
    @Autowired
    private ProductoServiceImpl productoService;
	
	@PostMapping("/t/registro")
    public ResponseEntity<ProductoDTO> registrarProducto(@RequestBody Producto producto) {
		ProductoDTO dto = productoService.crearProducto(producto);
		return ResponseEntity.ok(dto);
    }
}
