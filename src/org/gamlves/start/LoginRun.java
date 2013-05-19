package org.gamlves.start;

import javax.swing.JFrame;

import org.gamlves.gui.LoginFrame;

/**
 * Ejecución del frame de login
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
		// Datos.loadData();
		show();
	}

	/**
	 * Crea y muestra el frame de login
	 */
	private void show() {
		loginFrame = new LoginFrame();
		loginFrame.setVisible(true);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}