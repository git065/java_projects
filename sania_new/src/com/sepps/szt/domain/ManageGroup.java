package com.sepps.szt.domain;

import java.sql.*;
import java.util.ArrayList;

import com.sepps.szt.column.ManagerGroupColumn;
import com.sepps.szt.jdbc.MvbOracleConnection;
import com.sepps.szt.table.DatabaseTableColumn;

public class ManageGroup {
	private MvbOracleConnection mvb = MvbOracleConnection.getInstance();
	private ManagerGroupColumn mgc = ManagerGroupColumn.getInstance();
	private DatabaseTableColumn[] columns = mgc.getColumns();

	public ArrayList findAll(String ManageGroup) throws Exception {

		String sql = "  select 	TO_NUMBER(substr(dp.x1120_6_mychars,instr(dp.x1120_6_mychars,'-')+1))  ind, "
				+ " dp.X1120_6_MYCHARS delegatpool , "
				+ " D.X1119_0_MYCHARS domain_name ,"
				+ " d.x1119_5_intval zona , "
				+ " MG.X1154_1_MYCHARS managergroup , "
				+ " substr(dp.x1052_2_date, 7, 2) || '.' ||  "
				+ " substr(dp.x1052_2_date, 5, 2) || '.' ||  "
				+ " substr(dp.x1052_2_date, 1, 4) datacreate  "
				+ "  FROM DOMAINSTATE ds, DOMAIN d, managergroup mg, delegatpool dp "
				+ "  where DS.X1133_1_TIVEDOMAIN = d.id  "
				+ "  and DS.X1133_3_VEMGRGROUP = mg.id  "
				+ "  and dp.X1120_8_TIVEMEMBER = ds.id  "
				+ "  and MG.X1154_1_MYCHARS = "
				+ "'"
				+ ManageGroup
				+ "'"
				+ "  order by ind ";

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
