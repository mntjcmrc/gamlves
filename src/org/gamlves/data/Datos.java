package org.gamlves.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import org.gamlves.start.MainRun;

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
	 * Array con todos los géneros seleccionables para los juegos
	 */
	public static final String[] GENEROS = { "Seleccione género", "Acción",
			"Aventura", "Casual", "Estrategia", "MMO", "RPG", "Simulación",
			"Plataformas", "Otro" };
	/**
	 * Representa el número de caractéres máximo que podrá tener los usernames
	 */
	public static final int USUARIOUSER = 15;
	/**
	 * Representa el número de caracteres máximo que podrá tener los nombres de
	 * usuario
	 */
	public static final int USUARIONOMBRE = 30;
	/**
	 * Representa el cúmero de caracteres máximo que podra tener los nombres de
	 * los juegos
	 */
	public static final int JUEGONOMBRE = 20;

	/**
	 * Pide todos los datos a la base de datos y los carga en memoria
	 * @throws SQLException 
	 */
	public static void loadData() throws SQLException {
		_usuarios = DriverGamlves.get_usuarios();
		_juegos = DriverGamlves.get_juegos();
		_usuariosjuegos = DriverGamlves.get_usuariosjuegos();
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
	 * Compruena que el juego dado por la id no existe en la base de datos
	 * 
	 * @param id
	 *            ID del juego a comprobar
	 * @return Si existe o no
	 * @throws SQLException
	 */
	protected static boolean checkJuego(int id) throws SQLException {
		boolean exist = false;

		if (DriverGamlves.get_juego(id) == null) {
			exist = false;
		} else {
			exist = true;
		}

		return exist;
	}

	/**
	 * Comprueba que el nombre del juego dado no existe en la base de datos
	 * 
	 * @param nombre
	 *            Nombre del juego a comprobar
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
	 * Comprueba que la relación con los datos dados existe
	 * 
	 * @param idJuego
	 *            ID del juego de la relación
	 * @param user
	 *            Username del usuario de la relación
	 * @return Si la relación existe o no
	 * @throws SQLException
	 */
	protected static boolean checkUsuarioJuego(int idJuego, String user)
			throws SQLException {
		boolean exist = false;

		if (DriverGamlves.get_usuariojuego(idJuego, user) == null) {
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
	 * @return Los datos del usuario
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

	/**
	 * Crea un juego con los datos dados y lo devuelve
	 * 
	 * @param nombre
	 *            El nombre del juego
	 * @param genero
	 *            El género del juego
	 * @return Los datos del juego
	 * @throws SQLException
	 */
	public static Juego createJuego(String nombre, String genero)
			throws SQLException {
		Juego juego = null;

		if (!(checkJuego(nombre))) {
			juego = new Juego(nombre, genero);
		} else {
			// JOptionPane.showMessageDialog(MainRun.mainFrame, "El usuario " +
			// user + " ya existe");
			// System.out.println("El usuario " + user + " ya existe");
		}

		return juego;

	}

	/**
	 * Crea una relación entre un usuario y un juego y lo devuelve
	 * 
	 * @param idJuego
	 *            ID del juego a relacionar
	 * @param user
	 *            Username del usuario a relacionar
	 * @return los datos de la relación
	 * @throws SQLException
	 */
	public static UsuarioJuego createUsuarioJuego(int idJuego, String user)
			throws SQLException {
		UsuarioJuego usuariojuego = null;

		if (!(checkUsuarioJuego(idJuego, user))) {
			usuariojuego = new UsuarioJuego(idJuego, user);
		} else {
			// JOptionPane.showMessageDialog(MainRun.mainFrame,
			// "Esa relación ya existe");
		}

		return usuariojuego;
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
	public static Juego searchJuego(int id) {
		Juego juego = null;

		for (int i = 0; i < _juegos.size(); i++) {
			if (id == _juegos.get(i).get_id()) {
				juego = _juegos.get(i);
			}
		}

		return juego;
	}

	/**
	 * Busca juegos que contengan en su nombre la cadena dada
	 * 
	 * @param nombre
	 *            Cadena a buscar en los nombres de los juegos
	 * @return Todos los juegos que pasan el filtro
	 */
	public static Vector<String> searchJuego(String nombre) {
		Vector<String> juegos = new Vector<String>();
		int size = _juegos.size();
		for (int i = 0; i < size; i++) {
			if (_juegos.get(i).get_nombre().contains(nombre)) {
				juegos.add(_juegos.get(i).toString());
			}
		}

		return juegos;
	}
	
	public static Juego searchJuegoJ(String nombre) {
		Juego juego = null;
		int size = _juegos.size();
		for (int i = 0; i < size; i++) {
			if (_juegos.get(i).get_nombre().equals(nombre)) {
				juego = _juegos.get(i);
			}
		}

		return juego;
	}

	/**
	 * Devuelve una relación entre juego y usuario con los datos dados
	 * buscandolos en memoria
	 * 
	 * @param idJuego
	 *            ID del juego de la relación buscada
	 * @param user
	 *            Username del usuario de la relación buscada
	 * @return Un objeto UsuarioJuego con los datos necesarios, null si no
	 *         existe
	 */
	public static UsuarioJuego searchUsuarioJuego(int idJuego, String user) {
		UsuarioJuego usuariojuego = null;

		for (int i = 0; i < _usuariosjuegos.size(); i++) {
			if (idJuego == _usuariosjuegos.get(i).get_idJuego()
					&& user.equals(_usuariosjuegos.get(i).get_user())) {
				usuariojuego = _usuariosjuegos.get(i);
			}
		}

		return usuariojuego;
	}

	/**
	 * Aglutina todos los pasos al crear un usuario en el sistema, estos son:
	 * comprobar si ya existe un usuario con ese username, crear un objeto del
	 * usuario, añadirlo en la base de datos, pedir los datos para asignar el id
	 * en el objeto en memoria y añadirlo al array en memoria
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

	/**
	 * Aglutina todos los pasos al crear un juego en el sistema, estos son:
	 * comprobar si ya existe un juego con ese nombre, crear un objeto del
	 * juego, añadirlo en la base de datos, pedir los datos para asignar el id
	 * en el objeto en memoria y añadirlo al array en memoria
	 * 
	 * @param nombre
	 *            Nombre del juego
	 * @param genero
	 *            Género del juego
	 * @return 0 si no ha habido ningún problema, 1 si falla al comprobar la
	 *         existencia del juego en el sistema, 2 si falla al crear el objeto
	 *         de tipo Juego (el juego ya existe), 3 si falla al añadir el juego
	 *         a la base de datos (saldrá una ventana con el error SQL
	 *         concreto), 4 si falla al pedir los datos recién introducidos
	 */
	public static int juegoTransaction(String nombre, String genero) {
		int transaction = 0;

		Juego juego;
		Juego juegoDatabase;
		try {
			juego = Datos.createJuego(nombre, genero);
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

	/**
	 * Aglutina todos los pasos al crear un juego en el sistema, estos son:
	 * comprobar si ya existe un juego con ese nombre, crear un objeto del
	 * juego, añadirlo en la base de datos, pedir los datos para asignar el id
	 * en el objeto en memoria y añadirlo al array en memoria
	 * 
	 * @param idJuego
	 *            ID del juego de la relación a añadir
	 * 
	 * @param user
	 *            Username del usuario de la relación a añadir
	 * @return 0 si no ha habido ningún problema, 1 si falla al comprobar la
	 *         existencia del juego en el sistema, 2 si falla al crear el objeto
	 *         de tipo Juego (el juego ya existe), 3 si falla al añadir el juego
	 *         a la base de datos (saldrá una ventana con el error SQL
	 *         concreto), 4 si falla al pedir los datos recién introducidos
	 */
	public static int usuariojuegoTransaction(int idJuego, String user) {
		int transaction = 0;

		Usuario usuario;
		Juego juego;
		UsuarioJuego usuariojuego;
		try {
			usuariojuego = Datos.createUsuarioJuego(idJuego, user);
		} catch (SQLException e) {
			// Error al comprobar al juego en la base de datos
			transaction = 1;
			return transaction;
			// e.printStackTrace();
		} finally {
			DriverGamlves.disconnect();
		}

		usuario = Datos.searchUser(user);
		juego = Datos.searchJuego(idJuego);

		if (!(usuario == null && juego == null)) {
			if (!(usuariojuego == null)) {
				try {
					DriverGamlves.addUsuarioJuego(usuariojuego);
				} catch (SQLException e) {
					// Error al añadir la relación a la base de datos
					transaction = 3;
					// System.out.println("SQL Error: " + e.getErrorCode());
					JOptionPane.showMessageDialog(null,
							"SQL Error: " + e.getErrorCode()
									+ "\nAvise al administrador");
					return transaction;

				} finally {
					DriverGamlves.disconnect();
				}

				// No es necesario aplicar id en este caso
				// try {
				// usuariojuegoDatabase = DriverGamlves
				// .get_usuariojuego(usuariojuego.get_idJuego(),
				// usuariojuego.get_user());
				// } catch (SQLException e) {
				// // Error al pedir los datos de la relación
				// transaction = 4;
				// return transaction;
				// // e.printStackTrace();
				// } finally {
				// DriverGamlves.disconnect();
				// }

				_juegos.add(juego);

			} else {
				// Error al crear el objeto
				transaction = 2;
				return transaction;
			}
		} else {
			JOptionPane.showMessageDialog(MainRun.mainFrame,
					"El usuario y/o el juego no existen");
		}

		return transaction;

	}
}