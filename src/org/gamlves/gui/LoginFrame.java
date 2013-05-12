package org.gamlves.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.gamlves.data.Login;
import org.gamlves.data.Seguridad;

public class LoginFrame extends JFrame {

	private JTextField txtUser, txtPass;
	private JLabel lblUser, lblPass;
	private JButton btnAceptar, btnCancelar;
	String usuario, pass;

	/**
	 *  
	 */
	public LoginFrame() {
		
		// Título y layout
		setTitle("Login en Gamlves");
		setLayout(new FlowLayout());
		
		// Tamaño
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int widthWindow = 500;
		int heightWindow = 120;
		int X = (screen.width / 2) - (widthWindow / 2);
		int Y = (screen.height / 2) - (heightWindow / 2);
		setSize(new Dimension(widthWindow, heightWindow));
		setResizable(false);
		setBounds(X, Y, widthWindow, heightWindow);

		// Username
		lblUser = new JLabel("Usuario: ");
		txtUser = new JTextField(10);
		this.add(lblUser);
		this.add(txtUser);

		// Contraseña
		lblPass = new JLabel("Contraseña: ");
		txtPass = new JPasswordField(15);
		this.add(lblPass);
		this.add(txtPass);
		
		// Botones
		btnAceptar = new JButton("Aceptar");
		btnCancelar = new JButton("Cancelar");
		
		this.add(btnAceptar);
		this.add(btnCancelar);
		
		ActionListener aceptar = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainFrame mainFrame;
				
				if(txtUser.getText().length() > 0 && txtPass.getText().length() > 0){
					if(Seguridad.checkLogin(txtUser.getText(), txtPass.getText())){
						String user = txtUser.getText();
						setVisible(false);
						if (user.equals("admin")){
							mainFrame = new MainFrame(true);
						} else {
							mainFrame = new MainFrame(false);
						}
						Login._user = user;
						mainFrame.setVisible(true);
						
					} else {
						JOptionPane.showMessageDialog(null, "El nombre de usuario y/o la contraseña no son correctos");
						txtUser.setText("");
						txtPass.setText("");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Debes escribir un usuario y una contraseña");
				}
				
			}
			
		};
		btnAceptar.addActionListener(aceptar);
		
		ActionListener cancelar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
			
		};
		btnCancelar.addActionListener(cancelar);
		
	}
}