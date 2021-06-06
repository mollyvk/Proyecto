package application;

import java.io.IOException;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	private JFXTextField tf_usuario;

	@FXML
	private JFXPasswordField txt_password;

	@FXML
	private ImageView btn_entrar;

	/**
	 * Este método recoge el evento creado al pulsar sobre el botón de entrar.
	 * 
	 * TODO Debe consultar si usuario y password son correctos.
	 * 
	 * @param event es el evento al pulsar
	 */
	@FXML
	void entrar(MouseEvent event) {

		metodoEntrar();

	}

	/**
	 * 
	 * Este evento se genera si dentro de alguno de los cuadros de texto de la
	 * pantalla. Si es la tecla enter se llama al método que gestiona la entrada
	 * 
	 * 
	 * @param event es el evento al pulsar la tecla
	 */
	@FXML
	public void handleEnterPressed(KeyEvent event) {
		System.out.println(event.getCode());
		if (event.getCode() ==KeyCode.ENTER) {
		
			System.out.println("Es enter");
			metodoEntrar();
		} else {
			System.out.println("No es enter");
		}
	}

	/**
	 * 
	 * Este método recupera los datos de los cuadros de texto.
	 * 
	 * TODO Validar los datos de acceso.
	 * 
	 */
	public void metodoEntrar() {
		// Instanciar el usuario
		String txtUsuario = tf_usuario.getText();
		String txtPassword = txt_password.getText();

		modelos.Usuario usuario = new modelos.Usuario(txtUsuario, txtPassword);

		// Mensaje de bienvendida
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Auditorias");
		alert.setHeaderText("Estas entrando sin validar ningún dato");
		alert.setContentText("Usuario: " + txtUsuario + " - Password: " + txtPassword);

		alert.showAndWait();

		// Abrir el menú principal
		try {
			// Instancio la Stage de la que vengo
			Stage primaryStage = (Stage) btn_entrar.getScene().getWindow();
			// Cargo la nueva scene
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
			Parent root;

			root = (Parent) fxmlLoader.load();

			Scene scene = new Scene(root);
			// scene.getStylesheets().add(PlayListController.class.getClass().getResource(cssFile
			// + ".css").toExternalForm());
			primaryStage.setScene(scene);
		} catch (IOException e) {

			e.printStackTrace();

			Alert alert2 = new Alert(AlertType.INFORMATION);
			alert2.setTitle("Error");
			alert2.setHeaderText("Lo siento, ha habido un error al cargar la nueva ventana.");
			alert2.setContentText("Si este error continúa contacta con el administrador.");

			alert2.showAndWait();
		}
	}
}
