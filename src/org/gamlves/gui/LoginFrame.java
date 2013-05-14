package org.gamlves.gui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9198884323424763366L;
	/**
	 * Campos de texto con el usuario de login
	 */
	protected static JTextField txtUser;
	/**
	 * Campo de texto con la contraseña de login
	 */
	protected static JTextField txtPass;
	/**
	 * Etiquetas del usuario y contraseña
	 */
	private JLabel lblUser, lblPass;
	/**
	 * Botones de aceptar y cancelar
	 */
	private JButton btnAceptar, btnCancelar;

	// private String usuario, pass;

	/**
	 * Crea el frame de login con las etiquetas, campos de texto y botones
	 */
	public LoginFrame() {

		// Título y layout
		setTitle("Login en Gamlves");
		setLayout(new FlowLayout());

		// Tamaño
		int widthFrame = 500;
		int heightFrame = 120;
		Actions.centerFrame(this, widthFrame, heightFrame);

		// Username
		lblUser = new JLabel("Usuario: ");
		txtUser = new JTextField(10);
		this.add(lblUser);
		this.add(txtUser);

		// Contraseña
		lblPass = new JLabel("Contraseña: ");
		txtPass = new JPasswordField(15);
		this.add(lblPass);
		this.add(txtPass);

		// Botones
		btnAceptar = new JButton("Aceptar");
		btnCancelar = new JButton("Cancelar");

		btnAceptar.addActionListener(Actions.loginAceptar);
		btnCancelar.addActionListener(Actions.cerrar);

		this.add(btnAceptar);
		this.add(btnCancelar);

	}
}