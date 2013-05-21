package org.gamlves.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ToTextPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7254919329213702549L;
	protected JButton btnUsuarios;
	protected JButton btnJuegos;

	public ToTextPanel() {
		setLayout(new BorderLayout());

		JPanel buttons = new JPanel(new FlowLayout());

		btnUsuarios = new JButton("Importar usuarios");
		btnJuegos = new JButton("Importar juegos");

		buttons.add(btnUsuarios);
		buttons.add(btnJuegos);

		this.add(buttons, BorderLayout.PAGE_START);

	}
}
