package org.gamlves.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
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
public class AddPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6649879111663947403L;
	
	private JPanel panelAddUsuario;
	private JPanel panelAddJuego;
	private JPanel panelCards;
	
	private final String ADDUSUARIO = "Usuario";
	private final String ADDJUEGO = "Juego";

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
		panelAddUsuario = new JPanel();
		panelAddUsuario.setLayout(new GridLayout(3, 2));
		JLabel lblNombreUsuario = new JLabel("Nombre: ");
		JTextField txtNombreUsuario = new JTextField(Datos.USUARIONOMBRE);
		JLabel lblUser = new JLabel("User: ");
		JTextField txtUser = new JTextField(Datos.USUARIOUSER);
		JLabel lblPass = new JLabel("Contraseña: ");
		JTextField txtPass = new JPasswordField(); 
		
		panelAddUsuario.add(lblNombreUsuario);
		panelAddUsuario.add(txtNombreUsuario);
		panelAddUsuario.add(lblUser);
		panelAddUsuario.add(txtUser);
		panelAddUsuario.add(lblPass);
		panelAddUsuario.add(txtPass);
		
//		panelAddUsuario.add(new JLabel("testUsuario"));
		
		
		// Panel para añadir juegos
		panelAddJuego = new JPanel();
		panelAddJuego.add(new JLabel("testJuego"));
		
		// Se añaden las cartas
		panelCards.add(panelAddUsuario, ADDUSUARIO);
		panelCards.add(panelAddJuego, ADDJUEGO);
		
//		this.add(new JLabel("addTest"));
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
	protected void panelAddJuego(){
		CardLayout layout = (CardLayout) panelCards.getLayout();
		layout.show(panelCards, ADDJUEGO);
	}
}