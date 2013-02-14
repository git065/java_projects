/*test*/
package simple_album;
import java.net.URL;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class SimpleAlbum {

	JFrame frame1;
	Boolean visible;
	JButton ButtonRight;
	JButton ButtonLeft;
	int i = 0;

	String[] Img_Array = { "1.jpg", "2.jpg", "3.jpg", "4.jpg", "5.jpg", "6.jpg" };

	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		SimpleAlbum gui = new SimpleAlbum();
		gui.go();
	}

	public void go() {
		frame1 = new JFrame();
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		visible = false;

		ButtonRight = new JButton(">");
		ButtonRight.addActionListener(new LabelButtonListenerRight());

		ButtonLeft = new JButton("<");
		ButtonLeft.addActionListener(new LabelButtonListenerLeft());

		// label = new JLabel("I'm a label");
		MyDrawPanel drawPanel = new MyDrawPanel();

		frame1.getContentPane().add(BorderLayout.CENTER, drawPanel);
		frame1.getContentPane().add(BorderLayout.EAST, ButtonRight);
		frame1.getContentPane().add(BorderLayout.WEST, ButtonLeft);

		frame1.setSize(700, 700);
		frame1.setVisible(true);

	}

	class LabelButtonListenerRight implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (i < 5) {
				i++;
			} else {
				i = 0;
			}
			frame1.repaint();
		}
	} // close inner class
	
	class LabelButtonListenerLeft implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (i > 0) {
				i--;
			} else {
				i = 5;
			}
			frame1.repaint();
		}
	} // close inner class

	class ColorButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			frame1.repaint();
		}
	} // close inner class
	
	protected static ImageIcon createIcon(String path) {
		URL imgURL = SimpleAlbum.class.getResource(path);		
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("File not found " + path);
			return null;
		}
	}

	class MyDrawPanel extends JPanel {

		public void paintComponent(Graphics g) {

			Graphics2D g2d = (Graphics2D) g;
			int w = getWidth();
			int h = getHeight();

			GradientPaint gradient = new GradientPaint(0, 0, Color.BLUE, 700,
					700, Color.ORANGE);
			g2d.setPaint(gradient);
			g2d.fillRect(0, 0, w, h);
			String path = "images/" + Img_Array[i];
			
			ImageIcon myimg = createIcon(path);
			
			g2d.drawImage(myimg.getImage(), 150, 150, this);
		}

	}

}
