package com.pe.restaurante.productos.service.impl;

import java.util.ArrayList;
import java.util.List;
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
        errores.forEach(error -> 
            logger.error("C�digo: {}, Mensaje: {}", error.getCod(), error.getMsg()));
    }
    @Override
    public ResponseEntity<Object> obtenerTodosClientes(){
        List<ErrorMensaje> errores = new ArrayList<>();
        List<Producto> resultBD = new ArrayList<>();
        List<ProductoDTO> resultToShow = new ArrayList<>();

        resultBD = productoRepository.findAll();

        if(ValidacionUtil.esListaVacia(resultBD)){
            errores.add(new ErrorMensaje(ErroresEnum.ERROR_06.getCodigo(), 
            ErroresEnum.getMensaje(ErroresEnum.ERROR_06.getCodigo())));
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errores);
        }
        for(Producto p:resultBD){
            ProductoDTO dto = new ProductoDTO(p);
            resultToShow.add(dto);
        }
        return ResponseEntity.status(HttpStatus.OK).body(resultToShow);
        
    }
}
