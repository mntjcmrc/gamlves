package org.gamlves.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.gamlves.data.Datos;
import org.gamlves.data.Login;
import org.gamlves.data.Seguridad;
import org.gamlves.start.LoginRun;
import org.gamlves.start.LoginRun;
import org.gamlves.start.MainRun;

public class Actions {
	public static ActionListener loginAceptar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			MainFrame mainFrame;

			if (LoginRun.loginFrame.txtUser.getText().length() > 0
					&& LoginRun.loginFrame.txtPass.getText().length() > 0) {
				if (Seguridad.checkLogin(LoginRun.loginFrame.txtUser.getText(),
						LoginRun.loginFrame.txtPass.getText())) {
					String user = LoginRun.loginFrame.txtUser.getText();
					LoginRun.loginFrame.setVisible(false);

					if (user.equals("admin")) {
						MainRun.mainFrame = new MainFrame(true);
					} else {
						mainFrame = new MainFrame(false);
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
	protected static ActionListener loginCancelar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);

		}

	};
	
	protected static void centerFrame(JFrame frame, int widthWindow, int heightWindow){
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int X = (screen.width / 2) - (widthWindow / 2);
		int Y = (screen.height / 2) - (heightWindow / 2);
		frame.setSize(new Dimension(widthWindow, heightWindow));
		frame.setResizable(false);
		frame.setBounds(X, Y, widthWindow, heightWindow);
	}

}