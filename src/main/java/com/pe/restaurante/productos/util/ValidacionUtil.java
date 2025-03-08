package com.pe.restaurante.productos.util;

import java.util.List;
import java.util.Objects;

public class ValidacionUtil {

	public static boolean esNombreProductoValido(String nombreProducto) {
		final int digitosValidos = 50;
		return !esTextoVacio(nombreProducto) && nombreProducto.length() <= digitosValidos;
	}

	public static boolean esCategoriaValida(Object categoriaId) {
		final int categoriaMinima = 1;

		Integer categoriaFinal = convertirAEntero(categoriaId);
		if (categoriaFinal == null) {
			return false;
		}

		if (categoriaFinal < categoriaMinima) {
			return false;
		}

		return true;
	}

	public static boolean esPrecioValido(Object precio) {
		final double precioMinimo = 0.01;
		final double precioMaximo = 1000.00;

		Double precioFinal = convertirADouble(precio);
		if (precioFinal == null) {
			return false;
		}

		if (precioFinal < precioMinimo || precioFinal > precioMaximo) {
			return false;
		}

		return true;
	}

	public static Integer convertirAEntero(Object valor) {
		if (valor == null) {
			return null;
		}

		if (valor instanceof Integer) {
			return (Integer) valor;
		} else if (valor instanceof Long) {
			return ((Long) valor).intValue();
		} else if (valor instanceof String) {
			try {
				return Integer.parseInt((String) valor);
			} catch (NumberFormatException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	public static Double convertirADouble(Object valor) {
		if (valor instanceof Double) {
			return (Double) valor;
		} else if (valor instanceof Integer) {
			return ((Integer) valor).doubleValue();
		} else if (valor instanceof String) {
			try {
				return Double.parseDouble((String) valor);
			} catch (NumberFormatException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	public static boolean esListaVacia(List<?> lista) {
		return lista == null || lista.size() == 0;
	}

	public static boolean esTextoVacio(String text) {
		return Objects.isNull(text) || text.trim().isEmpty();
	}

}
