package modelos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DiferenciasLinea {

	private SimpleIntegerProperty id = new SimpleIntegerProperty();
	private SimpleStringProperty referencia = new SimpleStringProperty();
	private SimpleStringProperty descripcion = new SimpleStringProperty();
	private SimpleIntegerProperty cantidadContada = new SimpleIntegerProperty();
	private SimpleIntegerProperty cantidadTeorica = new SimpleIntegerProperty();
	private SimpleIntegerProperty cantidadDiferencia = new SimpleIntegerProperty();

	public DiferenciasLinea(String referencia, String descripcion, int cantidadContada, int cantidadTeorica,
			int cantidadDiferencia) {

		this.referencia = new SimpleStringProperty(referencia);
		this.descripcion = new SimpleStringProperty(descripcion);
		this.cantidadContada = new SimpleIntegerProperty(cantidadContada);
		this.cantidadTeorica = new SimpleIntegerProperty(cantidadTeorica);
		this.cantidadDiferencia = new SimpleIntegerProperty(cantidadDiferencia);
		
	}

	public SimpleIntegerProperty idProperty() {
		return this.id;
	}
	

	public int getId() {
		return this.idProperty().get();
	}
	

	public void setId(int id) {
		this.idProperty().set(id);
	}
	

	public SimpleStringProperty referenciaProperty() {
		return this.referencia;
	}
	

	public String getReferencia() {
		return this.referenciaProperty().get();
	}
	

	public void setReferencia(String referencia) {
		this.referenciaProperty().set(referencia);
	}
	

	public SimpleStringProperty descripcionProperty() {
		return this.descripcion;
	}
	

	public String getDescripcion() {
		return this.descripcionProperty().get();
	}
	

	public void setDescripcion(String descripcion) {
		this.descripcionProperty().set(descripcion);
	}
	

	public SimpleIntegerProperty cantidadContadaProperty() {
		return this.cantidadContada;
	}
	

	public int getCantidadContada() {
		return this.cantidadContadaProperty().get();
	}
	

	public void setCantidadContada(int cantidadContada) {
		this.cantidadContadaProperty().set(cantidadContada);
	}
	

	public SimpleIntegerProperty cantidadTeoricaProperty() {
		return this.cantidadTeorica;
	}
	

	public int getCantidadTeorica() {
		return this.cantidadTeoricaProperty().get();
	}
	

	public void setCantidadTeorica(int cantidadTeorica) {
		this.cantidadTeoricaProperty().set(cantidadTeorica);
	}
	

	public SimpleIntegerProperty cantidadDiferenciaProperty() {
		return this.cantidadDiferencia;
	}
	

	public int getCantidadDiferencia() {
		return this.cantidadDiferenciaProperty().get();
	}
	

	public void setCantidadDiferencia(int cantidadDiferencia) {
		this.cantidadDiferenciaProperty().set(cantidadDiferencia);
	}

	@Override
	public String toString() {
		return "DiferenciasLinea [id=" + id + ", referencia=" + referencia + ", descripcion=" + descripcion
				+ ", cantidadContada=" + cantidadContada + ", cantidadTeorica=" + cantidadTeorica
				+ ", cantidadDiferencia=" + cantidadDiferencia + "]";
	}
	
	
	
	
}
