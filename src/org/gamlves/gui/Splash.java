package org.gamlves.gui;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

/**
 * Representa el frame con el splash del programa
 * 
 * @author mntjcmrc
 * 
 */
public class Splash extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1414776997510521575L;
	/**
	 * Anchura del frame
	 */
	private final int WIDTH = 200;
	/**
	 * Altura del frame
	 */
	private final int HEIGHT = 60;
	/**
	 * Barra de progreso
	 */
	public JProgressBar progress;

	/**
	 * Crea el splash con el tamaño definido y lo centra en la pantalla
	 */
	public Splash() {

		// Título
		setTitle("Cargando");

		// JProgressBar
		progress = new JProgressBar();
		progress.setMinimum(0);
		progress.setMaximum(100);
		progress.setValue(0);
		progress.setForeground(new Color(50, 50, 150, 100));

		// Tamaño y posición
		Actions.centerFrame(this, WIDTH, HEIGHT);
		setResizable(false);

		// Componentes
		setLayout(new FlowLayout());
		this.add(progress);

		Actions.systemLookAndFeel();
	}
}