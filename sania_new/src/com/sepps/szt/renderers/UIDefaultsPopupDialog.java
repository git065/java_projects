package com.sepps.szt.renderers;

import javax.swing.*;
import javax.swing.JTextField;

public class UIDefaultsPopupDialog extends CutNPastePopupDialog {

	public UIDefaultsPopupDialog(JDialog f, JTextField txt) {
		super(f, txt);
	}

	protected void buildMenu() {
		super.buildMenu();
		menuItems[0].setEnabled(false);
	}
}
