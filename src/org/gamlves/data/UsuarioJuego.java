package org.gamlves.data;

/**
 * Representa las relaciones entre los usuarios y los juegos que poseen
 * 
 * @author mntjcmrc
 * 
 */
public class UsuarioJuego {

	/**
	 * Username del usuario relacionado con el juego
	 */
	private String _user;
	/**
	 * ID del juego relacionado con el usuario
	 */
	private String _idJuego;

	/**
	 * Creará la relación entre un usuario y un juego
	 * 
	 * @param user Username del usuario relacionado con el juego
	 * @param idJuego ID del juego relacionado con el usuario
	 */
	protected UsuarioJuego(String user, String idJuego) {
		this._user = user;
		this._idJuego = idJuego;
	}
	
	/**
	 * @return Username del usuario relacionado con el juego
	 */
	public String get_user(){
		return this._user;
	}
	
	/**
	 * @return ID del juego relacionado con el usuario
	 */
	public String get_idJuego(){
		return this._idJuego;
	}

}
