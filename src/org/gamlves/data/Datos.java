package org.gamlves.data;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;


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
public class Datos{

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
	
	private static DriverGamlves driver = new DriverGamlves();

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

	public static void loadData() {
		_usuarios = driver.get_usuarios();
//		_juegos = get_juegos();
//		_usuariosjuegos = get_usuariosjuegos();
	}

	/**
	 * Comprueba que el nombre de usuario dado no existe en la base de datos
	 * 
	 * @param user
	 *            Nombre de usuario a comprobar
	 * @return Si ya existe o no
	 */
	public boolean checkUser(String user) {
		boolean exist;

		if (driver.get_usuario(user) == null) {
			exist = false;
		} else {
			exist = true;
		}

		return exist;
	}

	/**
	 * Crea un usuario con los datos dados y lo añade a la base de datos, el id
	 * se generará automáticamente en la base de datos
	 * 
	 * @param nombre
	 *            El nombre del usuario
	 * @param user
	 *            Nombre de usuario para el login
	 * @param pass
	 *            Contraseña en texto plano, se convertirá en hash en este
	 *            método
	 */
	public void createUser(String nombre, String user, String pass) {
		if (checkUser(user)) {
			System.out.println("El usuario " + user + " ya existe");
		} else {
			Usuario usuario;
			String passHash;
			passHash = Seguridad.createHash(pass);

			usuario = new Usuario(nombre, user, passHash);

		}
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
}