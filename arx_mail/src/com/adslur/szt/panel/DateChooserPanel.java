package com.adslur.szt.panel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.Locale;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JSpinnerDateEditor;

public class DateChooserPanel extends JPanel implements PropertyChangeListener {
	private static final long serialVersionUID = -1282280858252793253L;
	private JComponent[] components;

	JLabel lblNar;
	JTextField edtNar;
	JButton addButton, exitButton;
	JLabel labelImage;
	JLabel NumNar;
	JLabel DateStopStart;
	JLabel DateStopEnd;
	JTextField ufield;
	Dimension shortField = new Dimension(120, 20);
	EventListenerList listeners;

	public DateChooserPanel() {
		setName("JDateChooser");

		edtNar = new JTextField();
		addButton = new JButton("Применить");
		addButton.setActionCommand("DATE_CHOOS_OK");
		addButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				DateChooseOkActionPerformed(evt);
			}
		});

		exitButton = new JButton("Отмена");
		exitButton.setActionCommand("DATE_CHOOS_CANSEL");
		exitButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				DateChooseCanselActionPerformed(evt);
			}
		});

		components = new JComponent[2];
		components[0] = new JDateChooser();
		components[1] = new JDateChooser();

		// addEntry("Номер наряда", edtNar, gridbag);
		// addEntry("Дата начала приостановления", components[0], gridbag);
		// addEntry("Дата окончания приостановления", components[1], gridbag);
		// addEntry("", addButton, gridbag);
		// addEntry("", exitButton, gridbag);
		init();
		listeners = new EventListenerList();

	}

	private void init() {

		JPanel buttonpanel;
		EmptyBorder border = new EmptyBorder(new Insets(0, 0, 0, 5));
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		c.insets = new Insets(4, 4, 4, 4);

		this.setLayout(new BorderLayout());

		JPanel panel2 = new JPanel(new BorderLayout());
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.setLayout(new GridBagLayout());
		panel1.setBorder(BorderFactory.createCompoundBorder(BorderFactory
				.createEtchedBorder(), BorderFactory.createEmptyBorder(20, 5,
				20, 5)));

		c.insets = new Insets(0, 0, 0, 64);
		labelImage = new JLabel(new ImageIcon("Images/VtmTimer.gif"));
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 3;
		c.anchor = GridBagConstraints.NORTHEAST;
		layout.setConstraints(labelImage, c);
		panel1.add(labelImage, c);

		c.insets = new Insets(4, 4, 4, 4);
		NumNar = new JLabel("Номер наряда:");
		NumNar.setBorder(border);
		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.WEST;
		layout.setConstraints(NumNar, c);
		panel1.add(NumNar, c);

		ufield = new JTextField();
		// ufield.setColumns(20);
		ufield.setPreferredSize(shortField);
		c.gridwidth = 1;
		c.gridx = 2;
		c.gridy = 0;
		c.anchor = GridBagConstraints.WEST;
		layout.setConstraints(ufield, c);
		panel1.add(ufield, c);

		c.insets = new Insets(4, 4, 4, 4);
		DateStopStart = new JLabel("Дата начала приостановления услуги:");
		DateStopStart.setBorder(border);
		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.WEST;
		layout.setConstraints(DateStopStart, c);
		panel1.add(DateStopStart, c);

		c.gridwidth = 1;
		c.gridx = 2;
		c.gridy = 1;
		// this.dateEditor1.getUiComponent().setPreferredSize( shortField );

		((JFormattedTextField) ((JDateChooser) components[0]).getDateEditor())
				.setEditable(false);
		((JFormattedTextField) ((JDateChooser) components[0]).getDateEditor())
				.setBackground(Color.WHITE);

		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.WEST;
		layout.setConstraints(components[0], c);

		panel1.add(components[0], c);

		c.insets = new Insets(4, 4, 4, 4);
		DateStopStart = new JLabel("Дата окончания приостановления услуги:");
		DateStopStart.setBorder(border);
		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.WEST;
		layout.setConstraints(DateStopStart, c);
		panel1.add(DateStopStart, c);

		c.gridwidth = 1;
		c.gridx = 2;
		c.gridy = 2;
		((JFormattedTextField) ((JDateChooser) components[1]).getDateEditor())
				.setEditable(false);
		((JFormattedTextField) ((JDateChooser) components[1]).getDateEditor())
				.setBackground(Color.WHITE);

		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.WEST;
		layout.setConstraints(components[1], c);
		panel1.add(components[1], c);

		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 4;
		c.fill = GridBagConstraints.NONE;
		c.ipadx = 40;
		c.insets = new Insets(20, 20, 0, 20);
		c.anchor = GridBagConstraints.WEST;
		layout.setConstraints(addButton, c);
		panel1.add(addButton, c);

		c.gridwidth = 1;
		c.gridx = 2;
		c.gridy = 4;
		c.ipadx = 0;
		c.anchor = GridBagConstraints.WEST;
		layout.setConstraints(exitButton, c);
		panel1.add(exitButton, c);

		add(panel1, BorderLayout.WEST);

	}

	public String getDateFormatString() {
		return ((JDateChooser) components[1]).getDateFormatString();
	}

	public void setDateFormatString(String dfString) {
		for (int i = 0; i < 4; i++) {
			((JDateChooser) components[i]).setDateFormatString(dfString);
		}
	}

	public Date getDate1() {
		return ((JDateChooser) components[0]).getDate();
	}

	public Date getDate2() {
		return ((JDateChooser) components[1]).getDate();
	}

	public void setDate(Date date) {
		for (int i = 0; i < 4; i++) {
			((JDateChooser) components[i]).setDate(date);
		}
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("date")) {
			setDate((Date) evt.getNewValue());
		}
	}

	public Locale getLocale() {
		return ((JDateChooser) components[0]).getLocale();
	}

	public void setLocale(Locale locale) {
		for (int i = 0; i < 5; i++) {
			components[i].setLocale(locale);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Component#isEnabled()
	 */
	public boolean isEnabled() {
		return ((JDateChooser) components[0]).isEnabled();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#setEnabled(boolean)
	 */
	public void setEnabled(boolean enabled) {
		for (int i = 0; i < 5; i++) {
			components[i].setEnabled(enabled);
		}
	}

	public Date getMinSelectableDate() {
		return ((JDateChooser) components[0]).getMinSelectableDate();
	}

	public void setMinSelectableDate(Date date) {
		for (int i = 0; i < 4; i++) {
			((JDateChooser) components[i]).setMinSelectableDate(date);
		}
	}

	public Date getMaxSelectableDate() {
		return ((JDateChooser) components[0]).getMaxSelectableDate();
	}

	public void setMaxSelectableDate(Date date) {
		for (int i = 0; i < 4; i++) {
			((JDateChooser) components[i]).setMaxSelectableDate(date);
		}
	}

	/*
	 * ------
	 */
	private void fireActionEvent(ActionEvent evt) {
		ActionListener[] listenerList = listeners
				.getListeners(ActionListener.class);

		for (int i = listenerList.length - 1; i >= 0; --i) {
			listenerList[i].actionPerformed(evt);
		}

	}

	public void addActionListener(ActionListener listener) {
		listeners.add(ActionListener.class, listener);
	}

	public void removeActionListener(ActionListener listener) {
		if (listeners != null) {
			listeners.remove(ActionListener.class, listener);
		}
	}

	private void DateChooseOkActionPerformed(java.awt.event.ActionEvent evt) {
		fireActionEvent(evt);
	}

	private void DateChooseCanselActionPerformed(java.awt.event.ActionEvent evt) {
		fireActionEvent(evt);
	}

	public static void main(String[] s) {
		JFrame frame = new JFrame("JDateChooser");
		DateChooserPanel dateChooser = new DateChooserPanel();

		frame.getContentPane().add(dateChooser);
		// frame.getContentPane().add(panel2);
		frame.setSize(1000, 1000);
		frame.pack();
		frame.setVisible(true);
	}
}
