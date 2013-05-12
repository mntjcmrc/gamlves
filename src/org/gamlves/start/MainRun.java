package org.gamlves.start;

import org.gamlves.gui.MainFrame;

public class MainRun implements Runnable {
	public static MainFrame mainFrame;

	public void run(){
		mainFrame.setVisible(true);
	}
}
