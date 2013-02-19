package com.adslur.szt.validation;

import java.text.*;

public class ValidationUtil {
	public static boolean isLong(String num) {
		boolean rv = true;

		try {
			long tmp = Long.parseLong(num);
		} catch (NumberFormatException nfe) {
			rv = false;
		} finally {
			return rv;
		}
	}

	public static boolean isDouble(String num) {
		boolean rv = true;

		try {
			Double.parseDouble(num);
		} catch (NumberFormatException nfe) {
			rv = false;
		} finally {
			return rv;
		}
	}

	public static String formatCurrency(long money) {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		nf.setMaximumFractionDigits(0);

		String rv = nf.format(money);

		return rv;
	}
}
