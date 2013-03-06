package com.sepps.szt.gui;



import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;


	
	
	import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

	import com.sepps.szt.gui.JUtilMain;

	import com.sepps.szt.utils.OutLookToolBar;

import com.sepps.szt.panel.ManageGroupSearch;
	import com.sepps.szt.panel.UserSearch;

	import com.sepps.szt.renderers.UIDefaultsPopup;
import com.sepps.szt.renderers.UIDefaultsPopupDialog;

	public class JManageGroupSearch extends JInternalFrame implements ActionListener {
		private JUtilMain _mainFrame = JUtilMain.getInstance();
		private JTextArea textArea = new JTextArea();
		JButton button1;
		JButton button2;
		JButton button3;
		JButton button4;
		JButton button5;
		ManageGroupSearch pnl;
		
	/*
		OutLookToolBar m_tlbBar = null;
		JToolBar m_toolbar;
	*/
		private JScrollPane scrollPane = new JScrollPane();
		private boolean firstInternalframeActivated = false;

		public JManageGroupSearch() {
		//	initComponents();
			setSize(400, 500);
			setTitle("Поиск по группе");
			setMaximizable(true);
			setIconifiable(true);
			setClosable(true);
			setResizable(true);

			pnl = new ManageGroupSearch();
			UIDefaultsPopup popup = new UIDefaultsPopup(this, pnl.dTlf);
			pnl.dTlf.setComponentPopupMenu(popup);

			scrollPane.getViewport().add(pnl);

			getContentPane().setLayout(new BorderLayout());
			//getContentPane().add(m_toolbar, BorderLayout.NORTH);
			getContentPane().add(scrollPane, BorderLayout.CENTER);

		}
	
		public void actionPerformed(ActionEvent event) {

			int iRow = -10;
			Object object1 = null;
			String sDmnd_id = null;

			Object object = event.getSource();

			// System.out.println("Action MODAL :" + object.toString());

			

			
			}

		}

