package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import application.InventarioConteoController;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modelos.BaseArticulosLinea;
import modelos.Captura;
import modelos.DiferenciasLinea;
import modelos.Producto;
import modelos.StockTeoricoLinea;

public class ConexionBase {
	private static String url = "db/inventario.db";
	private static Connection connector;

	public static void connect() {
		try {
			connector = DriverManager.getConnection("jdbc:sqlite:" + url);
			if (connector != null) {
				System.out.println("Conectado");
			}
		} catch (Exception e) {
			System.err.println("No se ha podido conectar" + e.getMessage());
		}
	}

	public static void close() {
		try {
			connector.close();
			System.out.println("Desconectado");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void insertarCaptura(Captura linea) throws SQLException {
		String referencia = linea.getReferenciaCapturada();
		int cantidad = linea.getCantidadCapturada();
		boolean modoCaptura = linea.getCapturaCantidad();
		String usuario = linea.getUsuario();
		String codigoInterno = linea.getCodigoInterno();
		int ubicacion = linea.getUbicacion();

		Statement st = connector.createStatement();
		st.executeUpdate(
				"INSERT INTO capturas ('referencia_capturada','cantidad_capturada','captura_cantidad','usuario', 'codigoInterno','ubicacion') "
						+ "VALUES ('" + referencia + "', " + cantidad + ", " + modoCaptura + ", '" + usuario + "', '"
						+ codigoInterno + "', '" + ubicacion + "');");

	}

	public static Producto recuperarUltimaCaptura() throws SQLException {

		Statement sentencia = connector.createStatement();

		ResultSet resultset = sentencia
				.executeQuery("SELECT * FROM capturas WHERE id = (SELECT MAX(id) FROM capturas)");

		Producto productoCapturado = new Producto();

		String codigoCapturado = null;

		while (resultset.next()) {
			codigoCapturado = resultset.getString(3);
			int cantidad = resultset.getInt(4);

			productoCapturado.setReferenciaCapturada(codigoCapturado);
			productoCapturado.setCantidadCapturada(cantidad);
			System.out.println(resultset.isClosed());
		}
		System.out.println(resultset.isClosed());

		if (codigoCapturado.length() == 7 || codigoCapturado.length() == 8) {
			resultset = sentencia.executeQuery("SELECT * FROM baseProductos WHERE codigoInterno = " + codigoCapturado);
		} else {
			resultset = sentencia.executeQuery("SELECT * FROM baseProductos WHERE codigoEAN = " + codigoCapturado);
		}

		productoCapturado.setCodigoInterno(resultset.getString(2));
		productoCapturado.setCodigoEAN(resultset.getString(3));
		productoCapturado.setDescripcion(resultset.getString(4));
		productoCapturado.setSeccion(resultset.getString(5));
		productoCapturado.setSubseccion(resultset.getString(6));
		productoCapturado.setTipo(resultset.getString(7));
		productoCapturado.setSubtipo(resultset.getString(8));
		// productoCapturado.setPrecio(resultset.getDouble(9));
		productoCapturado.setPrecio(0.00);

		return productoCapturado;

	}

	public static ArrayList<BaseArticulosLinea> getBaseArticulos(Stage stage) throws SQLException, IOException {

		ArrayList<BaseArticulosLinea> list = new ArrayList<BaseArticulosLinea>();
		// Pedir fichero
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Abrir archivo de recursos");
		File file = fileChooser.showOpenDialog(stage);

		// Leer fichero
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String lineaLeida = "";

		while (lineaLeida != null) {
			lineaLeida = bufferedReader.readLine();

			if (lineaLeida != null) {
				// Guardar cada linea como un objeto en ArrayList
				String referenciaInterna;
				String ean;
				String descripcion;
				String seccion;
				String subseccion;
				String tipo;
				String subtipo;
				String[] lineaArray = lineaLeida.split(";");
				referenciaInterna = lineaArray[0];
				ean = lineaArray[1];
				descripcion = lineaArray[2];
				seccion = lineaArray[3];
				subseccion = lineaArray[4];
				tipo = lineaArray[5];
				subtipo = lineaArray[6];

				BaseArticulosLinea lineaParaGuardar = new BaseArticulosLinea(referenciaInterna, ean, descripcion,
						seccion, subseccion, tipo, subtipo);

				list.add(lineaParaGuardar);

				// System.out.println(lineaParaGuardar.getReferenciaInterna());

				// Guardar en base de datos

				Statement statement = connector.createStatement();
				statement.executeUpdate(
						"INSERT INTO baseProductos ('codigoInterno', 'codigoEAN', 'descripcion', 'seccion', 'subseccion', 'tipo', 'subtipo') VALUES ('"
								+ referenciaInterna + "', '" + ean + "', '" + descripcion + "', '" + seccion + "', '"
								+ subseccion + "', '" + tipo + "', '" + subtipo + "')");

			}
		}

		// System.out.println(list.size());
		return list;
	}

	public static void borrarBaseArticulos() throws SQLException {
		Statement st = connector.createStatement();
		st.executeUpdate("DELETE FROM baseProductos");

	}

	public static ArrayList<BaseArticulosLinea> actualizarBaseArticulos() throws SQLException {

		ArrayList<BaseArticulosLinea> base = new ArrayList<BaseArticulosLinea>();

		Statement statement = connector.createStatement();

		ResultSet resultset = statement.executeQuery("SELECT * FROM baseProductos");

		while (resultset.next()) {

			int id;
			String referenciaInterna;
			String ean;
			String descripcion;
			String seccion;
			String subseccion;
			String tipo;
			String subtipo;
			id = resultset.getInt(1);
			referenciaInterna = resultset.getString(2);
			ean = resultset.getString(3);
			descripcion = resultset.getString(4);
			seccion = resultset.getString(5);
			subseccion = resultset.getString(6);
			tipo = resultset.getString(7);
			subtipo = resultset.getString(8);

			BaseArticulosLinea lineaRecibida = new BaseArticulosLinea(id, referenciaInterna, ean, descripcion, seccion,
					subseccion, tipo, subtipo);

			System.out.println(lineaRecibida);

			base.add(lineaRecibida);

		}
		System.out.println(base.size());
		return base;

	}

	public static ArrayList<StockTeoricoLinea> actualizarStockTeorico() throws SQLException {

		ArrayList<StockTeoricoLinea> stockTeorico = new ArrayList<StockTeoricoLinea>();

		Statement statement = connector.createStatement();

		ResultSet resultset = statement.executeQuery("SELECT * FROM stock_teorico");

		while (resultset.next()) {
			String referenciaInterna = resultset.getString(1);
			int stock = resultset.getInt(2);

			StockTeoricoLinea lineaStock = new StockTeoricoLinea(referenciaInterna, stock);

			stockTeorico.add(lineaStock);

		}

		return stockTeorico;
	}

	public static void borrarStockTeorico() throws SQLException {
		Statement st = connector.createStatement();
		st.executeUpdate("DELETE FROM stock_teorico");

	}

	public static ArrayList<StockTeoricoLinea> getStockTeorico(Stage stage) throws SQLException, IOException {
		ArrayList<StockTeoricoLinea> list = new ArrayList<StockTeoricoLinea>();
		// Pedir fichero
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Abrir archivo de recursos");
		File file = fileChooser.showOpenDialog(stage);

		// Leer fichero
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String lineaLeida = "";

		while (lineaLeida != null) {
			lineaLeida = bufferedReader.readLine();

			if (lineaLeida != null) {
				// Guardar cada linea como un objeto en ArrayList

				String[] lineaArray = lineaLeida.split(";");

				String referenciaInterna = lineaArray[0];
				int stock = Integer.parseInt(lineaArray[1]);

				StockTeoricoLinea lineaParaGuardar = new StockTeoricoLinea(referenciaInterna, stock);

				list.add(lineaParaGuardar);

				// System.out.println(lineaParaGuardar.getReferenciaInterna());

				// Guardar en base de datos

				Statement statement = connector.createStatement();
				statement.executeUpdate("INSERT INTO stock_teorico ('codigoInterno', 'stock_teorico') VALUES ('"
						+ referenciaInterna + "', '" + stock + "')");

			}
		}

		// System.out.println(list.size());
		return list;
	}

	public static String getCodigoInterno(String referenciaCapturada) throws SQLException {

		Statement statement = connector.createStatement();

		if (referenciaCapturada.length() == 7 || referenciaCapturada.length() == 8) {

			return referenciaCapturada;
		} else {

			ResultSet resultset = statement.executeQuery(
					"SELECT codigoInterno FROM baseProductos WHERE codigoEAN = '" + referenciaCapturada + "'");

			String codigoInterno = resultset.getString(1);

			return codigoInterno;
		}

	}

	public static ArrayList<DiferenciasLinea> calcularContadoConsolidado() throws SQLException {

		ArrayList<DiferenciasLinea> list = new ArrayList<DiferenciasLinea>();

		Statement statement = connector.createStatement();

		// Borrar la tabla de diferencias

		statement.executeUpdate("DELETE FROM diferencias");

		// TODO Revisar SQL de diferencias.
		// Sólo funciona si no permites referencias de fuera de la base de datos.
		// La descripción no la pilla bien.
		statement.execute("INSERT INTO diferencias ('referencia', 'descripcion', 'contado', 'teorico', 'diferencia') "
				+ "select capturas.codigoInterno, baseProductos.descripcion, SUM(capturas.cantidad_capturada), stock_teorico.stock_teorico, SUM(capturas.cantidad_capturada) - stock_teorico.stock_teorico "
				+ "FROM capturas, stock_teorico, baseProductos  "
				+ "WHERE capturas.codigoInterno = stock_teorico.codigoInterno AND capturas.codigoInterno = baseProductos.codigoInterno "
				+ "group by capturas.codigoInterno " + "UNION ALL "
				+ "SELECT stock_teorico.codigoInterno, baseProductos.descripcion, 0, stock_teorico.stock_teorico, 0-stock_teorico.stock_teorico "
				+ "FROM stock_teorico, capturas, baseProductos "
				+ "where stock_teorico.codigoInterno NOT IN (SELECT codigoInterno FROM capturas) AND stock_teorico.codigoInterno = baseProductos.codigoInterno "
				+ "GROUP BY stock_teorico.codigoInterno");

		ResultSet rs = statement.executeQuery("SELECT * FROM 'diferencias'");

		while (rs.next()) {
			String referencia = rs.getString(2);
			String descripcion = rs.getString(3);
			int contado = rs.getInt(4);
			int teorico = rs.getInt(5);
			int diferencia = rs.getInt(6);

			DiferenciasLinea nuevaLinea = new DiferenciasLinea(referencia, descripcion, contado, teorico, diferencia);

			list.add(nuevaLinea);

		}

		return list;

	}

	public static ArrayList<DiferenciasLinea> cargarDiferencias() throws SQLException {

		ArrayList<DiferenciasLinea> list = new ArrayList<DiferenciasLinea>();

		Statement st = connector.createStatement();

		ResultSet rs = st.executeQuery("SELECT * FROM diferencias");

		while (rs.next()) {
			String referencia = rs.getString(2);
			String descripcion = rs.getString(3);
			int contado = rs.getInt(4);
			int teorico = rs.getInt(5);
			int diferencia = rs.getInt(6);

			DiferenciasLinea nuevaLinea = new DiferenciasLinea(referencia, descripcion, contado, teorico, diferencia);

			list.add(nuevaLinea);

		}

		return list;

	}

	public static boolean referenciaEnBase(String referencia) throws SQLException {

		Statement st = connector.createStatement();

		ResultSet rs = st.executeQuery("select count(id) FROM baseProductos WHERE codigoInterno = " + referencia);

		int aparicionesReferecia = rs.getInt(1);

		if (aparicionesReferecia == 0) {
			return false;
		} else {
			return true;
		}

	}

	public static int getLastLocation() throws SQLException {

		Statement st = connector.createStatement();

		ResultSet rs = st.executeQuery("SELECT MAX(ubicacion) FROM capturas");

		int lastLocation = rs.getInt(1);

		return lastLocation;
	}

	public static int getQuantityLocation() throws SQLException {

		Statement st = connector.createStatement();

		int ubicacion = InventarioConteoController.getUbicacion();

		ResultSet rs = st.executeQuery("SELECT SUM(cantidad_capturada) FROM capturas WHERE ubicacion = " + ubicacion);

		int cantidad = rs.getInt(1);

		return cantidad;
	}

	public static ArrayList<Captura> getCapturasUbicacion(int ubicacion) throws SQLException {

		ArrayList<Captura> capturasUbicacion = new ArrayList<Captura>();

		Statement st = connector.createStatement();

		ResultSet rs = st.executeQuery("SELECT * FROM capturas WHERE ubicacion = " + ubicacion);

		int id;
		String codigoInterno;
		String codigoEan;
		String descripcion;
		int cantidad;

		while (rs.next()) {
			id = rs.getInt(1);
			codigoInterno = rs.getString(9);
			codigoEan = getCodigoEan(codigoInterno);
			descripcion = getDescripcion(codigoInterno);
			cantidad = rs.getInt(4);

			Captura lineaBuscada = new Captura(id, codigoInterno, codigoEan, descripcion, cantidad);

			capturasUbicacion.add(lineaBuscada);
//			System.out.println("CONTROL BASE");
//			System.out.println(lineaBuscada);

		}

		return capturasUbicacion;
	}

	private static String getDescripcion(String codigoInterno) throws SQLException {
		Statement st = connector.createStatement();

		ResultSet rs = st.executeQuery("SELECT descripcion FROM baseProductos WHERE codigoInterno = " + codigoInterno);

		String ean = rs.getString(1);

		return ean;

	}

	private static String getCodigoEan(String codigoInterno) throws SQLException {
		Statement st = connector.createStatement();

		ResultSet rs = st.executeQuery("SELECT codigoEAN FROM baseProductos WHERE codigoInterno = " + codigoInterno);

		String ean = rs.getString(1);

		return ean;
	}

	public static void eliminarLineaCaptura(int idSeleccionado) throws SQLException {

		Statement st = connector.createStatement();

		st.executeUpdate("DELETE FROM capturas WHERE id = " + idSeleccionado);

	}

	public static void actualizarLineaCaptura(int idSeleccionado, String nuevoCodigo, int nuevaCantidad, String nuevoCodigoInterno)
			throws SQLException {
		Statement st = connector.createStatement();
		st.executeUpdate("UPDATE capturas SET referencia_capturada = '" + nuevoCodigo + "', cantidad_capturada = "
				+ nuevaCantidad + ", codigoInterno = '"
						+ nuevoCodigoInterno +"' WHERE id = " + idSeleccionado + ";");

	}

}
