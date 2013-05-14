package org.gamlves.data;

/**
 * Representa a un usuario del programa, el id y user son únicos en la báse de
 * datos. El id se generará en la base de datos automáticamente.
 * 
 * @author mntjcmrc
 * 
 */
public class Usuario {
	private int _id;
	private String _nombre;
	private String _user;
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
}