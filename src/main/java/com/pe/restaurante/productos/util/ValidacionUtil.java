package com.pe.restaurante.productos.util;

import java.util.List;

public class ValidacionUtil {

	private static final int MAX_LONGITUD_NOMBRE = 50;
	private static final int CATEGORIA_MINIMA = 1;
	private static final double PRECIO_MINIMO = 0.01;
	private static final double PRECIO_MAXIMO = 1000.00;

	public static boolean esNombreProductoValido(String nombreProducto) {
		return !esTextoVacio(nombreProducto) && nombreProducto.length() <= MAX_LONGITUD_NOMBRE;
	}

	public static boolean esCategoriaValida(Object categoriaId) {
		Integer categoriaFinal = convertirAEntero(categoriaId);
		return categoriaFinal != null && categoriaFinal >= CATEGORIA_MINIMA;
	}

	public static boolean esPrecioValido(Object precio) {
		Double precioFinal = convertirADouble(precio);
		return precioFinal != null && precioFinal >= PRECIO_MINIMO && precioFinal <= PRECIO_MAXIMO;
	}

	public static Integer convertirAEntero(Object valor) {
		if (valor == null) {
			return null;
		}
		if (valor instanceof Integer) {
			return (Integer) valor;
		}
		if (valor instanceof Long) {
			return ((Long) valor).intValue();
		}
		if (valor instanceof String) {
			try {
				return Integer.parseInt((String) valor);
			} catch (NumberFormatException e) {
				return null;
			}
		}
		return null;
	}

	public static Double convertirADouble(Object valor) {
		if (valor instanceof Double) {
			return (Double) valor;
		}
		if (valor instanceof Integer) {
			return ((Integer) valor).doubleValue();
		}
		if (valor instanceof String) {
			try {
				return Double.parseDouble((String) valor);
			} catch (NumberFormatException e) {
				return null;
			}
		}
		return null;
	}

	public static boolean esListaVacia(List<?> lista) {
		return lista == null || lista.isEmpty();
	}

	public static boolean esTextoVacio(String text) {
		return text == null || text.trim().isEmpty();
	}
}
