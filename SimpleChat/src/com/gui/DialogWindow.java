package com.gui;

import java.io.*;
import java.net.URL;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DialogWindow {

	private JFrame frame;
	private JButton ButtonSend;
	private JTextArea TextInputField;
	private JTextArea TextChatHistory;
	
	private String text = "";
	StringBuffer lines = new StringBuffer();
	private File file;
	
	private BufferedWriter writer;
	private BufferedReader reader;
	
	private ObjectInputStream os2;
	private FileInputStream fileStream2;

	public DialogWindow() {
		
		try {
			file = new File("History.sav");
			if(!file.exists()) {
				file.createNewFile();
			}
			writer = new BufferedWriter(new FileWriter(file, true));
			reader = new BufferedReader(new FileReader(file));
			
			while ( (text = reader.readLine()) != null) {
				lines.append(text);
				lines.append("\n");
			}
			
			text = lines.toString();
			
		} catch (IOException e) {
			//e.printStackTrace();
			text = "";
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		DialogWindow dialog = new DialogWindow();
		dialog.go();

	}

	public void go() {
		Font font = new Font("Verdana", Font.PLAIN, 11);
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel LaberUsrName = new JLabel("Naladar");

		JPanel dialog_panel = new JPanel();
		JPanel chat_panel = new JPanel();
		JPanel info_panel = new JPanel();				

		TextChatHistory = new JTextArea(20, 55);
		TextChatHistory.setCaretColor(Color.GREEN);
		TextChatHistory.setSelectionColor(Color.GRAY);
		TextChatHistory.setSelectedTextColor(Color.WHITE);
		TextChatHistory.setLineWrap(true);
		TextChatHistory.setEditable(false);
		TextChatHistory.setText(text);

		JScrollPane scroller_history = new JScrollPane(TextChatHistory);
		scroller_history
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller_history
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		TextInputField = new JTextArea(4, 45);
		TextInputField.setCaretColor(Color.GREEN);
		TextInputField.setSelectionColor(Color.GRAY);
		TextInputField.setSelectedTextColor(Color.WHITE);
		TextInputField.setLineWrap(true);
		TextInputField.setFocusable(true);

		JScrollPane scroller_input_field = new JScrollPane(TextInputField);
		scroller_input_field
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller_input_field
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);		

		ButtonSend = new JButton();
		ButtonSend.addActionListener(new ButtonListenerGo());
		ImageIcon icon = createIcon("images/send.png");
		ButtonSend.setIcon(icon);
		ButtonSend.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		info_panel.add(LaberUsrName);
		dialog_panel.add(scroller_history);
		chat_panel.add(scroller_input_field);
		chat_panel.add(ButtonSend);

		frame.getContentPane().add(BorderLayout.CENTER, dialog_panel);
		frame.getContentPane().add(BorderLayout.SOUTH, chat_panel);
		frame.getContentPane().add(BorderLayout.NORTH, info_panel);

		// chat_panel.setSize(chat_panel.getWidth(),chat_panel.getHeight());
		// ???????????????
		WindowListener adapter = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	try {
					writer.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
            @Override
            public void windowClosed(WindowEvent e) {
            	try {
					writer.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        };
		
		frame.setSize(700, 500);
		frame.setVisible(true);
		frame.addWindowListener(adapter);
	}

	class ButtonListenerGo implements ActionListener {
		private ObjectOutputStream os;

		public void actionPerformed(ActionEvent event) {
			if (!TextInputField.getText().isEmpty()) {
				TextChatHistory.append(TextInputField.getText() + "\n");
				try {
					text = "\n" + TextInputField.getText();
					writer.write(text);
					//writer.flush();
					
					/*FileOutputStream fileStream = new FileOutputStream(
							"History.sav");
					os = new ObjectOutputStream(fileStream);
					os.writeObject(TextChatHistory.getText());*/
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			TextInputField.setText("");
			// TextChatHistory.append("Кнопка 'Отправить' нажата!\n");
			// System.out.println("Кнопка 'Отправить' нажата!");
		}
	}

	protected static ImageIcon createIcon(String path) {
		URL imgURL = DialogWindow.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Файл не найден " + path);
			return null;
		}
	}
}
