package org.gamlves.start;

import javax.swing.JFrame;

import org.gamlves.gui.LoginFrame;

public class LoginRun implements Runnable {
	public static LoginFrame loginFrame;

	public void run() {
		loginFrame = new LoginFrame();
		loginFrame.setVisible(true);
		loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
