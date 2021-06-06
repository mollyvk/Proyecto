package modelos;

public class Producto {

	private String codigoInterno;
	private String codigoEAN;
	private String descripcion;
	private String seccion;
	private String subseccion;
	private String tipo;
	private String subtipo;
	private Double precio;
	private String referenciaCapturada;
	private int cantidadCapturada;

	/**
	 * @return the codigoInterno
	 */
	public String getCodigoInterno() {
		return codigoInterno;
	}
	/**
	 * @param codigoInterno the codigoInterno to set
	 */
	public void setCodigoInterno(String codigoInterno) {
		this.codigoInterno = codigoInterno;
	}
	/**
	 * @return the referenciaCapturada
	 */
	public String getReferenciaCapturada() {
		return referenciaCapturada;
	}
	/**
	 * @param referenciaCapturada the referenciaCapturada to set
	 */
	public void setReferenciaCapturada(String referenciaCapturada) {
		this.referenciaCapturada = referenciaCapturada;
	}
	/**
	 * @return the cantidadCapturada
	 */
	public int getCantidadCapturada() {
		return cantidadCapturada;
	}
	/**
	 * @param cantidadCapturada the cantidadCapturada to set
	 */
	public void setCantidadCapturada(int cantidadCapturada) {
		this.cantidadCapturada = cantidadCapturada;
	}
	public Producto () {
		
	}
	public Producto(String codigoInterno, String codigoEAN, String descripcion, String seccion, String subseccion,
			String tipo, String subtipo, Double precio) {
		super();
		this.codigoInterno = codigoInterno;
		this.codigoEAN = codigoEAN;
		this.descripcion = descripcion;
		this.seccion = seccion;
		this.subseccion = subseccion;
		this.tipo = tipo;
		this.subtipo = subtipo;
		this.precio = precio;
	}


	/**
	 * @return the codigoEAN
	 */
	public String getCodigoEAN() {
		return codigoEAN;
	}

	/**
	 * @param codigoEAN the codigoEAN to set
	 */
	public void setCodigoEAN(String codigoEAN) {
		this.codigoEAN = codigoEAN;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the seccion
	 */
	public String getSeccion() {
		return seccion;
	}

	/**
	 * @param seccion the seccion to set
	 */
	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	/**
	 * @return the subseccion
	 */
	public String getSubseccion() {
		return subseccion;
	}

	/**
	 * @param subseccion the subseccion to set
	 */
	public void setSubseccion(String subseccion) {
		this.subseccion = subseccion;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the subtipo
	 */
	public String getSubtipo() {
		return subtipo;
	}

	/**
	 * @param subtipo the subtipo to set
	 */
	public void setSubtipo(String subtipo) {
		this.subtipo = subtipo;
	}

	/**
	 * @return the precio
	 */
	public Double getPrecio() {
		return precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	@Override
	public String toString() {
		return "Producto [codigoInterno=" + codigoInterno + ", codigoEAN=" + codigoEAN + ", descripcion=" + descripcion
				+ ", seccion=" + seccion + ", subseccion=" + subseccion + ", tipo=" + tipo + ", subtipo=" + subtipo
				+ ", precio=" + precio + ", referenciaCapturada=" + referenciaCapturada + ", cantidadCapturada="
				+ cantidadCapturada + "]";
	}

}
