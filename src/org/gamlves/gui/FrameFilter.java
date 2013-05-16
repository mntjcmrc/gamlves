package org.gamlves.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.gamlves.data.Datos;

public class FrameFilter extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2891889069831606564L;
	private int WIDTH = 100;
	private int HEIGHT = 200;

	protected FrameFilter() {
		setTitle("Filtrado por géneros");
		setLayout(new BorderLayout());
		
		// Lugar en la pantalla
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		
		setSize(new Dimension(WIDTH, HEIGHT));
		setResizable(false);
		int X = ((screen.width / 2) - (WIDTH / 2)) - 225;
		int Y = (screen.height / 2) - (HEIGHT / 2);
		setBounds(X, Y, WIDTH, HEIGHT);
		
		
		JPanel panelFilter = new JPanel();
		panelFilter.setLayout(new BoxLayout(panelFilter, BoxLayout.PAGE_AXIS));

		for (int i = 0; i < Datos.GENEROS.length; i++) {
			JCheckBox check = new JCheckBox(Datos.GENEROS[i]);
			check.setActionCommand(Datos.GENEROS[i]);
			check.setSelected(true);
			panelFilter.add(check);
		}
		this.add(panelFilter, BorderLayout.CENTER);
	}
}