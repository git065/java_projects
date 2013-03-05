package com.adslur.szt.renderers;

import javax.swing.*;
import java.awt.*;

public class AnimatedLabel extends JLabel implements Runnable {
	protected Icon[] m_icons;
	protected int m_index = 0;
	boolean done;
	protected boolean m_isRunning;
	Thread tr;

	public AnimatedLabel(String gifName, int numGifs) {
		m_icons = new Icon[numGifs];
		for (int k = 0; k < numGifs; k++)
			m_icons[k] = new ImageIcon("Images/" + gifName + k + ".GIF");

		setIcon(m_icons[0]);
		tr = new Thread(this);
		// tr.setPriority(Thread.MAX_PRIORITY);
		tr.start();
	}

	public void setRunning(boolean isRunning) {
		m_isRunning = isRunning;
	}

	public boolean getRunning() {
		return m_isRunning;
	}

	public void setStart(int index) {
		m_index = index;
		setIcon(m_icons[m_index]);
		setVisible(true);

	}

	public void stop() {
		done = true;
		while (tr.isAlive()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
		tr = null;
	}

	public void stopA() {
		done = true;
		tr = null;

	}

	public void startA() {
		done = false;
		setRunning(true);

		if (tr == null) {

			tr = new Thread(this);

			tr.start();
		}
	}

	public void run() {

		// while(true) {
		while (!done) {
			// System.out.println("-------TRACE     ANIMATED LABEL RUN =====> "
			// + m_index );
			if (m_isRunning) {
				m_index++;
				if (m_index >= m_icons.length)
					m_index = 1;
				setIcon(m_icons[m_index]);

			} else {
				if (m_index > 0) {
					m_index = 0;
					setIcon(m_icons[0]);
				}

				if (m_index == 0) {
					setIcon(m_icons[1]);

				}

			}

			try {
				Thread.sleep(500);
			} catch (Exception ex) {
			}

		}
		tr = null;

	}
}