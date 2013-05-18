package org.gamlves.gui;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

		// Tamaño
		int widthFrame = 500;
		int heightFrame = 100;
		Actions.centerFrame(this, widthFrame, heightFrame);
		
		// Panel superior
		JPanel upperPanel = new JPanel(new FlowLayout());

		// Username
		lblUser = new JLabel("Usuario: ");
		txtUser = new JTextField(10);

		txtUser.requestFocus();
		txtUser.addActionListener(Actions.loginAceptar);

		upperPanel.add(lblUser);
		upperPanel.add(txtUser);

		// Contraseña
		lblPass = new JLabel("Contraseña: ");
		txtPass = new JPasswordField(15);

		txtPass.addActionListener(Actions.loginAceptar);

		upperPanel.add(lblPass);
		upperPanel.add(txtPass);
		
		// Panel inferior
		JPanel lowPanel = new JPanel(new FlowLayout());

		// Botones
		btnAceptar = new JButton("Aceptar");
		btnCancelar = new JButton("Salir");

		btnAceptar.addActionListener(Actions.loginAceptar);
		btnCancelar.addActionListener(Actions.cerrar);

		lowPanel.add(btnAceptar);
		lowPanel.add(btnCancelar);
		
		mainPanel.add(upperPanel);
		mainPanel.add(lowPanel);
		
		this.add(mainPanel);
		
		Actions.systemLookAndFeel();
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