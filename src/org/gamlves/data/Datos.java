package org.gamlves.data;

import org.gamlves.db.DriverGamlves;

/**
 * 
 * Clase encargada de manejar los datos provistos por DriverGamlves
 * 
 * @author mntjcmrc
 * 
 */
public class Datos extends DriverGamlves {

	private Seguridad sc;
	private DriverGamlves driver;

	public Datos() {
		sc = new Seguridad();
		driver = new DriverGamlves();
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

		if (get_usuario(user) == null) {
			exist = false;
		} else {
			exist = true;
		}

		return exist;
	}

	// El id se generará automáticamente en la base de datos
	/**
	 * Crea un usuario con los datos dados y lo añade a la base de datos
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
			passHash = sc.createHash(pass);

			usuario = new Usuario(nombre, user, passHash);
			
			
		}
	}

	private class Juego {

	}

	private class UsuarioJuego {

	}
}