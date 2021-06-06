package util;

import java.sql.SQLException;

public class Validaciones {

	/**
	 * Calcula si un código y una cantidad son válidas para incluirlos en la base de
	 * datos.
	 * 
	 * @param referencia String con el código del producto.
	 * @param cantidad   String con la cantidad que se ha encontrado del producto.
	 * @return true si ambas son válidas. False si alguna no es válida.
	 * @throws SQLException propaga una excepción si hay un problema al guardar la
	 *                      información en la base de datos.
	 */
	public static boolean validarLinea(String referencia, String cantidad) throws SQLException {

		boolean resultadoReferencia = validarReferencia(referencia);
		boolean resultadoCantidad = validarCantidad(cantidad);

		if (resultadoReferencia == true && resultadoCantidad == true) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Devuelve si una referencia es válida. Para ello busca en la base de datos
	 * mediante la clase ConexionesBase
	 * 
	 * @param referencia String con la referencia buscada.
	 * @return true si es una referencia válida. False si no es válida. 
	 * @throws SQLException propaga una excepción si hay un problema al guardar la
	 *                      información en la base de datos.
	 */
	public static boolean validarReferencia(String referencia) throws SQLException {
//		if (referencia.length() == 7 || referencia.length() == 13) {
//			return true;
//		} else {
//			return false;
//		}

		// TODO Comprobar la referencia en la base de artículos

		Boolean enBase = ConexionBase.referenciaEnBase(referencia);
		return enBase;

	}

	/**
	 * 
	 * Comprueba si un String contiene un número entero válido.
	 * 
	 * @param cantidad String con el posible número.
	 * @return true si se puede transformar a entero. false si no se puede transformar. 
	 */
	public static boolean validarCantidad(String cantidad) {
		try {
			Integer.parseInt(cantidad);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
