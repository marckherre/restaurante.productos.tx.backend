package com.pe.restaurante.productos.model.dao.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.pe.restaurante.productos.model.dao.ProductoDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * Clase que implementa las operaciones de consutla avanzada en la entidad Producto.
 * 
 * @author Marco Herrera
 * @version 1.0
 * @since 2025-03-07
 */

@Repository
@Transactional(readOnly = true)
public class ProductoDaoJpa implements ProductoDao{

    @PersistenceContext
    private EntityManager entityManager;
    
}
