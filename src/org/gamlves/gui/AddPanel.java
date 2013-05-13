package org.gamlves.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.gamlves.data.Datos;

/**
 * Diseño del panel para la interfaz de añadir registros a la base de datos
 * 
 * @author mntjcmrc
 * 
 */
/**
 * @author mutilx9
 * 
 */
public class AddPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6649879111663947403L;

	/**
	 * Panel exterior con el formulario para introducir los datos de un usuario
	 */
	private JPanel panelAddUsuario;
	/**
	 * Panel con el formulario para introducir los datos de un juego
	 */
	private JPanel panelAddJuego;
	/**
	 * Panel que usando el CardLayout tendrá los dos paneles con los formularios
	 */
	private JPanel panelCards;
	/**
	 * Panel interior con el formulario para introducir los datos de un usuario
	 */
	protected JPanel formAddUsuario;

	/**
	 * Campo de texto para introducir el nombre del usuario
	 */
	protected JTextField txtNombreUsuario;
	/**
	 * Campo de texto para introducir el username único del usuario
	 */
	protected JTextField txtUser;
	/**
	 * Campo de texto para introducir la contraseña del usuario
	 */
	protected JTextField txtPass;

	/**
	 * Constante para que nombre mantener en los triggers de los paneles
	 */
	private final String ADDUSUARIO = "Usuario";
	/**
	 * Constante para que nombre mantener en los triggers de los paneles
	 */
	private final String ADDJUEGO = "Juego";

	/**
	 * Crea el panel para añadir registros. Modo admin: se pueden añadir
	 * usuarios o juegos. Modo user: se pueden añadir juegos a la biblioteca del
	 * usuario logueado.
	 * 
	 * @param admin
	 *            Con esto sabremos fácilmente si el usuario logueado es el
	 *            administrador o no
	 */
	protected AddPanel(boolean admin) {
		this.setLayout(new BorderLayout());

		// Opciones para que añadir
		JPanel opciones = new JPanel();
		opciones.setLayout(new FlowLayout());

		JRadioButton rAddUsuario = new JRadioButton(ADDUSUARIO);
		rAddUsuario.addActionListener(Actions.showAddUsuario);
		rAddUsuario.setEnabled(true);

		JRadioButton rAddJuego = new JRadioButton(ADDJUEGO);
		rAddJuego.addActionListener(Actions.showAddJuego);

		ButtonGroup add = new ButtonGroup();

		add.add(rAddUsuario);
		add.add(rAddJuego);

		opciones.add(rAddUsuario);
		opciones.add(rAddJuego);

		this.add(opciones, BorderLayout.PAGE_START);

		// Organización de las cartas
		panelCards = new JPanel(new CardLayout());
		this.add(panelCards, BorderLayout.CENTER);

		// Panel para añadir usuarios
		panelAddUsuario = new JPanel(new BorderLayout());
		formAddUsuario = new JPanel();
		formAddUsuario.setLayout(new GridLayout(3, 2));
		JLabel lblNombreUsuario = new JLabel("Nombre: ");
		txtNombreUsuario = new JTextField(Datos.USUARIONOMBRE);
		JLabel lblUser = new JLabel("User: ");
		txtUser = new JTextField(Datos.USUARIOUSER);
		JLabel lblPass = new JLabel("Contraseña: ");
		txtPass = new JPasswordField();

		formAddUsuario.add(lblNombreUsuario);
		formAddUsuario.add(txtNombreUsuario);
		formAddUsuario.add(lblUser);
		formAddUsuario.add(txtUser);
		formAddUsuario.add(lblPass);
		formAddUsuario.add(txtPass);

		JPanel btnAddUsuario = new JPanel(new FlowLayout());
		JButton btnAceptarAddUsuario = new JButton("Aceptar");
		btnAceptarAddUsuario.addActionListener(Actions.addUserAceptar);
		JButton btnLimpiarAddUsuario = new JButton("Limpiar");
		btnLimpiarAddUsuario.addActionListener(Actions.addUserLimpiar);

		btnAddUsuario.add(btnAceptarAddUsuario);
		btnAddUsuario.add(btnLimpiarAddUsuario);

		panelAddUsuario.add(formAddUsuario, BorderLayout.CENTER);
		panelAddUsuario.add(btnAddUsuario, BorderLayout.PAGE_END);

		// panelAddUsuario.add(new JLabel("testUsuario"));

		// Panel para añadir juegos
		panelAddJuego = new JPanel();
		panelAddJuego.add(new JLabel("testJuego"));

		// Se añaden las cartas
		panelCards.add(panelAddUsuario, ADDUSUARIO);
		panelCards.add(panelAddJuego, ADDJUEGO);

		// this.add(new JLabel("addTest"));
	}

	/**
	 * Se visualizará el panel para que el admin añada usuarios
	 */
	protected void panelAddUsuario() {
		CardLayout layout = (CardLayout) panelCards.getLayout();
		layout.show(panelCards, ADDUSUARIO);
	}

	/**
	 * Se visualizará el panel para que el admin añada juegos
	 */
	protected void panelAddJuego() {
		CardLayout layout = (CardLayout) panelCards.getLayout();
		layout.show(panelCards, ADDJUEGO);
	}
}