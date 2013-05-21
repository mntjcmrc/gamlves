package org.gamlves.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
	 * 
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
	 * Devuelve un usuario con el ID dado, el usuario es buscado en los arrays
	 * en memoria
	 * 
	 * @param ID
	 *            ID del usuario
	 * @return Un objeto de tipo usuario con los datos necesarios, null si no
	 *         existe
	 */
	public static Usuario searchUser(int ID) {
		Usuario usuario = null;

		for (int i = 0; i < _usuarios.size(); i++) {
			if (ID == _usuarios.get(i).get_id()) {
				usuario = _usuarios.get(i);
			}
		}

		return usuario;
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

		int size = _juegos.size();

		for (int i = 0; i < size; i++) {
			if (id == _juegos.get(i).get_id()) {
				juego = _juegos.get(i);
			}
		}

		return juego;
	}

	/**
	 * Busca todos los juegos de un usuario
	 * 
	 * @param user
	 *            Username del usuario
	 * @return Todos los juegos de un usuario
	 */
	public static ArrayList<Juego> searchJuegosUsuario(String user) {
		ArrayList<Juego> biblioteca = new ArrayList<Juego>();
		int size = _usuariosjuegos.size();
		Juego juego;

		for (int i = 0; i < size; i++) {
			if (user.equals(_usuariosjuegos.get(i).get_user())) {
				juego = searchJuego(_usuariosjuegos.get(i).get_idJuego());
				biblioteca.add(juego);
			}
		}

		return biblioteca;
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
				juegos.add(_juegos.get(i).get_nombre());
			}
		}

		return juegos;
	}

	/**
	 * Busca un juego en base a su nombre
	 * 
	 * @param nombre
	 *            Nombre del juego a buscar
	 * @return Objeto de tipo Juego con los datos pedidos
	 */
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
	 * Busca cual es el índice en el array de géneros del género dado
	 * 
	 * @param genero
	 *            Género del que buscar su índice
	 * @return El índice en el array de géneros del género dado
	 */
	public static int searchGeneroIndex(String genero) {

		for (int i = 0; i < Datos.GENEROS.length; i++) {
			if (genero.equals(Datos.GENEROS[i])) {
				return i;
			}
		}

		return 0;
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
	 * Aglutina todos los pasos para modificar los datos de un usuario, estos
	 * son: crear el nuevo hash si se está cambiando la contraseña, comprobar
	 * que efectivamente hay cambios comparando con los datos en memoria, mandar
	 * la modificación a la base de datos y cambiar en memoria el objeto
	 * modificado
	 * 
	 * @param ID
	 *            ID del usuario a modificar
	 * @param nombre
	 *            Nombre del usuario, puede ser nuevo
	 * @param user
	 *            Username del usuario a modificar
	 * @param pass
	 *            Nueva contraseña del usuario a modificar, si está vacía es que
	 *            no se cambia
	 * @param changePass
	 *            Si se está cambiando la contraseña o no
	 * @return 0 si no ha habido ningún problema, 1 si no hay modificaciones en
	 *         los datos, 2 si falla en la conexión con la base de datos
	 */
	public static int userTransaction(int ID, String nombre, String user,
			String pass, boolean changePass) {
		int transaction = 0;

		Usuario usuario;
		Usuario usuarioDatabase;

		usuarioDatabase = searchUser(ID);
		if (changePass) {
			String passHash;
			passHash = Seguridad.createHash(pass);
			usuario = new Usuario(nombre, user, passHash);
			usuario.set_id(ID);
		} else {
			usuario = new Usuario(nombre, user, usuarioDatabase.get_pass());
			usuario.set_id(ID);
		}

		if (usuarioDatabase.equals(usuario)) {
			// No ha habido modificaciones
			transaction = 1;
			return transaction;
		} else {
			// Ha habido modificaciones
			try {
				DriverGamlves.modUser(usuario, changePass);
			} catch (SQLException e) {
				// Error al modificar el registro en la base de datos
				transaction = 2;
				JOptionPane.showMessageDialog(null,
						"SQL Error: " + e.getErrorCode()
								+ "\nAvise al administrador");
				return transaction;
			} finally {
				DriverGamlves.disconnect();
			}
			// Se cambia en memoria
			int i = _usuarios.indexOf(usuarioDatabase);
			_usuarios.set(i, usuario);

			return transaction;
		}

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
	 * Aglutina todos los pasos para modificar los datos de un juego, estos son:
	 * comprobar que efectivamente hay cambios comparando con los datos en
	 * memoria, mandar la modificación a la base de datos y cambiar en memoria
	 * el objeto modificado
	 * 
	 * @param ID
	 *            ID del juego a modificar
	 * @param nombre
	 *            Nombre del juego, puede ser nuevo
	 * @param genero
	 *            Género del juego, puede ser nuevo
	 * @return 0 si no ha habido ningún problema, 1 si no hay modificaciones en
	 *         los datos, 2 si falla en la conexión con la base de datos
	 */
	public static int juegoTransaction(int ID, String nombre, String genero) {
		int transaction = 0;

		Juego juego;
		Juego juegoDatabase;

		juegoDatabase = searchJuego(ID);

		juego = new Juego(nombre, genero);
		juego.set_id(ID);

		if (juegoDatabase.equals(juego)) {
			// No ha habido modificaciones
			transaction = 1;
			return transaction;
		} else {
			// Ha habido modificaciones
			try {
				DriverGamlves.modJuego(juego);
			} catch (SQLException e) {
				// Error al modificar el registro en la base de datos
				transaction = 2;
				JOptionPane.showMessageDialog(null,
						"SQL Error: " + e.getErrorCode()
								+ "\nAvise al administrador");
				return transaction;
			} finally {
				DriverGamlves.disconnect();
			}
			// Se cambia en memoria
			int i = _juegos.indexOf(juegoDatabase);
			_juegos.set(i, juego);

			return transaction;
		}

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

				_usuariosjuegos.add(usuariojuego);

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

	/**
	 * @return Metadatos para crear una tabla con todos los usuarios en memoria
	 */
	public static DefaultTableModel getUsuariosMetadata() {
		DefaultTableModel usuariosMD = new DefaultTableModel();
		int size = _usuarios.size();

		usuariosMD.addColumn("ID");
		usuariosMD.addColumn("Nombre");
		usuariosMD.addColumn("Usuario");

		for (int i = 0; i < size; i++) {
			Object[] row = new Object[3];
			row[0] = _usuarios.get(i).get_id();
			row[1] = _usuarios.get(i).get_nombre();
			row[2] = _usuarios.get(i).get_user();
			usuariosMD.addRow(row);
		}

		return usuariosMD;
	}

	/**
	 * @return Metadatos para crear una tabla con todos los juegos en memoria
	 */
	public static DefaultTableModel getJuegosMetadata() {
		DefaultTableModel juegosMD = new DefaultTableModel();
		int size = _juegos.size();

		juegosMD.addColumn("ID");
		juegosMD.addColumn("Nombre");
		juegosMD.addColumn("Género");

		for (int i = 0; i < size; i++) {
			Object[] row = new Object[3];
			row[0] = _juegos.get(i).get_id();
			row[1] = _juegos.get(i).get_nombre();
			row[2] = _juegos.get(i).get_genero();
			juegosMD.addRow(row);
		}

		return juegosMD;
	}

	/**
	 * 
	 * @param juegos
	 *            Juegos de los que hacer el modelo
	 * @return Metadatos para crear una tabla con los juegos dados en arraylist
	 */
	public static DefaultTableModel getJuegosMetadata(ArrayList<Juego> juegos) {
		DefaultTableModel juegosMD = new DefaultTableModel();
		int size = juegos.size();

		juegosMD.addColumn("ID");
		juegosMD.addColumn("Nombre");
		juegosMD.addColumn("Género");

		for (int i = 0; i < size; i++) {
			Object[] row = new Object[3];
			row[0] = juegos.get(i).get_id();
			row[1] = juegos.get(i).get_nombre();
			row[2] = juegos.get(i).get_genero();
			juegosMD.addRow(row);
		}

		return juegosMD;
	}

	/**
	 * @param user
	 *            Username del usuario del que queremos los metadatos de su
	 *            colección de juegos
	 * @return Metadatos para crear una tabla con los juegos de un usuario
	 *         concreto
	 */
	public static DefaultTableModel getLibraryMetadata(String user) {
		DefaultTableModel libraryMD = new DefaultTableModel();

		libraryMD.addColumn("ID");
		libraryMD.addColumn("Nombre");
		libraryMD.addColumn("Género");

		ArrayList<Juego> juegosUser = searchJuegosUsuario(user);
		int size = juegosUser.size();

		for (int i = 0; i < size; i++) {
			Object[] row = new Object[3];
			row[0] = juegosUser.get(i).get_id();
			row[1] = juegosUser.get(i).get_nombre();
			row[2] = juegosUser.get(i).get_genero();
			libraryMD.addRow(row);
		}

		return libraryMD;
	}

	/**
	 * @return Array con todos los usernames menos admin
	 */
	public static String[] getUsuarios() {
		String[] usuarios = new String[_usuarios.size() - 1];

		for (int i = 0; i < usuarios.length; i++) {
			usuarios[i] = _usuarios.get(i + 1).get_user();
		}

		return usuarios;
	}

	/**
	 * @param juegos
	 *            Array de juegos a filtrar
	 * @param generos
	 *            Géneros que hay que mostrar
	 * @return Array con los juegos de los géneros filtrados
	 */
	public static ArrayList<Juego> filterGenero(ArrayList<Juego> juegos,
			Vector<String> generos) {
		ArrayList<Juego> juegosFiltered = new ArrayList<Juego>();

		for (int i = 0; i < juegos.size(); i++) {
			if (generos.contains(juegos.get(i).get_genero())) {
				juegosFiltered.add(juegos.get(i));
			}
		}

		return juegosFiltered;
	}

	/**
	 * @return Tamaño del array de usuarios en memoria
	 */
	public static int get_usuariosSize() {
		return _usuarios.size();
	}

	/**
	 * @return Tamaño del array de juegos en memoria
	 */
	public static int get_juegosSize() {
		return _juegos.size();
	}

	/**
	 * @param i
	 *            Índice en el que buscar el usuario
	 * @return Usuario en el índice dado
	 */
	public static Usuario get_usuarioIndex(int i) {
		if (i < _usuarios.size()) {
			return _usuarios.get(i);
		} else {
			return null;
		}
	}

	/**
	 * @param i
	 *            Índice en el que buscar el juego
	 * @return Juego en el índice dado
	 */
	public static Juego get_juegoIndex(int i) {
		if (i < _juegos.size()) {
			return _juegos.get(i);
		} else {
			return null;
		}
	}
}