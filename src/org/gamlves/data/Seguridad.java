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

	private String algorithm = "MD5";
	private String charset = "UTF-8";
	private MessageDigest md;

	/**
	 * Por defecto usará MD5 como algoritmo y UTF-o como charset
	 */
	public Seguridad() {
		try {
			md = MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	// Pendiente
	/**
	 * Comprueba que la contraseña dada es del usuario que se está logeando en
	 * la aplicación.
	 * 
	 * @param s
	 * @return
	 */
	public boolean checkLogin(String user, String pass) {
		boolean check = false;
		String passHash;
		
		passHash = createHash(pass);

		return check;
	}

	/**
	 * Dada una string, crea un hash con el algoritmo en el atributo de clase
	 * 
	 * @param s
	 *            String a pasar a hash
	 * @return Hash en forma de string
	 */
	protected String createHash(String s) {

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