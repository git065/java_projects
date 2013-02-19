package com.adslur.szt.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import com.adslur.szt.panel.TreePanel;

public class JNaradDemand extends JInternalFrame implements ActionListener {

	TreePanel treeN;
	private JScrollPane scrollPane = new JScrollPane();

	public JNaradDemand(String tlf, String delim) {

		setSize(1000, 900);
		// setTitle("История атрибутов заявки");
		setTitle(tlf + treeN.numTlf + delim + treeN.dmnd_idTlf);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setResizable(true);

		treeN = new TreePanel();

		scrollPane.getViewport().add(treeN);
		getContentPane().setLayout(new BorderLayout());

		getContentPane().add(scrollPane, BorderLayout.CENTER);

	}

	public void actionPerformed(ActionEvent event) {
	}

	public void reEnabled(String look) {

		try {

			UIManager.setLookAndFeel(look);
			SwingUtilities.updateComponentTreeUI(JNaradDemand.this);

			// this.setVisible(false);

		}

		catch (ClassNotFoundException e) {
			System.err
					.println("Couldn't find class for specified look and feel:"
							+ look);
			// System.err.println("Did you include the L&F library in the class path?");
			System.err.println("Using the default look and feel.");
		}

		catch (UnsupportedLookAndFeelException e) {
			System.err.println("Can't use the specified look and feel (" + look
					+ ") on this platform.");
			System.err.println("Using the default look and feel.");
		}

		catch (Exception e) {
			System.err.println("Couldn't get specified look and feel (" + look
					+ "), for some reason.");
			System.err.println("Using the default look and feel.");
			e.printStackTrace();
		}
	}

}
