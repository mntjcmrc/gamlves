package org.gamlves.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.gamlves.data.*;

/**
 * Driver que enlaza la conexión de la base de datos con el formato de datos de
 * la propia aplicación gamlves.
 * 
 * @author mntjcmrc
 * 
 */
public class DriverGamlves {

	/**
	 * Base de datos con el login por defecto
	 */
	private DB database = new DB("gamlves", "gamlves", "abc123ABC");

	/**
	 * Mantiene el login por defecto
	 */
	public DriverGamlves() {

	}

	/**
	 * Nos da los datos de todos los usuarios
	 * 
	 * @return Un array con todos los usuarios
	 */
	protected ArrayList<Usuario> get_usuarios() {
		ArrayList<Usuario> usuarios = null;
		ResultSet rs;

		try {
			rs = database.makeQuery("SELECT * FROM Usuarios;");
			usuarios = new ArrayList<Usuario>();
			while (rs.next()) {
				Usuario usuario;
				usuario = crear_usuario(rs);
				usuarios.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			database.disconnect();
		}

		return usuarios;
	}

	/**
	 * Método estándar para crear un array con todos los objetos de una tabla
	 * 
	 * @param tabla
	 *            De que tabla se van a sacar los datos de los objetos
	 * @param type
	 *            Tipo de los objetos, sólo puede ser 'usuario', 'juego' y
	 *            'usuariojuego'
	 * @return Un array con todos los objetos de la tabla proporcionada
	 */
	protected ArrayList<Object> get_objetos(String tabla, String type) {
		ArrayList<Object> objetos = null;
		ResultSet rs;

		try {
			rs = database.makeQuery("SELECT * FROM " + tabla);
			objetos = new ArrayList<Object>();
			while (rs.next()) {
				Object objeto;
				objeto = crear_objeto(type, rs);
				objetos.add(objeto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			database.disconnect();
		}

		return objetos;
	}

	/**
	 * Nos da los datos de un usuario
	 * 
	 * @param user
	 *            Username del usuario
	 * @return Datos del usuario
	 */
	protected Usuario get_usuario(String user) {
		Usuario usuario = null;
		ResultSet rs;

		try {
			rs = database.makeQuery("SELECT * FROM Usuarios WHERE User='"
					+ user + "';");
			rs.last();
			if (rs.getRow() == 0) {
				// No existe el usuario user
			} else {
				crear_usuario(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			database.disconnect();
		}

		return usuario;
	}

	/**
	 * Método estándar para conseguir los datos de un tipo de Objeto. Leyenda de
	 * los objetos: Tabla(clave). Ejemplos: Usuarios(User), Juegos(ID),
	 * UsuariosJuegos(ID).
	 * 
	 * @param tabla
	 *            De que tabla de la base de datos vamos a sacar el objeto
	 * @param clave
	 *            El nombre de la clave para buscar el objeto en la tabla
	 * @param checkClave
	 *            La clave a comprobar
	 * @return Objeto buscado con sus datos
	 */
	protected Object get_objeto(String tabla, String clave, String checkClave) {
		Object objeto = null;
		ResultSet rs;

		try {
			rs = database.makeQuery("SELECT * FROM " + tabla + " WHERE "
					+ clave + "='" + checkClave + "';");
			rs.last();
			if (rs.getRow() == 0) {
				// No existe el objeto
			} else {
				// crear_objeto();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			database.disconnect();
		}

		return objeto;
	}

	/**
	 * Crea un objeto usuario con el ResultSet dado
	 * 
	 * @param rs
	 *            ResultSet en la posición del usuario que se quiere crear
	 * @return Usuario con los datos de la posición del ResultSet
	 * @throws SQLException
	 */
	private Usuario crear_usuario(ResultSet rs) throws SQLException {
		Usuario usuario = null;

		usuario = (Usuario) crear_objeto("usuario", rs);

		return usuario;
	}

	/**
	 * Método estándar para crear un objeto de un tipo particular
	 * 
	 * @param type
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Object crear_objeto(String type, ResultSet rs) throws SQLException {
		Object objeto = null;

		if (type.equals("usuario")) {

			rs.getInt("ID");
			rs.getString("Nombre");
			rs.getString("User");
			rs.getString("Pass");

		} else if (type.equals("juego")) {
			// Introducir los get de las rows de juego

		} else if (type.equals("usuariojuego")) {
			// Introducir los get de las rows de usuariojuego
		}

		return objeto;
	}

	/**
	 * Añade un registro de usuario a la base de datos
	 * 
	 * @param usuario
	 *            Datos del usuario a añadir
	 * @return Si se ha añadido correctamente o no
	 */
	protected boolean addUser(Usuario usuario) {
		boolean add = false;
		String nombre, user, pass;
		String update;

		nombre = usuario.get_nombre();
		user = usuario.get_user();
		pass = usuario.get_pass();

		update = "INSERT INTO Usuarios (Nombre, Usuario, Pass) VALUES('"
				+ nombre + "', '" + user + "', '" + pass + "');";
		add = database.makeUpdate(update);

		return add;
	}
}