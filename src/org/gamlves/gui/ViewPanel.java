package org.gamlves.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.gamlves.data.Datos;
import org.gamlves.data.Login;

/**
 * Diseño del panel con la interfaz para ver los datos
 * 
 * @author mntjcmrc
 * 
 */
public class ViewPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7746786162624050015L;

	/**
	 * Panel con los diversos paneles para ver datos
	 */
	private JPanel panelCards;
	/**
	 * Panel con la tabla con todos los usuarios
	 */
	private JPanel panelViewUsuario;
	/**
	 * Panel con la tabla con todos los juegos
	 */
	private JPanel panelViewJuego;
	protected JRadioButton rViewUsuario;
	protected JRadioButton rViewJuego;
	protected JRadioButton rViewLibrary;
	/**
	 * Panel con la tabla con los juegos del usuario elegido en la combobox
	 */
	private JPanel panelViewLibrary;
	/**
	 * Combobox para elegir de que usuario se quieren ver los juegos
	 */
	protected JComboBox<String> comboUserLibrary;
	protected JTable tableViewUsuario;
	protected JTable tableViewJuego;
	/**
	 * Tabla con los juegos de un usuario concreto
	 */
	protected JTable tableViewLibrary;
	/**
	 * Panel con los checkbox para filtrar el conjunto de juegos por géneros
	 */
	protected JPanel checkGenero;
	/**
	 * Constante para nombrar el panel con la tabla de los usuarios
	 */
	private final String USUARIO = "Usuario";
	/**
	 * Constante para nombrar el panel con la tabla de los juegos
	 */
	private final String JUEGO = "Juego";
	/**
	 * Constante para nombrar el panel con la tabla con los juegos de un usuario
	 * concreto
	 */
	private final String LIBRARY = "Biblioteca";

	protected ViewPanel(boolean admin) {
		if (admin) {
			// Modo admin

			// Modelos para las tablas

			// Opciones para qué ver
			this.setLayout(new BorderLayout());
			JPanel opcionesAdmin = new JPanel();
			opcionesAdmin.setLayout(new FlowLayout());

			rViewUsuario = new JRadioButton(USUARIO);
			rViewUsuario.addActionListener(Actions.showViewUsuario);
			rViewJuego = new JRadioButton(JUEGO);
			rViewJuego.addActionListener(Actions.showViewJuego);
			// rViewJuego.addChangeListener(Actions.showFilterAdmin);
			rViewLibrary = new JRadioButton("Biblioteca");
			rViewLibrary.addActionListener(Actions.showViewLibrary);
			// rViewLibrary.addChangeListener(Actions.showFilterAdmin);

			ButtonGroup view = new ButtonGroup();
			view.add(rViewUsuario);
			view.add(rViewJuego);
			view.add(rViewLibrary);
			view.setSelected(rViewUsuario.getModel(), true);

			opcionesAdmin.add(rViewUsuario);
			opcionesAdmin.add(rViewJuego);
			opcionesAdmin.add(rViewLibrary);

			this.add(opcionesAdmin, BorderLayout.PAGE_START);

			// Organización de las cartas
			panelCards = new JPanel(new CardLayout());

			// Panel para ver los datos de los usuarios
			panelViewUsuario = new JPanel(new BorderLayout());
			JScrollPane scrollTableUsuario = new JScrollPane();
			tableViewUsuario = new JTable(Datos.getUsuariosMetadata());
			tableViewUsuario.setEnabled(false);

			scrollTableUsuario.getViewport().add(tableViewUsuario);

			panelViewUsuario.add(scrollTableUsuario, BorderLayout.CENTER);

			// Panel para ver los datos de los juegos
			panelViewJuego = new JPanel(new BorderLayout());
			JScrollPane scrollTableJuego = new JScrollPane();
			tableViewJuego = new JTable(Datos.getJuegosMetadata());
			tableViewJuego.setEnabled(false);

			scrollTableJuego.getViewport().add(tableViewJuego);

			panelViewJuego.add(scrollTableJuego, BorderLayout.CENTER);

			// Panel para ver las bibliotecas de juegos de los usuarios
			panelViewLibrary = new JPanel(new BorderLayout());
			comboUserLibrary = new JComboBox<String>(Datos.getUsuarios());
			comboUserLibrary.addItemListener(Actions.showUserLibrary);
			JScrollPane scrollTableLibrary = new JScrollPane();
			tableViewLibrary = new JTable(
					Datos.getLibraryMetadata((String) comboUserLibrary
							.getSelectedItem()));
			tableViewLibrary.setEnabled(false);

			scrollTableLibrary.getViewport().add(tableViewLibrary);
			panelViewLibrary.add(scrollTableLibrary, BorderLayout.CENTER);
			panelViewLibrary.add(comboUserLibrary, BorderLayout.PAGE_END);

			// Se añaden los paneles como cartas
			panelCards.add(panelViewUsuario, USUARIO);
			panelCards.add(panelViewJuego, JUEGO);
			panelCards.add(panelViewLibrary, LIBRARY);

			this.add(panelCards, BorderLayout.CENTER);

		} else {
			// Modo user

			panelViewLibrary = new JPanel(new BorderLayout());
			JScrollPane scrollTableLibrary = new JScrollPane();
			tableViewLibrary = new JTable(Datos.getLibraryMetadata(Login._user));
			tableViewLibrary.setEnabled(false);
			scrollTableLibrary.getViewport().add(tableViewLibrary);
			panelViewLibrary.add(scrollTableLibrary, BorderLayout.CENTER);

			this.add(new JLabel("Biblioteca de " + Login._nombre),
					BorderLayout.PAGE_START);
			this.add(panelViewLibrary, BorderLayout.CENTER);
			this.add(new JLabel("    "), BorderLayout.PAGE_END);
		}
	}

	/**
	 * Se visualizará el panel para que el admin vea los datos de los usuarios
	 */
	protected void panelViewUsuario() {
		CardLayout layout = (CardLayout) panelCards.getLayout();
		layout.show(panelCards, USUARIO);
	}

	/**
	 * Se visualizará el panel para que el admin vea los datos de los juegos
	 */
	protected void panelViewJuego() {
		CardLayout layout = (CardLayout) panelCards.getLayout();
		layout.show(panelCards, JUEGO);
	}

	/**
	 * Se visualizará el panel para que el admin vea los datos de los juegos de
	 * un usuario
	 */
	protected void panelViewLibrary() {
		CardLayout layout = (CardLayout) panelCards.getLayout();
		layout.show(panelCards, LIBRARY);
	}
}