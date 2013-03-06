package com.sepps.szt.renderers;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.*;
import java.text.*;

public class demandTableRenderer extends DefaultTableCellRenderer {

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		String END_DATE = "2999-12-31 00:00:00.0";
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd.MM.yyyy  HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd.MM.yyyy ");

		String dateStringLong = sdf1.format((Date) value);
		String dateStringHort = sdf2.format((Date) value);
		String TEMP;
		Date d;

		if (value instanceof Date) {
			d = (Date) value;
			TEMP = d.toString();
			// System.out.println("TRACE-TEMP:|"+TEMP+"|");
			if (TEMP.equals(END_DATE)) {

				setValue(dateStringHort);

			} else {
				setValue(dateStringLong);
			}
		}
		setFont(new Font("Arial", 0, 12));

		return (this);
	}

}