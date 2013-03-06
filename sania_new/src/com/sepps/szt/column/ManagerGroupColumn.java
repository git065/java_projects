package com.sepps.szt.column;

import java.sql.*;


import com.sepps.szt.table.DatabaseTableColumn;

public class ManagerGroupColumn {

	private static ManagerGroupColumn _mgc = null;
	private DatabaseTableColumn[] columns = null;

	public ManagerGroupColumn() {

		columns = new DatabaseTableColumn[6];

		try {

			Class StringClass = Class.forName("java.lang.String");

			columns[0] = new DatabaseTableColumn("�����", "ind", StringClass,
					2, Types.VARCHAR);

			columns[1] = new DatabaseTableColumn("��� �������������",
					"delegatpool", StringClass, 20, Types.VARCHAR);
			columns[2] = new DatabaseTableColumn("����� ", "domain_name",
					StringClass, 30, Types.VARCHAR);
			columns[3] = new DatabaseTableColumn("���� ������������", "zona",
					StringClass, 20, Types.VARCHAR);

			columns[4] = new DatabaseTableColumn("������ ����������",
					"managergroup", StringClass, 20, Types.VARCHAR);
			columns[5] = new DatabaseTableColumn("���� �������� ���� ",
					"datacreate", StringClass, 40, Types.VARCHAR);

		} catch (ClassNotFoundException exc) {
			exc.printStackTrace();
		}
	}

	public DatabaseTableColumn[] getColumns() {
		return columns;
	}

	public String getKeyField() {
		return "ind";
	}

	public static synchronized ManagerGroupColumn getInstance() {
		if (_mgc == null) {
			_mgc = new ManagerGroupColumn();

		}
		return _mgc;
	}

}
