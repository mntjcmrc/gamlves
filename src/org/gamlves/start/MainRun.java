package org.gamlves.start;

import javax.swing.JFrame;

import org.gamlves.gui.MainFrame;

/**
 * Ejecución del frame principal
 * 
 * @author mntjcmrc
 * 
 */
public class MainRun implements Runnable {
	public static MainFrame mainFrame;

	public void run() {
		show();
	}

	/**
	 * Muestra el frame y pide el foco
	 */
	private void show() {
		mainFrame.setVisible(true);
		mainFrame.requestFocus();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
