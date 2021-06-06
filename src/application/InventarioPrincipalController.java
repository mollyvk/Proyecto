package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import util.ConexionBase;

public class InventarioPrincipalController implements Initializable{

	@FXML
	private AnchorPane btnConteo;

	@FXML
	private AnchorPane btnUbicaciones;

	@FXML
	private AnchorPane btnCargarBase;

	@FXML
	private AnchorPane btnStockTeorico;

	@FXML
	private AnchorPane btnDiferencias;

	@FXML
	private AnchorPane btnResultado;
	@FXML
	private AnchorPane paneProyector;

	@FXML
	void cambiarVentana(MouseEvent event) {

		String fuenteEvento = event.getSource().toString();

		System.out.println(fuenteEvento);
		Pane newLoadedPane;
		switch (fuenteEvento) {
		case "AnchorPane[id=btnConteo]":

			try {
				newLoadedPane = FXMLLoader.load(getClass().getResource("InventarioConteo.fxml"));
				paneProyector.getChildren().add(newLoadedPane);
			} catch (IOException e) {
				
				e.printStackTrace();
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Error");
				alert.setHeaderText("Lo siento, ha habido un error al cargar la nueva ventana.");
				alert.setContentText("Si este error continúa contacta con el administrador.");

				alert.showAndWait();
			}

			break;

		case "AnchorPane[id=btnUbicaciones]":

			try {
				newLoadedPane = FXMLLoader.load(getClass().getResource("InventarioUbicaciones.fxml"));
				paneProyector.getChildren().add(newLoadedPane);
			} catch (IOException e) {
				
				e.printStackTrace();
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Error");
				alert.setHeaderText("Lo siento, ha habido un error al cargar la nueva ventana.");
				alert.setContentText("Si este error continúa contacta con el administrador.");

				alert.showAndWait();
			}

			break;
		case "AnchorPane[id=btnCargarBase]":

			try {
				newLoadedPane = FXMLLoader.load(getClass().getResource("InventarioCargarBase.fxml"));
				paneProyector.getChildren().add(newLoadedPane);
			} catch (IOException e) {
				
				e.printStackTrace();
				
				Alert alert2 = new Alert(AlertType.INFORMATION);
				alert2.setTitle("Error");
				alert2.setHeaderText("Lo siento, ha habido un error al cargar la nueva ventana.");
				alert2.setContentText("Si este error continúa contacta con el administrador.");

				alert2.showAndWait();
			}

			break;

		case "AnchorPane[id=btnStockTeorico]":

			try {
				newLoadedPane = FXMLLoader.load(getClass().getResource("InventarioCargarStock.fxml"));
				paneProyector.getChildren().add(newLoadedPane);
			} catch (IOException e) {
				
				e.printStackTrace();
				
				Alert alert4 = new Alert(AlertType.INFORMATION);
				alert4.setTitle("Error");
				alert4.setHeaderText("Lo siento, ha habido un error al recuperar los datos.");
				alert4.setContentText("Si este error continúa contacta con el administrador.");

				alert4.showAndWait();
			}

			break;

		case "AnchorPane[id=btnDiferencias]":

			try {
				newLoadedPane = FXMLLoader.load(getClass().getResource("InventarioDiferencias.fxml"));
				paneProyector.getChildren().add(newLoadedPane);
			} catch (IOException e) {
				Alert alert5 = new Alert(AlertType.INFORMATION);
				alert5.setTitle("Error");
				alert5.setHeaderText("Lo siento, ha habido un error al vargar la nueva ventana.");
				alert5.setContentText("Si este error continúa contacta con el administrador.");

				alert5.showAndWait();
				e.printStackTrace();
			}

			break;

		case "AnchorPane[id=btnResultado]":

			try {
				newLoadedPane = FXMLLoader.load(getClass().getResource("InventarioResultado.fxml"));
				paneProyector.getChildren().add(newLoadedPane);
			} catch (IOException e) {
				
				e.printStackTrace();
				
				Alert alert3 = new Alert(AlertType.INFORMATION);
				alert3.setTitle("Error");
				alert3.setHeaderText("Lo siento, ha habido un error al cargar la nueva ventana.");
				alert3.setContentText("Si este error continúa contacta con el administrador.");

				alert3.showAndWait();
			}

			break;
		default:
			break;
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ConexionBase.connect();
		
	}

}
