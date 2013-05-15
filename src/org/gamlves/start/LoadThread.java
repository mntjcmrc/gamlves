package org.gamlves.start;

import org.gamlves.data.Datos;

/**
 * Se cargará al empezar mostrando un frame con una progressbar en base a la
 * carga de los datos en memoria
 * 
 * @author mntjcmrc
 * 
 */
public class LoadThread extends Thread {

	public void run() {
		StartPoint.splash.progress.setIndeterminate(false);
		StartPoint.splash.progress.setStringPainted(true);
		StartPoint.splash.progress.setBorderPainted(true);
		new Thread(new Runnable() {

			@Override
			public void run() {
				Datos.loadData();
				for (int i = 0; i <= 100; i++) {
					StartPoint.splash.progress.setValue(i);
				}
			}

		}).start();
	}
}