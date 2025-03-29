package com.pe.restaurante.productos.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private final ProductoDaoJpaRepository productoRepository;
	private final CategoriaDaoJpaRepository categoriaRepository;
	private static final Logger logger = LoggerFactory.getLogger(ProductoServiceImpl.class);

	@Autowired
	public ProductoServiceImpl(ProductoDaoJpaRepository productoRepository,
			CategoriaDaoJpaRepository categoriaRepository) {
		this.productoRepository = productoRepository;
		this.categoriaRepository = categoriaRepository;
	}

	@Override
	public ResponseEntity<Object> crearProducto(ProductoDTO productoDTO) {
		List<ErrorMensaje> errores = validarProductoDTO(productoDTO);
		if (!errores.isEmpty()) {
			logErrors(errores);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errores);
		}

		Optional<Categoria> categoriaOpt = categoriaRepository.findById(productoDTO.getCategoriaId());
		if (!categoriaOpt.isPresent()) {
			errores.add(new ErrorMensaje(ErroresEnum.ERROR_04.getCodigo(),
					ErroresEnum.getMensaje(ErroresEnum.ERROR_04.getCodigo())));
			logErrors(errores);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errores);
		}

		Optional<Producto> productoExistente = productoRepository.findByNombre(productoDTO.getNombre());
		if (productoExistente.isPresent()) {
			errores.add(new ErrorMensaje(ErroresEnum.ERROR_05.getCodigo(),
					ErroresEnum.getMensaje(ErroresEnum.ERROR_05.getCodigo())));
			logErrors(errores);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errores);
		}

		Producto producto = new Producto();
		producto.setNombre(productoDTO.getNombre());
		producto.setPrecio(productoDTO.getPrecio());
		producto.setCategoria(categoriaOpt.get());

		Producto productoGuardado = productoRepository.save(producto);
		logErrors(errores);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ProductoDTO(productoGuardado));
	}

	private List<ErrorMensaje> validarProductoDTO(ProductoDTO productoDTO) {
		List<ErrorMensaje> errores = new ArrayList<>();

		if (!ValidacionUtil.esNombreProductoValido(productoDTO.getNombre())) {
			errores.add(new ErrorMensaje(ErroresEnum.ERROR_01.getCodigo(),
					ErroresEnum.getMensaje(ErroresEnum.ERROR_01.getCodigo())));
		}
		if (!ValidacionUtil.esPrecioValido(productoDTO.getPrecio())) {
			errores.add(new ErrorMensaje(ErroresEnum.ERROR_02.getCodigo(),
					ErroresEnum.getMensaje(ErroresEnum.ERROR_02.getCodigo())));
		}
		if (!ValidacionUtil.esCategoriaValida(productoDTO.getCategoriaId())) {
			errores.add(new ErrorMensaje(ErroresEnum.ERROR_03.getCodigo(),
					ErroresEnum.getMensaje(ErroresEnum.ERROR_03.getCodigo())));
		}
		return errores;
	}

	private void logErrors(List<ErrorMensaje> errores) {
		errores.forEach(error -> logger.error("Código: {}, Mensaje: {}", error.getCod(), error.getMsg()));
	}

	@Override
	public ResponseEntity<Object> obtenerTodosProductos() {
		List<ErrorMensaje> errores = new ArrayList<>();
		List<Producto> resultBD = new ArrayList<>();
		List<ProductoDTO> resultToShow = new ArrayList<>();

		resultBD = productoRepository.findAll();

		if (ValidacionUtil.esListaVacia(resultBD)) {
			errores.add(new ErrorMensaje(ErroresEnum.ERROR_06.getCodigo(),
					ErroresEnum.getMensaje(ErroresEnum.ERROR_06.getCodigo())));
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errores);
		}
		for (Producto p : resultBD) {
			ProductoDTO dto = new ProductoDTO(p);
			resultToShow.add(dto);
		}
		return ResponseEntity.status(HttpStatus.OK).body(resultToShow);

	}

	@Override
	public ResponseEntity<Object> actualizarProductoParcial(Long id, Map<String, Object> campos) {
		List<ErrorMensaje> errores = new ArrayList<>();

		Optional<Producto> productoOpt = productoRepository.findById(id);
		if (!productoOpt.isPresent()) {
			errores.add(new ErrorMensaje(ErroresEnum.ERROR_07.getCodigo(),
					ErroresEnum.getMensaje(ErroresEnum.ERROR_07.getCodigo())));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errores);
		}

		Producto producto = productoOpt.get();

		if (campos.containsKey("nombre")) {
			String nombre = campos.get("nombre").toString();
			if (!ValidacionUtil.esNombreProductoValido(nombre)) {
				errores.add(new ErrorMensaje(ErroresEnum.ERROR_01.getCodigo(), ErroresEnum.ERROR_01.getMensaje()));
			} else {
				producto.setNombre(nombre);
			}
		}

		if (campos.containsKey("precio")) {
			try {
				Double precio = Double.parseDouble(campos.get("precio").toString());
				if (!ValidacionUtil.esPrecioValido(precio)) {
					errores.add(new ErrorMensaje(ErroresEnum.ERROR_02.getCodigo(), ErroresEnum.ERROR_02.getMensaje()));
				} else {
					producto.setPrecio(precio);
				}
			} catch (NumberFormatException e) {
				errores.add(new ErrorMensaje(ErroresEnum.ERROR_02.getCodigo(), ErroresEnum.ERROR_02.getMensaje()));
			}
		}

		if (campos.containsKey("categoriaId")) {
			try {
				Long categoriaId = Long.parseLong(campos.get("categoriaId").toString());
				if (!ValidacionUtil.esCategoriaValida(categoriaId)) {
					errores.add(new ErrorMensaje(ErroresEnum.ERROR_03.getCodigo(), ErroresEnum.ERROR_03.getMensaje()));
				} else {
					Optional<Categoria> categoriaOpt = categoriaRepository.findById(categoriaId);
					if (!categoriaOpt.isPresent()) {
						errores.add(
								new ErrorMensaje(ErroresEnum.ERROR_04.getCodigo(), ErroresEnum.ERROR_04.getMensaje()));
					} else {
						producto.setCategoria(categoriaOpt.get());
					}
				}
			} catch (NumberFormatException e) {
				errores.add(new ErrorMensaje(ErroresEnum.ERROR_03.getCodigo(), ErroresEnum.ERROR_03.getMensaje()));
			}
		}

		if (!errores.isEmpty()) {
			logErrors(errores);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errores);
		}

		productoRepository.save(producto);
		return ResponseEntity.ok(new ProductoDTO(producto));
	}

	@Override
	public ResponseEntity<Object> eliminarProducto(Long id) {
		Optional<Producto> productoOpt = productoRepository.findById(id);
		if (!productoOpt.isPresent()) {
			List<ErrorMensaje> errores = new ArrayList<>();
			errores.add(new ErrorMensaje(ErroresEnum.ERROR_07.getCodigo(), ErroresEnum.ERROR_07.getMensaje()));
			logErrors(errores);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errores);
		}

		productoRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@Override
	public ResponseEntity<Object> obtenerProductoPorId(Long id) {
		List<ErrorMensaje> errores = new ArrayList<>();

		Optional<Producto> productoOpt = productoRepository.findById(id);
		if (!productoOpt.isPresent()) {
			errores.add(new ErrorMensaje(ErroresEnum.ERROR_07.getCodigo(), ErroresEnum.ERROR_07.getMensaje()));
			logErrors(errores);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errores);
		}

		Producto producto = productoOpt.get();
		ProductoDTO dto = new ProductoDTO(producto);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

}
