package com.adslur.szt.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

import com.adslur.szt.gui.JDemandMain;
import com.adslur.szt.gui.JNaradDemand;
import com.adslur.szt.utils.OutLookToolBar;
import com.adslur.szt.dialog.EditDemansStop;
import com.adslur.szt.panel.DemandTlf;
import com.adslur.szt.panel.TreePanel;
import com.adslur.szt.renderers.UIDefaultsPopup;
import com.adslur.szt.renderers.UIDefaultsPopupDialog;

public class JManageDemand extends JInternalFrame implements ActionListener {
	private JDemandMain _mainFrame = JDemandMain.getInstance();
	private JTextArea textArea = new JTextArea();
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	JButton button5;
	DemandTlf pnl;
	EditDemansStop stv;
	JNaradDemand nd;

	OutLookToolBar m_tlbBar = null;
	JToolBar m_toolbar;

	private JScrollPane scrollPane = new JScrollPane();
	private boolean firstInternalframeActivated = false;

	public JManageDemand() {
		initComponents();
		setSize(400, 500);
		setTitle("Управление заявкой");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setResizable(true);

		pnl = new DemandTlf();
		UIDefaultsPopup popup = new UIDefaultsPopup(this, pnl.dTlf);
		pnl.dTlf.setComponentPopupMenu(popup);

		scrollPane.getViewport().add(pnl);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(m_toolbar, BorderLayout.NORTH);
		getContentPane().add(scrollPane, BorderLayout.CENTER);

	}

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

	public void actionPerformed(ActionEvent event) {

		int iRow = -10;
		Object object1 = null;
		String sDmnd_id = null;

		Object object = event.getSource();

		// System.out.println("Action MODAL :" + object.toString());

		if (object == button1) {
			stv = new EditDemansStop();
			System.out.println("Action MODAL: " + "OK");
			stv.setModal(true);
			stv.pack();
			stv.setVisible(true);
		}

		if (object == button5) {

			TreePanel.numTlf = pnl.dTlf.getText();
			iRow = pnl.table.getSelectedRow();
			System.out.println("TRACE IROW: " + iRow);

			if (pnl.dTlf.getText().equals("")) {

				JOptionPane.showMessageDialog(null,
						"Не запонено поле  <номер телефона>", "Внимание!!",
						JOptionPane.WARNING_MESSAGE);
			} else if (iRow == -1) {

				JOptionPane.showMessageDialog(null,
						"Для набонента с номером телефона :"
								+ pnl.dTlf.getText() + "\n"
								+ "не найдена заявка", "Внимание!!",
						JOptionPane.WARNING_MESSAGE);

			} else {
				TreePanel.dmnd_idTlf = pnl.model.getValueAt(iRow, 0).toString();
				nd = new JNaradDemand("История атрибутов заявки для абонента:",
						"-номер заявки :");

				_mainFrame.loadForm(nd.getTitle(), nd);

			}
		}

	}

}