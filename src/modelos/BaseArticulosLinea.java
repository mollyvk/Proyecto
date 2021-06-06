package modelos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class BaseArticulosLinea {

	private int id;
	private final SimpleStringProperty referenciaInterna;
	private final SimpleStringProperty ean;
	private final SimpleStringProperty descripcion;
	private final SimpleStringProperty seccion;
	private final SimpleStringProperty subseccion;
	private final SimpleStringProperty tipo;
	private final SimpleStringProperty subtipo;


	
	public BaseArticulosLinea(int id, String referenciaInterna, String ean, String descripcion, String seccion,
			String subseccion, String tipo, String subtipo) {

		this.id = id;
		this.referenciaInterna = new SimpleStringProperty(referenciaInterna);
		this.ean = new SimpleStringProperty(ean);
		this.descripcion = new SimpleStringProperty(descripcion);
		this.seccion = new SimpleStringProperty(seccion);
		this.subseccion = new SimpleStringProperty(subseccion);
		this.tipo = new SimpleStringProperty(tipo);
		this.subtipo = new SimpleStringProperty(subtipo);
	}


	public BaseArticulosLinea(String referenciaInterna, String ean, String descripcion, String seccion,
			String subseccion, String tipo, String subtipo) {

		this.referenciaInterna = new SimpleStringProperty(referenciaInterna);
		this.ean = new SimpleStringProperty(ean);
		this.descripcion = new SimpleStringProperty(descripcion);
		this.seccion = new SimpleStringProperty(seccion);
		this.subseccion = new SimpleStringProperty(subseccion);
		this.tipo = new SimpleStringProperty(tipo);
		this.subtipo = new SimpleStringProperty(subtipo);
	}
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getReferenciaInterna() {
		return referenciaInterna.get();
	}

	public void setReferenciaInterna(String referenciaInterna) {
		this.referenciaInterna.set(referenciaInterna);
	}

	public String getEan() {
		return ean.get();
	}

	public void setEan(String ean) {
		this.ean.set(ean);
	}

	public String getDescripcion() {
		return descripcion.get();
	}

	public void setDescripcion(String descripcion) {
		this.descripcion.set(descripcion);
	}

	public String getSeccion() {
		return seccion.get();
	}

	public void setSeccion(String seccion) {
		this.seccion.set(seccion);
	}

	public String getSubseccion() {
		return subseccion.get();
	}

	public void setSubseccion(String subseccion) {
		this.subseccion.set(subseccion);
	}

	public String getTipo() {
		return tipo.get();
	}

	public void setTipo(String tipo) {
		this.tipo.set(tipo);
	}

	public String getSubtipo() {
		return subtipo.get();
	}

	public void setSubtipo(String subtipo) {
		this.subtipo.set(subtipo);
		;
	}

}
