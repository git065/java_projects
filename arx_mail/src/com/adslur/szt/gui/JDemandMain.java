package com.adslur.szt.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;

import javax.swing.plaf.metal.MetalTheme;
import javax.swing.plaf.metal.OceanTheme;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;

import com.adslur.szt.dialog.SplashScreens;
import com.adslur.szt.dialog.LoginDialog;
import com.adslur.szt.dialog.SettingView;
import com.adslur.szt.dialog.SearchIBS;
import com.adslur.szt.dialog.AboutDialog;

import com.adslur.szt.theme.XPStyleTheme;
import com.adslur.szt.theme.TestTheme;
import com.adslur.szt.utils.Misc;




public class JDemandMain extends JFrame implements ActionListener,
		WindowListener {

	private static JDemandMain _main = null;
	private SettingView _settView = null;;
	final static String LOOKANDFEEL = "Metal";
	double xSize = 0;
	double ySize = 0;
	private static final long serialVersionUID = 1L;
	private MDIDesktopPane desktop = new MDIDesktopPane();

	private static final String mac = "com.sun.java.swing.plaf.mac.MacLookAndFeel";
	private static final String metal = "javax.swing.plaf.metal.MetalLookAndFeel";
	private static final String motif = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
	private static final String windows = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	private static final String gtk = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
	private static final String kng = "com.incors.plaf.kunststoff.KunststoffLookAndFeel";
	private static final String oficxp = "org.fife.plaf.OfficeXP.OfficeXPLookAndFeel";
	private static final String ofic2003 = "org.fife.plaf.Office2003.Office2003LookAndFeel";
	private static final String vs2005 = "org.fife.plaf.VisualStudio2005.VisualStudio2005LookAndFeel";
	private static final String JGoodiesLF = "com.jgoodies.looks.plastic.PlasticLookAndFeel";
	private static final String JGoodies3D = "com.jgoodies.looks.plastic.Plastic3DLookAndFeel";
	private static final String JGoodiesXP = "com.jgoodies.looks.plastic.PlasticXPLookAndFeel";
	private static final String JGoodiesWindows = "com.jgoodies.looks.windows.WindowsLookAndFeel";

	public static boolean conn = false;

	private JPanel statusBar = new JPanel();
	JLabel lblActivFrame;

	private JMenuBar menuBar = new JMenuBar();

	// JMenu variable
	private JMenu mnuManage = new JMenu("Просмотр");

	private JMenu mnuSearch = new JMenu("Поиск");
	private JMenu mnuTesting = new JMenu("Проверка");
	private JMenu mnuDemand = new JMenu("Заявка");
	private JMenu mnuWindows = new JMenu("Окна");

	private JMenu mnuSetting = new JMenu("Настройки");

	private JMenu mnuAbout = new JMenu("Помощь");

	private JMenuItem itmViewDemand = new JMenuItem("Заявки");
	private JMenuItem itmExit = new JMenuItem("Выход");
	private JMenuItem itmNumTlf = new JMenuItem("По номеру телефона");
	private JMenuItem itmNls = new JMenuItem("По НЛС");
	private JMenuItem itmInventor = new JMenuItem("Техн.возможности");
	private JMenuItem itmBilling = new JMenuItem("По Билингу");
	private JMenuItem itmDemandAdd = new JMenuItem("Добавить заявку");
	private JMenuItem itmSettingAdd = new JMenuItem("Редактор настроек");

	private JMenuItem itmAbout = new JMenuItem("О программе ");

	String sMSGBOX_TITLE = "Заявки ADSL";

	// JInternalFrame Variables

	JAddDemand AddDemand;
	JManageDemand ManageDemand;
	SettingView settView;
	LoginDialog ld;
	AboutDialog about;
	JNaradDemand nd;
	SearchTV stv;

	private JScrollPane scrollPane = new JScrollPane();

	public static JDemandMain getInstance() {
		if (_main == null) {
			_main = new JDemandMain();
		}

		return _main;
	}

	public JDemandMain() {
		System.out.println("TRACE  CONCTRUCT JDemandMain ");
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screeenSize = kit.getScreenSize();
		xSize = screeenSize.getWidth();
		ySize = screeenSize.getHeight() - 30;

		setSize(new Dimension((int) xSize, (int) ySize));

		JLabel lblActivFrame = new JLabel(" Activ- ", Label.LEFT);
		statusBar.setLayout(new BorderLayout());
		statusBar.add(lblActivFrame, BorderLayout.WEST);

		menuBar.add(mnuManage);
		menuBar.add(mnuSearch);
		menuBar.add(mnuTesting);
		menuBar.add(mnuDemand);

		menuBar.add(new WindowMenu(desktop));

		menuBar.add(mnuSetting);

		menuBar.add(mnuAbout);

		mnuManage.add(itmViewDemand);
		mnuManage.add(itmExit);
		mnuManage.setToolTipText("Просмотр заявки ");

		mnuSearch.add(itmNumTlf);
		mnuSearch.add(itmNls);
		mnuSearch.setToolTipText("Поиск заявки ");
		mnuTesting.add(itmInventor);
		mnuTesting.add(itmBilling);

		mnuDemand.add(itmDemandAdd);
		mnuDemand.setToolTipText("Действия с заявкой ");
		mnuSetting.add(itmSettingAdd);

		mnuAbout.add(itmAbout);
		mnuAbout
				.setToolTipText("<html>Дополнительные сведения<br> о программе ");

		mnuTesting.setToolTipText("Проверка на возможность подключения ");
		mnuSetting.setToolTipText("Настройка внешнего вида программы");
		mnuWindows.setToolTipText("Доступные окна");

		itmViewDemand.addActionListener(this);
		itmExit.addActionListener(this);
		itmInventor.addActionListener(this);
		itmBilling.addActionListener(this);
		itmDemandAdd.addActionListener(this);
		itmSettingAdd.addActionListener(this);

		itmAbout.addActionListener(this);

		setJMenuBar(menuBar);
		setTitle("Заявки(юр.лица)");
		ImageIcon image = new ImageIcon("src/image/adsl.gif");
		setIconImage(image.getImage());

		statusBar.setLayout(new BorderLayout());
		statusBar.add(lblActivFrame, BorderLayout.WEST);

		getContentPane().setLayout(new BorderLayout());
		scrollPane.getViewport().add(desktop);

		getContentPane().add(scrollPane, java.awt.BorderLayout.CENTER);
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		desktop.setOpaque(false);

	}

	public void windowClosing(WindowEvent we) {
		System.out.println("Thank you for using JSwingpad by Puneet Wadhwa");
		System.exit(0);
		// If the application is exited this method is called !
	}

	public void windowIconified(WindowEvent we) {
	}

	public void windowDeiconified(WindowEvent we) {
	}

	public void windowClosed(WindowEvent we) {
	}

	public void windowDeactivated(WindowEvent we) {
	}

	public void windowActivated(WindowEvent we) {
	}

	public void windowOpened(WindowEvent we) {
	}

	public void actionPerformed(ActionEvent event) {
		Object object = event.getSource();
		if (object == itmExit) {
			UnloadWindow();
		} else if (object == itmViewDemand) {

			ManageDemand = new JManageDemand();

			loadForm("Управление заявкой", ManageDemand);
		} else if (object == itmDemandAdd) {
			AddDemand = new JAddDemand();
			loadForm("Добавить заявку", AddDemand);
                                                           
		}

		else if (object == itmInventor) {
			SearchTV stv = new SearchTV();
			stv.setTitle("Проверка технической возможности");
			// stv.setModal(true);
			// stv.pack( );
			loadForm("Проверка технической возможности", stv);

			stv.setSize(500, 400);
			// stv.setVisible(true);
		}

		else if (object == itmBilling) {
			SearchIBS stv = new SearchIBS();
			stv.setTitle("Проверка по ИБС");
			stv.setModal(true);
			stv.pack();
			stv.setVisible(true);
		}

		else if (object == itmSettingAdd) {
			// settView = new SettingView(this);
			_settView = SettingView.getInstance();
			_settView.setSize(400, 400);
			Misc.centerFrame(_settView);
			_settView.setModal(true);
			_settView.setVisible(true);

		}

		else if (object == itmAbout) {
			about = new AboutDialog(this, "О программе");
			about.dialogInit();
                                                            about.setSize(500, 400);
			about.setVisible(true);
         

		}

	}

	public void loadForm(String Title, JInternalFrame clsForm) {

		boolean xForm = isLoaded(Title);
		// System.out.println("TRACE  " + Title );
		if (xForm == false) {
			desktop.add(clsForm);
			// System.out.println("TRACE INTO  " + Title );
			clsForm.setVisible(true);
			clsForm.show();

		} else {
			try {
				clsForm.setIcon(false);
				clsForm.setSelected(true);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isLoaded(String FormTitle) {
		JInternalFrame[] Form = desktop.getAllFrames();
		for (int i = 0; i < Form.length; i++) {
			if (Form[i].getTitle().equalsIgnoreCase(FormTitle)) {
				Form[i].show();
				try {
					Form[i].setIcon(false);
					Form[i].setSelected(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return true;
			}
		}
		return false;
	}

	private static void loadSplashScreen() {

		SplashScreens Splash = new SplashScreens(new ImageIcon(
				"Images/SZTelecom.GIF"));
		System.out.println("TRACE  loadSplashScreen ");
		try {
			Splash.showStatus("Здравствуйте!");
			System.out.println("TRACE  HELLO SPLACH ");
			Thread.sleep(1500);
			Splash
					.showStatus("Сейчас Вы увидите мою первую программу на Java!");
			Thread.sleep(1500);
			Splash.showStatus("Было очень интересно ее писать...");
			Thread.sleep(1500);
			Splash.showStatus("... уже скоро стартуем...");
			Thread.sleep(1500);
			Splash.showStatus("... но сначала надо ввести пароль!");
			Thread.sleep(1500);
			Splash.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void UnloadWindow() {
		try {
			int reply = JOptionPane.showConfirmDialog(this,
					"Завершить работу с приложением ?", sMSGBOX_TITLE,
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			// If the confirmation was affirmative, handle exiting.
			if (reply == JOptionPane.YES_OPTION) {
				setVisible(false); // hide the Frame
			}
		} catch (Exception e) {
		}
	}// Unload Window

	private static void initLookAndFeel() {
		String lookAndFeel = null;

		if (LOOKANDFEEL != null) {
			if (LOOKANDFEEL.equals("Metal")) {
				lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
				// an alternative way to set the Metal L&F is to replace the
				// previous line with:
				// lookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";

			}

			else if (LOOKANDFEEL.equals("System")) {
				lookAndFeel = UIManager.getSystemLookAndFeelClassName();
			}

			else if (LOOKANDFEEL.equals("Motif")) {
				lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
			}

			else if (LOOKANDFEEL.equals("GTK")) {
				lookAndFeel = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
			} else if (LOOKANDFEEL.equals("Windows")) {
				lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
			}

			else if (LOOKANDFEEL.equals("Kingst")) {
				lookAndFeel = kng;
			} else if (LOOKANDFEEL.equals("OficeXP")) {
				lookAndFeel = oficxp;
			} else if (LOOKANDFEEL.equals("Ofice2003")) {
				lookAndFeel = ofic2003;
			} else if (LOOKANDFEEL.equals("VisualStudio2005")) {
				lookAndFeel = vs2005;
			} else if (LOOKANDFEEL.equals("JGoodiesLF")) {
				lookAndFeel = JGoodiesLF;
			} else if (LOOKANDFEEL.equals("JGoodies3D")) {
				lookAndFeel = JGoodies3D;
			} else if (LOOKANDFEEL.equals("JGoodiesXP")) {
				lookAndFeel = JGoodiesXP;
			} else if (LOOKANDFEEL.equals("JGoodiesWindows")) {
				lookAndFeel = JGoodiesWindows;
			}

			else {
				System.err
						.println("Unexpected value of LOOKANDFEEL specified: "
								+ LOOKANDFEEL);
				lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
			}

			try {

				UIManager.setLookAndFeel(lookAndFeel);
				MetalLookAndFeel.setCurrentTheme(new XPStyleTheme());
				UIManager.setLookAndFeel(new MetalLookAndFeel());

			}

			catch (ClassNotFoundException e) {
				System.err
						.println("Couldn't find class for specified look and feel:"
								+ lookAndFeel);
				System.err
						.println("Did you include the L&F library in the class path?");
				System.err.println("Using the default look and feel.");
			}

			catch (UnsupportedLookAndFeelException e) {
				System.err.println("Can't use the specified look and feel ("
						+ lookAndFeel + ") on this platform.");
				System.err.println("Using the default look and feel.");
			}

			catch (Exception e) {
				System.err.println("Couldn't get specified look and feel ("
						+ lookAndFeel + "), for some reason.");
				System.err.println("Using the default look and feel.");
				e.printStackTrace();
			}
		}
	}

	public void reEnabled(String look) {

		try {

			UIManager.setLookAndFeel(look);
			SwingUtilities.updateComponentTreeUI(this);

		}

		catch (Exception ex) {
			System.out.println("Failed loading L&F: " + look);
			System.out.println(ex);
		}
	}

	public static void main(String[] args) {

		//loadSplashScreen();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				initLookAndFeel();

				_main = getInstance();

				_main.setDefaultLookAndFeelDecorated(true);

				LoginDialog ld = new LoginDialog(_main);
				ld.setModal(true);

				java.awt.Toolkit tk = java.awt.Toolkit.getDefaultToolkit();

				_main.setVisible(true);
				ld.setVisible(true);
			}
		});

	}

}