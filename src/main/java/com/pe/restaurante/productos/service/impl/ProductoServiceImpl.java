package com.pe.restaurante.productos.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pe.restaurante.productos.dto.ProductoDTO;
import com.pe.restaurante.productos.model.Categoria;
import com.pe.restaurante.productos.model.Producto;
import com.pe.restaurante.productos.model.dao.CategoriaDaoJpaRepository;
import com.pe.restaurante.productos.model.dao.ProductoDaoJpaRepository;
import com.pe.restaurante.productos.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {
	
    @Autowired
    private ProductoDaoJpaRepository productoDaoJpaRepository;
    
    @Autowired
    private CategoriaDaoJpaRepository categoriaDaoJpaRepository;

	@Override
	public ProductoDTO crearProducto(Producto producto) {
        if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio.");
        }
        if (producto.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        
        Categoria categoria = categoriaDaoJpaRepository.findById(producto.getCategoria().getId())
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));
        
        producto.setCategoria(categoria);
        Producto productoGuardado = productoDaoJpaRepository.save(producto);
        
        return new ProductoDTO(productoGuardado);
	}
	
}
