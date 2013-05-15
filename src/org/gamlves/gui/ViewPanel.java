package org.gamlves.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.gamlves.data.Datos;

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

	private JPanel panelCards;
	private JPanel panelViewUsuario;
	private JPanel panelViewJuego;
	private final String USUARIO = "Usuario";
	private final String JUEGO = "Juego";

	protected ViewPanel(boolean admin) {
		if (admin) {
			// Modo admin

			// Modelos para las tablas

			// Opciones para qué ver
			this.setLayout(new BorderLayout());
			JPanel opcionesAdmin = new JPanel();
			opcionesAdmin.setLayout(new FlowLayout());
			JRadioButton rViewUsuario = new JRadioButton(USUARIO);
			rViewUsuario.addActionListener(Actions.showViewUsuario);

			JRadioButton rViewJuego = new JRadioButton(JUEGO);
			rViewJuego.addActionListener(Actions.showViewJuego);

			ButtonGroup view = new ButtonGroup();
			view.add(rViewUsuario);
			view.add(rViewJuego);
			view.setSelected(rViewUsuario.getModel(), true);

			opcionesAdmin.add(rViewUsuario);
			opcionesAdmin.add(rViewJuego);

			this.add(opcionesAdmin, BorderLayout.PAGE_START);

			// Organización de las cartas
			panelCards = new JPanel(new CardLayout());

			// Panel para ver los datos de los usuarios
			panelViewUsuario = new JPanel(new BorderLayout());
			JScrollPane scrollTableUsuario = new JScrollPane();
			JTable tableViewUsuario = new JTable(Datos.getUsuariosMetadata());

			scrollTableUsuario.getViewport().add(tableViewUsuario);

			panelViewUsuario.add(scrollTableUsuario, BorderLayout.CENTER);

			// Panel para ver los datos de los juegos
			panelViewJuego = new JPanel(new BorderLayout());
			JScrollPane scrollTableJuego = new JScrollPane();
			JTable tableViewJuego = new JTable(Datos.getJuegosMetadata());
			
			scrollTableJuego.getViewport().add(tableViewJuego);
			
			panelViewJuego.add(scrollTableJuego, BorderLayout.CENTER);

			// Se añaden los paneles como cartas
			panelCards.add(panelViewUsuario, USUARIO);
			panelCards.add(panelViewJuego, JUEGO);

			this.add(panelCards, BorderLayout.CENTER);

		} else {
			// Modo user

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
}