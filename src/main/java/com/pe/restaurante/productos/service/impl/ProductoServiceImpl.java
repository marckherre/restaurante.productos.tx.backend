package com.pe.restaurante.productos.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.pe.restaurante.productos.dto.ProductoDTO;
import com.pe.restaurante.productos.enums.ErroresEnum;
import com.pe.restaurante.productos.model.Categoria;
import com.pe.restaurante.productos.model.Producto;
import com.pe.restaurante.productos.model.dao.CategoriaDaoJpaRepository;
import com.pe.restaurante.productos.model.dao.ProductoDaoJpaRepository;
import com.pe.restaurante.productos.service.ProductoService;
import com.pe.restaurante.productos.util.ErrorMensaje;
import com.pe.restaurante.productos.util.ValidacionUtil;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoDaoJpaRepository productoDaoJpaRepository;

	@Autowired
	private CategoriaDaoJpaRepository categoriaDaoJpaRepository;

	@Override
	public ResponseEntity<Object> crearProducto(ProductoDTO  productoDTO) {
		List<ErrorMensaje> errores = new ArrayList<>();

		if (!ValidacionUtil.esNombreProductoValido(productoDTO.getNombre())) {
			errores.add(new ErrorMensaje(ErroresEnum.ERROR_01
					.getCodigo(), ErroresEnum
					.getMensaje(ErroresEnum.ERROR_01.getCodigo())));
		}

		if (!ValidacionUtil.esPrecioValido(productoDTO.getPrecio())) {
			errores.add(new ErrorMensaje(ErroresEnum.ERROR_02
					.getCodigo(), ErroresEnum
					.getMensaje(ErroresEnum.ERROR_02.getCodigo())));
		}


		if (!ValidacionUtil.esCategoriaValida(productoDTO.getCategoriaId())) {
		        errores.add(new ErrorMensaje(ErroresEnum.ERROR_03.getCodigo(),
		                ErroresEnum.getMensaje(ErroresEnum.ERROR_03.getCodigo())));
		    
		}

		if (!errores.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errores);
		}

		Categoria categoria = categoriaDaoJpaRepository.findById(productoDTO.getCategoriaId()).orElse(null);

		if (categoria == null) {
			errores.add(new ErrorMensaje(ErroresEnum.ERROR_04
					.getCodigo(), ErroresEnum
					.getMensaje(ErroresEnum.ERROR_04.getCodigo())));
		}
		
		if (!errores.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errores);
		}

        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setCategoria(categoria);
		
		Producto productoGuardado = productoDaoJpaRepository.save(producto);

		return ResponseEntity.status(HttpStatus.CREATED).body(new ProductoDTO(productoGuardado));
	}

}
