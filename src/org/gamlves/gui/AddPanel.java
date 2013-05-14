package org.gamlves.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

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
	private JPanel formAddUsuario;

	/**
	 * Panel interior con el formulario para introducir los datos de un juego
	 */
	private JPanel formAddJuego;

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
	 * Campo de texto para introducir el nombre del juego
	 */
	protected JTextField txtNombreJuego;
	/**
	 * ComboBox con los géneros que se pueden elegir
	 */
	protected JComboBox<String> comboGeneroJuego;
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

		if (admin) {
			// Modo admin

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
			SpringLayout springUser = new SpringLayout();
			formAddUsuario.setLayout(springUser);
			JLabel lblNombreUsuario = new JLabel("Nombre: ");
			txtNombreUsuario = new JTextField(Datos.USUARIONOMBRE);
			JLabel lblUser = new JLabel("User: ");
			txtUser = new JTextField(Datos.USUARIOUSER);
			JLabel lblPass = new JLabel("Contraseña: ");
			txtPass = new JPasswordField(15);

			formAddUsuario.add(lblNombreUsuario);
			springUser.putConstraint(SpringLayout.EAST, lblNombreUsuario, 100,
					SpringLayout.WEST, formAddUsuario);
			springUser.putConstraint(SpringLayout.NORTH, lblNombreUsuario, 40,
					SpringLayout.NORTH, formAddUsuario);

			formAddUsuario.add(txtNombreUsuario);
			springUser.putConstraint(SpringLayout.WEST, txtNombreUsuario, 30,
					SpringLayout.EAST, lblNombreUsuario);
			springUser.putConstraint(SpringLayout.NORTH, txtNombreUsuario, 40,
					SpringLayout.NORTH, formAddUsuario);

			formAddUsuario.add(lblUser);
			springUser.putConstraint(SpringLayout.EAST, lblUser, 100,
					SpringLayout.WEST, formAddUsuario);
			springUser.putConstraint(SpringLayout.NORTH, lblUser, 40,
					SpringLayout.NORTH, lblNombreUsuario);

			formAddUsuario.add(txtUser);
			springUser.putConstraint(SpringLayout.WEST, txtUser, 30,
					SpringLayout.EAST, lblUser);
			springUser.putConstraint(SpringLayout.NORTH, txtUser, 40,
					SpringLayout.NORTH, txtNombreUsuario);

			formAddUsuario.add(lblPass);
			springUser.putConstraint(SpringLayout.EAST, lblPass, 100,
					SpringLayout.WEST, formAddUsuario);
			springUser.putConstraint(SpringLayout.NORTH, lblPass, 40,
					SpringLayout.NORTH, lblUser);

			formAddUsuario.add(txtPass);
			springUser.putConstraint(SpringLayout.WEST, txtPass, 30,
					SpringLayout.EAST, lblPass);
			springUser.putConstraint(SpringLayout.NORTH, txtPass, 40,
					SpringLayout.NORTH, txtUser);

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
			panelAddJuego = new JPanel(new BorderLayout());

			formAddJuego = new JPanel();
			SpringLayout springJuego = new SpringLayout();
			formAddJuego.setLayout(springJuego);
			JLabel lblNombreJuego = new JLabel("Nombre: ");
			txtNombreJuego = new JTextField(Datos.JUEGONOMBRE);
			JLabel lblGeneroJuego = new JLabel("Género: ");
			comboGeneroJuego = new JComboBox<String>(Datos.GENEROS);
			comboGeneroJuego.setEditable(false);
			

			formAddJuego.add(lblNombreJuego);
			springJuego.putConstraint(SpringLayout.EAST, lblNombreJuego, 100,
					SpringLayout.WEST, formAddJuego);
			springJuego.putConstraint(SpringLayout.NORTH, lblNombreJuego, 40,
					SpringLayout.NORTH, formAddJuego);

			formAddJuego.add(txtNombreJuego);
			springJuego.putConstraint(SpringLayout.WEST, txtNombreJuego, 30,
					SpringLayout.EAST, lblNombreJuego);
			springJuego.putConstraint(SpringLayout.NORTH, txtNombreJuego, 40,
					SpringLayout.NORTH, formAddJuego);
			
			formAddJuego.add(lblGeneroJuego);
			springJuego.putConstraint(SpringLayout.EAST, lblGeneroJuego, 100,
					SpringLayout.WEST, formAddJuego);
			springJuego.putConstraint(SpringLayout.NORTH, lblGeneroJuego, 40,
					SpringLayout.NORTH, lblNombreJuego);
			
			formAddJuego.add(comboGeneroJuego);
			springJuego.putConstraint(SpringLayout.WEST, comboGeneroJuego, 30,
					SpringLayout.EAST, lblGeneroJuego);
			springJuego.putConstraint(SpringLayout.NORTH, comboGeneroJuego, 40,
					SpringLayout.NORTH, txtNombreJuego);
			

			JPanel btnAddJuego = new JPanel(new FlowLayout());
			JButton btnAceptarAddJuego = new JButton("Aceptar");
			btnAceptarAddJuego.addActionListener(Actions.addJuegoAceptar);
			JButton btnLimpiarAddJuego = new JButton("Limpiar");
			btnLimpiarAddJuego.addActionListener(Actions.addJuegoLimpiar);

			btnAddJuego.add(btnAceptarAddJuego);
			btnAddJuego.add(btnLimpiarAddJuego);

			panelAddJuego.add(formAddJuego, BorderLayout.CENTER);
			panelAddJuego.add(btnAddJuego, BorderLayout.PAGE_END);

			// panelAddJuego.add(new JLabel("testJuego"));

			// Se añaden las cartas
			panelCards.add(panelAddUsuario, ADDUSUARIO);
			panelCards.add(panelAddJuego, ADDJUEGO);
		} else {
			// Modo user
			
		}

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