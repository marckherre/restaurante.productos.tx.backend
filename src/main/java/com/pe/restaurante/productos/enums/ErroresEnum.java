package com.pe.restaurante.productos.enums;

public enum ErroresEnum {
	ERROR_01(01, "El nombre de producto es obligatorio y no puede tener mas de 50 caracteres."),
	ERROR_02(02, "El precio es obligatorio y debe estar en el rango de 0.01 y 1000"),
	ERROR_03(03, "El ID de la categoría es obligatoria y es un número entero"),
	ERROR_04(04, "La categoría no existe");


	private int code;
	private String msg;
 
	private ErroresEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
 
	public int getCodigo() {
		return code;
	}
 
	public void setCodigo(int code) {
		this.code = code;
	}
 
	public String getMensaje() {
		return msg;
	}
 
	public void setMensaje(String msg) {
		this.msg = msg;
	}
 
	public static String getMensaje(int code) {
		String msg = "";
		for (ErroresEnum enumHTTP : ErroresEnum.values()) {
			if (enumHTTP.code == code) {
				msg = enumHTTP.msg;
				break;
			}
		}
		return msg;
	}
}
