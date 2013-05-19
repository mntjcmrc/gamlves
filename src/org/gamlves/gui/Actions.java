package org.gamlves.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableModel;

import org.gamlves.data.Datos;
import org.gamlves.data.Juego;
import org.gamlves.data.Login;
import org.gamlves.data.Seguridad;
import org.gamlves.data.Usuario;
import org.gamlves.start.LoginRun;
import org.gamlves.start.MainRun;

/**
 * Aquí estarán todos los ActionListener necesarios
 * 
 * @author mntjcmrc
 * 
 */
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

			if (LoginRun.loginFrame.get_txtUser().length() > 0
					&& LoginRun.loginFrame.get_txtPass().length() > 0) {
				if (Seguridad.checkLogin(LoginRun.loginFrame.get_txtUser(),
						LoginRun.loginFrame.get_txtPass())) {
					String user = LoginRun.loginFrame.get_txtUser();
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
					LoginRun.loginFrame.set_txtUser("");
					LoginRun.loginFrame.set_txtPass("");
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
	 * Acción que lanzará el trigger de opciones del panel de ver del admin.
	 * Versión de Usuario.
	 */
	protected static ActionListener showViewUsuario = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			MainRun.mainFrame.viewPanel.panelViewUsuario();

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
	 * Acción que lanzará el trigger de opciones del panel de ver del admin.
	 * Versión de Juego.
	 */
	protected static ActionListener showViewJuego = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			MainRun.mainFrame.viewPanel.panelViewJuego();

		}
	};

	/**
	 * Acción que lanzará el trigger de opciones del panel de ver del admin.
	 * Versión de biblioteca.
	 */
	protected static ActionListener showViewLibrary = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			MainRun.mainFrame.viewPanel.panelViewLibrary();
		}

	};

	/**
	 * Acción usada para modificar la tabla con los juegos de un usuario del
	 * combobox
	 */
	protected static ItemListener showUserLibrary = new ItemListener() {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				String user = (String) e.getItem();
				MainRun.mainFrame.viewPanel.tableViewLibrary.setModel(Datos
						.getLibraryMetadata(user));
			}
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
			String nombre = MainRun.mainFrame.addPanel.get_txtNombreUsuario();
			String user = MainRun.mainFrame.addPanel.get_txtUser();
			String pass = MainRun.mainFrame.addPanel.get_txtPass();

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
			MainRun.mainFrame.addPanel.set_txtNombreUsuario("");
			MainRun.mainFrame.addPanel.set_txtUser("");
			MainRun.mainFrame.addPanel.set_txtPass("");
		}

	};

	protected static ActionListener modUserAceptar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			// PENDIENTE

		}

	};

	protected static ActionListener modJuegoAceptar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			// PENDIENTE
		}

	};

	private static void limpiarUser() {
		MainRun.mainFrame.addPanel.set_txtIDUser("");
		MainRun.mainFrame.addPanel.set_txtNombreUsuario("");
		MainRun.mainFrame.addPanel.set_txtUser("");
		MainRun.mainFrame.addPanel.set_txtPass("");
	}

	private static void limpiarJuego() {
		MainRun.mainFrame.addPanel.set_txtIDJuego("");
		MainRun.mainFrame.addPanel.set_txtNombreJuego("");
		MainRun.mainFrame.addPanel.set_comboGeneroJuego(0);
	}

	/**
	 * Limpia los campos de texto del formulario para añadir usuarios
	 */
	protected static ActionListener addUserLimpiar = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			limpiarUser();
		}
	};

	private static void loadDataUser() {
		int usuarios = Datos.get_usuariosSize();
		Usuario usuario;
		if (MainRun.mainFrame.addPanel.indexUser < 1) {
			MainRun.mainFrame.addPanel.btnPreviousUser.setEnabled(false);
		} else {
			MainRun.mainFrame.addPanel.btnPreviousUser.setEnabled(true);
		}
		if (MainRun.mainFrame.addPanel.indexUser == usuarios - 1) {
			MainRun.mainFrame.addPanel.btnNextUser.setEnabled(false);
		} else {
			MainRun.mainFrame.addPanel.btnNextUser.setEnabled(true);
		}
		usuario = Datos.get_usuarioIndex(MainRun.mainFrame.addPanel.indexUser);

		MainRun.mainFrame.addPanel.set_txtIDUser(usuario.get_id() + "");
		MainRun.mainFrame.addPanel.set_txtNombreUsuario(usuario.get_nombre());
		MainRun.mainFrame.addPanel.set_txtUser(usuario.get_user());
	}

	private static void loadDataJuego() {
		int juegos = Datos.get_juegosSize();
		Juego juego;
		if (MainRun.mainFrame.addPanel.indexJuego < 1) {
			MainRun.mainFrame.addPanel.btnPreviousJuego.setEnabled(false);
		} else {
			MainRun.mainFrame.addPanel.btnPreviousJuego.setEnabled(true);
		}
		if (MainRun.mainFrame.addPanel.indexJuego == juegos - 1) {
			MainRun.mainFrame.addPanel.btnNextJuego.setEnabled(false);
		} else {
			MainRun.mainFrame.addPanel.btnNextJuego.setEnabled(true);
		}
		juego = Datos.get_juegoIndex(MainRun.mainFrame.addPanel.indexJuego);

		MainRun.mainFrame.addPanel.set_txtIDJuego(juego.get_id() + "");
		MainRun.mainFrame.addPanel.set_txtNombreJuego(juego.get_nombre());
		MainRun.mainFrame.addPanel.set_comboGeneroJuego(Datos
				.searchGeneroIndex(juego.get_genero()));
	}

	protected static ActionListener previousUser = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			MainRun.mainFrame.addPanel.indexUser--;
			loadDataUser();
		}

	};

	protected static ActionListener nextUser = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			MainRun.mainFrame.addPanel.indexUser++;
			loadDataUser();
		}

	};

	protected static ActionListener changeToModUser = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int usuarios = Datos.get_usuariosSize();

			if (usuarios > 0) {

				// Cambios visuales y de ActionListeners
				MainRun.mainFrame.addPanel.btnChangeModeUser.setText("Añadir");
				MainRun.mainFrame.addPanel.disable_txtUser();

				MainRun.mainFrame.addPanel.btnLimpiarAddUsuario
						.setVisible(false);
				MainRun.mainFrame.addPanel.btnPreviousUser.setVisible(true);
				MainRun.mainFrame.addPanel.btnNextUser.setVisible(true);

				MainRun.mainFrame.addPanel.btnChangeModeUser
						.removeActionListener(Actions.changeToModUser);
				MainRun.mainFrame.addPanel.btnChangeModeUser
						.addActionListener(Actions.changeToAddUser);
				MainRun.mainFrame.addPanel.btnAceptarAddUsuario
						.removeActionListener(Actions.addUserAceptar);
				MainRun.mainFrame.addPanel.btnAceptarAddUsuario
						.removeActionListener(Actions.modUserAceptar);

				// Carga de datos del primer registro
				loadDataUser();
			} else {
				JOptionPane.showMessageDialog(MainRun.mainFrame,
						"No hay registros de usuarios para modificar");
			}
		}

	};
	
	protected static ActionListener previousJuego = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			MainRun.mainFrame.addPanel.indexJuego--;
			loadDataJuego();
		}

	};

	protected static ActionListener nextJuego = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			MainRun.mainFrame.addPanel.indexJuego++;
			loadDataJuego();
		}

	};

	protected static ActionListener changeToModJuego = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int juegos = Datos.get_juegosSize();

			if (juegos > 0) {

				// Cambios visuales y de ActionListeners
				MainRun.mainFrame.addPanel.btnChangeModeJuego.setText("Añadir");

				MainRun.mainFrame.addPanel.btnLimpiarAddJuego.setVisible(false);
				MainRun.mainFrame.addPanel.btnPreviousJuego.setVisible(true);
				MainRun.mainFrame.addPanel.btnNextJuego.setVisible(true);

				MainRun.mainFrame.addPanel.btnChangeModeJuego
						.removeActionListener(Actions.changeToModJuego);
				MainRun.mainFrame.addPanel.btnChangeModeJuego
						.addActionListener(Actions.changeToAddJuego);
				MainRun.mainFrame.addPanel.btnAceptarAddJuego
						.removeActionListener(Actions.addJuegoAceptar);
				MainRun.mainFrame.addPanel.btnAceptarAddJuego
						.addActionListener(Actions.modJuegoAceptar);

				// Carga de datos
				loadDataJuego();

			} else {
				JOptionPane.showMessageDialog(MainRun.mainFrame,
						"No hay registros de juegos para modificar");
			}
		}
	};

	protected static ActionListener changeToAddUser = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			MainRun.mainFrame.addPanel.btnChangeModeUser.setText("Modificar");
			MainRun.mainFrame.addPanel.enable_txtUser();

			MainRun.mainFrame.addPanel.btnLimpiarAddUsuario.setVisible(true);
			MainRun.mainFrame.addPanel.btnPreviousUser.setVisible(false);
			MainRun.mainFrame.addPanel.btnNextUser.setVisible(false);

			MainRun.mainFrame.addPanel.btnChangeModeUser
					.removeActionListener(Actions.changeToAddUser);
			MainRun.mainFrame.addPanel.btnChangeModeUser
					.addActionListener(Actions.changeToModUser);
			MainRun.mainFrame.addPanel.btnAceptarAddUsuario
					.addActionListener(Actions.addUserAceptar);

			limpiarUser();
		}

	};

	protected static ActionListener changeToAddJuego = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			MainRun.mainFrame.addPanel.btnChangeModeJuego.setText("Modificar");

			MainRun.mainFrame.addPanel.btnLimpiarAddJuego.setVisible(true);
			MainRun.mainFrame.addPanel.btnPreviousJuego.setVisible(false);
			MainRun.mainFrame.addPanel.btnNextJuego.setVisible(false);

			MainRun.mainFrame.addPanel.btnChangeModeJuego
					.removeActionListener(Actions.changeToAddJuego);
			MainRun.mainFrame.addPanel.btnChangeModeJuego
					.addActionListener(Actions.changeToModJuego);
			MainRun.mainFrame.addPanel.btnAceptarAddJuego
					.addActionListener(Actions.addJuegoAceptar);
			limpiarJuego();
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
			String nombre = MainRun.mainFrame.addPanel.get_txtNombreJuego();
			String genero = (String) MainRun.mainFrame.addPanel
					.get_comboGeneroJuego();

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
			MainRun.mainFrame.addPanel.set_txtNombreJuego("");
			MainRun.mainFrame.addPanel.set_comboGeneroJuego(0);

		}
	};

	/**
	 * Limpia los campos de texto del formulario para añadir juegos
	 */
	protected static ActionListener addJuegoLimpiar = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			limpiarJuego();
		}
	};

	/**
	 * Maneja la lista de juegos en la búsqueda del modo user
	 */
	protected static ActionListener buscarUsuarioJuego = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {

			deleteBuscarUsuarioJuego();

			String busqueda = MainRun.mainFrame.addPanel
					.get_txtBuscarUsuarioJuego();
			Vector<String> juegos = Datos.searchJuego(busqueda);
			int sizeJuegos = juegos.size();
			for (int i = 0; i < sizeJuegos; i++) {
				MainRun.mainFrame.addPanel.modelBuscarUsuarioJuego
						.addElement(juegos.get(i));
			}

		}
	};

	/**
	 * Elimina los datos en la JList de la búsqueda de juegos del usuario
	 */
	private static void deleteBuscarUsuarioJuego() {
		int sizeList = MainRun.mainFrame.addPanel.modelBuscarUsuarioJuego
				.getSize();
		for (int i = sizeList - 1; i > -1; i--) {
			MainRun.mainFrame.addPanel.modelBuscarUsuarioJuego.remove(i);
		}
	}

	/**
	 * Usado para restablecer la lista de juegos buscados
	 */
	protected static ActionListener delBuscarUsuarioJuego = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			deleteBuscarUsuarioJuego();
			MainRun.mainFrame.addPanel.set_txtBuscarUsuarioJuego("");
		}
	};

	/**
	 * Añade una relación entre el usuario logueado que ha añadido un juego a su
	 * biblioteca
	 */
	protected static ActionListener AddUsuarioJuego = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String nombreJuego = MainRun.mainFrame.addPanel.listBuscarUsuarioJuego
					.getSelectedValue();
			Juego juego = Datos.searchJuegoJ(nombreJuego);
			int transaction = Datos.usuariojuegoTransaction(juego.get_id(),
					Login._user);

			switch (transaction) {
			case 0:
				// System.out.println("La transacción se ha realizado correctamente");
				JOptionPane.showMessageDialog(MainRun.mainFrame,
						"La relación entre el juego " + nombreJuego
								+ " y el usuario " + Login._nombre
								+ " se ha realizado correctamente");
				break;

			case 1:
				JOptionPane
						.showMessageDialog(MainRun.mainFrame,
								"Fallo al comprobar la existencia de la relación en la base de datos");
				break;

			case 2:
				JOptionPane.showMessageDialog(MainRun.mainFrame,
						"La relación ya existe");
				break;

			case 3:
				JOptionPane.showMessageDialog(MainRun.mainFrame,
						"Fallo al añadir la relación a la base de datos");
				break;

			case 4:
				JOptionPane.showMessageDialog(MainRun.mainFrame,
						"Fallo al volver a pedir los datos a la base de datos");
				break;
			}
		}

	};

	protected static ChangeListener refreshTablesAdmin = new ChangeListener() {

		@Override
		public void stateChanged(ChangeEvent e) {
			if (e.getSource() instanceof JTabbedPane) {
				if (((JTabbedPane) e.getSource()).getSelectedComponent()
						.equals(MainRun.mainFrame.viewPanel)) {

					MainRun.mainFrame.viewPanel.tableViewUsuario.setModel(Datos
							.getUsuariosMetadata());
					MainRun.mainFrame.viewPanel.tableViewJuego.setModel(Datos
							.getJuegosMetadata());

					String user = (String) MainRun.mainFrame.viewPanel.comboUserLibrary
							.getSelectedItem();
					MainRun.mainFrame.viewPanel.tableViewLibrary.setModel(Datos
							.getLibraryMetadata(user));
					// if (MainRun.mainFrame.viewPanel.rViewJuego.isSelected()
					// || MainRun.mainFrame.viewPanel.rViewLibrary
					// .isSelected()) {
					((FilterFrame) MainRun.mainFrame.filterFrame).resetBounds();
					MainRun.mainFrame.filterFrame.setVisible(true);
					// }

				} else {
					MainRun.mainFrame.filterFrame.setVisible(false);
				}
			}

		}

	};

	// protected static ChangeListener showFilterAdmin = new ChangeListener() {
	//
	// @Override
	// public void stateChanged(ChangeEvent e) {
	//
	// if (e.getSource() instanceof JRadioButton) {
	// if (((JRadioButton) e.getSource()).isSelected()
	// && e.getSource().equals(
	// MainRun.mainFrame.viewPanel.rViewJuego)) {
	// ((FrameFilter) MainRun.mainFrame.frameFilter).resetBounds();
	// MainRun.mainFrame.frameFilter.setVisible(true);
	// }
	// if (((JRadioButton) e.getSource()).isSelected()
	// && e.getSource().equals(
	// MainRun.mainFrame.viewPanel.rViewLibrary)) {
	// ((FrameFilter) MainRun.mainFrame.frameFilter).resetBounds();
	// MainRun.mainFrame.frameFilter.setVisible(true);
	// }
	// if (((JRadioButton) e.getSource()).isSelected()
	// && e.getSource().equals(
	// MainRun.mainFrame.viewPanel.rViewUsuario)) {
	// MainRun.mainFrame.frameFilter.setVisible(false);
	// }
	// }
	// }
	// };

	protected static ChangeListener refreshTableLibrary = new ChangeListener() {

		@Override
		public void stateChanged(ChangeEvent e) {
			if (e.getSource() instanceof JTabbedPane) {
				if (((JTabbedPane) e.getSource()).getSelectedComponent()
						.equals(MainRun.mainFrame.viewPanel)) {
					MainRun.mainFrame.viewPanel.tableViewLibrary.setModel(Datos
							.getLibraryMetadata(Login._user));
					((FilterFrame) MainRun.mainFrame.filterFrame).resetBounds();
					MainRun.mainFrame.filterFrame.setVisible(true);
				} else {
					MainRun.mainFrame.filterFrame.setVisible(false);
				}
			}
		}
	};

	/**
	 * Muestra el frame con el filtrado por géneros si el usuario entra al tab
	 * con la vista de datos
	 */
	protected static ChangeListener showFilter = new ChangeListener() {

		@Override
		public void stateChanged(ChangeEvent e) {
			if (e.getSource() instanceof JTabbedPane) {
				((JTabbedPane) e.getSource()).getSelectedComponent().equals(
						MainRun.mainFrame.viewPanel);
				MainRun.mainFrame.filterFrame.setVisible(true);
			} else {
				MainRun.mainFrame.filterFrame.setVisible(false);
			}
		}
	};

	/**
	 * Se aplica el filtro seleccionado en el frame con los géneros
	 */
	protected static ActionListener applyFilter = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (MainRun.mainFrame.tabPanel.getSelectedIndex() != 1) {

			} else {
				if (MainRun.mainFrame.admin) {
					// Modo admin

					// Vista de juegos
					TableModel modelJ = Datos.getJuegosMetadata();
					ArrayList<Juego> juegosJ = new ArrayList<Juego>();
					Juego juegoJ;
					for (int i = 0; i < modelJ.getRowCount(); i++) {
						juegoJ = Datos.searchJuego((int) modelJ
								.getValueAt(i, 0));
						juegosJ.add(juegoJ);
					}

					Vector<String> generosJ = FilterFrame.getFilterGeneros();
					ArrayList<Juego> juegosFilteredJ = Datos.filterGenero(
							juegosJ, generosJ);
					TableModel filteredJ = Datos
							.getJuegosMetadata(juegosFilteredJ);
					MainRun.mainFrame.viewPanel.tableViewJuego
							.setModel(filteredJ);

					// Biblioteca
					TableModel modelL = Datos
							.getLibraryMetadata((String) MainRun.mainFrame.viewPanel.comboUserLibrary
									.getSelectedItem());
					ArrayList<Juego> juegosL = new ArrayList<Juego>();
					Juego juegoL;
					for (int i = 0; i < modelL.getRowCount(); i++) {
						juegoL = Datos.searchJuego((int) modelL
								.getValueAt(i, 0));
						juegosL.add(juegoL);
					}

					Vector<String> generosL = FilterFrame.getFilterGeneros();
					ArrayList<Juego> juegosFilteredL = Datos.filterGenero(
							juegosL, generosL);
					TableModel filteredL = Datos
							.getJuegosMetadata(juegosFilteredL);
					MainRun.mainFrame.viewPanel.tableViewLibrary
							.setModel(filteredL);
				} else {
					// Modo user

					TableModel model = Datos.getLibraryMetadata(Login._user);
					ArrayList<Juego> juegos = new ArrayList<Juego>();
					Juego juego;
					for (int i = 0; i < model.getRowCount(); i++) {
						juego = Datos.searchJuego((int) model.getValueAt(i, 0));
						juegos.add(juego);
					}

					Vector<String> generos = FilterFrame.getFilterGeneros();
					ArrayList<Juego> juegosFiltered = Datos.filterGenero(
							juegos, generos);
					TableModel filtered = Datos
							.getJuegosMetadata(juegosFiltered);
					MainRun.mainFrame.viewPanel.tableViewLibrary
							.setModel(filtered);

				}
			}
		}
	};
}