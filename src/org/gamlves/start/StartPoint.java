package org.gamlves.start;

import java.awt.EventQueue;

import javax.swing.JFrame;

import org.gamlves.gui.LoginFrame;

/**
 * Desde aquí se inicia el programa
 * 
 * @author mntjcmrc
 * 
 */
public class StartPoint {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				LoginFrame loginFrame = new LoginFrame();
				loginFrame.setVisible(true);
				loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
	}
}
