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

import com.toedter.calendar.JDateChooser;

import com.adslur.szt.panel.DateChooserPanel;

public class EditDemansStop extends JDialog implements ActionListener

{
	private int framePositionX;
	private int framePositionY;

	private Rectangle frameBounds = null;

	DateChooserPanel pnl;

	private int loginAttempts = 0;

	private JScrollPane scrollPane = new JScrollPane();

	private int dialogResult;

	public EditDemansStop() {

		// super(parent, title, m);
		// setResizable(false);

		JPanel buttonpanel;
		EmptyBorder border = new EmptyBorder(new Insets(0, 0, 0, 5));
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.insets = new Insets(4, 4, 4, 4);

		getContentPane().setLayout(layout);
		setTitle("Проверка подключения");

		JPanel panel2 = new JPanel(new BorderLayout());
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.setLayout(new GridBagLayout());

		setSize(800, 200);
		setResizable(false);

		pnl = new DateChooserPanel();
		scrollPane.getViewport().add(pnl);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		Dimension screenSize = getToolkit().getScreenSize();
		frameBounds = getBounds();
		framePositionX = (screenSize.width - frameBounds.width) / 2;
		framePositionY = (screenSize.height - frameBounds.height) / 2;
		setLocation(framePositionX, framePositionY);

		pnl.addActionListener(this);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// System.exit(0);
				setVisible(false);
				dispose();
			}
		});

	}

	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		System.out.println("ActionEvent: " + actionCommand);
		if (actionCommand.equalsIgnoreCase("DATE_CHOOS_OK")) {

			System.out
					.println("Action change data - START  :" + pnl.getDate1());
			System.out.println("Action change data - END :" + pnl.getDate2());
			this.setDialogResult(javax.swing.JOptionPane.OK_OPTION);
			setVisible(false);
			dispose();

		} else if (actionCommand.equalsIgnoreCase("DATE_CHOOS_CANSEL")) {

			this.setDialogResult(javax.swing.JOptionPane.CLOSED_OPTION);
			setVisible(false);
			dispose();

		}

	}

	public int getDialogResult() {
		return dialogResult;
	}

	public void setDialogResult(int dialogResult) {
		this.dialogResult = dialogResult;
	}

	public static void main(String[] args) {

		JFrame aFrame = new JFrame("TEST");
		aFrame.setSize(1000, 1000);
		aFrame.setVisible(true);
		aFrame.addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
			}

			public void windowClosed(WindowEvent e) {

			}

			public void windowClosing(WindowEvent e) {

				System.exit(0);
			}

			public void windowDeactivated(WindowEvent e) {

			}

			public void windowDeiconified(WindowEvent e) {

			}

			public void windowIconified(WindowEvent e) {

			}

			public void windowOpened(WindowEvent e) {

			}
		});

		EditDemansStop st = new EditDemansStop();

		Exception e = new Exception("This is a Testexception");
		e.initCause(new Throwable("this is the cause"));
		st.pack();
		st.setVisible(true);

	}

}
