package com.gui;

import java.net.URL;
import javax.swing.*;
import javax.swing.text.Caret;
import javax.swing.text.html.ListView;

import java.awt.*;
import java.awt.event.*;

public class DialogWindow {

	private JFrame frame;
	private JButton ButtonSend;
	private JTextArea TextInputField;
	private JTextArea TextChatHistory;

	public DialogWindow() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		DialogWindow diaTextInputField = new DialogWindow();
		diaTextInputField.go();

	}

	public void go() {
		Font font = new Font("Verdana", Font.PLAIN, 11);
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel LaberUsrName = new JLabel("Naladar");

		JPanel dialog_panel = new JPanel();
		JPanel chat_panel = new JPanel();
		JPanel info_panel = new JPanel();

		info_panel.add(LaberUsrName);

		TextChatHistory = new JTextArea(20, 55);
		TextChatHistory.setCaretColor(Color.GREEN);
		TextChatHistory.setSelectionColor(Color.GRAY);
		TextChatHistory.setSelectedTextColor(Color.WHITE);
		TextChatHistory.setLineWrap(true);

		JScrollPane scroller_history = new JScrollPane(TextChatHistory);
		scroller_history
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller_history
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		dialog_panel.add(scroller_history);

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
		chat_panel.add(scroller_input_field);

		ButtonSend = new JButton();
		ButtonSend.addActionListener(new ButtonListenerGo());
		ImageIcon icon = createIcon("images/send.png");
		ButtonSend.setIcon(icon);
		chat_panel.add(ButtonSend);

		MyDrawPanel drawPanel = new MyDrawPanel();

		frame.getContentPane().add(BorderLayout.CENTER, dialog_panel);
		frame.getContentPane().add(BorderLayout.SOUTH, chat_panel);
		frame.getContentPane().add(BorderLayout.NORTH, info_panel);

		// chat_panel.setSize(chat_panel.getWidth(),chat_panel.getHeight());
		// ???????????????
		frame.setSize(700, 500);
		frame.setVisible(true);
	}

	class ButtonListenerGo implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (!TextInputField.getText().isEmpty()) {
				TextChatHistory.append(TextInputField.getText() + "\n");
			}
			TextInputField.setText("");
			//TextChatHistory.append("Кнопка 'Отправить' нажата!\n");
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

	class MyDrawPanel extends JPanel {

		public void paintComponent(Graphics g) {

		}

	}

}
