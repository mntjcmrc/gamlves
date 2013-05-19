package org.gamlves.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
//import javax.swing.JScrollPane;

import org.gamlves.data.Datos;

public class FilterFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2891889069831606564L;
	/**
	 * Anchura del frame
	 */
	private int WIDTH = 150;
	/**
	 * Altura del frame
	 */
	private int HEIGHT = 280;
	/**
	 * Array con los JCheckbox
	 */
	protected static ArrayList<JCheckBox> checkboxes;

	/**
	 * Crea el frame, lo sitúa exactamente a la izquierda del frame principal,
	 * teniendo todos las chekboxes marcadas
	 */
	protected FilterFrame() {
		setTitle("Géneros");
		setLayout(new BorderLayout());

		// Lugar en la pantalla
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		setSize(new Dimension(WIDTH, HEIGHT));
		setResizable(false);
		int X = ((screen.width / 2) - (WIDTH / 2)) - (WIDTH / 2)
				- (MainFrame.WIDTH / 2);
		int Y = (screen.height / 2) - (HEIGHT / 2);
		setBounds(X, Y, WIDTH, HEIGHT);

		// JScrollPane scrollPane = new JScrollPane();
		JPanel panelFilter = new JPanel();
		panelFilter.setLayout(new BoxLayout(panelFilter, BoxLayout.PAGE_AXIS));

		checkboxes = new ArrayList<JCheckBox>();
		for (int i = 1; i < Datos.GENEROS.length; i++) {
			JCheckBox check = new JCheckBox(Datos.GENEROS[i]);
			check.setSelected(true);
			panelFilter.add(check);
			checkboxes.add(check);
		}

		JButton apply = new JButton("Aplicar");
		apply.addActionListener(Actions.applyFilter);
		panelFilter.add(apply);
		// scrollPane.getViewport().add(panelFilter);
		// this.add(scrollPane, BorderLayout.CENTER);
		this.add(panelFilter, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		Actions.systemLookAndFeel();
	}

	/**
	 * @return Vector con los géneros que hay que mostrar
	 */
	protected static Vector<String> getFilterGeneros() {
		Vector<String> generos = new Vector<String>();
		for (JCheckBox cb : checkboxes) {
			if (cb.isSelected()) {
				generos.add(cb.getText());
			}
		}
		return generos;
	}

	/**
	 * Reinicia la posición del frame, se hace esto porque al desparecer y
	 * aparecer se solía reiniciar a la posición 0,0
	 */
	protected void resetBounds() {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		setSize(new Dimension(WIDTH, HEIGHT));
		setResizable(false);
		int X = ((screen.width / 2) - (WIDTH / 2)) - (WIDTH / 2)
				- (MainFrame.WIDTH / 2);
		int Y = (screen.height / 2) - (HEIGHT / 2);
		setBounds(X, Y, WIDTH, HEIGHT);

	}
}