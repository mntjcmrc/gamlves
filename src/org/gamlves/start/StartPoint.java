package org.gamlves.start;

import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;
import org.gamlves.gui.Splash;

/**
 * Desde aquí se inicia el programa
 * 
 * @author mntjcmrc
 * 
 */
public class StartPoint {

	public static Splash splash;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		showSplash();
		try {
			EventQueue.invokeAndWait(new LoadThread());
			EventQueue.invokeAndWait(new LoginRun());
		} catch (InvocationTargetException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Comentar esta línea para ver que la JProgressBar si funciona
		hideSplash();
	}

	private static void showSplash() {
		splash = new Splash();
		splash.setVisible(true);

	}

	private static void hideSplash() {
		splash.setVisible(false);
	}
}