package com.sepps.szt.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;

import javax.swing.plaf.metal.MetalTheme;
import javax.swing.plaf.metal.OceanTheme;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;

import com.sepps.szt.dialog.AboutDialog;
import com.sepps.szt.dialog.LoginDialog;
import com.sepps.szt.theme.XPStyleTheme;
import com.sepps.szt.dialog.SettingView;
import com.sepps.szt.utils.Misc;

/*
 import com.adslur.szt.dialog.SplashScreens;
 import com.adslur.szt.dialog.LoginDialog;
 import com.adslur.szt.dialog.SettingView;
 import com.adslur.szt.dialog.SearchIBS;
 import com.adslur.szt.dialog.AboutDialog;

 import com.adslur.szt.theme.XPStyleTheme;
 import com.adslur.szt.theme.TestTheme;
 import com.adslur.szt.utils.Misc;

 */

public class JUtilMain extends JFrame implements ActionListener, WindowListener {

	private static JUtilMain _main = null;
	private SettingView _settView = null;
	// final static String LOOKANDFEEL = "Metal";
	final static String LOOKANDFEEL = "JGoodiesWindows";

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
	//private JMenu mnuReport = new JMenu("Отчет");
	private JMenu mnuSearch = new JMenu("Поиск");
	private JMenu mnuSvodka = new JMenu("Сводка");
	private JMenu mnuWindows = new JMenu("Окна");
	private JMenu mnuSetting = new JMenu("Настройки");
	private JMenu mnuAbout = new JMenu("Помощь");

	private JMenuItem itmReportViewGroup = new JMenuItem("По группам");
	private JMenuItem itmReportViewUser = new JMenuItem("По пользователям");
	private JMenuItem itmViewDelegPoul = new JMenuItem("Пул делегир-я");
	private JMenuItem itmViewManageGroup = new JMenuItem("Группа управления");
	private JMenuItem itmViewDomain = new JMenuItem("Домен");
	private JMenuItem itmSvodkaUser = new JMenuItem("По пользователям");
	
	private JMenuItem itmViewUser = new JMenuItem("По пользователям");

	// private JMenuItem itmViewDemand = new JMenuItem("Заявки");
	private JMenuItem itmSettingAdd = new JMenuItem("Редактор настроек");
	private JMenuItem itmExit = new JMenuItem("Выход");

	private JMenuItem itmAbout = new JMenuItem("О программе ");

	String sMSGBOX_TITLE = "Заявки ADSL";

	// JInternalFrame Variables

	LoginDialog ld;
	AboutDialog about;
	JUserSeach user_search;
	JManageGroupSearch ManageGroupSearch;

	private JScrollPane scrollPane = new JScrollPane();

	public static JUtilMain getInstance() {
		if (_main == null) {
			_main = new JUtilMain();
		}

		return _main;
	}

	public JUtilMain() {
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

		//menuBar.add(mnuReport);
		menuBar.add(mnuSearch);
		menuBar.add(mnuSvodka);

		menuBar.add(new WindowMenu(desktop));

		menuBar.add(mnuSetting);

		menuBar.add(mnuAbout);

		//mnuReport.add(itmReportViewGroup);
		//mnuReport.setToolTipText("Поиск по группам управления ");
		//mnuReport.add(itmReportViewUser);
		mnuSearch.add(itmViewDelegPoul);
		mnuSearch.add(itmViewManageGroup);
		mnuSearch.add(itmViewDomain);
		mnuSearch.add(itmViewUser);

		mnuSearch.setToolTipText("Поиск заявки ");
		mnuSetting.add(itmSettingAdd);
		
		mnuSvodka.add(itmSvodkaUser);
		
		
		
		mnuAbout.add(itmAbout);
		mnuAbout.setToolTipText("<html>Дополнительные сведения<br> о программе ");

		mnuWindows.setToolTipText("Доступные окна");
		itmViewManageGroup.addActionListener(this);
		itmViewUser.addActionListener(this);
		itmSvodkaUser.addActionListener(this);
		
		itmSettingAdd.addActionListener(this);
		itmExit.addActionListener(this);

		itmAbout.addActionListener(this);

		setJMenuBar(menuBar);
		setTitle("СЭППС");
		ImageIcon image = new ImageIcon("Images/dem_addd2.gif");
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
		} else if (object == itmViewUser) {

			JUserSeach user_search = new JUserSeach();

			loadForm("Поиск по логину", user_search);
		} else if (object == itmViewManageGroup) {

			JManageGroupSearch Manage_group_search = new JManageGroupSearch();

			loadForm("Поиск по ГРУППЕ", Manage_group_search);
		}else if (object == itmSvodkaUser) {

			JSvodksUser  SvodksUser = new JSvodksUser();

			loadForm("Поиск по ГРУППЕ", SvodksUser);
		}

		else if (object == itmSettingAdd) {
			// settView = new SettingView(this);
			_settView = SettingView.getInstance();
			_settView.setSize(400, 400);
			Misc.centerFrame(_settView);
			_settView.setModal(true);
			_settView.setVisible(true);

		} else if (object == itmAbout) {
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
				// UIManager.setLookAndFeel(new MetalLookAndFeel());

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
