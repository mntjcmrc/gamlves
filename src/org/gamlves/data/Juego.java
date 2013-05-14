package org.gamlves.data;

/**
 * Representa el registro de un juego con sus datos, el id es único en la base
 * de datos
 * 
 * @author mntjcmrc
 * 
 */
public class Juego{

	/**
	 * ID del juego
	 */
	private int _id;
	/**
	 * Nombre del juego
	 */
	private String _nombre;
	/**
	 * Género del juego, tendrá que ser uni de Datos.GENEROS
	 */
	private String _genero;

	/**
	 * Crea un juego, el id se generará en la base de datos
	 * 
	 * @param nombre
	 *            El nombre del juego
	 */
	protected Juego(String nombre, String genero) {
		this._nombre = nombre;
		this._genero = genero;
	}

	/**
	 * @return ID del juego
	 */
	public int get_id() {
		return this._id;
	}

	/**
	 * @return Nombre del juego
	 */
	public String get_nombre() {
		return this._nombre;
	}
	
	/**
	 * @return Género del juego
	 */
	public String get_genero() {
		return this._genero;
	}

	/**
	 * Asigna un id al juego, se usará una vez el juego se cree en la base de
	 * datos y se saque el id autogenerado
	 * 
	 * @param id
	 *            ID a asignar al juego
	 */
	protected void set_id(int id) {
		this._id = id;
	}
	
	public String toString(){
		return this._nombre + " - " + this._genero;
	}
}