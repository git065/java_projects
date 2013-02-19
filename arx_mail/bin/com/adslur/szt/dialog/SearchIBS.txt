package com.adslur.szt.dialog;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;

import com.adslur.szt.domen.PropertyIbsData;
import com.adslur.szt.jdbc.MvbOracleConnection;
import com.adslur.szt.panel.MoreInfoPanel;

public class SearchIBS extends JDialog {

	private PropertyIbsData tvd = null;
	private MvbOracleConnection mvb = MvbOracleConnection.getInstance();
	private int framePositionX;
	private int framePositionY;
	JScrollPane scroller;
	MoreInfoPanel mip;
	private Rectangle frameBounds = null;

	JButton btnOk = new JButton("Ok");

	JLabel lblTlf;
	JTextField dTlf;

	JButton okbutton;
	JButton cancelbutton;
	JTextArea area;

	public SearchIBS() {

		JPanel buttonpanel;
		EmptyBorder border = new EmptyBorder(new Insets(0, 0, 0, 5));
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.insets = new Insets(4, 4, 4, 4);

		setTitle("Проверка по ИБС ");

		JPanel panel2 = new JPanel(new BorderLayout());
		panel2.setBorder(BorderFactory.createCompoundBorder(BorderFactory
				.createEtchedBorder(), BorderFactory.createEmptyBorder(20, 5,
				20, 5)));
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.setLayout(new GridBagLayout());

		constraints.insets = new Insets(4, 4, 4, 4);
		lblTlf = new JLabel("Номер телефона :");
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(lblTlf, constraints);
		panel1.add(lblTlf, constraints);

		dTlf = new JTextField();
		dTlf.setColumns(20);
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(dTlf, constraints);
		panel1.add(dTlf, constraints);

		dTlf.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (dTlf.getText().length() < 7) {

					// ClearField();
					// mip.showBottom(false);

				}

			}

		});

		constraints.insets = new Insets(40, 40, 4, 4);
		okbutton = new JButton("Применить");
		getRootPane().setDefaultButton(okbutton);
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(okbutton, constraints);
		panel1.add(okbutton, constraints);

		constraints.insets = new Insets(40, 40, 4, 40);
		cancelbutton = new JButton("Отмена");
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		layout.setConstraints(cancelbutton, constraints);
		panel1.add(cancelbutton, constraints);
		panel1.add(cancelbutton, constraints);

		panel2.add(panel1, BorderLayout.NORTH);

		setSize(800, 200);
		setResizable(false);

		Dimension screenSize = getToolkit().getScreenSize();
		frameBounds = getBounds();
		framePositionX = (screenSize.width - frameBounds.width) / 2;
		framePositionY = (screenSize.height - frameBounds.height) / 2;
		setLocation(framePositionX, framePositionY);

		Container grabbedContent = this.getContentPane();
		area = new JTextArea("", 5, 30);
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		area.setEditable(false);

		scroller = new JScrollPane(area,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		mip = new MoreInfoPanel(grabbedContent, scroller);

		grabbedContent.add(panel2);

		setContentPane(mip);

		cancelbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				setVisible(false);
			}

		});

		okbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				dTlf.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				ClearField();
				if (dTlf.getText().equals("")) {

					JOptionPane.showMessageDialog(null,
							"Не запонено поле  <номер телефона>", "Внимание!!",
							JOptionPane.WARNING_MESSAGE);
				} else {
					findIBS(dTlf.getText());
				}
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				dTlf.setCursor(Cursor
						.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

				// mip.getInstanse().handleClick();
				mip.showBottom(true);

			}

		});

	}

	public void findIBS(String tlf) {
		String flagUVO = null;
		String flagSPAR = null;

		System.out.println("START  -  findTv");
		area.setText("");
		area.append("У клиента с номером телефона :" + tlf + "\n");

		tvd = new PropertyIbsData();

		flagUVO = tvd.findUVO(tlf);
		area.setLineWrap(true);
		if (flagUVO.equals("Y")) {
			area.append("У клиента обнаружено наличие охранной сигнализации"
					+ "\n");
		} else if (flagUVO.equals("N")) {
			area.append("У клиента не обнаружено наличие охранной сигнализации"
					+ "\n");
		}

		flagSPAR = tvd.findSPAR(tlf);
		area.setLineWrap(true);
		if (flagSPAR.equals("Y")) {
			area.append("У клиента обнаружено наличие АВУ" + "\n");
		} else if (flagSPAR.equals("N")) {
			area.append("У клиента не обнаружено наличие АВУ" + "\n");
		}

		System.out.println("END  -  - findIBS");

	}

	private void ClearField() {
		area.setText("");

	}

	public static void main(String[] args) {

		SearchIBS dialog = new SearchIBS();

		dialog.pack();
		dialog.setVisible(true);

	}

}