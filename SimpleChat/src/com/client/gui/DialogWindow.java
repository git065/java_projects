package com.client.gui;

import java.io.*;
import java.net.URL;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import com.client.utils.*;

public class DialogWindow {

	private JFrame frame;
	private JButton ButtonSend;
	private JTextArea TextInputField;
	private JTextArea TextChatHistory;
	
	private History history;
	
	public DialogWindow() {
		
	}	
	public DialogWindow(History history) {
		this.history = history;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		History history = new History("History.sav");
		DialogWindow dialog = new DialogWindow(history);		
		dialog.go();

	}

	public History getHistory() {
		return history;
	}

	public void setHistory(History history) {
		this.history = history;
	}

	public void go() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem openMenuItem = new JMenuItem("Open");
		openMenuItem.addActionListener(new OpenMenuItemListener());
		
		Font font = new Font("Arial", Font.PLAIN, 12);
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
		TextChatHistory.setText(history.getText());
		TextChatHistory.setFont(font);

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
		fileMenu.add(openMenuItem);
		menuBar.add(fileMenu);

		frame.getContentPane().add(BorderLayout.CENTER, dialog_panel);
		frame.getContentPane().add(BorderLayout.SOUTH, chat_panel);
		frame.getContentPane().add(BorderLayout.NORTH, info_panel);

		// chat_panel.setSize(chat_panel.getWidth(),chat_panel.getHeight());
		// ???????????????
		WindowListener adapter = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	try {
            		history.BufferWriteFlush();
            		history.CloseIOStreams();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
            @Override
            public void windowClosed(WindowEvent e) {
            	try {
            		history.BufferWriteFlush();
            		history.CloseIOStreams();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        };
		
        frame.setJMenuBar(menuBar);
		frame.setSize(700, 500);
		frame.setVisible(true);
		frame.addWindowListener(adapter);
	}

	class ButtonListenerGo implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (!TextInputField.getText().isEmpty()) {
				TextChatHistory.append(TextInputField.getText() + "\n");
				history.setText(TextInputField.getText() +"\n");
				history.addTextinBufferWrite();
				//writer.flush();
			}
			TextInputField.setText("");
		}
	}
	
	class OpenMenuItemListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			JFileChooser fileopen = new JFileChooser();
			int ret = fileopen.showDialog(null, "Открыть файл");               
			
			if (ret == JFileChooser.APPROVE_OPTION) {
				history.setFile(fileopen.getSelectedFile());
				history.readFileHistory();
				TextChatHistory.setText(history.getText());
			}

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
