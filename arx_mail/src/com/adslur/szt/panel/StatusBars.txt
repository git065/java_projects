package com.adslur.szt.panel;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.adslur.szt.renderers.AnimatedLabel;
import com.adslur.szt.renderers.TimerLabel;

public class StatusBars extends JPanel implements PropertyChangeListener {

	private static StatusBars _stb = null;
	JLabel labelText;
	JLabel labelTextMess;

	public AnimatedLabel m_runner;
	public TimerLabel timelbl;

	GridBagConstraints c1 = new GridBagConstraints();
	Dimension hugeFieldshortmax = new Dimension(600, 20);

	public StatusBars() {

		this.setLayout(new BorderLayout());

		JPanel p = new JPanel();
		JPanel middlePanel = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.add(top1Panel());
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
		middlePanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory
				.createEtchedBorder(), BorderFactory.createEmptyBorder(2, 2, 2,
				2)));
		middlePanel.add(p);

		this.add(middlePanel, BorderLayout.NORTH);

	}

	public static StatusBars getInstance() {
		if (_stb == null) {
			_stb = new StatusBars();

		}

		return _stb;
	}

	private JPanel top1Panel() {

		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints с = new GridBagConstraints();

		с.insets = new Insets(2, 2, 2, 2);

		JPanel panel2 = new JPanel();
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridBagLayout());
		panel1.setBorder(new TitledBorder(new EtchedBorder(), ""));
		panel1.setMinimumSize(new Dimension(60, 100));

		labelText = new JLabel("Статус выполнения операции :");
		с.gridx = 0;
		с.gridy = 0;
		с.gridwidth = 1;
		с.gridheight = 1;
		с.anchor = GridBagConstraints.WEST;
		layout.setConstraints(labelText, с);
		panel1.add(labelText, с);

		labelTextMess = new JLabel("");
		с.gridx = 1;
		с.gridy = 0;
		с.gridwidth = 1;
		с.gridheight = 1;
		с.weightx = 1.0;
		с.anchor = GridBagConstraints.WEST;
		layout.setConstraints(labelTextMess, с);
		panel1.add(labelTextMess, с);

		m_runner = new AnimatedLabel("clock", 5);
		с.gridx = 2;
		с.gridy = 0;
		с.gridwidth = 1;
		с.gridheight = 1;
		с.weightx = 1.0;
		с.anchor = GridBagConstraints.EAST;
		layout.setConstraints(m_runner, с);
		panel1.add(m_runner, с);

		m_runner.setStart(0);

		m_runner.setRunning(false);

		timelbl = new TimerLabel("0:00:00");
		с.gridx = 3;
		с.gridy = 0;
		с.gridwidth = 1;
		с.gridheight = 1;
		с.weightx = 0;
		с.anchor = GridBagConstraints.EAST;
		layout.setConstraints(timelbl, с);
		panel1.add(timelbl, с);

		return panel1;

	}

	public void setMessage(String msg) {
		labelTextMess.setText(msg);

	}

	public String getMessage() {
		return labelTextMess.getText();

	}

	public void propertyChange(PropertyChangeEvent e) {
		// System.out.println("-------TRACE   FAIER  STATUS BAR =====> " );

		String propertyName = e.getPropertyName();
		if ("start".equals(propertyName)) {
			System.out.println("-------TRACE   FAIER   START =====> ");
			setMessage("Операция выполняется     -   ждите");
			m_runner.startA();
			// System.out.println("-------TRACE   THREAD   START =====> " );
			m_runner.setRunning(true);
			timelbl.start();
		} else if ("done".equals(propertyName)) {
			setMessage("Операция выполнена");
			System.out
					.println("-------TRACE   FAIER   DONE STATUS BAR =====> ");
			timelbl.stop();
			m_runner.setRunning(false);
			m_runner.stop();

			System.out
					.println("-------TRACE   FAIER   DONE STATUS BAR     TIMER  STOP=====> ");
		}

	}

	public static void main(String args[]) {
		JFrame frame = new JFrame("Fredy Browser");
		frame.addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
			}

			public void windowClosed(WindowEvent e) {
			}

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

			public void windowDeactivated(WindowEvent e) {
			}

			public void windowDeiconified(WindowEvent e) {
			}

			public void windowIconified(WindowEvent e) {
			}

			public void windowOpened(WindowEvent e) {
			}
		});

		StatusBars browser = new StatusBars();
		// browser.start();

		frame.getContentPane().add(browser);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(1000, 1000);
		// m_runner.startA();

		// m_runner.setRunning (true);
		// _time.start();
		frame.setVisible(true);

	}
}
