package com.adslur.szt.dialog;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.IOException;

import java.sql.*;
import java.util.*;

import com.adslur.szt.jdbc.MvbOracleConnection;
import com.adslur.szt.domen.OperFio;
import com.adslur.szt.utils.Configuration;

public class LoginDialog extends JDialog implements ActionListener

{

	private final static String PROP_USERID = "USERID";
	private final static String PROP_PASSWORD = "PASSWORD";
	private final static String PROP_DATABASE = "DATABASE";
	String dialogmessage;

	JLabel labelImage;
	JLabel labelUser;
	JLabel labelPassword;
	JLabel labelbase;
	JTextField ufield;
	JPasswordField pfield;
	JTextField dfield;
	JButton okbutton;
	JButton cancelbutton;

	String username = null;
	String password = null;
	String darabase = null;

	String continue_input = null;
	private static String IES = "Y";

	boolean ok = false;

	private boolean connection = false;
	Connection conn = null;
	private MvbOracleConnection mvb = MvbOracleConnection.getInstance();

	private int loginAttempts = 0;

	public LoginDialog(JFrame parent) {
		super(parent, "", true);
		setResizable(false);

		JPanel buttonpanel;
		EmptyBorder border = new EmptyBorder(new Insets(0, 0, 0, 5));
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.insets = new Insets(4, 4, 4, 4);

		getContentPane().setLayout(layout);
		setTitle("Проверка подключения");

		// label = new JLabel("Login to Bodington System");
		// constraints.gridwidth = 2;
		// constraints.gridx = 0;
		// constraints.gridy = 0;
		// constraints.anchor = GridBagConstraints.CENTER;
		// layout.setConstraints(label, constraints);
		// getContentPane().add(label);
		JPanel panel2 = new JPanel(new BorderLayout());
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.setLayout(new GridBagLayout());

		constraints.insets = new Insets(0, 0, 0, 64);
		labelImage = new JLabel(new ImageIcon("Images/SZTelecom.GIF"));
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 3;
		constraints.anchor = GridBagConstraints.NORTHEAST;
		layout.setConstraints(labelImage, constraints);
		panel1.add(labelImage, constraints);

		constraints.insets = new Insets(4, 4, 4, 4);
		labelUser = new JLabel("Имя пользователя:");
		labelUser.setBorder(border);
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(labelUser, constraints);
		panel1.add(labelUser, constraints);

		ufield = new JTextField();
		ufield.setColumns(20);
		constraints.gridwidth = 1;
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(ufield, constraints);
		panel1.add(ufield, constraints);

		labelPassword = new JLabel("Пароль:");
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(labelPassword, constraints);
		panel1.add(labelPassword, constraints);

		pfield = new JPasswordField();
		pfield.setColumns(20);
		constraints.gridwidth = 1;
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(pfield, constraints);
		panel1.add(pfield, constraints);

		labelbase = new JLabel("База данных ");
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(labelbase, constraints);
		panel1.add(labelbase, constraints);

		dfield = new JTextField();
		dfield.setColumns(20);
		constraints.gridwidth = 1;
		constraints.gridx = 2;
		constraints.gridy = 2;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(dfield, constraints);
		panel1.add(dfield, constraints);

		okbutton = new JButton("Продолжить");
		getRootPane().setDefaultButton(okbutton);
		constraints.gridwidth = 1;
		constraints.gridx = 3;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(okbutton, constraints);
		panel1.add(okbutton, constraints);

		cancelbutton = new JButton("Отменить");
		constraints.gridwidth = 1;
		constraints.gridx = 3;
		constraints.gridy = 2;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(dfield, constraints);
		panel1.add(cancelbutton, constraints);
		panel1.add(cancelbutton, constraints);

		panel2.add(panel1, BorderLayout.NORTH);

		getContentPane().add(panel2);
		setSize(800, 200);
		setResizable(false);

		loadProperties();

		pfield.addActionListener(this);
		okbutton.addActionListener(this);
		cancelbutton.addActionListener(this);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}

	private void InitFio(String login) {

		OperFio ab = new OperFio();

		ab = mvb.getOperFio(login);

		if (ab == null) {

			JOptionPane.showMessageDialog(null,
					"В базе данных отсутствуют реквизиты оператора",
					"Ошибка при входе", JOptionPane.ERROR_MESSAGE);

			System.exit(0);

		}

	}

	public void setVisible(boolean b) {
		if (b) {
			setLocation(250, 250);
			ok = false;
		}
		super.setVisible(b);
	}

	public void loadProperties() {
		Properties props;
		String temp;

		props = Configuration.instance();
		ufield.setText(props.getProperty(PROP_USERID, ""));
		pfield.setText(props.getProperty(PROP_PASSWORD, ""));
		dfield.setText(props.getProperty(PROP_DATABASE, ""));

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == cancelbutton) {
			int res = JOptionPane.showConfirmDialog(null,
					"Действительно выйти?");
			if (res == JOptionPane.YES_OPTION) {
				dispose();
				System.exit(0);
			}

		}
		// System.out.println("TRACE  START CONNECT");
		connection = mvb.connect(ufield.getText(), String.valueOf(pfield
				.getPassword()));
		mvb.setUserName(ufield.getText());
		mvb.setPassword(String.valueOf(pfield.getPassword()));
		if (connection == true) {
			//

			InitFio(ufield.getText().trim().toLowerCase());

			//

			dispose();
		} else {
			loginAttempts++;

			JOptionPane.showMessageDialog(null,
					"Неправильный логин  или  пароль", "Внимание!!",
					JOptionPane.WARNING_MESSAGE);

			ufield.setText("");
			pfield.setText("");

			if (loginAttempts >= 3) {
				dispose();
				System.exit(0);
			} else {
				pfield.setText("");
			}
		}
	}

}
