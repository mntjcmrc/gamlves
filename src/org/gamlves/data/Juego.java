package org.gamlves.data;

/**
 * Representa el registro de un juego con sus datos, el id es único en la base
 * de datos
 * 
 * @author mntjcmrc
 * 
 */
public class Juego {

	/**
	 * ID del juego
	 */
	private String _id;
	/**
	 * Nombre del juego
	 */
	private String _nombre;

	/**
	 * Crea un juego, el id se generará en la base de datos
	 * 
	 * @param nombre
	 *            El nombre del juego
	 */
	protected Juego(String nombre) {
		this._nombre = nombre;
	}
	
	/**
	 * @return ID del juego
	 */
	public String get_id(){
		return this._id;
	}
	
	/**
	 * @return Nombre del juego
	 */
	public String get_nombre(){
		return this._nombre;
	}

	/**
	 * Asigna un id al juego, se usará una vez el juego se cree en la base de
	 * datos y se saque el id autogenerado
	 * 
	 * @param id
	 *            ID a asignar al juego
	 */
	protected void set_id(String id) {
		this._id = id;
	}

}