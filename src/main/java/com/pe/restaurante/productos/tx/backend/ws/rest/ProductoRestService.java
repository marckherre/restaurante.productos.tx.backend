package com.pe.restaurante.productos.tx.backend.ws.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productos")
public class ProductoRestService {
	
	@PostMapping("/t/registro")
    public ResponseEntity<String> registrarProducto() {
        return ResponseEntity.ok("Endpoint de registro de producto funcionando correctamente");
    }

}
