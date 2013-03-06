package com.sepps.szt.renderers;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;

import java.io.IOException;

import javax.swing.*;

public class CutNPastePopup extends AbstractPopup {
	private JInternalFrame host;
	private JTextField textComponent;

	public CutNPastePopup(JInternalFrame host, JTextField component) {
		super(host, component);
		this.host = host;
		textComponent = component;
	}

	protected void cut() {
		if (textComponent.getSelectedText() != null) {
			copy();
			textComponent.setText("");
		}
	}

	protected void copy() {
		String data = textComponent.getSelectedText();
		if (data != null) {
			StringSelection ss = new StringSelection(data);
			clipboard.setContents(ss, ss);
		} else {
			return;
		}
	}

	protected void paste() {
		Transferable t = clipboard.getContents(textComponent);
		if (t == null) {
			return; // do nothing
		}
		try {
			String s = (String) t.getTransferData(DataFlavor.stringFlavor);
			textComponent.setText(s);
		} catch (UnsupportedFlavorException ufe) {
			return;
		} catch (IOException ioe) {
			return;
		}
	}

}
