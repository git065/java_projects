package com.sepps.szt.domain;

import java.sql.*;
import java.util.ArrayList;

import com.sepps.szt.jdbc.MvbOracleConnection;
import com.sepps.szt.table.DatabaseTableColumn;

import com.sepps.szt.column.SearchUserColumn;

public class User {
	
                private MvbOracleConnection mvb = MvbOracleConnection.getInstance();
	private SearchUserColumn mgc = SearchUserColumn.getInstance();
	private DatabaseTableColumn[] columns = mgc.getColumns();



	public ArrayList findAll(String login) throws Exception {

		String sql = " 	select sm.x1126_1_intval zone_obsl, "
				+ " substr(sm.x1052_2_date, 7, 2) || '.' || "
				+ " substr(sm.x1052_2_date, 5, 2) || '.' || "
				+ " substr(sm.x1052_2_date, 1, 4) data_create_zone, "
				+ " sm.x1126_0_mychars  zone_otv , t.user_name user_name, t.fam_ima fam_ima, "
				+ " t.group_name group_name,  dm.x1119_0_mychars domen_nain  "
				+ " from (SELECT mg.id  mg_g_id, u.x1115_0_mychars  user_name, "
				+ " u.x1115_12_mychars fam_ima, mg.id group_id,mg.x1154_1_mychars group_name "
				+ "  FROM ManagerX mx, ManagerGroup mg, IrUser u, IrUserVers uv "
				+ "  WHERE u.id = uv.x1077_43_respondmo  AND uv.classid = mx.x1131_2_cmuser  "
				+ "  AND uv.id = mx.x1131_3_cmuser AND mg.classid = mx.x1131_0_nagergroup  "
				+ "  AND mg.id = mx.x1131_1_nagergroup  and u.x1115_0_mychars = "
				+ "'"
				+ login
				+ "'"
				+ ")"
				+ " t ,  DOMAINSTATE ds, domain dm, delegatpool dp, smareamap sm "
				+ "  where ds.X1133_1_TIVEDOMAIN = dm.id   and ds.X1133_3_VEMGRGROUP = t.mg_g_id  "
				+ " and dp.X1120_8_TIVEMEMBER = ds.id  and sm.x1126_2_enumval = 1 "
				+ " and (sm.x1126_1_intval = dm.x1119_5_intval or  sm.x1126_1_intval = dm.x1119_8_intval or "
				+ " sm.x1126_1_intval = dm.x1119_11_intval or  sm.x1126_1_intval = dm.x1119_14_intval or  "
				+ " sm.x1126_1_intval = dm.x1119_17_intval or  sm.x1126_1_intval = dm.x1119_20_intval or  "
				+ " sm.x1126_1_intval = dm.x1119_23_intval or  sm.x1126_1_intval = dm.x1119_26_intval or  "
				+ "  sm.x1126_1_intval = dm.x1119_29_intval or  sm.x1126_1_intval = dm.x1119_32_intval)  "
				+ "  order by dm.x1119_0_mychars ";
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


	public ArrayList findAll_n(String login) throws Exception {

		String sql = " 	select sm.x1126_1_intval zone_obsl, "
				+ " substr(sm.x1052_2_date, 7, 2) || '.' || "
				+ " substr(sm.x1052_2_date, 5, 2) || '.' || "
				+ " substr(sm.x1052_2_date, 1, 4) data_create_zone, "
				+ " sm.x1126_0_mychars  zone_otv , t.user_name user_name, t.fam_ima fam_ima, "
				+ " t.group_name group_name,  dm.x1119_0_mychars domen_nain  "
				+ " from delegatpool dp,   domain dm, DOMAINSTATE ds,   smareamap sm, "
				+ "  (SELECT mg.id  mg_g_id, u.x1115_0_mychars  user_name, "
				+ " u.x1115_12_mychars fam_ima, mg.id group_id,mg.x1154_1_mychars group_name "
				+ "  FROM ManagerX mx, ManagerGroup mg, IrUser u, IrUserVers uv "
				+ "  WHERE u.id = uv.x1077_43_respondmo  AND uv.classid = mx.x1131_2_cmuser  "
				+ "  AND uv.id = mx.x1131_3_cmuser AND mg.classid = mx.x1131_0_nagergroup  "
				+ "  AND mg.id = mx.x1131_1_nagergroup  and u.x1115_0_mychars = "
				+ "'"
				+ login
				+ "'"
				+ ")"
				+ " t   "
				+ "  where ds.X1133_1_TIVEDOMAIN = dm.id  "
				+ " and dp.X1120_8_TIVEMEMBER = ds.id "
				+ " and t. mg_g_id = ds.X1133_3_VEMGRGROUP "
				+ " and sm.x1126_1_intval = dm.x1119_5_intval";
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
