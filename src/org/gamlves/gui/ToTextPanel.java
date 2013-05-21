package org.gamlves.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.gamlves.data.ToText;

public class ToTextPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7254919329213702549L;
	/**
	 * Botón para importar a un archivo de texto los datos de los usuarios
	 */
	protected JButton btnUsuarios;
	/**
	 * Botón para importar a un archivo de texto los datos de los juegos
	 */
	protected JButton btnJuegos;

	/**
	 * Crea el diseño del panel para importar a texto, además de comprobar si la
	 * carpeta del programa existe, y si no la crea
	 */
	public ToTextPanel() {
		ToText.checkAndCreateAppFolder();

		setLayout(new BorderLayout());

		JPanel buttons = new JPanel(new FlowLayout());

		btnUsuarios = new JButton("Importar usuarios");
		btnUsuarios.addActionListener(Actions.importUsuarios);

		btnJuegos = new JButton("Importar juegos");
		btnJuegos.addActionListener(Actions.importJuegos);

		buttons.add(btnUsuarios);
		buttons.add(btnJuegos);

		this.add(buttons, BorderLayout.PAGE_START);

	}
}
