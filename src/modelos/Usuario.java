package modelos;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Usuario {

	private static String usuario;
	private static String password;

	public Usuario(String usuario, String password) {

		this.usuario = usuario;
		this.password = password;

	}

	/**
	 * @return the usuario
	 */
	public static String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the password
	 */
	public static String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
