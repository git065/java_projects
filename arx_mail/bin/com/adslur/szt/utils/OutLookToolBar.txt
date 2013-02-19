package com.adslur.szt.utils;

import java.awt.Component;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JToolBar;

public final class OutLookToolBar {

	private JToolBar m_toolbar = null;

	public OutLookToolBar(JToolBar t) {
		m_toolbar = t;

		if (m_toolbar == null) {
			throw new NullPointerException(
					"JToolBar instance required for OutLookToolBar is NULL!");
		}

		m_toolbar.addContainerListener(new MyContainerListener());
	}

	private final class MyContainerListener implements ContainerListener {

		public MyContainerListener() {
			transform();
		}

		private void transform() {
			if (m_toolbar != null) {
				int count = m_toolbar.getComponentCount();

				for (int i = 0; i < count; i++) {
					Component c = m_toolbar.getComponentAtIndex(i);

					if (c instanceof JButton) {
						JButton b = (JButton) c;
						b.setBorderPainted(false);
						b.setFocusPainted(false);
						b.addMouseListener(new MouseOver());
					}
				}
			}
		}

		public void componentAdded(ContainerEvent e) {
			transform();
		}

		public void componentRemoved(ContainerEvent e) {
			m_toolbar.repaint();
		}

	}

	private static final class MouseOver implements MouseListener {

		public void mouseEntered(MouseEvent e) {
			Component c = e.getComponent();
			if (c instanceof JButton) {
				JButton b = (JButton) c;
				b.setBorderPainted(true);
			}
		}

		public void mouseExited(MouseEvent e) {
			Component c = e.getComponent();
			if (c instanceof JButton) {
				JButton b = (JButton) c;
				b.setBorderPainted(false);
			}
		}

		public void mouseClicked(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}

	}

}
