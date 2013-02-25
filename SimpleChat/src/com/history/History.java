package com.history;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class History {

	private String text = "";
	private File file;

	private BufferedWriter writer;
	private BufferedReader reader;

	public History() {
		readFileHistory();
	}

	public History(File file) {
		this.file = file;
		readFileHistory();
	}
	
	public History(String fileName) {
		this.file = new File(fileName);
		readFileHistory();
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public void BufferWriteFlush() throws IOException {
		this.writer.flush();
	}
	
	public void addTextinBufferWrite() {
		try {
			this.writer.write(this.text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String readFileHistory() {
		StringBuffer lines = new StringBuffer();
		try {
			if (!this.file.exists()) {
				this.file.createNewFile();
			}
			this.writer = new BufferedWriter(new FileWriter(this.file, true));
			this.reader = new BufferedReader(new FileReader(this.file));

			while ((this.text = this.reader.readLine()) != null) {
				lines.append(text);
				lines.append("\n");
			}

			this.text = lines.toString();

		} catch (IOException e) {
			// e.printStackTrace();
			this.text = "";
		}
		return this.text;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
