package util;

import java.sql.SQLException;

public class Validaciones {

	public static boolean validarLinea(String referencia, String cantidad) throws SQLException {
	
		boolean resultadoReferencia = validarReferencia(referencia);
		boolean resultadoCantidad = validarCantidad(cantidad);
	
		if (resultadoReferencia == true && resultadoCantidad == true) {
			return true;
		} else {
			return false;
		}
	}

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

	public static boolean validarCantidad(String cantidad) {
		try {
			Integer.parseInt(cantidad);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
