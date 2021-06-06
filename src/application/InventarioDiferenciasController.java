package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import modelos.BaseArticulosLinea;
import modelos.DiferenciasLinea;
import util.ConexionBase;

// TODO Cambiar Exportar diferencias por exportar resultado

// TODO Crear una clase util para grabar en disco

public class InventarioDiferenciasController implements Initializable {

	@FXML
	private TableView<DiferenciasLinea> tabla;

	@FXML
	private TableColumn<DiferenciasLinea, String> referenciaCol;

	@FXML
	private TableColumn descripcionCol;

	@FXML
	private TableColumn contadoCol;

	@FXML
	private TableColumn teoricoCol;

	@FXML
	private TableColumn diferenciaCol;

	ObservableList<DiferenciasLinea> list = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		actualizarDiferencias();
	
		asignarColumnas();
	
		llenarTabla();
	
	}

	private void actualizarDiferencias() {
		// list.add(new DiferenciasLinea("test", "test", 0, 0, 0));
	
		System.out.println("Calculando en diferencias");
	
		ArrayList<DiferenciasLinea> diferencias = new ArrayList<DiferenciasLinea>();
	
		try {
			// Crea la tabla de diferencias.
			diferencias = ConexionBase.cargarDiferencias();
	
			// TODO Posibilidad de modificar las referencias con diferencias
	
		} catch (SQLException e) {
	
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error");
			alert.setHeaderText("Lo siento, ha habido un error al recuperar los datos.");
			alert.setContentText("Si este error continúa contacta con el administrador.");
	
			alert.showAndWait();
	
			e.printStackTrace();
		}
	
		list.remove(0, list.size());
		for (int i = 0; i < diferencias.size(); i++) {
			list.add(diferencias.get(i));
		}
	}

	private void asignarColumnas() {
		referenciaCol.setCellValueFactory(new PropertyValueFactory<DiferenciasLinea, String>("referencia"));
		descripcionCol.setCellValueFactory(new PropertyValueFactory<DiferenciasLinea, String>("descripcion"));
		contadoCol.setCellValueFactory(new PropertyValueFactory<DiferenciasLinea, String>("cantidadContada"));
		teoricoCol.setCellValueFactory(new PropertyValueFactory<DiferenciasLinea, String>("cantidadTeorica"));
		diferenciaCol.setCellValueFactory(new PropertyValueFactory<DiferenciasLinea, String>("cantidadDiferencia"));
	
	}

	private void llenarTabla() {
	
		tabla.setItems(list);
	
	}

	@FXML
	void calcularDiferencias(MouseEvent event) {
		ArrayList<DiferenciasLinea> diferencias = new ArrayList<DiferenciasLinea>();

		try {
			// Crea la tabla de diferencias.
			diferencias = ConexionBase.calcularContadoConsolidado();

			// TODO Posibilidad de modificar las referencias con diferencias
			list.remove(0, list.size());
			for (int i = 0; i < diferencias.size(); i++) {
				list.add(diferencias.get(i));
			}

		} catch (SQLException e) {

			e.printStackTrace();

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error");
			alert.setHeaderText("Lo siento, ha habido un error al calcular las diferencias.");
			alert.setContentText("Si este error continúa contacta con el administrador.");

			alert.showAndWait();

		}

	}

	@FXML
	void exportarDiferencias(MouseEvent event) {
		// TODO Exportar fichero de diferencias

	}
	


}
