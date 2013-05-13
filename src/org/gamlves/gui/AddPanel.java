package org.gamlves.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Diseño del panel para la interfaz de añadir registros a la base de datos
 * 
 * @author mntjcmrc
 * 
 */
public class AddPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6649879111663947403L;

	protected AddPanel(boolean admin) {
		this.setLayout(new BorderLayout());
		
		// Opciones para que añadir
		JPanel opciones = new JPanel();
		opciones.setLayout(new FlowLayout());
		JButton addUsuario = new JButton("Usuario");
		addUsuario.addActionListener(Actions.showAddUsuario);
		JButton addJuego = new JButton("Juego");
		addJuego.addActionListener(Actions.showAddJuego);
		opciones.add(addUsuario);
		opciones.add(addJuego);
		this.add(opciones, BorderLayout.PAGE_START);
		
//		this.add(new JLabel("addTest"));
	}
	
	/**
	 * Añadirá el panel para que el admin añada usuarios
	 */
	protected void panelAddUsuario() {
		JPanel addUsuario = new JPanel();
		
	}
	
	/**
	 * Añadirá el panel para que el admin añada juegos
	 */
	protected void panelAddJuego(){
		
	}
}