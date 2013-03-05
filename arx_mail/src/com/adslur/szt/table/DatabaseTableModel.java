package com.adslur.szt.table;

import javax.swing.table.*;
import java.util.ArrayList;

public class DatabaseTableModel extends AbstractTableModel {
	protected DatabaseTableColumn[] columns;
	protected int keyCol;
	protected ArrayList data;
	protected boolean modifiable;

	public DatabaseTableModel(ArrayList data, DatabaseTableColumn[] columns,
			String keyField, boolean modifiable) {
		this.data = data;
		this.columns = columns;
		keyCol = 0;
		for (int i = 0; i < columns.length; i++) {

			if (keyField.equals(columns[i].getField())) {
				keyCol = i;

				break;
			}

		}

		this.modifiable = modifiable;
	}

	public int getColumnCount() {
		return columns.length;
	}

	public String getColumnName(int col) {

		return columns[col].getName();
	}

	public Class getColumnClass(int col) {
		return columns[col].getType();
	}

	public boolean isCellEditable(int row, int col) {

		if (col == keyCol)
			return false;
		else
			return modifiable;
	}

	public Object getValueAt(int row, int col) {
		Object[] rowData = (Object[]) data.get(row);

		return rowData[col];
	}

	public void setValueAt(Object value, int row, int col) {
		Object[] rowData = (Object[]) data.get(row);
		rowData[col] = value;
		data.set(row, rowData);
		fireTableCellUpdated(row, col);
	}

	public boolean isModifiable() {
		return modifiable;
	}

	public int getColumnWidth(int col) {
		return columns[col].getWidth();
	}

	public int getRowCount() {
		return data.size();
	}

	public String getFieldName(int col) {
		return columns[col].getField();
	}

	public int getKeyFieldColumn() {
		return keyCol;
	}

	public int getKeyIdRow(Object keyId) {
		for (int i = 0; i < data.size(); i++) {
			Object[] rowData = (Object[]) data.get(i);
			if (rowData[keyCol].equals(keyId))
				return i;
		}
		return -1;
	}

	public Object getKeyIdAt(int row) {
		Object[] rowData = (Object[]) data.get(row);
		return rowData[keyCol];
	}

	public void addRow(Object[] rowData) {
		data.add(rowData);
		fireTableRowsInserted(data.size(), data.size());
	}

	public void modifyRow(int row, Object[] rowData) {
		data.set(row, rowData);
		fireTableRowsUpdated(row, row);
	}

	public void deleteRow(int row) {
		data.remove(row);
		fireTableRowsDeleted(row, row);
	}
}
