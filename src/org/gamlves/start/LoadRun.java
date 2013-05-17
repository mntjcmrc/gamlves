package org.gamlves.start;

/**
 * Se cargará al empezar mostrando un frame con una progressbar en base a la
 * carga de los datos en memoria
 * 
 * @author mntjcmrc
 * 
 */
public class LoadRun implements Runnable {

	public void run() {
		StartPoint.splash.progress.setIndeterminate(false);
		StartPoint.splash.progress.setStringPainted(true);
		StartPoint.splash.progress.setBorderPainted(true);
		new Thread(new Runnable() {

			@Override
			public void run() {
				// try {
				// Datos.loadData();
				// } catch (SQLException e) {
				// JOptionPane.showMessageDialog(null,
				// "Ha fallado la carga de datos");
				// }
				for (int i = 0; i <= 100; i++) {
					StartPoint.splash.progress.setValue(i);
				}
			}

		}).start();
	}
}