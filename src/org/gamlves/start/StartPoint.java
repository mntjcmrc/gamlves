package org.gamlves.start;

import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.gamlves.data.Datos;
import org.gamlves.gui.Splash;

/**
 * Desde aquí se inicia el programa
 * 
 * @author mntjcmrc
 * 
 */
public class StartPoint {

	/**
	 * Frame con el splash del programa, sólo es una barra de progreso
	 */
	public static Splash splash;

	/**
	 * Muestra el splash, carga los datos en memoria y lanza la ejecución del
	 * frame de login
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		showSplash();

		try {
			SwingUtilities.invokeLater(new LoadRun());
			Datos.loadData();
			EventQueue.invokeAndWait(new LoginRun());
		} catch (InvocationTargetException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error al cargar datos");
			hideSplash();
		}
		// Comentar esta línea para ver que la JProgressBar si funciona
		hideSplash();
	}

	/**
	 * Crea el splash y lo muestra
	 */
	private static void showSplash() {
		splash = new Splash();
		splash.setVisible(true);

	}

	/**
	 * Quita la visibilidad del splash
	 */
	private static void hideSplash() {
		splash.setVisible(false);
	}
}