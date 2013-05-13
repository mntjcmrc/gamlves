package org.gamlves.data;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Métodos para asegurar la seguridad de los datos sensibles
 * 
 * @author mntjcmrc
 * 
 */
public class Seguridad {

	/**
	 * Algoritmo usado para crear el hash
	 */
	private static final String algorithm = "MD5";
	/**
	 * Charset usado en la contraseña
	 */
	private static final String charset = "UTF-8";
	private static MessageDigest md;
	
	/**
	 * Por defecto usará MD5 como algoritmo y UTF-8 como charset
	 */
	public Seguridad() {
		try {
			md = MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	// Sin testear
	/**
	 * Comprueba que la contraseña dada es del usuario que se está logeando en
	 * la aplicación.
	 * 
	 * @param user Usuario intentando loguearse
	 * @param pass Supuesta contraseña del usuario
	 * @return Si es correcto el login
	 */
	public static boolean checkLogin(String user, String pass) {		
		boolean check = false;
		String passHash;
		
		passHash = createHash(pass);
		
		Usuario usuario = Datos.searchUser(user);
		if(usuario == null){
			
		} else {
			if (usuario.get_pass().equals(passHash)){
				check = true;
			}
		}

		return check;
	}

	/**
	 * Dada una string, crea un hash con el algoritmo en el atributo de clase
	 * 
	 * @param s
	 *            String a pasar a hash
	 * @return Hash en forma de string
	 */
	protected static String createHash(String s) {
		
		try {
			md = MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		byte[] bytes;
		byte[] digest;
		String hash = null;

		try {
			bytes = s.getBytes(charset);
			digest = md.digest(bytes);
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < digest.length; i++) {
				String hex = Integer.toHexString(0xFF & digest[i]);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			hash = hexString.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return hash;
	}
}