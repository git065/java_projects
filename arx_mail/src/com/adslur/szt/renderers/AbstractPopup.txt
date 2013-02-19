package com.adslur.szt.renderers;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.HeadlessException;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JPopupMenu;
import javax.swing.*;

public abstract class AbstractPopup extends JPopupMenu implements
		ActionListener {

	private JComponent host;
	private JComponent component;

	protected JMenuItem[] menuItems;
	protected static Clipboard clipboard;

	public AbstractPopup(JComponent host, JComponent component) {
		this.host = host;
		this.component = component;
		init();
	}

	private void init() {
		buildMenu();
		getClipboard();
	}

	protected void buildMenu() {

		menuItems = new JMenuItem[3];

		String cut = "Вырезать"; // rb.getString("popup.cut"); // I18N
		String copy = "Копировать"; // rb.getString("popup.copy"); // I18N
		String paste = "Вставить"; // rb.getString("popup.paste"); // I18N

		String[] labels = new String[] { cut, copy, paste };
		String[] cmds = new String[] { "cut", "copy", "paste" };

		for (int i = 0; i < labels.length; ++i) {
			menuItems[i] = new JMenuItem(labels[i]);
			menuItems[i].setActionCommand(cmds[i]);
			menuItems[i].addActionListener(this);
			add(menuItems[i]);
		}
	}

	protected boolean checkClipboardAccess() {
		SecurityManager security = System.getSecurityManager();
		if (security != null) {
			try {
				security.checkSystemClipboardAccess();
				return true;
			} catch (SecurityException se) {
				System.err.println(se.getCause());
				System.err.println(se.getMessage());
				se.printStackTrace();
				return false;
			}
		} else {
			return true;
		}
	}

	protected void getClipboard() {
		if (checkClipboardAccess()) {
			try {
				clipboard = host.getToolkit().getSystemClipboard();
			} catch (HeadlessException he) {
				System.err.println(he.getCause());
				System.err.println(he.getMessage());
				he.printStackTrace();
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("cut"))
			cut();
		if (cmd.equals("copy"))
			copy();
		if (cmd.equals("paste"))
			paste();
	}

	protected abstract void paste();

	protected abstract void copy();

	protected abstract void cut();

}
