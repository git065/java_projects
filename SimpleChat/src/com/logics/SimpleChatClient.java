package com.logics;
import com.client.gui.*;
import com.client.utils.*;

public class SimpleChatClient {
	Messenger messenger;
	DialogWindow dialog_gui;
	History history;

	public SimpleChatClient() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleChatClient client = new SimpleChatClient();
		client.messenger = new Messenger("10.0.0.63", 8080);
		client.history = new History("History.sav");
		client.dialog_gui = new DialogWindow(client.history);
		client.dialog_gui.go();
	}

}
