package org.gamlves.data;

/**
 * Representa a un usuario del programa, el id y user son únicos en la báse de
 * datos. El id se generará en la base de datos automáticamente.
 * 
 * @author mntjcmrc
 * 
 */
public class Usuario {
	/**
	 * ID único que identifica al usuario
	 */
	private int _id;
	/**
	 * Nombre del usuario
	 */
	private String _nombre;
	/**
	 * Username único que identifica al usuario
	 */
	private String _user;
	/**
	 * Contraseña guardada en un hash MD5
	 */
	private String _pass;

	/**
	 * Crea un usuario sin id (se asignará en la base de datos y posteriormente
	 * en el objeto). La contraseña se da ya convertida en un hash.
	 * 
	 * @param nombre
	 * @param user
	 * @param pass
	 */
	protected Usuario(String nombre, String user, String pass) {
		this._nombre = nombre;
		this._user = user;
		this._pass = pass;
	}

	/**
	 * @return ID del usuario
	 */
	public int get_id() {
		return this._id;
	}

	/**
	 * @return Nombre del usuario
	 */
	public String get_nombre() {
		return this._nombre;
	}

	/**
	 * @return Username del usuario
	 */
	public String get_user() {
		return this._user;
	}

	/**
	 * @return Hash de la contraseña del usuario
	 */
	public String get_pass() {
		return this._pass;
	}

	/**
	 * Asigna un id al usuario, se usará una vez el usuario se cree en la base
	 * de datos y se saque el id autogenerado
	 * 
	 * @param id
	 *            ID a asignar al usuario
	 */
	public void set_id(int id) {
		this._id = id;
	}
	
	@Override
	public String toString() {
		return "ID: " + this._id + "\nNombre: " + this._nombre + "\nUsername: " + this._user;
	}

	/**
	 * Comprueba que dos objetos Usuario son idénticos
	 * 
	 * @param usuario
	 *            Usuario a comprobar con él mismo
	 * @return Si son iguales o no
	 */
	public boolean equals(Usuario usuario) {
		return (this._id == usuario.get_id())
				&& (this._nombre.equals(usuario.get_nombre()))
				&& this._user.equals(usuario.get_user())
				&& this._pass.equals(usuario.get_pass());
	}
}