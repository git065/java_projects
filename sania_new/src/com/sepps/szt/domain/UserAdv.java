package com.sepps.szt.domain;


import java.sql.*;
import java.util.ArrayList;


import com.sepps.szt.column.ReportUserColumn;
import com.sepps.szt.jdbc.MvbOracleConnection;
import com.sepps.szt.table.DatabaseTableColumn;

public class UserAdv {
	private MvbOracleConnection mvb = MvbOracleConnection.getInstance();
	private ReportUserColumn mgc = ReportUserColumn.getInstance();
	private DatabaseTableColumn[] columns = mgc.getColumns();

	public ArrayList findAll() throws Exception {

		String sql = " 	select distinct IRUser.X1115_0_mychars login, "
				+ " IRUser.X1115_12_mychars fio, "
				+ " ManagerGroup.X1154_1_mychars managergroup, "
				+ " IRUser.X1115_5_mychars tlf, "
				+ " IRUser.X1115_13_mychars groupp "
				+ " from MANAGERX, IRUSER, MANAGERGROUP, IRUSERVERS  "
				+ " where IRUSER.id = iruservers.x1163_1_cmmo  "
				+ " AND iruservers.id = managerx.X1131_3_cmUser(+)  "
				+ "  AND managerx.X1131_1_nagerGroup = managergroup.id(+) "	;

		ResultSet result = null;
		Statement s = null;
		try {

			Connection connection = mvb.getConnection();

			s = connection.createStatement();
			result = s.executeQuery(sql);

		} catch (SQLException exc) {
			exc.printStackTrace();
			throw new Exception("error sql :report_user ");
		}

		ArrayList data = new ArrayList();

		while (result.next()) {

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
	
}
