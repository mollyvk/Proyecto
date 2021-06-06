package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelos.BaseArticulosLinea;
import util.ConexionBase;

public class InventarioCargarBaseController implements Initializable{

    @FXML
    private TableView tabla;

    @FXML
    private TableColumn refInternaCol;

    @FXML
    private TableColumn eanCol;

    @FXML
    private TableColumn descrpcionCol;

    @FXML
    private TableColumn sCol;

    @FXML
    private TableColumn ssCol;

    @FXML
    private TableColumn tCol;

    @FXML
    private TableColumn stCol;

    @FXML
    private AnchorPane btnCargar;

    @FXML
    private AnchorPane btnBorrar;
    
    ObservableList<BaseArticulosLinea> list = FXCollections.observableArrayList();
    
    

    @FXML
    void borrarBase(MouseEvent event) {

    	list.remove(0,list.size());
    	try {
			ConexionBase.borrarBaseArticulos();
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Auditorias");
			alert.setHeaderText("Error al borrar la base de datos");
			alert.setContentText("Error al borrar la base de datos");

			alert.showAndWait();	
			e.printStackTrace();
		}
    	System.out.println(list.size());
    }

    @FXML
    void cargarBase(MouseEvent event) {

		cargarBaseArticulosEnList();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		//list.add(new BaseArticulosLinea("test", "test", "test", "test", "test", "test", "test"));
		actualizarBaseArticulos();
		
		asignarColumnas();
		
		llenarTabla();
		
		
	}
	
	private void actualizarBaseArticulos() {
		
		
		try {
			ArrayList<BaseArticulosLinea> baseRecibida;
			baseRecibida = ConexionBase.actualizarBaseArticulos();
			for(int i = 0; i < baseRecibida.size(); i++) {
				list.add(baseRecibida.get(i));
				//System.out.println(baseRecibida.get(i));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Auditorias");
			alert.setHeaderText("Error al actualizar la base de datos");
			alert.setContentText("Error al actualizar la base de datos");

			alert.showAndWait();		
		}

		
	}

	private void llenarTabla() {
		tabla.setItems(list);
		
	}

	private void asignarColumnas() {
		refInternaCol.setCellValueFactory(new PropertyValueFactory<BaseArticulosLinea, String>("referenciaInterna"));
		eanCol.setCellValueFactory(new PropertyValueFactory<BaseArticulosLinea, String>("ean"));
		descrpcionCol.setCellValueFactory(new PropertyValueFactory<BaseArticulosLinea, String>("descripcion"));
		sCol.setCellValueFactory(new PropertyValueFactory<BaseArticulosLinea, String>("seccion"));
		ssCol.setCellValueFactory(new PropertyValueFactory<BaseArticulosLinea, String>("subseccion"));
		tCol.setCellValueFactory(new PropertyValueFactory<BaseArticulosLinea, String>("tipo"));
		stCol.setCellValueFactory(new PropertyValueFactory<BaseArticulosLinea, String>("subtipo"));
		
	}

	private void cargarBaseArticulosEnList() {
		ArrayList<BaseArticulosLinea> recibido;
		try {
			
			Stage stage = (Stage) tabla.getScene().getWindow();
			recibido = ConexionBase.getBaseArticulos(stage);
			//list = FXCollections.observableArrayList(recibido);
			list.remove(0,list.size());
			for(int i = 0; i < recibido.size(); i++) {
				list.add(recibido.get(i));
				//System.out.println(recibido.get(i));
			}
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Auditorias");
			alert.setHeaderText("Error al cargar la base de datos");
			alert.setContentText("Error al cargar la base de datos");

			alert.showAndWait();		
		}

		
	}
	
	

}
