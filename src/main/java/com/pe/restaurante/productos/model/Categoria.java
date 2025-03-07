package com.pe.restaurante.productos.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;


@Entity
public class Categoria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String indEst;
    private LocalDateTime fecRegis;
    private LocalDateTime fecMod;

    public Categoria() {
    }


    public Categoria(Long id, String nombre, String indEst, LocalDateTime fecRegis, LocalDateTime fecMod) {
        this.id = id;
        this.nombre = nombre;
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

	public void setNombre(String desNom) {
		this.nombre = desNom;
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
