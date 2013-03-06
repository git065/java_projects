package com.sepps.szt.utils;



import java.util.*;
import java.io.*;

public class Configuration extends Properties {
	private static Configuration config;

	private final static String PROPERTIES_DIRECTORY = "SepsUtils";
	private final static String FILENAME_PROPERTIES = "SepsUtils.Properties";
	private final static String PROP_SYSTEM_USERHOMEDIR = "user.home";

	private static String userHomeDirectory;

	private Configuration() {
		userHomeDirectory = System.getProperty(PROP_SYSTEM_USERHOMEDIR);
		System.out.println("userHomeDirectory1 :" + userHomeDirectory);
		if (userHomeDirectory == null) {
			userHomeDirectory = PROPERTIES_DIRECTORY;
			System.out.println("userHomeDirectory2 :" + userHomeDirectory);
		} else {
			userHomeDirectory += "/" + PROPERTIES_DIRECTORY;
			new File(userHomeDirectory).mkdirs();
			System.out.println("userHomeDirectory3 :" + userHomeDirectory);
		}
	}

	public static synchronized Configuration instance() {
		if (config == null) {
			config = new Configuration();
		}

		config.setupProperties();

		return config;
	}

	public void store() {
		File configFile;

		configFile = getFile(FILENAME_PROPERTIES);

		try {
			store(new FileOutputStream(configFile, false),
					"BasicQuery Configuration");
		} catch (Throwable any) {

		}
	}

	private void setupProperties() {
		File propFile;

		propFile = getFile(FILENAME_PROPERTIES);

		try {
			config.load(new FileInputStream(propFile));
		} catch (Throwable any) {

		}
	}

	public File getFile(String fileName) {
		File configFile;

		configFile = new File(getFilePath(fileName));
		if (!configFile.exists()) {
			userDefaultFile(fileName);
		}

		return configFile;
	}

	private String getFilePath(String fileName) {
		return userHomeDirectory + "/" + fileName;
	}

	private void userDefaultFile(String fileName) {
		File realFile, defaultFile;

		realFile = new File(userHomeDirectory + "/" + fileName);
		if (!realFile.exists()) {
			defaultFile = new File(fileName + ".txt");
			if (defaultFile.exists()) {
				copyFile(defaultFile, realFile);
			}
		}
	}

	private static void copyFile(File source, File dest) {
		BufferedReader in;
		PrintWriter out;
		String data;

		in = null;
		out = null;

		try {
			in = new BufferedReader(new FileReader(source));
			out = new PrintWriter(new FileWriter(dest));

			while ((data = in.readLine()) != null) {
				out.println(data);
			}
		} catch (Throwable any) {

		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Throwable any) {

				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (Throwable any) {

				}
			}
		}
	}
}
