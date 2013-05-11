package org.gamlves.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.MenuBar;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;

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

	/**
	 * Constructor del frame que asignará todas sus opciones, añadirá el tab
	 * panel y cada JPanel
	 */
	public MainFrame(boolean admin) {
		// Titulo
		setTitle("Gamlves");

		// Tamaño
		setResizable(false);
		setBounds(100, 100, 677, 459);

		// Menús
		MenuBar menubar = new MenuBar();

		this.setMenuBar(menubar);
		
		// Barra de estado
		JPanel statusPanel = new JPanel();
		statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		statusPanel.setPreferredSize(new Dimension(this.getWidth(), 16));
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
		
		this.add(statusPanel, BorderLayout.PAGE_END);
		

		// Cierre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Panel con los tabs
		tabPanel = new JTabbedPane();
		this.add(tabPanel, BorderLayout.CENTER);
		addPanel = new AddPanel();
		tabPanel.addTab("Añadir", addPanel);
		viewPanel = new ViewPanel();
		tabPanel.addTab("Ver datos", viewPanel);

		lookAndFeel();

	}

	/**
	 * Aplicará el LookAndFeel del sistema al frame
	 */
	private void lookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Diseño del panel con la interfaz para ver los datos
	 * 
	 * @author mntjcmrc
	 * 
	 */
	protected class ViewPanel extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 7746786162624050015L;

		protected ViewPanel() {
			this.add(new JLabel("viewTest"));
		}

	}
}