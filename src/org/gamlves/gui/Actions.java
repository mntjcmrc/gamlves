package org.gamlves.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.gamlves.data.Datos;
import org.gamlves.data.Login;
import org.gamlves.data.Seguridad;
import org.gamlves.data.Usuario;
import org.gamlves.start.LoginRun;
import org.gamlves.start.MainRun;

public class Actions {
	/**
	 * Acción a ejecutar cuando el botón aceptar del frame de login es pulsado.
	 * Comprueba si el usuario ha escrito algo en los TextField, si no lo ha
	 * hecho saldrá un mensaje avisándolo. En caso de hacerlo, se comprobará si
	 * se tienen esos datos en memoria. Si no están, avisará de usuario y/o
	 * contraseña incorrectos. Si están procederá a loguearse, cerrar el frame
	 * de login y lanzar el frame principal.
	 */
	protected static ActionListener loginAceptar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			if (LoginRun.loginFrame.txtUser.getText().length() > 0
					&& LoginRun.loginFrame.txtPass.getText().length() > 0) {
				if (Seguridad.checkLogin(LoginRun.loginFrame.txtUser.getText(),
						LoginRun.loginFrame.txtPass.getText())) {
					String user = LoginRun.loginFrame.txtUser.getText();
					LoginRun.loginFrame.setVisible(false);

					Usuario usuario = Datos.searchUser(user);
					Login._user = user;
					Login._nombre = usuario.get_nombre();

					if (user.equals("admin")) {
						MainRun.mainFrame = new MainFrame(true);
					} else {
						MainRun.mainFrame = new MainFrame(false);
					}

					EventQueue.invokeLater(new MainRun());

				} else {
					JOptionPane
							.showMessageDialog(null,
									"El nombre de usuario y/o la contraseña no son correctos");
					LoginRun.loginFrame.txtUser.setText("");
					LoginRun.loginFrame.txtPass.setText("");
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Debes escribir un usuario y una contraseña");
			}

		}

	};

	/**
	 * Acción a ejecutar cuando el botón cancelar del frame de login es pulsado.
	 * Cierra el programa.
	 */
	protected static ActionListener cerrar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);

		}

	};

	/**
	 * Acción que lanzará el trigger de opciones del panel de añadir del admin.
	 * Versión de Usuario.
	 */
	protected static ActionListener showAddUsuario = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			MainRun.mainFrame.addPanel.panelAddUsuario();

		}

	};

	/**
	 * Acción que lanzará el trigger de opciones del panel de añadir del admin.
	 * Versión de Juego.
	 */
	protected static ActionListener showAddJuego = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			MainRun.mainFrame.addPanel.panelAddJuego();

		}
	};

	/**
	 * Centra en la pantalla el JFrame dado
	 * 
	 * @param frame
	 *            JFrame a centrar
	 * @param widthWindow
	 *            Anchura del frame
	 * @param heightWindow
	 *            Altura del frame
	 */
	protected static void centerFrame(JFrame frame, int widthWindow,
			int heightWindow) {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int X = (screen.width / 2) - (widthWindow / 2);
		int Y = (screen.height / 2) - (heightWindow / 2);
		frame.setSize(new Dimension(widthWindow, heightWindow));
		frame.setResizable(false);
		frame.setBounds(X, Y, widthWindow, heightWindow);
	}

	/**
	 * Aplicará el LookAndFeel del sistema al frame
	 */
	protected static void systemLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Recoge los datos introducidos por el admin e inicia una transacción para
	 * crear un usuario en la base de datos y guardarlo en memoria. Saldrán
	 * mensajes por cada error posible
	 */
	protected static ActionListener addUserAceptar = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String nombre = MainRun.mainFrame.addPanel.txtNombreUsuario
					.getText();
			String user = MainRun.mainFrame.addPanel.txtUser.getText();
			String pass = MainRun.mainFrame.addPanel.txtPass.getText();

			if (nombre.length() > 0 && user.length() > 0 && pass.length() > 0) {
				int transaction = Datos.userTransaction(nombre, user, pass);

				switch (transaction) {
				case 0:
					// System.out.println("La transacción se ha realizado correctamente");
					JOptionPane.showMessageDialog(MainRun.mainFrame,
							"El usuario " + user
									+ " se ha añadido correctamente");
					break;

				case 1:
					JOptionPane
							.showMessageDialog(MainRun.mainFrame,
									"Fallo al comprobar la existencia del usuario en la base de datos");
					break;

				case 2:
					JOptionPane.showMessageDialog(MainRun.mainFrame,
							"El usuario " + user + " ya existe");
					break;

				case 3:
					JOptionPane.showMessageDialog(MainRun.mainFrame,
							"Fallo al añadir el usuario a la base de datos");
					break;

				case 4:
					JOptionPane
							.showMessageDialog(MainRun.mainFrame,
									"Fallo al volver a pedir los datos a la base de datos");
					break;
				}

			} else {
				JOptionPane
						.showMessageDialog(MainRun.mainFrame,
								"Tienes que introducir todos los datos para poder crear el usuario");
			}

		}
	};

	/**
	 * Limpia los campos de texto del formulario para añadir usuarios
	 */
	protected static ActionListener addUserLimpiar = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			MainRun.mainFrame.addPanel.txtNombreUsuario.setText("");
			MainRun.mainFrame.addPanel.txtUser.setText("");
			MainRun.mainFrame.addPanel.txtPass.setText("");
		}
	};

	/**
	 * Recoge los datos introducidos por el admin e inicia una transacción para
	 * crear un usuario en la base de datos y guardarlo en memoria. Saldrán
	 * mensajes por cada error posible
	 */
	protected static ActionListener addJuegoAceptar = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String nombre = MainRun.mainFrame.addPanel.txtNombreJuego.getText();
			String genero = (String) MainRun.mainFrame.addPanel.comboGeneroJuego
					.getSelectedItem();

			if (nombre.length() > 0 && !(genero.equals(Datos.GENEROS[0]))) {
				int transaction = Datos.juegoTransaction(nombre, genero);

				switch (transaction) {
				case 0:
					// System.out.println("La transacción se ha realizado correctamente");
					JOptionPane.showMessageDialog(MainRun.mainFrame,
							"El juego " + nombre
									+ " se ha añadido correctamente");
					break;

				case 1:
					JOptionPane
							.showMessageDialog(MainRun.mainFrame,
									"Fallo al comprobar la existencia del juego en la base de datos");
					break;

				case 2:
					JOptionPane.showMessageDialog(MainRun.mainFrame,
							"El juego " + nombre + " ya existe");
					break;

				case 3:
					JOptionPane.showMessageDialog(MainRun.mainFrame,
							"Fallo al añadir el juego a la base de datos");
					break;

				case 4:
					JOptionPane
							.showMessageDialog(MainRun.mainFrame,
									"Fallo al volver a pedir los datos a la base de datos");
					break;
				}

			} else {
				JOptionPane
						.showMessageDialog(MainRun.mainFrame,
								"Tienes que introducir todos los datos para poder crear el juego");
			}

		}
	};

	/**
	 * Limpia los campos de texto del formulario para añadir juegos
	 */
	protected static ActionListener addJuegoLimpiar = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			MainRun.mainFrame.addPanel.txtNombreJuego.setText("");
		}
	};
}