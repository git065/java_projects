package com.adslur.szt.utils;

import java.util.Comparator;
import javax.swing.JInternalFrame;

public final class JInternalFrameComparator implements
		Comparator<JInternalFrame> {

	public int compare(JInternalFrame o1, JInternalFrame o2) {
		int ret = 0;

		if (o1 != null && o2 != null) {
			String t1 = o1.getTitle();
			String t2 = o2.getTitle();

			if (t1 != null && t2 != null) {
				ret = t1.compareTo(t2);
			} else if (t1 == null && t2 != null) {
				ret = -1;
			} else if (t1 != null && t2 == null) {
				ret = 1;
			} else {
				ret = 0;
			}
		}

		return (ret);
	}

}
