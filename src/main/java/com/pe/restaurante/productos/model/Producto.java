package com.pe.restaurante.productos.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;


@Entity
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", unique = true, nullable = false)
    private String nombre;
    private Double precio;

	@ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    private String indEst;
    private LocalDateTime fecRegis; 
    private LocalDateTime fecMod;   

    public Producto() {
    }


    public Producto(Long id, String nombre, int cantidad, double precio, Categoria categoria, String indEst, LocalDateTime fecRegis, LocalDateTime fecMod) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.indEst = indEst;
        this.fecRegis = fecRegis;
        this.fecMod = fecMod;
    }
    
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


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public String getIndEst() {
		return indEst;
	}


	public void setIndEst(String indEst) {
		this.indEst = indEst;
	}


	public LocalDateTime getFecRegis() {
		return fecRegis;
	}


	public void setFecRegis(LocalDateTime fecRegis) {
		this.fecRegis = fecRegis;
	}


	public LocalDateTime getFecMod() {
		return fecMod;
	}


	public void setFecMod(LocalDateTime fecMod) {
		this.fecMod = fecMod;
	}
    
    @PrePersist
    protected void onCreate() {
        fecRegis = LocalDateTime.now();
        fecMod = LocalDateTime.now();
        indEst = "1"; 
    }

    @PreUpdate
    protected void onUpdate() {
        fecMod = LocalDateTime.now();
    }
}