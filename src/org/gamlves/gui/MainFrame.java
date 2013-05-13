package org.gamlves.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;

import org.gamlves.data.Login;

// Tendrá dos modos:
// admin, en el que se pueden añadir juegos y
// user, en el que cada usuario añade juegos a su biblioteca
/**
 * Frame principal de la aplicación
 * 
 * @author mntjcmrc
 * 
 */
public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8889393132295302487L;
	/**
	 * Tab panel para alojar cada JPanel
	 */
	private JTabbedPane tabPanel;
	/**
	 * Panel para añadir registros a la base de datos
	 * Modo admin: añade juegos a la tabla Juegos
	 * Modo user: añade juegos a la tabla UsuariosJuegos
	 */
	protected AddPanel addPanel;
	/**
	 * Panel para ver los datos
	 */
	protected ViewPanel viewPanel;
	
	public JLabel lblStatus;
	
	private boolean admin;

	/**
	 * Constructor del frame que asignará todas sus opciones, añadirá el tab
	 * panel y cada JPanel
	 */
	public MainFrame(boolean admin) {
		this.admin = admin;
		
		// Titulo
		setTitle(Login._nombre + " - Gamlves");

		// Tamaño
		int widthWindow = 550;
		int heightWindow = 500;
		Actions.centerFrame(this, widthWindow, heightWindow);

		// Menús
		JMenuBar mb = new JMenuBar();
		// Archivo
		JMenu archivo = new JMenu("Archivo");
		JMenuItem salir = new JMenuItem("Salir");
		salir.addActionListener(Actions.cerrar);
		archivo.add(salir);
		
		mb.add(archivo);

		this.setJMenuBar(mb);
		
		// Barra de estado
		JPanel statusPanel = new JPanel();
		statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		statusPanel.setPreferredSize(new Dimension(this.getWidth(), 16));
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
		lblStatus = new JLabel("");
		statusPanel.add(lblStatus);
		
		this.add(statusPanel, BorderLayout.PAGE_END);

		// Panel con los tabs
		tabPanel = new JTabbedPane();
		this.add(tabPanel, BorderLayout.CENTER);
		if(this.admin){
			addPanel = new AddPanel(true);
		} else {
			addPanel = new AddPanel(false);	
		}
		tabPanel.addTab("Añadir", addPanel);
		viewPanel = new ViewPanel();
		tabPanel.addTab("Ver datos", viewPanel);

		Actions.systemLookAndFeel();

	}
}