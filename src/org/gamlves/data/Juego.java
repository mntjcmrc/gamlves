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
	 * @param genero
	 *            Género del juego
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

	@Override
	public String toString() {
		return "ID: " + this._id + "\nNombre: " + this._nombre + "\nGénero: " + this._genero;
	}

	/**
	 * Comprueba que dos objetos Juego son idénticos
	 * 
	 * @param juego
	 *            Juego a comprobar con él mismo
	 * @return Si son iguales o no
	 */
	public boolean equals(Juego juego) {
		return (this._id == juego.get_id())
				&& (this._nombre.equals(juego.get_nombre()))
				&& (this._genero.equals(juego.get_genero()));
	}
}