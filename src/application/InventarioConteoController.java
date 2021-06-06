package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXToggleButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import modelos.Captura;
import modelos.Producto;
import modelos.Usuario;
import util.ConexionBase;
import util.Validaciones;

// TODO Dar uso al botón de conteo en cantidad

// TODO Buscar un sistema de ubicaciones. Quizás un ArrayList donde se creen las ubicaciones que queremos

public class InventarioConteoController implements Initializable {

	@FXML
	private TextField txt_referencia;

	@FXML
	private TextField txt_cantidad;

	@FXML
	private JFXToggleButton btn_modoCantidad;

	@FXML
	private Label lblUbicacion;

	@FXML
	private Label lblCantidades;

	@FXML
	private Label lbCodigoInterno;

	@FXML
	private Label lbEAN;

	@FXML
	private Label lbDescripcion;

	@FXML
	private Label lbCantidad;

	@FXML
	private Label lbFamilia;

	private static int ubicacion;

	@FXML
	void guardarCaptura(ActionEvent event) {

		// TODO Revisar por qué guarda los códigos de 7 y 8 que no están en la base.

		// Recoger información de la GUI
		String referenciaCapturada = txt_referencia.getText();
		String cantidadCapturada = txt_cantidad.getText();
		boolean modoCantidad = btn_modoCantidad.isSelected();
		String codigoInterno = "";

		try {

			// INSERTAR EN LA BASE DE DATOS
			if (Validaciones.validarLinea(referenciaCapturada, cantidadCapturada)) {
				System.out.println(referenciaCapturada + " - " + cantidadCapturada);
				Captura linea = new Captura(referenciaCapturada, Integer.parseInt(cantidadCapturada), modoCantidad);

				codigoInterno = ConexionBase.getCodigoInterno(referenciaCapturada);

				linea.setCodigoInterno(codigoInterno);
				linea.setUbicacion(ubicacion);

				ConexionBase.insertarCaptura(linea);

				int cantidadesUbicacion = ConexionBase.getQuantityLocation();

				lblCantidades.setText(cantidadesUbicacion + "");

			} else {
				// Mensaje
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Error en la captura");
				alert.setHeaderText("Hay un problema con los datos que has introducido.");
				alert.setContentText("Alguno de los datos introducidos no es válido.");

				alert.showAndWait();
			}

		} catch (SQLException e) {
			// Mensaje
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error en la captura");
			alert.setHeaderText("Hay un problema con los datos que has introducido.");
			alert.setContentText("Alguno de los datos introducidos no es válido.");

			alert.showAndWait();

			e.printStackTrace();
		}

		// Recuperar última captura

		try {
			Producto ultimaCaptura = ConexionBase.recuperarUltimaCaptura();

			lbCodigoInterno.setText(ultimaCaptura.getCodigoInterno());
			lbEAN.setText(ultimaCaptura.getCodigoEAN());
			lbDescripcion.setText(ultimaCaptura.getDescripcion());
			lbCantidad.setText(ultimaCaptura.getCantidadCapturada() + "");
			String familia = ultimaCaptura.getSeccion() + " - " + ultimaCaptura.getSubseccion() + " - "
					+ ultimaCaptura.getTipo() + " - " + ultimaCaptura.getSubtipo();
			lbFamilia.setText(familia);

		} catch (SQLException e) {

			// Mensaje
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error en la captura");
			alert.setHeaderText("Hay un problema al guardar la captura");

			alert.showAndWait();

		}

		// Borrar los datos de los textFields
		txt_referencia.setText("");
		txt_cantidad.setText("");

	}

	// TODO Evento de guardar al pulsar enter

	@FXML
	void pasarAUbicacionAnterior(ActionEvent event) {

		if (ubicacion > 1) {
			try {
				ubicacion--;
				lblUbicacion.setText(ubicacion + "");
				int cantidadesUbicacion;
				cantidadesUbicacion = ConexionBase.getQuantityLocation();
				lblCantidades.setText(cantidadesUbicacion + "");
			} catch (SQLException e) {
				
				e.printStackTrace();
				
				// Mensaje
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Error en la captura");
				alert.setHeaderText("Hay un problema al cambiar de ubicación.");

				alert.showAndWait();
			}
		}

	}

	@FXML
	void pasarAUbicacionSiguiente(ActionEvent event) {

		try {
			ubicacion++;
			lblUbicacion.setText(ubicacion + "");
			int cantidadesUbicacion = ConexionBase.getQuantityLocation();
			lblCantidades.setText(cantidadesUbicacion + "");
		} catch (SQLException e) {
			// Mensaje
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error en la captura");
			alert.setHeaderText("Hay un problema al cambiar de ubicación.");

			alert.showAndWait();
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			int ubicacionrecibida = ConexionBase.getLastLocation();

			if (ubicacionrecibida == 0) {
				ubicacion = 1;
			} else {
				ubicacion = ubicacionrecibida;
			}

			lblUbicacion.setText(ubicacion + "");

			int cantidadesUbicacion = ConexionBase.getQuantityLocation();

			lblCantidades.setText(cantidadesUbicacion + "");

		} catch (SQLException e) {
			// Ya está puesto el mensaje en la ventana principal.
			e.printStackTrace();
		}

	}

	public static int getUbicacion() {
		return ubicacion;
	}
}
