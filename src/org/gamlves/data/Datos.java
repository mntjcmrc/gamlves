package org.gamlves.data;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * 
 * Clase encargada de manejar los datos provistos por DriverGamlves
 * 
 * Esta clase manejará los datos cargándolos en memoria a través de ArrayList,
 * con lo cual sólo se podrá usar en un entorno monousuario.
 * 
 * @author mntjcmrc
 * 
 */
/**
 * @author mutilx9
 * 
 */
public class Datos {

	/**
	 * Array con todos los usuarios cargados en memoria
	 */
	private static ArrayList<Usuario> _usuarios = new ArrayList<Usuario>();
	/**
	 * Array con todos los juegos cargados en memoria
	 */
	private static ArrayList<Juego> _juegos = new ArrayList<Juego>();
	/**
	 * Array con todos las relaciones entre usuarios y juegos cargadas en
	 * memoria
	 */
	private static ArrayList<UsuarioJuego> _usuariosjuegos = new ArrayList<UsuarioJuego>();

	/**
	 * Representa el número de caractéres máximo que podrá tener los usernames
	 */
	public static final int USUARIOUSER = 15;
	/**
	 * Representa el número de caracteres máximo que podrá tener los nombres de
	 * usuario
	 */
	public static final int USUARIONOMBRE = 30;
	public static final int JUEGONOMBRE = 20;

	// private static DriverGamlves driver = new DriverGamlves();

	// /**
	// * Crea una instancia del objeto desde el que se controlarán todos los
	// * datos, creando los ArrayList en los que se almacenarán los datos
	// */
	// public Datos() {
	//
	// _usuarios = new ArrayList<Usuario>();
	// _juegos = new ArrayList<Juego>();
	// _usuariosjuegos = new ArrayList<UsuarioJuego>();
	// }

	/**
	 * Pide todos los datos a la base de datos y los carga en memoria
	 */
	public static void loadData() {
		try {
			_usuarios = DriverGamlves.get_usuarios();
			_juegos = DriverGamlves.get_juegos();
			// _usuariosjuegos = DriverGamlves.get_usuariosjuegos();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error al conectar con la base de datos");
			e.printStackTrace();
		} finally {
			DriverGamlves.disconnect();
		}
	}

	/**
	 * Comprueba que el nombre de usuario dado no existe en la base de datos
	 * 
	 * @param user
	 *            Nombre de usuario a comprobar
	 * @return Si ya existe o no
	 * @throws SQLException
	 */
	protected static boolean checkUser(String user) throws SQLException {
		boolean exist = false;

		if (DriverGamlves.get_usuario(user) == null) {
			exist = false;
		} else {
			exist = true;
		}

		return exist;
	}

	/**
	 * Comprueba que el id del juego dado no existe en la base de datos
	 * 
	 * @param id
	 *            ID del juego a comprobar
	 * @return Si existe o no
	 * @throws SQLException
	 */
	protected static boolean checkJuego(String nombre) throws SQLException {
		boolean exist = false;

		if (DriverGamlves.get_juego(nombre) == null) {
			exist = false;
		} else {
			exist = true;
		}

		return exist;
	}

	/**
	 * Crea un usuario con los datos dados y lo devuelve
	 * 
	 * @param nombre
	 *            El nombre del usuario
	 * @param user
	 *            Nombre de usuario para el login
	 * @param pass
	 *            Contraseña en texto plano, se convertirá en hash en este
	 *            método
	 * @throws SQLException
	 */
	public static Usuario createUser(String nombre, String user, String pass)
			throws SQLException {
		Usuario usuario = null;

		if (!(checkUser(user))) {
			String passHash;
			passHash = Seguridad.createHash(pass);
			usuario = new Usuario(nombre, user, passHash);
		} else {
			// JOptionPane.showMessageDialog(MainRun.mainFrame, "El usuario " +
			// user + " ya existe");
			// System.out.println("El usuario " + user + " ya existe");
		}

		return usuario;

	}
	
	public static Juego createJuego(String nombre)
			throws SQLException {
		Juego juego = null;

		if (!(checkJuego(nombre))) {
			juego = new Juego(nombre);
		} else {
			// JOptionPane.showMessageDialog(MainRun.mainFrame, "El usuario " +
			// user + " ya existe");
			// System.out.println("El usuario " + user + " ya existe");
		}

		return juego;

	}

	/**
	 * Devuelve un usuario con el username dado, el usuario es buscado en los
	 * arrays en memoria
	 * 
	 * @param user
	 *            Username del usuario buscado
	 * @return Un objeto Usuario con los datos necesarios, null si no existe
	 */
	public static Usuario searchUser(String user) {
		Usuario usuario = null;

		for (int i = 0; i < _usuarios.size(); i++) {
			if (user.equals(_usuarios.get(i).get_user())) {
				usuario = _usuarios.get(i);
			}
		}

		return usuario;
	}

	/**
	 * Devuelve un juego con el id dado, el juego es buscado en los arrays en
	 * memoria
	 * 
	 * @param id
	 *            ID del juego a buscar
	 * @return Un objeto Juego con los datos necesarios, null si no existe
	 */
	public static Juego searchJuego(String id) {
		Juego juego = null;

		for (int i = 0; i < _juegos.size(); i++) {
			if (id.equals(_juegos.get(i).get_id())) {
				juego = _juegos.get(i);
			}
		}

		return juego;
	}

	/**
	 * Aglutina todos los pasos al crear un usuario en el sistema, estos son:
	 * comprobar si ya existe un usuario con ese username, crear un objeto del
	 * usuario, añadirlo en la base de datos
	 * 
	 * @param nombre
	 *            Nombre que tendrá el usuario
	 * @param user
	 *            Username único que será comprobado en la base de datos
	 * @param pass
	 *            Contraseña del usuario que será convertida en hash en el
	 *            proceso de creación
	 * @return 0 si no ha habido ningún problema, 1 si falla al comprobar la
	 *         existencia del usuario en el sistema, 2 si falla al crear el
	 *         objeto de tipo Usuario (el usuario ya existe), 3 si falla al
	 *         añadir el usuario a la base de datos (saldrá una ventana con el
	 *         error SQL concreto), 4 si falla al pedir los datos recién
	 *         introducidos
	 */
	public static int userTransaction(String nombre, String user, String pass) {
		int transaction = 0;

		Usuario usuario;
		Usuario usuarioDatabase;
		try {
			usuario = Datos.createUser(nombre, user, pass);
		} catch (SQLException e) {
			// Error al comprobar al usuario en la base de datos
			transaction = 1;
			return transaction;
			// e.printStackTrace();
		} finally {
			DriverGamlves.disconnect();
		}

		if (!(usuario == null)) {
			try {
				DriverGamlves.addUser(usuario);
			} catch (SQLException e) {
				// Error al añadir el usuario a la base de datos
				transaction = 3;
				// System.out.println("SQL Error: " + e.getErrorCode());
				JOptionPane.showMessageDialog(null,
						"SQL Error: " + e.getErrorCode()
								+ "\nAvise al administrador");
				return transaction;

			} finally {
				DriverGamlves.disconnect();
			}

			try {
				usuarioDatabase = DriverGamlves.get_usuario(usuario.get_user());
			} catch (SQLException e) {
				// Error al pedir los datos del usuario
				transaction = 4;
				return transaction;
				// e.printStackTrace();
			} finally {
				DriverGamlves.disconnect();
			}

			usuario.set_id(usuarioDatabase.get_id());
			_usuarios.add(usuario);
		} else {
			// Error al crear el objeto
			transaction = 2;
			return transaction;
		}

		return transaction;

	}
	
	public static int juegoTransaction(String nombre) {
		int transaction = 0;

		Juego juego;
		Juego juegoDatabase;
		try {
			juego = Datos.createJuego(nombre);
		} catch (SQLException e) {
			// Error al comprobar al juego en la base de datos
			transaction = 1;
			return transaction;
			// e.printStackTrace();
		} finally {
			DriverGamlves.disconnect();
		}

		if (!(juego == null)) {
			try {
				DriverGamlves.addJuego(juego);
			} catch (SQLException e) {
				// Error al añadir el juego a la base de datos
				transaction = 3;
				// System.out.println("SQL Error: " + e.getErrorCode());
				JOptionPane.showMessageDialog(null,
						"SQL Error: " + e.getErrorCode()
								+ "\nAvise al administrador");
				return transaction;

			} finally {
				DriverGamlves.disconnect();
			}

			try {
				juegoDatabase = DriverGamlves.get_juego(juego.get_nombre());
			} catch (SQLException e) {
				// Error al pedir los datos del juego
				transaction = 4;
				return transaction;
				// e.printStackTrace();
			} finally {
				DriverGamlves.disconnect();
			}

			juego.set_id(juegoDatabase.get_id());
			_juegos.add(juego);
		} else {
			// Error al crear el objeto
			transaction = 2;
			return transaction;
		}

		return transaction;

	}
}