package com.adslur.szt.dialog;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class SplashScreens extends JWindow {

	private static String fredysVersion = "Version 1.0  Apr. 2010 ";

	public String getVersion() {
		return fredysVersion;
	}

	JLabel StatusBar;

	private Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

	public static void main(String Args[]) {
		SplashScreens Splash = new SplashScreens(new ImageIcon(
				"Images/SZTelecom.GIF"));
		try {
			Splash.showStatus("Здравствуйте!");
			Thread.sleep(1000);
			Splash
					.showStatus("Сейчас Вы увидите мою первую программу на Java!");
			Thread.sleep(1000);
			Splash.showStatus("Было очень интересно ее писать...");
			Thread.sleep(1000);
			Splash.showStatus("... уже скоро стартуем...");
			Thread.sleep(1000);
			Splash.showStatus("... но сначала надо ввести пароль!");
			Thread.sleep(1000);
			Splash.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	public SplashScreens(ImageIcon CoolPicture) {
		super(new JFrame());

		JPanel PanelForBorder = new JPanel(new BorderLayout());
		PanelForBorder.setLayout(new BorderLayout());
		PanelForBorder.add(new JLabel(CoolPicture), BorderLayout.CENTER);
		PanelForBorder.add(
				StatusBar = new JLabel("...", SwingConstants.CENTER),
				BorderLayout.SOUTH);
		PanelForBorder.setBorder(new BevelBorder(BevelBorder.RAISED));

		add(PanelForBorder);
		pack();

		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

		setSize(d.width - 600, d.height - 800);
		setLocation(d.width / 2 - getWidth() / 2, d.height / 2 - getHeight()
				/ 2);
		System.out.println("TRACE  SPLACH  VISIBLE ");
		setVisible(true);
	}

	public void showStatus(String CurrentStatus) {
		try {

			SwingUtilities.invokeLater(new UpdateStatus(CurrentStatus));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {

			SwingUtilities.invokeLater(new CloseSplashScreen());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setBackGround(Color c) {
		this.setBackground(c);
	}

	class UpdateStatus implements Runnable {
		String NewStatus;

		public UpdateStatus(String Status) {
			NewStatus = Status;
		}

		public void run() {
			StatusBar.setText(NewStatus);
		}
	}

	class CloseSplashScreen implements Runnable {
		public void run() {
			setVisible(false);
			dispose();
		}
	}
}
