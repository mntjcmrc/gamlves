package org.gamlves.start;

import java.awt.EventQueue;

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
		EventQueue.invokeLater(new LoginRun());
	}
}