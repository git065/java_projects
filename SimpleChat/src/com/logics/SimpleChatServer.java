package com.logics;

import java.io.*;
import java.net.*;
import java.util.*;

public class SimpleChatServer {

	private static SimpleChatServer instance;
	private ArrayList clientOutputStreams;

	private SimpleChatServer() {
	}

	public static SimpleChatServer getInstance() {
		if (instance == null) {
			instance = new SimpleChatServer();
		} else {
			System.out.println("������ ��� ����������!");
		}
		return instance;
	}

	public class ClientHandler implements Runnable {
		BufferedReader reader;
		Socket sock;

		public ClientHandler(Socket clientSOcket) {
			try {
				sock = clientSOcket;
				InputStreamReader isReader = new InputStreamReader(
						sock.getInputStream());
				reader = new BufferedReader(isReader);

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		public void run() {
			String message;
			try {
				while ((message = reader.readLine()) != null) {
					System.out.println("read " + message);
					tellEveryone(message);
				}
			} catch (IOException e) {
				System.out.println("���������� �������� ��� ������ ������!");
			} catch (Exception ex) {
				// ex.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {
		SimpleChatServer.getInstance().go();
	}

	public void go() {		
		clientOutputStreams = new ArrayList();
		try {
			ServerSocket serverSock = new ServerSocket(5000);
			System.out.println("������ �������!");
			while (true) {
				Socket clientSocket = serverSock.accept();
				PrintWriter writer = new PrintWriter(
						clientSocket.getOutputStream());
				clientOutputStreams.add(writer);

				Thread t = new Thread(new ClientHandler(clientSocket));
				t.start();
				System.out.println("�������� ����������!");
			}
		} catch (Exception ex) {
			System.out.println("������ ��� �������!");
			//ex.printStackTrace();
		}
	}

	public void tellEveryone(String message) {
		Iterator it = clientOutputStreams.iterator();
		while (it.hasNext()) {
			try {
				PrintWriter writer = (PrintWriter) it.next();
				writer.println(message);
				writer.flush();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
