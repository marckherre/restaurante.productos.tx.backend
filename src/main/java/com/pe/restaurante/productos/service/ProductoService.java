package com.pe.restaurante.productos.service;

import com.pe.restaurante.productos.dto.ProductoDTO;
import com.pe.restaurante.productos.model.Producto;

public interface ProductoService {
	ProductoDTO crearProducto(Producto producto);
}
