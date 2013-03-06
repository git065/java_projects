package com.sepps.szt.utils;

import java.awt.Component;

import javax.swing.JOptionPane;

public final class Messager {

	private Messager() {

	}

	public static void exception(Component component, String msg) {
		JOptionPane.showConfirmDialog(component, wrapMessage(msg), "Ошибка",
				JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
	}

	public static String wrapMessage(String message) {

		final int LINE_LENGTH = 100;
		final int MAX_LINES = 15;
		int lineCount = 0;
		StringBuffer tempBuffer = new StringBuffer();
		int beginIndex = 0;
		int endIndex = 0;
		boolean messageTruncated = false;

		if (message == null) {
			return "<null>";
		}

		String[] lines = message.split("\n");

		for (int i = 0; i < lines.length; i++) {
			if (lineCount++ > MAX_LINES) {
				messageTruncated = true;
				break;
			}

			if (lines[i].length() > LINE_LENGTH) {
				// Split line in shorter parts
				beginIndex = 0;

				while (true) {
					if (lineCount++ > MAX_LINES) {
						messageTruncated = true;
						break;
					}

					endIndex = beginIndex + LINE_LENGTH;

					if (endIndex > lines[i].length()) {
						tempBuffer.append(lines[i].substring(beginIndex,
								lines[i].length()));
						tempBuffer.append("\n");
						break;
					}

					// Get last blank
					char[] chars = lines[i].substring(beginIndex, endIndex)
							.toCharArray();

					for (int k = (chars.length - 1); k >= 0; k--) {
						if (chars[k] == ' ') {
							endIndex = k;
							break;
						}
					}

					tempBuffer.append(lines[i].substring(beginIndex, endIndex)
							+ "\n");
					beginIndex = endIndex + 1;
				}
			} else {
				tempBuffer.append(lines[i] + "\n");
			}
		}

		if (messageTruncated) {
			tempBuffer.append("\n");
			tempBuffer.append("\n=== Message truncated ===");
			tempBuffer.append("\nCheck application log for full message.");
		}

		return tempBuffer.toString();
	}

	public static void information(Component component, String msg) {

		JOptionPane.showConfirmDialog(component, wrapMessage(msg),
				"Информационное сообщение", JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static void warning(Component component, String msg) {

		JOptionPane.showConfirmDialog(component, wrapMessage(msg),
				"Обратите внимание", JOptionPane.DEFAULT_OPTION,
				JOptionPane.WARNING_MESSAGE);

	}

	public static int question(Component component, String msg) {

		return JOptionPane.showConfirmDialog(component, wrapMessage(msg),
				"Вопрос", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
	}
}