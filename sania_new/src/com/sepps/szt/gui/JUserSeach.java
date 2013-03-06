package com.sepps.szt.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

import com.sepps.szt.gui.JUtilMain;

import com.sepps.szt.utils.OutLookToolBar;

import com.sepps.szt.panel.UserSearch;

import com.sepps.szt.renderers.UIDefaultsPopup;
import com.sepps.szt.renderers.UIDefaultsPopupDialog;

public class JUserSeach extends JInternalFrame implements ActionListener {
	private JUtilMain _mainFrame = JUtilMain.getInstance();
	private JTextArea textArea = new JTextArea();
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	JButton button5;
	UserSearch pnl;
	
/*
	OutLookToolBar m_tlbBar = null;
	JToolBar m_toolbar;
*/
	private JScrollPane scrollPane = new JScrollPane();
	private boolean firstInternalframeActivated = false;

	public JUserSeach() {
	//	initComponents();
		setSize(400, 500);
		setTitle("Поиск по имени пользователя");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setResizable(true);

		pnl = new UserSearch();
		UIDefaultsPopup popup = new UIDefaultsPopup(this, pnl.dTlf);
		pnl.dTlf.setComponentPopupMenu(popup);

		scrollPane.getViewport().add(pnl);

		getContentPane().setLayout(new BorderLayout());
		//getContentPane().add(m_toolbar, BorderLayout.NORTH);
		getContentPane().add(scrollPane, BorderLayout.CENTER);

	}
/*
	private void initComponents() {

		m_toolbar = new JToolBar();
		m_tlbBar = new OutLookToolBar(m_toolbar);

		button1 = new JButton();
		button1.setFocusable(false);
		button1.setIcon(new ImageIcon("Images/SEARCHN.gif"));
		button2 = new JButton();
		button2.setIcon(new ImageIcon("Images/speed_18.GIF"));
		button2.setFocusable(false);
		button3 = new JButton();
		button3.setIcon(new ImageIcon("Images/delete.gif"));
		button3.setFocusable(false);
		button4 = new JButton();
		button4.setIcon(new ImageIcon("Images/localerror.gif"));
		button4.setFocusable(false);
		button5 = new JButton();
		button5.setIcon(new ImageIcon("Images/OPENED.GIF"));
		button5.setFocusable(false);

		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		button5.addActionListener(this);

		m_toolbar.add(button1);
		m_toolbar.add(button2);

		m_toolbar.add(button3);
		m_toolbar.add(button4);
		m_toolbar.add(button5);

	}
*/
	public void actionPerformed(ActionEvent event) {

		int iRow = -10;
		Object object1 = null;
		String sDmnd_id = null;

		Object object = event.getSource();

		// System.out.println("Action MODAL :" + object.toString());

		

		
		}

	}

