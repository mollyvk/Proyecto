package modelos;

import java.text.SimpleDateFormat;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Captura {

	private SimpleIntegerProperty id = new SimpleIntegerProperty();
	private SimpleIntegerProperty inventarioId = new SimpleIntegerProperty();
	private SimpleStringProperty referenciaCapturada = new SimpleStringProperty();
	private SimpleIntegerProperty cantidadCapturada = new SimpleIntegerProperty();
	private SimpleBooleanProperty capturaCantidad = new SimpleBooleanProperty();
	private SimpleStringProperty usuario = new SimpleStringProperty();
	private SimpleStringProperty ubicacion = new SimpleStringProperty();
	private SimpleDateFormat tiempo = new SimpleDateFormat();
	private SimpleStringProperty codigoInterno = new SimpleStringProperty();
	private SimpleStringProperty codigoEan = new SimpleStringProperty();
	private SimpleStringProperty descripcion = new SimpleStringProperty();

	public Captura() {
		
	}
	
	public Captura(String referencia, int cantidad, boolean modoCantidad) {

		// El id lo crea automáticamente la base de datos.
		// De momento no tenemos varios inventarios
		// inventarioId = new SimpleIntegerProperty();
		referenciaCapturada = new SimpleStringProperty(referencia);
		cantidadCapturada = new SimpleIntegerProperty(cantidad);
		usuario = new SimpleStringProperty(Usuario.getUsuario());
		capturaCantidad = new SimpleBooleanProperty(modoCantidad);
		//ubicacion = new SimpleStringProperty(ubicacion);
		// TODO grabar la hora actual

	}
	
	public Captura(String referencia, int cantidad, boolean modoCantidad, String ubicacion) {

		// El id lo crea automáticamente la base de datos.
		// De momento no tenemos varios inventarios
		// inventarioId = new SimpleIntegerProperty();
		referenciaCapturada = new SimpleStringProperty(referencia);
		cantidadCapturada = new SimpleIntegerProperty(cantidad);
		usuario = new SimpleStringProperty(Usuario.getUsuario());
		capturaCantidad = new SimpleBooleanProperty(modoCantidad);
		this.ubicacion = new SimpleStringProperty(ubicacion);
		

	}
	
	public Captura(String referencia, String ean, String descripcion, int cantidad) {

		codigoInterno = new SimpleStringProperty(referencia);
		codigoEan = new SimpleStringProperty(ean);
		this.descripcion = new SimpleStringProperty(descripcion);
		cantidadCapturada = new SimpleIntegerProperty(cantidad);
		
		
		
		

	}

	public Captura(int id2, String codigoInterno2, String codigoEan2, String descripcion2, int cantidad) {
		id = new SimpleIntegerProperty(id2);
		codigoInterno = new SimpleStringProperty(codigoInterno2);
		codigoEan = new SimpleStringProperty(codigoEan2);
		this.descripcion = new SimpleStringProperty(descripcion2);
		cantidadCapturada = new SimpleIntegerProperty(cantidad);
	}

	public String getReferenciaCapturada() {
		return referenciaCapturada.get();
	}

	public int getCantidadCapturada() {
		return cantidadCapturada.get();
	}

	public String getUsuario() {
		return usuario.get();
	}

	public boolean getCapturaCantidad() {
		return capturaCantidad.get();
	}

	public void setReferenciaCapturada(String referencia) {
		this.referenciaCapturada.set(referencia);
	}

	public void setCantidadCapturada(int cantidad) {
		this.cantidadCapturada.set(cantidad);
	}

	public void setUsuario(String usuario) {
		this.usuario.set(usuario);
	}

	public final SimpleStringProperty codigoInternoProperty() {
		return this.codigoInterno;
	}
	

	public String getCodigoInterno() {
		return this.codigoInternoProperty().get();
	}
	

	public void setCodigoInterno(String codigoInterno) {
		this.codigoInternoProperty().set(codigoInterno);
	}
	
	public void setUbicacion(int ubicacion) {
		this.ubicacion.set(ubicacion+"");
	}
	
	public int getUbicacion() {
		return Integer.parseInt(this.ubicacion.get()); 
	}
	
	public String getCodigoEan() {
		return this.codigoEan.get();
	}
	

	public void setCodigoEan(String codigoEan) {
		this.codigoEan.set(codigoEan);
	}
	
	public String getDescripcion() {
		return this.descripcion.get();
	}
	

	public void setDescripcion(String descripcion) {
		this.descripcion.set(descripcion);
	}

	@Override
	public String toString() {
		return "Captura [id=" + id + ", inventarioId=" + inventarioId + ", referenciaCapturada=" + referenciaCapturada
				+ ", cantidadCapturada=" + cantidadCapturada + ", capturaCantidad=" + capturaCantidad + ", usuario="
				+ usuario + ", ubicacion=" + ubicacion + ", tiempo=" + tiempo + ", codigoInterno=" + codigoInterno
				+ ", codigoEan=" + codigoEan + ", descripcion=" + descripcion + "]";
	}

	public SimpleIntegerProperty idProperty() {
		return this.id;
	}
	

	public int getId() {
		return this.idProperty().get();
	}
	

	public void setId(final int id) {
		this.idProperty().set(id);
	}
	
	
	
}
