package org.gamlves.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.gamlves.data.Datos;

public class FrameFilter extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2891889069831606564L;
	private int WIDTH = 150;
	private int HEIGHT = 280;

	protected FrameFilter() {
		setTitle("Filtrado por géneros");
		setLayout(new BorderLayout());

		// Lugar en la pantalla
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		setSize(new Dimension(WIDTH, HEIGHT));
		setResizable(false);
		int X = ((screen.width / 2) - (WIDTH / 2)) - (WIDTH / 2)
				- (MainFrame.WIDTH / 2);
		int Y = (screen.height / 2) - (HEIGHT / 2);
		setBounds(X, Y, WIDTH, HEIGHT);

		JScrollPane scrollPane = new JScrollPane();
		JPanel panelFilter = new JPanel();
		panelFilter.setLayout(new BoxLayout(panelFilter, BoxLayout.PAGE_AXIS));

		for (int i = 1; i < Datos.GENEROS.length; i++) {
			JCheckBox check = new JCheckBox(Datos.GENEROS[i]);
			check.setActionCommand(Datos.GENEROS[i]);
			check.setSelected(true);
			panelFilter.add(check);
		}
		JButton apply = new JButton("Aplicar");
		apply.addActionListener(Actions.applyFilter);
		panelFilter.add(apply);
		scrollPane.getViewport().add(panelFilter);
		this.add(scrollPane, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
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