package com.adslur.szt.gui;

import java.awt.*;
import javax.swing.event.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.beans.*;

import com.adslur.szt.domen.PortProperty;
import com.adslur.szt.jdbc.MvbOracleConnection;
import com.adslur.szt.panel.MoreInfoPanel;

public class SearchTV extends JInternalFrame implements ActionListener,
		MouseListener, InternalFrameListener

{
	private PortProperty tv = new PortProperty();

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
	MyWorker worker = null;
	JPanel panel2;

	volatile boolean cansel = false;
	boolean animating = false;
	Timer timer;
	public static int count = 2;
	ImageIcon image1 = new ImageIcon("Images/1_1.gif");
	ImageIcon image2 = new ImageIcon("Images/1_2.gif");

	public SearchTV() {

		JPanel buttonpanel;
		EmptyBorder border = new EmptyBorder(new Insets(0, 0, 0, 5));
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.insets = new Insets(4, 4, 4, 4);

		setTitle("Проверка технической возможности ");

		// JPanel panel2 = new JPanel(new BorderLayout());
		panel2 = new JPanel(new BorderLayout());
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
		// UIDefaultsPopupDialog popup = new UIDefaultsPopupDialog(this, dTlf);
		// dTlf.setComponentPopupMenu(popup);
		dTlf.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {

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
		cancelbutton.setEnabled(false);
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

		cancelbutton.addActionListener(this);
		okbutton.addActionListener(this);
		cancelbutton.addMouseListener(this);
		okbutton.addMouseListener(this);
		addMouseListener(this);
		addInternalFrameListener(this);
		setSize(500, 400);
		setFrameIcon(image1);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setResizable(true);

	}

	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == okbutton) {
			mvb.setCountRequest(mvb.getCountRequest() + 1);
			cansel = false;
			
			okbutton.setEnabled(false);
			cancelbutton.setEnabled(true);
			if (worker == null) {
				(worker = new MyWorker()).execute();
				// System.out.println("TRACE   -WORKER- CREATE:" + worker);
			}

		}

		else if (evt.getSource() == cancelbutton) {

			cansel = true;
			okbutton.setEnabled(true);
			cancelbutton.setEnabled(false);
			worker.cancel(true);
			worker = null;
			mvb.setCountRequest(mvb.getCountRequest() - 1);
			
			area.append("Запрос на определение" + "\n"
					+ " технической возможности" + "  отменен !!!" + "\n");

		}

		mip.showBottom(true);

	}

	public PortProperty findTv(String tlf) {

		int errCode;

		area.setText("");
		area.append("Отправлен запрос на определение технической возможности"
				+ "\n" + " для клиента   с номером телефона :" + tlf + "\n");
		// tvd = new PortPropertyData();
		tv = new PortProperty();

		tv = mvb.findTV(tlf);
		// System.out.println(" TRACE  START  -  findTv");

		return tv;

	}

	private void ClearField() {
		area.setText("");

	}

	private void printArea(PortProperty tv) {
		area.setLineWrap(true);

		area.append("Количество портов :" + tv.getCount() + "\n");
		area.append("Количество  свободных портов :" + tv.getCountLiberty()
				+ "\n");

		if (tv.getErrCode() == -20004) {
			area.append("Нет диапазона для данного телефона\n");
		}
		if (tv.getErrCode() == -20005) {
			area.append("Нет свободных портов  для данного диапазона\n");
		}

		System.out.println("END  -  -  findTv");

	}

	public static void main(String[] args) {

		SearchTV dialog = new SearchTV();

		dialog.pack();
		dialog.setVisible(true);

	}

	// ///////
	public class MyWorker extends SwingWorker<PortProperty, Void> {

		@Override
		protected PortProperty doInBackground() {
			//System.out.println("TRACE  RUN BACKGROUND  SERCH TV");
			if (!isCancelled()) {
				
				tv = findTv(dTlf.getText());
				return null;
			}
			
			return tv;
		}

		@Override
		protected void done() {
			try {

				if (cansel == false) {

					
					printArea(tv);
					okbutton.setEnabled(true);
					cancelbutton.setEnabled(false);

					worker = null;
					
					timer = new Timer(1000, new TimerCaption());
					timer.start();
					animating = true;
					if (mvb.getCountRequest() != 0) {
						mvb.incCountRequest(1);
					}
					if (mvb.getCountRequest() == 0) {
						mvb.close();
					}
					System.out.println("TRACE    DONE  SEARCH TV :"	+ mvb.getCountRequest());
					
				}
			} catch (Exception ignore) {
			}

		}

	}

	// ///

	public void mouseEntered(MouseEvent e) {
/*
		Object object = e.getSource();
		if (object == cancelbutton) {

			Component c = e.getComponent();

			if (c instanceof JButton) {
				JButton b = (JButton) c;
				// System.out.println("TRACE   mouseEntered" + cansel );
				b.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}
*/
	}

	// WAIT_CURSOR

	public void mouseExited(MouseEvent e) {

/*
		Object object = e.getSource();
		if (object == cancelbutton) {
			// System.out.println("TRACE   INER mouseExited" + cansel );

			Component c = e.getComponent();

			if (c instanceof JButton) {

				if (cansel == false) {
					JButton b = (JButton) c;

					b.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					// System.out.println("TRACE   ----- >   mouseExited" +
					// cansel);
				}
			}
		}
*/
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e)

	{
		if (animating){
                                              timer.stop();
		     setFrameIcon(image1);
                                        }

	}

	public void mouseReleased(MouseEvent e) {
	}

	public void internalFrameOpened(InternalFrameEvent e) {
	};

	public void internalFrameClosing(InternalFrameEvent e) {
	};

	public void internalFrameClosed(InternalFrameEvent e) {
	};

	public void internalFrameIconified(InternalFrameEvent e) {
	};

	public void internalFrameDeiconified(InternalFrameEvent e) {
	};

	public void internalFrameActivated(InternalFrameEvent e) {
		// System.out.println("TRACE   ----- >  TIMER  STOP ");
		if (animating == true) {

			timer.stop();
			setFrameIcon(image1);
		}
	};

	public void internalFrameDeactivated(InternalFrameEvent e) {
	};

	class TimerCaption implements ActionListener {

		public void actionPerformed(ActionEvent evt) {

			if (count == 2) {
				count = count - 1;
				setFrameIcon(image2);
			} else if (count == 1) {
				count = count + 1;
				setFrameIcon(image1);
			}
			// System.out.println("TRACE   ----- >  TIMER  START "+count);

		}

	}
}
