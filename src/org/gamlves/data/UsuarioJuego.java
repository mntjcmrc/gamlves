package org.gamlves.data;

/**
 * Representa las relaciones entre los usuarios y los juegos que poseen
 * 
 * @author mntjcmrc
 * 
 */
public class UsuarioJuego implements Comparable<UsuarioJuego> {

	/**
	 * ID del juego relacionado con el usuario
	 */
	private int _idJuego;
	/**
	 * Username del usuario relacionado con el juego
	 */
	private String _user;

	/**
	 * Creará la relación entre un usuario y un juego
	 * 
	 * @param user Username del usuario relacionado con el juego
	 * @param idJuego ID del juego relacionado con el usuario
	 */
	protected UsuarioJuego(int idJuego, String user) {
		this._idJuego = idJuego;
		this._user = user;
	}
	
	
	/**
	 * @return ID del juego relacionado con el usuario
	 */
	public int get_idJuego(){
		return this._idJuego;
	}
	
	/**
	 * @return Username del usuario relacionado con el juego
	 */
	public String get_user(){
		return this._user;
	}
	
	@Override
	public int compareTo(UsuarioJuego usuariojuego) {
		return this._user.compareTo(usuariojuego.get_user());
	}
}