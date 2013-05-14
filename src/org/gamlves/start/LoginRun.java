package org.gamlves.start;

import javax.swing.JFrame;

import org.gamlves.data.Datos;
import org.gamlves.gui.LoginFrame;

/**
 * Desde aquí se ejecuta el frame de login, antes se cargan los datos de la base
 * de datos en memoria
 * 
 * @author mntjcmrc
 * 
 */
public class LoginRun implements Runnable {
	/**
	 * Frame de login
	 */
	public static LoginFrame loginFrame;
	
	public void run() {
		Datos.loadData();
		show();
	}

	/**
	 * Crea y muestra el frame de login 
	 */
	private void show() {
		loginFrame = new LoginFrame();
		loginFrame.setVisible(true);
		loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
