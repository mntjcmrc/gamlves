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
	protected JTextField txtUser, txtPass;
	private JLabel lblUser, lblPass;
	private JButton btnAceptar, btnCancelar;
//	private String usuario, pass;

	/**
	 *  
	 */
	public LoginFrame() {
		
		// Título y layout
		setTitle("Login en Gamlves");
		setLayout(new FlowLayout());
		
		// Tamaño
		Actions.centerFrame(this, 500, 120);

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
		
		this.add(btnAceptar);
		this.add(btnCancelar);

		btnAceptar.addActionListener(Actions.loginAceptar);
		
		btnCancelar.addActionListener(Actions.cerrar);
		
	}
}