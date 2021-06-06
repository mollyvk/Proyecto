package modelos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class StockTeoricoLinea {

	private int id;
	private SimpleStringProperty referenciaInterna;
	private SimpleIntegerProperty stock;
	
	public StockTeoricoLinea(String referenciaInterna, int stock) {
		this.referenciaInterna = new SimpleStringProperty(referenciaInterna);
		this.stock = new SimpleIntegerProperty(stock);
	}
	
	public String getReferenciaInterna() {
		return this.referenciaInterna.get();
	}
	
	public void setReferenciaInterna(String referenciaInterna) {
		this.referenciaInterna.set(referenciaInterna);
	}
	
	public int getStock() {
		return this.stock.get();
	}
	
	public void setStock(int stock) {
		this.stock.set(stock);
	}
}
