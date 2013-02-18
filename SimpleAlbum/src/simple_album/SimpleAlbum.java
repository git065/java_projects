/*user_panel*/
package simple_album;

import java.net.URL;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class SimpleAlbum {

	JFrame frame;
	JLabel label;
	Boolean visible;
	JButton ButtonRight;
	JButton ButtonLeft;
	JButton ButtonGo;
	JTextField field;
	JTextArea log;
	String path = "images/1.jpg";
	String img = "1.jpg";
	Font font;

	int i = 0;

	String[] Img_Array = { "1.jpg", "2.jpg", "3.jpg", "4.jpg", "5.jpg", "6.jpg" };

	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		SimpleAlbum gui = new SimpleAlbum();
		gui.go();
	}

	public void go() {
		font = new Font("Verdana", Font.PLAIN, 11);
		field = new JTextField(20);
		label = new JLabel("Enter images name:");
		JPanel user_panel = new JPanel();
		JPanel logs_panel = new JPanel();
		
		log = new JTextArea(24, 20);
		log.setLineWrap(true);
		JScrollPane scroller = new JScrollPane(log);
		
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		logs_panel.add(scroller);
		log.setFont(font);
		log.setForeground(Color.BLACK);
		
		frame = new JFrame();
		JPanel panel = new JPanel();
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
		
		field.setText("6.jpg");
		field.requestFocus();
		field.addActionListener(new ButtonListenerGo());

		user_panel.add(label);
		user_panel.add(field);
		user_panel.add(ButtonGo);
		// panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		// label = new JLabel("I'm a label");
		MyDrawPanel drawPanel = new MyDrawPanel();

		frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
		frame.getContentPane().add(BorderLayout.SOUTH, panel);
		frame.getContentPane().add(BorderLayout.NORTH, user_panel);
		frame.getContentPane().add(BorderLayout.EAST, logs_panel);

		frame.setSize(700, 500);
		frame.setVisible(true);

	}

	class ButtonListenerRight implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (i < 5) {
				i++;
			} else {
				i = 0;
			}
			img = Img_Array[i];	
			log.append("Кнопка '>' нажата! \n");
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
			img = Img_Array[i];			
			log.append("Кнопка '<' нажата! \n");
			frame.repaint();
		}
	} // close inner class

	class ButtonListenerGo implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			img = field.getText();			
			frame.repaint();
			log.append("Кнопка 'Go' нажата! \n");
		}
	} // close inner class

	protected static ImageIcon createIcon(String path) {
		URL imgURL = SimpleAlbum.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Файл не найден " + path);
			return null;
		}
	}

	class MyDrawPanel extends JPanel {

		public void paintComponent(Graphics g) {

			Graphics2D g2d = (Graphics2D) g;
			int w = getWidth();
			int h = getHeight();
			path = "images/" + img;

			GradientPaint gradient = new GradientPaint(0, 0, Color.BLUE, 700,
					700, Color.ORANGE);
			g2d.setPaint(gradient);
			g2d.fillRect(0, 0, w, h);		

			ImageIcon myimg = createIcon(path);

			if (myimg == null) {
				log.setForeground(Color.RED);
				label.setText(img + " не найден!");
				log.append(img + " не найден!\n");
				img = "1.jpg";
				myimg = createIcon("images/" + img);				
			}
			g2d.drawImage(myimg.getImage(), 15, 15, this);
			log.append(img + " открыт! \n");
			log.append("--------------------\n");
			//log.setForeground(Color.BLACK);
		}

	}

}
