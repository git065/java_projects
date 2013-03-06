package com.sepps.szt.jdbc;

import javax.sql.DataSource;
import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.text.*;
import java.util.*;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;
import com.sepps.szt.utils.Configuration;

//import com.adslur.szt.domen.OperFio;

//import com.adslur.szt.utils.Messager;

public class MvbOracleConnection {
	private static MvbOracleConnection _mvb = null;
	private final static Logger logger = Logger
			.getLogger(MvbOracleConnection.class);
	private final static String FILENAME_LOGGER = "LoginDialog.Logger";
	Connection con = null;

	protected boolean driverLoaded = false;
	String username;
	String password;
	static String url = "jdbc:oracle:thin:@192.168.212.110:1521:OSOL7";
	volatile int countRequest = 0;

	static {

		PropertyConfigurator.configure(Configuration.instance()
				.getFile(FILENAME_LOGGER).getAbsolutePath());

	}

	public synchronized int getCountRequest() {
		return this.countRequest;

	}

	public synchronized void setCountRequest(int countRequest) {
		this.countRequest = countRequest;

	}

	public synchronized void addCountRequest(int countRequest) {
		this.countRequest = this.countRequest + countRequest;

	}

	public synchronized void incCountRequest(int countRequest) {
		this.countRequest = this.countRequest - countRequest;

	}

	public String getUserName() {
		return this.username;

	}

	public void setUserName(String username) {
		this.username = username;

	}

	public String getPassword() {
		return this.password;

	}

	public void setPassword(String password) {
		this.password = password;

	}

	public static synchronized MvbOracleConnection getInstance() {
		if (_mvb == null) {
			_mvb = new MvbOracleConnection();

		}
		return _mvb;
	}

	public boolean connect(String username, String password) {
		try {

			if (!driverLoaded) {
				DriverManager
						.registerDriver(new oracle.jdbc.driver.OracleDriver());
				driverLoaded = true;
			}

			con = DriverManager.getConnection(url, username, password);

			close();

			return true;
		} catch (SQLException ex) {
			return false;
		}
	}

	public Connection connect(String url, String username, String password) {
		Connection connection;
		try {

			if (!driverLoaded) {
				DriverManager
						.registerDriver(new oracle.jdbc.driver.OracleDriver());
				driverLoaded = true;
			}
			// System.out.println("TRACE  connect  <--- > :" + url +":" +
			// username +":" + password);
			connection = DriverManager.getConnection(url, username, password);

			connection.setAutoCommit(false);

			return connection;
		} catch (SQLException ex) {
			return null;
		}
	}

	public Connection getConnection() {
		// System.out.println("TRACE  getConnection1  <--- > :");
		if (con == null) {
			con = connect(url, getUserName(), getPassword());

			// System.out.println("TRACE  getConnection  <--- > :"+con );
		}
		return con;
	}

	public boolean isDriverLoaded() {
		return driverLoaded;
	}

	public void close() {
		try {
			if (con != null) {
				// System.out.println("TRACE  CLOSE   <--- > :");
				con.close();
				con = null;

			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("TRACE  CLOSE   EXEPT <--- > :" + e.toString());
		}
	}

}
