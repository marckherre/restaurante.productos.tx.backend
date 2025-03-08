package com.pe.restaurante.productos.dto;

import com.pe.restaurante.productos.model.Producto;

public class ProductoDTO {
    private Long id;
    private String nombre;
    private Double precio;
    private Long categoriaId;
    private String categoriaNombre;
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}


	public String getCategoriaNombre() {
		return categoriaNombre;
	}


	public void setCategoriaNombre(String categoriaNombre) {
		this.categoriaNombre = categoriaNombre;
	}

	public ProductoDTO() {}
	
	public ProductoDTO(Producto producto) {
        this.id = producto.getId();
        this.nombre = producto.getNombre();
        this.precio = producto.getPrecio();
        this.categoriaId = producto.getCategoria().getId();
        this.categoriaNombre = producto.getCategoria().getNombre();
    }
    
}
