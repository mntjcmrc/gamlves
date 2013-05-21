package org.gamlves.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Métodos para convertir los datos guardados en memoria en un archivo de texto
 * 
 * @author mntjcmrc
 * 
 */
public class ToText {

	/**
	 * Nombre en minúsculas del sistema operativo
	 */
	private static String OS = System.getProperty("os.name").toLowerCase();
	/**
	 * Carpeta del usuario del sistema que ejecuta el programa
	 */
	private static File userFolder = new File(System.getProperty("user.home"));
	/**
	 * Carpeta del programa, por defecto será .gamlves en la carpeta del usuario
	 */
	private static File appFolder;
	/**
	 * Fichero de texto donde se importarán los datos de los usuarios en memoria
	 */
	private static File usuarios;
	/**
	 * Fichero de texto donde se importarán los datos de los usuarios en memoria
	 */
	private static File juegos;

	/**
	 * @return Si se ha comprobado la existencia de la carpeta y creado la
	 *         carpeta
	 */
	public static boolean checkAndCreateAppFolder() {
		if (checkAppFolder()) {
			return true;
		} else {
			return createAppFolder();
		}

	}

	/**
	 * @return Si la carpeta del programa existe o no
	 */
	private static boolean checkAppFolder() {

		if (OS.startsWith("w")) {
			appFolder = new File(userFolder.getAbsolutePath() + "\\.gamlves");
		} else if (OS.startsWith("l")) {
			appFolder = new File(userFolder.getAbsolutePath() + "/.gamlves");
		}

		return appFolder.exists();
	}

	/**
	 * @return Si se ha podido crear la carpeta del programa
	 */
	private static boolean createAppFolder() {
		return appFolder.mkdir();
	}

	/**
	 * Si los ficheros existen
	 * 
	 * @param usuario
	 *            Si el fichero a crear es el de usuario o no
	 * @return Si se ha creado el fichero correctamente
	 */
	public static boolean dataToText(boolean usuario) {
		boolean filesExist;

		if (usuario) {
			filesExist = checkFile(true);

			if (filesExist) {
				usuarios.delete();
			}
			try {
				usuarios.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return writeData(createDataUsuario(), usuarios);
		} else {
			filesExist = checkFile(false);

			if (filesExist) {
				juegos.delete();
			}
			try {
				juegos.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return writeData(createDataJuego(), juegos);
		}
	}

	/**
	 * Comprueba si uno de los ficheros donde se importarán los datos existe
	 * 
	 * @param usuario
	 *            Si el fichero a comprobar es el de usuarios o no
	 * @return Si el fichero existe o no
	 */
	private static boolean checkFile(boolean usuario) {
		if (usuario) {
			if (OS.startsWith("w")) {
				usuarios = new File(appFolder.getAbsolutePath()
						+ "\\usuarios.txt");
			} else if (OS.startsWith("l")) {
				usuarios = new File(appFolder.getAbsolutePath()
						+ "/usuarios.txt");
			}

			return usuarios.exists();
		} else {
			if (OS.startsWith("w")) {
				juegos = new File(appFolder.getAbsolutePath() + "\\juegos.txt");
			} else if (OS.startsWith("l")) {
				juegos = new File(appFolder.getAbsolutePath() + "/juegos.txt");
			}

			return juegos.exists();
		}

	}

	/**
	 * @return Todos los datos de los usuarios en formato de texto
	 */
	private static String createDataUsuario() {
		String dataUsuario = "Usuarios\n========\n\n";
		ArrayList<Usuario> usuarios = Datos.get_usuarios();
		int size = usuarios.size();

		for (int i = 0; i < size; i++) {
			dataUsuario = dataUsuario + usuarios.get(i).toString() + "\n\n";
		}

		return dataUsuario;
	}

	/**
	 * @return Todos los datos de los juegos en formato de texto
	 */
	private static String createDataJuego() {
		String dataJuego = "Juegos\n======\n\n";
		ArrayList<Juego> juegos = Datos.get_juegos();
		int size = juegos.size();

		for (int i = 0; i < size; i++) {
			dataJuego = dataJuego + juegos.get(i).toString() + "\n\n";
		}

		return dataJuego;
	}

	/**
	 * @param data
	 *            Datos en forma de String para escribir en un fichero
	 * @param output
	 *            Fichero en el que se escribirán los datos
	 * @return Si se ha podido escribir o no
	 */
	private static boolean writeData(String data, File output) {
		Writer writer = null;

		try {
			writer = new BufferedWriter(new FileWriter(output));
			writer.write(data);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return false;
	}
}