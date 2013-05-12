package org.gamlves.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

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
				if (Seguridad.checkLogin(
						LoginRun.loginFrame.txtUser.getText(),
						LoginRun.loginFrame.txtPass.getText())) {
					String user = LoginRun.loginFrame.txtUser.getText();
					LoginRun.loginFrame.setVisible(false);
					if (user.equals("admin")) {
						MainRun.mainFrame = new MainFrame(true);
					} else {
						mainFrame = new MainFrame(false);
					}
					Login._user = user;
					MainRun.mainFrame.setVisible(true);

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


}