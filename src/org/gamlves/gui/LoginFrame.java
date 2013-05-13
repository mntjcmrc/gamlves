package org.gamlves.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.gamlves.data.Login;
import org.gamlves.data.Seguridad;

public class LoginFrame extends JFrame {

	protected JTextField txtUser, txtPass;
	private JLabel lblUser, lblPass;
	private JButton btnAceptar, btnCancelar;
	String usuario, pass;

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
		
		btnCancelar.addActionListener(Actions.loginCancelar);
		
	}
}