package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private AnchorPane btn_abrir_inventario;

    @FXML
    private AnchorPane btn_gestionarArchivos;

    @FXML
    void abrirInventario(MouseEvent event) {
    	try {
			// Instancio la Stage de la que vengo
			Stage primaryStage = (Stage) btn_abrir_inventario.getScene().getWindow();
			// Cargo la nueva scene
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InventarioPrincipal.fxml"));
			Parent root;

			root = (Parent) fxmlLoader.load();

			Scene scene = new Scene(root);
			// scene.getStylesheets().add(PlayListController.class.getClass().getResource(cssFile
			// + ".css").toExternalForm());
			primaryStage.setScene(scene);
		} catch (IOException e) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error");
			alert.setHeaderText("Lo siento, ha habido un error al entrar a la auditoría.");
			alert.setContentText("Si este error continúa contacta con el administrador.");

			alert.showAndWait();
			e.printStackTrace();
		}
    }

    @FXML
    void gestionarArchivos(MouseEvent event) {

    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Error");
		alert.setHeaderText("Lo siento, esta función aún no está habilitada.");
		alert.setContentText("Si este error continúa contacta con el administrador.");

		alert.showAndWait();
		
    	System.out.println(event.getSource());
    }

}

