package com.sepps.szt.dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.border.*;

import com.sepps.szt.gui.JUtilMain;





import com.sepps.szt.theme.XPStyleTheme;

public class SettingView extends JDialog {

	/**
	 * The only instance of this class (implements the singleton pattern).
	 */
	private static SettingView _instance = null;

	private int framePositionX;
	private int framePositionY;

	private static JUtilMain opt = JUtilMain.getInstance();
	
	AboutDialog about = null;
	

	JComboBox cmbLookAndFeel;
	JButton btnOk = new JButton("Ok");
	// components of the login window
	private JTextField usernameField = new JTextField(10);
	private JPasswordField passwordField = new JPasswordField(10);
	private JLabel usernameLabel = new JLabel("Enter username:  ");
	private JLabel passwordLabel = new JLabel("Enter password:  ");
	private JButton loginButton = new JButton("Log In");

	public static String lookAndFeel;
	String LOOKANDFEEL_SELECT;
	// private static final String mac =
	// "com.sun.java.swing.plaf.mac.MacLookAndFeel";
	private static final String metal = "javax.swing.plaf.metal.MetalLookAndFeel";
	private static final String nimbus = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
	private static final String motif = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
	private static final String windows = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	private static final String windowsClassic = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";

	private static final String gtk = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
	private static final String Kunststoff = "com.incors.plaf.kunststoff.KunststoffLookAndFeel";
	private static final String OfficeXP = "org.fife.plaf.OfficeXP.OfficeXPLookAndFeel";
	private static final String Office2003 = "org.fife.plaf.Office2003.Office2003LookAndFeel";
	private static final String VisualStudio2005 = "org.fife.plaf.VisualStudio2005.VisualStudio2005LookAndFeel";
	private static final String JGoodiesLF = "com.jgoodies.looks.plastic.PlasticLookAndFeel";
	private static final String JGoodies3D = "com.jgoodies.looks.plastic.Plastic3DLookAndFeel";
	private static final String JGoodiesXP = "com.jgoodies.looks.plastic.PlasticXPLookAndFeel";
	private static final String JGoodiesWindows = "com.jgoodies.looks.windows.WindowsLookAndFeel";

	private static final String[] look = {
			"Metal",
			"Nimbus",
			// "GTK" ,
			// "Motif" ,
			"Windows", "Kunststoff", "OfficeXP", "Office2003",
			"VisualStudio2005", "JGoodiesLF", "JGoodies3D", "JGoodiesXP",
			"JGoodiesWindows" };

	// final static String THEME = "Test";
	final static String THEME = "XP Style Theme";

	public SettingView(JFrame parent, boolean modal) {
		super(parent, true);
		// this.opt = (JDemandMain) parent ;

		setTitle("Настойка вида");
		setResizable(false);

		passwordField.setEchoChar('*');

		// content pane for the login window
		JPanel loginPane = new JPanel();
		setContentPane(loginPane);
		loginPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		cmbLookAndFeel = new JComboBox();

		for (int n = 0; n < look.length - 1; n++) {
			cmbLookAndFeel.addItem(look[n]);

		}

		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints с = new GridBagConstraints();

		с.insets = new Insets(4, 4, 4, 4);

		с.gridx = 0;
		с.gridy = 0;
		с.gridwidth = 1;
		с.gridheight = 1;
		с.anchor = GridBagConstraints.WEST;
		layout.setConstraints(cmbLookAndFeel, с);
		loginPane.add(cmbLookAndFeel, с);

		с.gridx = 1;
		с.gridy = 0;
		с.gridwidth = 1;
		с.gridheight = 1;
		с.anchor = GridBagConstraints.NORTHWEST;
		layout.setConstraints(btnOk, с);
		loginPane.add(btnOk, с);
		Dimension screenSize = getToolkit().getScreenSize();
		framePositionX = (screenSize.width / 2 - screenSize.height / 2);
		framePositionY = (screenSize.width / 4 - screenSize.height / 4);
		setLocation(framePositionX, framePositionY);

		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// UIManager.setLookAndFeel((String)cmbLookAndFeel.getSelectedItem());
				System.out.println("Trace  theme  :" + THEME);
				LOOKANDFEEL_SELECT = (String) cmbLookAndFeel.getSelectedItem();
				System.out.println("selectet  :" + LOOKANDFEEL_SELECT);
				if (LOOKANDFEEL_SELECT != null) {

					if (LOOKANDFEEL_SELECT.equals("Metal")) {

						// lookAndFeel =
						// UIManager.getCrossPlatformLookAndFeelClassName();
						lookAndFeel = metal;
						System.out.println("LOOKANDFEEL_SELECT  =   :"
								+ lookAndFeel);
					}

					else if (LOOKANDFEEL_SELECT.equals("Nimbus")) {
						lookAndFeel = nimbus;
					}

					else if (LOOKANDFEEL_SELECT.equals("GTK")) {
						lookAndFeel = gtk;
					}

					else if (LOOKANDFEEL_SELECT.equals("Motif")) {

						lookAndFeel = motif;
					}

					else if (LOOKANDFEEL_SELECT.equals("Windows")) {
						lookAndFeel = windows;
						// System.out.println("equals- Windows  : " +
						// lookAndFeel );
					}

					else if (LOOKANDFEEL_SELECT.equals("Kunststoff")) {
						lookAndFeel = Kunststoff;
					} else if (LOOKANDFEEL_SELECT.equals("OfficeXP")) {
						lookAndFeel = OfficeXP;
					} else if (LOOKANDFEEL_SELECT.equals("Office2003")) {
						lookAndFeel = Office2003;
					} else if (LOOKANDFEEL_SELECT.equals("VisualStudio2005")) {
						lookAndFeel = VisualStudio2005;
					} else if (LOOKANDFEEL_SELECT.equals("JGoodiesLF")) {
						lookAndFeel = JGoodiesLF;
					} else if (LOOKANDFEEL_SELECT.equals("JGoodies3D")) {
						lookAndFeel = JGoodies3D;
					} else if (LOOKANDFEEL_SELECT.equals("JGoodiesXP")) {
						lookAndFeel = JGoodiesXP;
					} else if (LOOKANDFEEL_SELECT.equals("JGoodiesWindows")) {
						lookAndFeel = JGoodiesWindows;
					}

				}

				try {

					UIManager.setLookAndFeel(lookAndFeel);
					if (LOOKANDFEEL_SELECT.equals("Metal")) {
						if (THEME.equals("DefaultMetal"))
							MetalLookAndFeel
									.setCurrentTheme(new DefaultMetalTheme());
						else if (THEME.equals("Ocean"))
							MetalLookAndFeel.setCurrentTheme(new OceanTheme());
						
						else
							MetalLookAndFeel
									.setCurrentTheme(new XPStyleTheme());

						UIManager.setLookAndFeel(new MetalLookAndFeel());
					}

					SwingUtilities.updateComponentTreeUI(SettingView.this);

					if (opt == null) {
						System.out.println("MainFrame - null  :");

					} else if (opt != null) {

						System.out.println("MainFrame -  is not null  :");
						opt.reEnabled(lookAndFeel);

					}

					if (opt == null) {
						System.out.println("MainFrame - null  :");

					}
					/*
					else if (addDem != null) {

						System.out.println("MainFrame -  is not null  :");
						System.out.println(" lookAndFeel =  :  " + lookAndFeel);
						addDem.reEnabled(lookAndFeel);

					}

					else if (nrd != null) {

						System.out.println("NARAD Dialog -  is not null  :");
						System.out.println(" lookAndFeel =  :  " + lookAndFeel);
						nrd.reEnabled(lookAndFeel);

					}
					*/

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
					System.err
							.println("Can't use the specified look and feel ("
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
		});

	}

	public String getLookAndF()

	{

		return lookAndFeel;

	}

	public final static SettingView getInstance() {
		if (_instance == null) {
			_instance = new SettingView(opt, true);
		}
		return _instance;
	}

}
