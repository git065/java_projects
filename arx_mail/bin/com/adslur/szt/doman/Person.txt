package com.adslur.szt.domen;

import java.sql.*;
import java.util.ArrayList;

import com.adslur.szt.jdbc.MvbOracleConnection;
import com.adslur.szt.table.DatabaseTableColumn;

public class Person {
	private MvbOracleConnection mvb = MvbOracleConnection.getInstance();

	private DatabaseTableColumn[] columns = null;

	public void open() {

		initializeColumns();
	}

	public DatabaseTableColumn[] getColumns() {
		return columns;
	}

	public String getKeyField() {
		return "dmnd";
	}

	public ArrayList findAll(String tlf) throws Exception {

		String sql = "   select   a.dmnd_id   dmnd , a.org_name   org_name ,a.start_date   start_date  ,a.end_date   end_date ,  "
				+ "   b.naim ,a.proezd   proezd "
				+ "   from      inqdb.demands_ur a ,  "
				+ "   inqdb.demand_statuses_ur b  "
				+ "  where    a.dems_dems_id = b.kod  "
				+ "  and   a.msisdn =  " + "'" + tlf + "'";

		ResultSet result = null;
		Statement s = null;
		try {

			Connection connection = mvb.getConnection();

			s = connection.createStatement();
			result = s.executeQuery(sql);

		} catch (SQLException exc) {
			exc.printStackTrace();
			throw new Exception("error sql :demands_dilers ");
		}

		ArrayList data = new ArrayList();

		while (result.next()) {
			// System.out.println("TRACE - WHILE -- START_DATE : -----"
			// +result.getString(3));
			Object[] record = new Object[columns.length];
			for (int i = 0; i < columns.length; i++) {
				String fieldName = columns[i].getField();
				Object content = result.getObject(fieldName);
				record[i] = content;
			}
			data.add(record);
		}

		result.close();

		if (s != null)
			s.close();
		if (result != null)
			result.close();

		return data;
	}

	private void initializeColumns() {
		columns = new DatabaseTableColumn[6];

		try {

			Class StringClass = Class.forName("java.lang.String");
			Class IntegerClass = Class.forName("java.lang.Integer");
			Class DateClass = Class.forName("java.sql.Timestamp");
			columns[0] = new DatabaseTableColumn("Номер заявки", "dmnd",
					IntegerClass, 10, Types.VARCHAR);
			columns[1] = new DatabaseTableColumn("Наименование организации",
					"org_name", StringClass, 100, Types.VARCHAR);
			columns[2] = new DatabaseTableColumn("Начало действия",
					"start_date", DateClass, 100, Types.TIMESTAMP);
			columns[3] = new DatabaseTableColumn("Окончание действия",
					"end_date", DateClass, 100, Types.TIMESTAMP);
			columns[4] = new DatabaseTableColumn("Статус", "naim", StringClass,
					10, Types.VARCHAR);
			columns[5] = new DatabaseTableColumn("Адрес", "proezd",
					StringClass, 100, Types.VARCHAR);

		} catch (ClassNotFoundException exc) {
			exc.printStackTrace();
		}
	}

}