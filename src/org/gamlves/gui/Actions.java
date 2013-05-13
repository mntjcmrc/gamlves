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

import org.gamlves.data.Login;
import org.gamlves.data.Seguridad;
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
	public static ActionListener loginAceptar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			if (LoginRun.loginFrame.txtUser.getText().length() > 0
					&& LoginRun.loginFrame.txtPass.getText().length() > 0) {
				if (Seguridad.checkLogin(LoginRun.loginFrame.txtUser.getText(),
						LoginRun.loginFrame.txtPass.getText())) {
					String user = LoginRun.loginFrame.txtUser.getText();
					LoginRun.loginFrame.setVisible(false);

					if (user.equals("admin")) {
						MainRun.mainFrame = new MainFrame(true);
					} else {
						MainRun.mainFrame = new MainFrame(false);
					}
					Login._user = user;
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
	protected static ActionListener loginCancelar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);

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
}