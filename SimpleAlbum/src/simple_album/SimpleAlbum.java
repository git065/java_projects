
package simple_album;
import java.net.URL;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class SimpleAlbum {

	JFrame frame;
	JPanel panel;
	JLabel label;
	Boolean visible;
	JButton ButtonRight;
	JButton ButtonLeft;
	JButton ButtonGo;
	JTextField field;
	String img = "1.jpg";
	
	int i = 0;

	String[] Img_Array = { "1.jpg", "2.jpg", "3.jpg", "4.jpg", "5.jpg", "6.jpg" };

	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		SimpleAlbum gui = new SimpleAlbum();
		gui.go();
	}

	public void go() {
		field = new JTextField(20);
		label = new JLabel("Enter images name:");
		JPanel test = new JPanel();
		
		frame = new JFrame();
		panel = new JPanel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		visible = false;

		ButtonRight = new JButton(">");
		ButtonRight.addActionListener(new ButtonListenerRight());

		ButtonLeft = new JButton("<");
		ButtonLeft.addActionListener(new ButtonListenerLeft());
		
		ButtonGo = new JButton("Go");
		ButtonGo.addActionListener(new ButtonListenerGo());
		
		panel.add(ButtonLeft);
		panel.add(ButtonRight);
		
		test.add(label);
		test.add(field);
		test.add(ButtonGo);
		//panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		// label = new JLabel("I'm a label");
		MyDrawPanel drawPanel = new MyDrawPanel();

		frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
		frame.getContentPane().add(BorderLayout.SOUTH, panel);
		frame.getContentPane().add(BorderLayout.NORTH, test);

		frame.setSize(700, 700);
		frame.setVisible(true);

	}

	class ButtonListenerRight implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (i < 5) {
				i++;
			} else {
				i = 0;
			}
			frame.repaint();
		}
	} // close inner class
	
	class ButtonListenerLeft implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (i > 0) {
				i--;
			} else {
				i = 5;
			}
			frame.repaint();
		}
	} // close inner class

	class ButtonListenerGo implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			img = field.getText();
			frame.repaint();
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
			/*String path = "images/" + Img_Array[i];*/
			String path = "images/" + img;
			
			ImageIcon myimg = createIcon(path);
			
			if(myimg == null){
				myimg = createIcon("images/1.jpg");
				label.setText("File" + img + " not found!");
			}
			g2d.drawImage(myimg.getImage(), 150, 150, this);
		}

	}

}
