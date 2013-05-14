package org.gamlves.gui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Frame con el login en el sistema
 * 
 * @author mntjcmrc
 * 
 */
public class LoginFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9198884323424763366L;
	/**
	 * Campo de texto con el usuario de login
	 */
	private JTextField txtUser;
	/**
	 * Campo de texto con la contraseña de login
	 */
	private JTextField txtPass;
	/**
	 * Etiquetas del usuario
	 */
	private JLabel lblUser;
	/**
	 * Etiqueta de la contraseña
	 */
	private JLabel lblPass;
	/**
	 * Botón de aceptar
	 */
	private JButton btnAceptar;
	/**
	 * Botón de cancelar
	 */
	private JButton btnCancelar;

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

		txtUser.requestFocus();
		txtUser.addActionListener(Actions.loginAceptar);

		this.add(lblUser);
		this.add(txtUser);

		// Contraseña
		lblPass = new JLabel("Contraseña: ");
		txtPass = new JPasswordField(15);

		txtPass.addActionListener(Actions.loginAceptar);

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

	/**
	 * @return Texto del campo de texto del usuario
	 */
	protected String get_txtUser() {
		return this.txtUser.getText();
	}

	/**
	 * @param user
	 *            Texto a asignar en el campo de texto del usuario
	 */
	protected void set_txtUser(String user) {
		this.txtUser.setText(user);
	}

	/**
	 * @return Texto del campo te texto de la contraseña
	 */
	protected String get_txtPass() {
		return this.txtPass.getText();
	}

	/**
	 * @param pass
	 *            Texto a asignar en el campo de texto de la contraseña
	 */
	protected void set_txtPass(String pass) {
		this.txtPass.setText(pass);
	}
}