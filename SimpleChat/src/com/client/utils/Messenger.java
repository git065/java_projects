package com.client.utils;

import java.io.*;
import java.net.*;

public class Messenger {

	Socket chatSocket;
	BufferedReader reader;
	BufferedWriter writer;
	
	public Messenger(String host, int port) {
		// TODO Auto-generated constructor stub
		try {
			InetAddress ip = InetAddress.getByName(host);
			this.chatSocket = new Socket(ip, port);
			InputStreamReader streamReader = new InputStreamReader(chatSocket.getInputStream());
			OutputStreamWriter streamWriter = new OutputStreamWriter(chatSocket.getOutputStream());
			this.reader = new BufferedReader(streamReader);
			this.writer = new BufferedWriter(streamWriter);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String readMessage() {
		String message = "";
		try {
			message = this.reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
	
	public void sendMessage(String message) {
		try {
			this.writer.write(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Messenger messenger = new Messenger("127.0.0.1", 5000);		
	}
}
