package com.adslur.szt.domen;

import java.sql.*;
import java.util.ArrayList;
import java.text.*;

import com.adslur.szt.jdbc.MvbOracleConnection;

public class PropertyIbsData {
	private MvbOracleConnection mvb = MvbOracleConnection.getInstance();

	public String findUVO(String tlf) {
		ResultSet rslt = null;
		Statement stmt = null;
		String flag = null;
		String sql = "SELECT  INQDB.bis_app_stu.get_uvo_nt ( " + tlf + ","
				+ "sysdate) " + "FROM DUAL ";
		try {
			Connection conn = mvb.getConnection();
			stmt = conn.createStatement();
			rslt = stmt.executeQuery(sql);
			while (rslt.next()) {

				flag = rslt.getString(1);
				System.out.print("ANALIZE   findUVO  FLAG :" + flag + "\n");
			}

			if (rslt != null)
				rslt.close();
			if (stmt != null)
				stmt.close();

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return flag;
	}

	public String findSPAR(String tlf) {
		ResultSet rslt = null;
		Statement stmt = null;
		String flag = null;
		String sql = "SELECT   INQDB.bis_app_stu.get_spar_nt ( " + tlf + ","
				+ "sysdate) " + "FROM DUAL ";
		try {
			Connection conn = mvb.getConnection();
			stmt = conn.createStatement();
			rslt = stmt.executeQuery(sql);
			while (rslt.next()) {

				flag = rslt.getString(1);
				// System.out.print("ANALIZE  findSPAR  FLAG :" + flag + "\n");

			}

			if (rslt != null)
				rslt.close();
			if (stmt != null)
				stmt.close();

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return flag;
	}

}