package org.gamlves.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

/**
 * Representa a la base de datos. Mantiene la conexión con esta cerrada salvo
 * cuando se envía los datos o metadatos de una query, en estos casos el driver
 * en el paquete deberá usar disconnect() para cerrarla. Los datos de login en
 * la base de datos se mantendrán en atributos privados sin getters ni setters.
 * 
 * @author mntjcmrc
 * 
 */
public class DB {

	private Connection con;
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	private String _protocol = "mysql";
	private String _server = "localhost:3306";
	private String _database;
	private String _user;
	private String _pass;

	/**
	 * Instancia una base de datos con el protocolo de conexión, servidor,
	 * nombre de base de datos, usuario y contraseña dados en el constructor.
	 * Una vez hecha la conexión, la cierra por seguridad. Se usará en el driver
	 * del paquete para poder conectarse a la base de datos y guardar los datos
	 * en el formato adecuado para el programa.
	 * 
	 * @param protocol
	 *            Protocolo usado para conectarse a la base de datos, ejemplos:
	 *            "mysql", "sqlite"
	 * @param server
	 *            Nombre del servidor con el puerto en el que escucha el
	 *            servidor de base de datos
	 * @param database
	 *            Nombre de la base de datos a la que conectarse
	 * @param user
	 *            Usuario usado para la conexión
	 * @param pass
	 *            Contraseña del usuario
	 */
	protected DB(String protocol, String server, String database, String user,
			String pass) {
		_protocol = protocol;
		_server = server;
		_database = database;
		_user = user;
		_pass = pass;
		try {
			con = DriverManager.getConnection("jdbc:" + _protocol + "://"
					+ _server + "/" + database, _user, _pass);
		} catch (SQLException e) {
			System.out.println("Error al intentar testear la conexión con la base de datos");
			JOptionPane.showMessageDialog(null, "Error al intentar testear la conexión con la base de datos");
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	/**
	 * Instancia una base de datos con el nombre de base de datos, usuario y
	 * contraseña dados en el constructor. El protocolo de conexión y el
	 * servidor serán los de por defecto: "mysql" y "localhost:3306". Una vez
	 * hecha la conexión, la cierra por seguridad. Se usará en el driver del
	 * paquete para poder conectarse a la base de datos y guardar los datos en
	 * el formato adecuado para el programa.
	 * 
	 * Este constructor está pensado para hacer pruebas en una base de datos
	 * local.
	 * 
	 * @param database
	 *            Nombre de la base de datos a la que conectarse
	 * @param user
	 *            Usuario usado para la conexión
	 * @param pass
	 *            Contraseña del usuario
	 */
	protected DB(String database, String user, String pass) {
		_database = database;
		_user = user;
		_pass = pass;
		try {
			con = DriverManager.getConnection("jdbc:" + _protocol + "://"
					+ _server + "/" + _database, _user, _pass);
		} catch (SQLException e) {
			System.out.println("Error al intentar testear la conexión con la base de datos");
			JOptionPane.showMessageDialog(null, "Error al intentar testear la conexión con la base de datos");
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	/**
	 * Abre una conexión con la base de datos.
	 */
	private void connect() {
		try {
			con = DriverManager.getConnection("jdbc:" + _protocol + "://"
					+ _server + "/" + _database, _user, _pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Cierra la conexión con la base de datos
	 */
	protected void disconnect() {
		try {
			con.close();
		} catch (SQLException e) {
			System.out
					.println("Error al intentar cerrar la conexión a la base de datos");
			e.printStackTrace();
		}
	}

	/**
	 * Se hace la conexión a la base de datos con los datos de los atributos de
	 * la instancia, se crea un ResultSet con los datos de la query introducida
	 * y se devuelve ese ResultSet. Este método tiene que integrarse con un
	 * driver del programa en el mismo paquete que pase los datos a los que
	 * maneje él. Se debe de cerrar la conexión en el driver del paquete
	 * 
	 * @param query
	 *            Query a ejecutar en la base de datos
	 * @return Un ResultSet de sólo lectura en una dirección con el resultado de
	 *         la query
	 * @throws SQLException
	 *             No controlado aquí para que en el driver se asegure el cierre
	 *             de la conexión
	 */
	protected ResultSet makeQuery(String query) throws SQLException {
		connect();
		Statement stmt = null;
		stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_READ_ONLY);
		rs = stmt.executeQuery(query);

		return rs;

	}

	/**
	 * Se hace la conexión con la base de datos, ejecuta el update dado y cierra
	 * la conexión.
	 * 
	 * @param update
	 *            Sentencia de update a ejecutar
	 * @return Si se ha hecho el update o no
	 * @throws SQLException
	 */
	protected boolean makeUpdate(String update) throws SQLException {
		boolean didUpdate = false;
		Statement stmt = null;
		int n = 0;

		connect();

		stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_UPDATABLE);
		n = stmt.executeUpdate(update);

		if (n == 0) {
			didUpdate = false;
		} else {
			didUpdate = true;
		}

		disconnect();

		return didUpdate;

	}

	/**
	 * Se hace una query y se sacan los metadatos del ResultSet obtenido. Se
	 * debe de cerrar la conexión en el driver del paquete
	 * 
	 * @param query
	 *            Query de la que sacar los metadatos
	 * @return Metadatos de la query hecha
	 * @throws SQLException
	 *             No controlado aquí para que en el driver se asegure el cierre
	 *             de la conexión
	 */
	protected ResultSetMetaData getMetadata(String query) throws SQLException {

		rs = makeQuery(query);
		rsmd = rs.getMetaData();

		return rsmd;
	}
}