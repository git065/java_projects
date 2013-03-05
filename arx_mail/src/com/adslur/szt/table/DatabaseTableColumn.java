package com.adslur.szt.table;

public class DatabaseTableColumn {
	private String name;
	private String field;
	private Class type;
	private int width;
	private int sqlType;

	public DatabaseTableColumn(String name, String field, Class type,
			int width, int sqlType) {
		this.name = name;
		this.field = field;
		this.type = type;
		this.width = width;
		this.sqlType = sqlType;
	}

	public String getName() {
		return name;
	}

	public String getField() {
		return field;
	}

	public Class getType() {
		return type;
	}

	public int getWidth() {
		return width;
	}

	public int getSqlType() {
		return sqlType;
	}
}