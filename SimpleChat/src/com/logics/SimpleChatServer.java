package com.logics;

import java.io.*;
import java.net.*;

public class SimpleChatServer {

	ServerSocket serverSocket;

	public SimpleChatServer(int port) {
		// TODO Auto-generated constructor stub
		try {
			this.serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public void run() {
		System.out.println("Сервер запущен \n");
		String status = "Соединение установлено!";
		try {
			while (true) {
				Socket listener = this.serverSocket.accept();
				PrintWriter writer = new PrintWriter(listener.getOutputStream());
				writer.println(status);
				System.out.println(status + "\nIp: " + listener.getInetAddress().getHostAddress());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Невозможно создать соединение");
			//e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleChatServer server = new SimpleChatServer(5000);
		server.run();
	}

}
