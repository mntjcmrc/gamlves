package org.gamlves.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	private static DB database = new DB("gamlves", "gamlves", "abc123ABC");

	/**
	 * Mantiene el login por defecto
	 */
	public DriverGamlves() {

	}

	/**
	 * Usada para cortar la conexión con la base de datos desde fuera del driver
	 */
	protected static void disconnect() {
		database.disconnect();
	}

	/**
	 * Nos da los datos de todos los usuarios
	 * 
	 * @return Un array con todos los usuarios
	 * @throws SQLException
	 */
	protected static ArrayList<Usuario> get_usuarios() throws SQLException {
		ArrayList<Usuario> usuarios = null;
		ResultSet rs;
		rs = database.makeQuery("SELECT * FROM Usuarios;");
		usuarios = new ArrayList<Usuario>();
		while (rs.next()) {
			Usuario usuario;
			usuario = crear_usuario(rs);
			usuarios.add(usuario);
		}
		return usuarios;
	}

	/**
	 * Nos da los datos de todos los juegos
	 * 
	 * @return Un array con todos los juegos
	 * @throws SQLException
	 */
	protected static ArrayList<Juego> get_juegos() throws SQLException {
		ArrayList<Juego> juegos = null;
		ResultSet rs;
		rs = database.makeQuery("SELECT * FROM Juegos;");
		juegos = new ArrayList<Juego>();
		while (rs.next()) {
			Juego juego;
			juego = crear_juego(rs);
			juegos.add(juego);
		}
		return juegos;
	}
	
	/**
	 * Nos da los datos de todos las relaciones entre usuarios y juegos
	 * 
	 * @return un array con todas las relaciones
	 * @throws SQLException
	 */
	protected static ArrayList<UsuarioJuego> get_usuariosjuegos() throws SQLException {
		ArrayList<UsuarioJuego> usuariosjuegos = null;
		ResultSet rs;
		rs = database.makeQuery("SELECT * FROM UsuariosJuegos;");
		usuariosjuegos = new ArrayList<UsuarioJuego>();
		while (rs.next()) {
			UsuarioJuego usuariojuego;
			usuariojuego = crear_usuariojuego(rs);
			usuariosjuegos.add(usuariojuego);
		}
		return usuariosjuegos;
	}

	/**
	 * Nos da los datos de un usuario
	 * 
	 * @param user
	 *            Username del usuario
	 * @return Datos del usuario
	 * @throws SQLException
	 */
	protected static Usuario get_usuario(String user) throws SQLException {
		Usuario usuario = null;
		ResultSet rs;
		rs = database.makeQuery("SELECT * FROM Usuarios WHERE User='" + user
				+ "';");
		rs.last();
		if (rs.getRow() == 0) {
			// No existe el usuario user
		} else {
			usuario = crear_usuario(rs);
		}

		return usuario;
	}
	
	protected static Juego get_juego(int id) throws SQLException {
		Juego juego = null;
		ResultSet rs;
		rs = database.makeQuery("SELECT * FROM Juegos WHERE ID=" + id + ";");
		rs.last();
		if (rs.getRow() == 0) {
			// No existe el juego con ese ID
		} else {
			juego = crear_juego(rs);
		}
		
		return juego;
	}

	/**
	 * Nos da los datos de un juego en base al id dado
	 * 
	 * @param id
	 *            ID del juego
	 * @return Datos del juego
	 * @throws SQLException
	 */
	protected static Juego get_juego(String nombre) throws SQLException {
		Juego juego = null;
		ResultSet rs;
		rs = database.makeQuery("SELECT * FROM Juegos WHERE Nombre='" + nombre
				+ "';");
		rs.last();
		if (rs.getRow() == 0) {
			// No existe el juego nombre
		} else {
			juego = crear_juego(rs);
		}

		return juego;
	}

	/**
	 * Nos da los datos de un juego en base a los datos dados
	 * 
	 * @param idJuego
	 *            ID del juego
	 * @param user
	 *            Username del usuario que posee ese juego
	 * @return Datos de la relación
	 * @throws SQLException
	 */
	protected static UsuarioJuego get_usuariojuego(int idJuego, String user)
			throws SQLException {
		UsuarioJuego usuariojuego = null;
		ResultSet rs;
		rs = database.makeQuery("SELECT * FROM UsuariosJuegos WHERE IDJuego="
				+ idJuego + " AND User='" + user + "';");
		rs.last();
		if (rs.getRow() == 0) {
			// No existe esa relación
		} else {
			usuariojuego = crear_usuariojuego(rs);
		}

		return usuariojuego;
	}

	/**
	 * Crea un objeto usuario con el ResultSet dado
	 * 
	 * @param rs
	 *            ResultSet en la posición del usuario que se quiere crear
	 * @return Usuario con los datos de la posición del ResultSet
	 * @throws SQLException
	 */
	private static Usuario crear_usuario(ResultSet rs) throws SQLException {
		Usuario usuario = null;

		int id = rs.getInt("ID");
		String nombre = rs.getString("Nombre");
		String user = rs.getString("User");
		String pass = rs.getString("Pass");

		usuario = new Usuario(nombre, user, pass);
		usuario.set_id(id);

		return usuario;
	}

	/**
	 * Crea un objeto juego con el ResultSet dado
	 * 
	 * @param rs
	 *            ResultSet en la posición del juego que se quiere crear
	 * @return Juego con los datos de la posición del ResultSet
	 * @throws SQLException
	 */
	private static Juego crear_juego(ResultSet rs) throws SQLException {
		Juego juego = null;

		int id = rs.getInt("ID");
		String nombre = rs.getString("Nombre");
		String genero = rs.getString("Genero");

		juego = new Juego(nombre, genero);
		juego.set_id(id);

		return juego;
	}

	/**
	 * Crea un objeto usuariojuego con el ResultSet dado
	 * 
	 * @param rs
	 *            ResultSet en la posición del juego que se quiere crear
	 * @return Juego con los datos de la posición del ResultSet
	 * @throws SQLException
	 */
	private static UsuarioJuego crear_usuariojuego(ResultSet rs)
			throws SQLException {
		UsuarioJuego usuariojuego = null;

		int idJuego = rs.getInt("IDJuego");
		String user = rs.getString("User");

		usuariojuego = new UsuarioJuego(idJuego, user);

		return usuariojuego;
	}

	/**
	 * Añade un registro de usuario a la base de datos
	 * 
	 * @param usuario
	 *            Datos del usuario a añadir
	 * @return Si se ha añadido correctamente o no
	 * @throws SQLException
	 */
	protected static boolean addUser(Usuario usuario) throws SQLException {
		boolean add = false;
		String nombre, user, pass;
		String update;

		nombre = usuario.get_nombre();
		user = usuario.get_user();
		pass = usuario.get_pass();

		update = "INSERT INTO Usuarios (Nombre, User, Pass) VALUES('" + nombre
				+ "', '" + user + "', '" + pass + "');";
		add = database.makeUpdate(update);

		return add;
	}

	/**
	 * Añade un registro de juego a la base de datos
	 * 
	 * @param juego
	 *            Datos del juego a añadir
	 * @return Si se ha añadido correctamente o no
	 * @throws SQLException
	 */
	protected static boolean addJuego(Juego juego) throws SQLException {
		boolean add = false;
		String nombre;
		String genero;
		String update;

		nombre = juego.get_nombre();
		genero = juego.get_genero();

		update = "INSERT INTO Juegos (Nombre, Genero) VALUES('" + nombre
				+ "', '" + genero + "');";
		add = database.makeUpdate(update);

		return add;
	}
}