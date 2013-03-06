package com.sepps.szt.column;



import java.sql.*;


import com.sepps.szt.table.DatabaseTableColumn;

public class SearchUserColumn {

	private static SearchUserColumn _mgc = null;
	private DatabaseTableColumn[] columns = null;

	public SearchUserColumn() {

		columns = new DatabaseTableColumn[6];

		try {

			Class StringClass = Class.forName("java.lang.String");
			Class IntegerClass = Class.forName("java.lang.Integer");
			Class DateClass = Class.forName("java.sql.Timestamp");
			columns[0] = new DatabaseTableColumn("Зона обслуживания",
					"zone_obsl", StringClass, 20, Types.VARCHAR);
			columns[1] = new DatabaseTableColumn("Зона ответсвтвенности ",
					"zone_otv", StringClass, 30, Types.VARCHAR);

			columns[2] = new DatabaseTableColumn("Логин пользователя",
					"user_name", StringClass, 20, Types.VARCHAR);
			columns[3] = new DatabaseTableColumn("ФИО ", "fam_ima",
					StringClass, 30, Types.VARCHAR);
			columns[4] = new DatabaseTableColumn("Группа управления",
					"group_name", StringClass, 20, Types.VARCHAR);
			columns[5] = new DatabaseTableColumn("Домен ", "domen_nain",
					StringClass, 40, Types.VARCHAR);

		} catch (ClassNotFoundException exc) {
			exc.printStackTrace();
		}
	}

	public DatabaseTableColumn[] getColumns() {
		return columns;
	}

	public String getKeyField() {
		return "user_name";
	}

	public static synchronized SearchUserColumn getInstance() {
		if (_mgc == null) {
			_mgc = new SearchUserColumn();

		}
		return _mgc;
	}

}

