package com.adslur.szt.renderers;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TimerLabel extends JLabel implements Runnable {

	Thread _timerThread;

	long _time;
	long _startTime;

	public TimerLabel(String msg) {
		_startTime = -1L;
		setText("0:00:00");

	}

	public void setCountdown(int time) {
		_time = 1000L * (long) time;
		display(_time);
	}

	public void start() {

		setText("0:00:00");
		_startTime = -1L;

		if (_startTime == -1L) {
			_startTime = System.currentTimeMillis();
		}

		if (_timerThread == null) {
			_timerThread = new Thread(this);
			_timerThread.start();
		}
	}

	public void stop() {

		_timerThread = null;
		// System.out.println("-------TRACE  TIMERLABEL  STOP END=====> " +
		// _timerThread );

	}

	public void run() {

		// While there's still time left and noone stopped the thread.
		long remainingTime;

		try {
			do {
				// System.out.println("-------TRACE  TIMERLABEL  THREAD=====> "
				// );
				Thread.sleep(100); // Wait 1/10 of a second.
				remainingTime = System.currentTimeMillis() - _startTime;
				apply(display(remainingTime)); // decrease the time by a second
												// and display it.
			} while ((remainingTime >= 0)
					&& (Thread.currentThread() == _timerThread));
		} catch (InterruptedException ignored) {
		}

	}

	String display(long time) {
		int timeSec = (int) (time / 1000L); // Display accurate to 1 s.
		StringBuffer timeString = new StringBuffer();
		int hours = timeSec / 3600;
		timeString.append("" + hours);
		int minutes = (timeSec / 60) % 60;
		timeString.append(((minutes < 10) ? ":0" : ":") + minutes);
		int seconds = timeSec % 60;
		timeString.append(((seconds < 10) ? ":0" : ":") + seconds);
		return timeString.toString();
		// /// _display.setText( timeString.toString());
		// // _display.paintImmediately( 0, 0, _displayWidth, _displayHeight);
	}

	public int getRemainingTime() {
		return (int) (_time / 1000L);
	}

	public void apply(String msg) {
		setText(msg);

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

		JPanel p = new JPanel();
		TimerLabel lbl = new TimerLabel("0:00:00");
		p.add(lbl);
		lbl.start();
		frame.getContentPane().add(p);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(1000, 1000);

		frame.setVisible(true);

	}
}
