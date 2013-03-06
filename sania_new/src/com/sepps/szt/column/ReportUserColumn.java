package com.sepps.szt.column;



import java.sql.*;


import com.sepps.szt.table.DatabaseTableColumn;

public class ReportUserColumn {

	private static ReportUserColumn _mgc = null;
	private DatabaseTableColumn[] columns = null;

	public ReportUserColumn() {

		columns = new DatabaseTableColumn[5];

		try {

			Class StringClass = Class.forName("java.lang.String");

			columns[0] = new DatabaseTableColumn("����� ������������", "login", StringClass,
					15, Types.VARCHAR);

			columns[1] = new DatabaseTableColumn("���",
					"fio", StringClass, 20, Types.VARCHAR);
			columns[2] = new DatabaseTableColumn("������ ����������", "managergroup",
					StringClass, 30, Types.VARCHAR);
			columns[3] = new DatabaseTableColumn("�������", "tlf",
					StringClass, 20, Types.VARCHAR);

			columns[4] = new DatabaseTableColumn("�������������",
					"groupp", StringClass, 50, Types.VARCHAR);
			
		} catch (ClassNotFoundException exc) {
			exc.printStackTrace();
		}
	}

	public DatabaseTableColumn[] getColumns() {
		return columns;
	}

	public String getKeyField() {
		return "groupp";
	}

	public static synchronized ReportUserColumn getInstance() {
		if (_mgc == null) {
			_mgc = new ReportUserColumn();

		}
		return _mgc;
	}

}

