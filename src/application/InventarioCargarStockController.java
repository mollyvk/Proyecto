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
import javafx.stage.Stage;
import modelos.BaseArticulosLinea;
import modelos.StockTeoricoLinea;
import util.ConexionBase;

public class InventarioCargarStockController implements Initializable{

    @FXML
    private TableView table;

    @FXML
    private TableColumn referenciaInternaCol;

    @FXML
    private TableColumn stockCol;

    @FXML
    void borrarStockTeorico(MouseEvent event) {
    	
    	try {
    		list.remove(0,list.size());
			ConexionBase.borrarStockTeorico();
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Auditorias");
			alert.setHeaderText("Error al borrar el stock teórico");
			alert.setContentText("Error al borrar el stock teórico");

			alert.showAndWait();	
			e.printStackTrace();
		}
    	System.out.println(list.size());
    }

    @FXML
    void cargarStockTeorico(MouseEvent event) {

    	cargarStockTeoricoEnList();
    }

    private void cargarStockTeoricoEnList() {
		ArrayList<StockTeoricoLinea> recibido;
		try {
			
			Stage stage = (Stage) table.getScene().getWindow();
			recibido = ConexionBase.getStockTeorico(stage);
			//list = FXCollections.observableArrayList(recibido);
			list.remove(0,list.size());
			for(int i = 0; i < recibido.size(); i++) {
				list.add(recibido.get(i));
				System.out.println(recibido.get(i));
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

	ObservableList<StockTeoricoLinea> list = FXCollections.observableArrayList();
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		
		actualizarStockTeorico();
		
		asignarColumnas();
		
		llenarTabla();
		
	}

	private void actualizarStockTeorico() {
		
		try {
			ArrayList<StockTeoricoLinea> stockTeorico;
			stockTeorico = ConexionBase.actualizarStockTeorico();
			for(int i = 0; i < stockTeorico.size(); i++) {
				list.add(stockTeorico.get(i));
				//System.out.println(stockTeorico.get(i));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Auditorias");
			alert.setHeaderText("Error al actualizar el stock teórico");
			alert.setContentText("Error al actualizar el sotck teórico");

			alert.showAndWait();		
		}

		
	}

	private void llenarTabla() {
		table.setItems(list);
		
	}

	private void asignarColumnas() {
		
		referenciaInternaCol.setCellValueFactory(new PropertyValueFactory<StockTeoricoLinea, String>("referenciaInterna"));
		stockCol.setCellValueFactory(new PropertyValueFactory<StockTeoricoLinea, Integer>("stock"));
		
	}

}
