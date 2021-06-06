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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import modelos.BaseArticulosLinea;
import modelos.Captura;
import util.ConexionBase;

public class InventarioUbicacionesController implements Initializable {

	@FXML
	private TextField txt_referencia;

	@FXML
	private TextField txt_cantidad;

	@FXML
	private Label txtUbicacion;

	@FXML
	private TableView<Captura> tabla;

	@FXML
	private TableColumn<Captura, String> referenciaCol;

	@FXML
	private TableColumn<Captura, String> eanCol;

	@FXML
	private TableColumn<Captura, String> descripcionCol;

	@FXML
	private TableColumn<Captura, String> cantidadCol;

	ObservableList<Captura> list = FXCollections.observableArrayList();

	private Captura lineaSeleccionada;
	
	@FXML
	void eliminarCaptura(ActionEvent event) {

		if(lineaSeleccionada!=null) {
			
			
			try {
				int idSeleccionado = lineaSeleccionada.getId();
				ConexionBase.eliminarLineaCaptura(idSeleccionado);
				int ubicacion = Integer.parseInt(txtUbicacion.getText());
				actualizarCapturas(ubicacion);
			} catch (SQLException e) {
				
				e.printStackTrace();
				
				e.printStackTrace();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Auditorias");
				alert.setHeaderText("Error al eliminar la referencia.");
				alert.setContentText("Error al eliminar la referencia.");

				alert.showAndWait();
			}
			
		}
		
	}

	@FXML
	void guardarCaptura(ActionEvent event) {

		if(lineaSeleccionada!=null) {
			
			
			try {
				int idSeleccionado = lineaSeleccionada.getId();
				String nuevoCodigo = txt_referencia.getText();
				int nuevaCantidad = Integer.parseInt(txt_cantidad.getText()+"");
				String nuevoCodigoInterno = ConexionBase.getCodigoInterno(nuevoCodigo);
				ConexionBase.actualizarLineaCaptura(idSeleccionado, nuevoCodigo, nuevaCantidad, nuevoCodigoInterno);
				int ubicacion = Integer.parseInt(txtUbicacion.getText());
				actualizarCapturas(ubicacion);
			} catch (SQLException e) {
				
				e.printStackTrace();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Auditorias");
				alert.setHeaderText("Error al eliminar la referencia.");
				alert.setContentText("Error al eliminar la referencia.");

				alert.showAndWait();
			} catch (Exception e) {
				e.printStackTrace();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Auditorias");
				alert.setHeaderText("Error al modificar la referencia.");
				alert.setContentText("Error al modificar la referencia.");

				alert.showAndWait();

			}
			
		}
	}
	
    @FXML
    void mostrarInformacion(MouseEvent event) {

    	System.out.println(event.getSource());
    	lineaSeleccionada = tabla.getSelectionModel().getSelectedItem();
    	String referenciaSelecionada = lineaSeleccionada.getCodigoInterno();
    	String cantidadSeleccionada = lineaSeleccionada.getCantidadCapturada()+"";
    	int idSeleccionado = lineaSeleccionada.getId();
    	System.out.println(idSeleccionado);
    	
    	txt_referencia.setText(referenciaSelecionada);
    	txt_cantidad.setText(cantidadSeleccionada);
    }

	@FXML
	void pasarUbicacionAnterior(ActionEvent event) {

		try {
			int ubicacion = Integer.parseInt(txtUbicacion.getText());
			if (ubicacion > 1) {

				ubicacion--;
				txtUbicacion.setText(ubicacion + "");
				actualizarCapturas(ubicacion);
			}

		} catch (SQLException e) {

			e.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Auditorias");
			alert.setHeaderText("Error al cambiar de ubicación");
			alert.setContentText("Error al cargar la ubicacion");

			alert.showAndWait();
		}
	}

	@FXML
	void pasarUbicacionSiguiente(ActionEvent event) {

		try {
			int ubicacion = Integer.parseInt(txtUbicacion.getText());
			ubicacion++;
			txtUbicacion.setText(ubicacion + "");
			actualizarCapturas(ubicacion);
		} catch (SQLException e) {

			e.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Auditorias");
			alert.setHeaderText("Error al cambiar de ubicación");
			alert.setContentText("Error al cargar la ubicacion");

			alert.showAndWait();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {

			actualizarCapturas(ConexionBase.getLastLocation());

			asignarColumnas();

			llenarTabla();

			txtUbicacion.setText(ConexionBase.getLastLocation() + "");

		} catch (SQLException e) {

			e.printStackTrace();

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Auditorias");
			alert.setHeaderText("Error al cargar la ubicacion");
			alert.setContentText("Error al cargar la ubicacion");

			alert.showAndWait();
		}

	}

	private void llenarTabla() {
		tabla.setItems(list);

	}

	private void asignarColumnas() {
		referenciaCol.setCellValueFactory(new PropertyValueFactory<Captura, String>("codigoInterno"));
		eanCol.setCellValueFactory(new PropertyValueFactory<Captura, String>("codigoEan"));
		descripcionCol.setCellValueFactory(new PropertyValueFactory<Captura, String>("descripcion"));
		cantidadCol.setCellValueFactory(new PropertyValueFactory<Captura, String>("cantidadCapturada"));

	}

	private void actualizarCapturas(int ubicacion) throws SQLException {

		ArrayList<Captura> capturasUbicacion = ConexionBase.getCapturasUbicacion(ubicacion);

		list.remove(0, list.size());
		for (int i = 0; i < capturasUbicacion.size(); i++) {
			list.add(capturasUbicacion.get(i));
			// System.out.println("CONTROLADOR VISTA");
			// System.out.println(capturasUbicacion);
		}

	}

}
