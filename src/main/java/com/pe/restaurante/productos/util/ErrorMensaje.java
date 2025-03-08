package com.pe.restaurante.productos.util;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ErrorMensaje {
	
	private int cod;
	private String msg;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<ErrorMensaje> errors;
 
	public ErrorMensaje(int cod, String msg) {
		this.cod = cod;
		this.msg = msg;
	}
 
	public int getCod() {
		return cod;
	}
 
	public void setCod(int cod) {
		this.cod = cod;
	}
 
	public String getMsg() {
		return msg;
	}
 
	public void setMsg(String msg) {
		this.msg = msg;
	}
 
	public List<ErrorMensaje> getErrors() {
		return errors;
	}
 
	public void setErrors(List<ErrorMensaje> errors) {
		this.errors = errors;
	}
 
	@Override
	public String toString() {
		return "ErrorMessage [cod=" + cod + ", msg=" + msg + "]";
	}

}
