package com.sepps.szt.utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.*;
import java.util.*;
import java.util.jar.*;
import java.net.*;
import java.io.*; /*
 import javax.swing.DefaultComboBoxModel;
 import javax.swing.JComboBox;
 import javax.swing.JComponent;
 import javax.swing.JFileChooser;
 import javax.swing.JOptionPane;
 import javax.swing.JTree;
 import javax.swing.UIDefaults;
 import javax.swing.UIManager;
 */
import javax.swing.border.LineBorder;
import javax.swing.text.JTextComponent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import javax.swing.*;

/**
 * 
 * @author Administrator
 */
public class Misc {
	public static final String[] special_chars = new String[] { "&", "&amp;",
			"\"", "&quot;", "'", "&apos;", "<", "&lt;", ">", "&gt;" };

	/*
	 * "&","&amp;", "?","&aacute;", "?","&acirc;", "?","&aelig;",
	 * "?","&agrave;", "?","&aring;", "?","&atilde;", "?","&auml;",
	 * "?","&ccedil;", "?","&eacute;", "?","&ecirc;", "?","&egrave;",
	 * "?","&eth;", "?","&euml;", ">","&gt;", "?","&iacute;", "?","&icirc;",
	 * "?","&igrave;", "?","&iuml;", "<","&lt;", "?","&ntilde;", "?","&oacute;",
	 * "?","&ocirc;", "?","&ograve;", "?","&oslash;", "?","&otilde;",
	 * "?","&ouml;", "\"","&quot;", "?","&szlig;", "?","&thorn;",
	 * "?","&uacute;", "?","&ucirc;", "?","&ugrave;", "?","&uuml;",
	 * "?","&yacute;", "?","&yuml;", "?","&#161;", "?","&#170;", "?","&#183;",
	 * "?","&#162;", "?","&#171;", "?","&#184;", "?","&#163;", "?","&#174;",
	 * "?","&#185;", "?","&#164;", "?","&#176;", "?","&#186;", "?","&#165;",
	 * "?","&#177;", "?","&#187;", "?","&#166;", "?","&#178;", "?","&#188;",
	 * "?","&#167;", "?","&#179;", "?","&#189;", "?","&#168;", "?","&#181;",
	 * "?","&#190;", "?","&#169;", "?","&#182;", "?","&#191;", "?","&#172;",
	 * "?","&#215;", "?","&#247;", "?","&#177;", "?","&#183;", "?","&#189;",
	 * "?","&#171;", "?","&#178;", "?","&#185;", "?","&#190;", "?","&#172;",
	 * "?","&#179;", "?","&#187;", "?","&#215;", "?","&#176;", "?","&#181;",
	 * "?","&#188;", "?","&#247;"};
	 */

	/** Creates a new instance of Misc */
	public Misc() {
	}

	public static String xmlEscape(String text) {
		if (text == null)
			return "";
		int i = 0;
		String tmp = "";
		for (i = 0; i < special_chars.length; i += 2) {
			text = string_replace(special_chars[i + 1], special_chars[i], text);
			// text = string_replace(special_chars[i], special_chars[i+1],
			// text);
		}

		return text;
	}

	/*
	 * public static java.awt.Image loadImageFromResources(String filename) {
	 * try { ClassLoader cl = ClassLoader.getSystemClassLoader();
	 * //java.io.InputStream in = new java.io.FileInputStream(
	 * cl.getResource(filename).getPath() ); java.io.InputStream in =
	 * cl.getResourceAsStream(filename); byte[] data =
	 * getBytesFromInputStream(in, in.available()); return
	 * java.awt.Toolkit.getDefaultToolkit().createImage(data); } catch
	 * (Exception ex) {
	 * System.out.println("Exception loading resource: "+filename);
	 * //ex.getMessage(); //ex.printStackTrace(); } return null; }
	 */
	/** New version by Umberto Uderzo */
	public static java.awt.Image loadImageFromResources(String filename) {
		try {
			return new javax.swing.ImageIcon(Misc.class.getResource("/"
					+ filename)).getImage();
		} catch (Exception ex) {
			System.out.println("Exception loading resource: " + filename);
		}
		return null;
	}

	/**
	 * Returns an array of bytes containing the bytecodes for the class
	 * represented by the InputStream
	 * 
	 * @param in
	 *            the inputstream of the class file
	 * @return the bytecodes for the class
	 * @exception java.io.IOException
	 *                if the class cannot be read
	 */
	private static byte[] getBytesFromInputStream(java.io.InputStream in,
			int length) throws java.io.IOException {
		java.io.DataInputStream din = new java.io.DataInputStream(in);
		byte[] bytecodes = new byte[length];
		try {
			din.readFully(bytecodes);
		} finally {
			if (din != null)
				din.close();
		}
		return bytecodes;
	}

	public static java.awt.image.BufferedImage loadBufferedImageFromResources(
			Component c, String filename) {

		try {
			Misc m = new Misc();
			java.awt.Image img = loadImageFromResources(filename);
			MediaTracker mt = new MediaTracker(c);
			mt.addImage(img, 0);
			mt.waitForID(0);
			int width = img.getWidth(null);
			int height = img.getHeight(null);
			BufferedImage bi = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics gg = bi.getGraphics();
			gg.drawImage(img, 0, 0, null);
			gg.dispose();
			return bi;
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return null;
	}

	public static void updateComboBox(javax.swing.JComboBox comboBox,
			Vector newItems) {
		updateComboBox(comboBox, newItems, false);
	}

	public static void updateComboBox(javax.swing.JComboBox comboBox,
			Vector newItems, boolean addNullEntry) {
		Object itemSelected = null;
		if (comboBox.getSelectedIndex() >= 0) {
			itemSelected = comboBox.getSelectedItem();
		}

		// comboBox.removeAllItems();

		java.util.Vector items = new java.util.Vector(newItems.size(), 1);
		boolean selected = false;
		boolean foundNullItem = false;
		Enumeration e = newItems.elements();
		int selectedIndex = -1;
		int currentelement = 0;
		while (e.hasMoreElements()) {
			Object item = e.nextElement();
			items.add(item);
			if (item == itemSelected) {
				selectedIndex = currentelement;
			}
			if (item.equals("")) {
				foundNullItem = true;
			}

			currentelement++;
		}

		if (addNullEntry) {
			if (!foundNullItem)
				items.add(0, "");
			if (selectedIndex < 0)
				selectedIndex = 0;
		}

		comboBox.setModel(new DefaultComboBoxModel(items));
		comboBox.setSelectedIndex(selectedIndex);

	}

	/**
	 * Mthis method perform equals based on string rapresentation of objects
	 * 
	 */
	public static void updateStringComboBox(javax.swing.JComboBox comboBox,
			Vector newItems, boolean addNullEntry) {
		Object itemSelected = null;
		if (comboBox.getSelectedIndex() >= 0) {
			itemSelected = comboBox.getSelectedItem() + "";
		}

		// comboBox.removeAllItems();

		java.util.Vector items = new java.util.Vector(newItems.size(), 1);

		boolean selected = false;
		boolean foundNullItem = false;
		Enumeration e = newItems.elements();
		int selectedIndex = -1;
		int currentelement = 0;

		while (e.hasMoreElements()) {
			String item = "" + e.nextElement();
			items.add(item);
			// comboBox.addItem(item);
			if (item.equals(itemSelected)) {
				selectedIndex = currentelement;
			}
			if (item.equals("")) {
				foundNullItem = true;
			}
			currentelement++;
		}

		if (addNullEntry) {
			if (!foundNullItem)
				items.add(0, "");
			if (selectedIndex < 0)
				selectedIndex = 0;
		}

		comboBox.setModel(new DefaultComboBoxModel(items));
		comboBox.setSelectedIndex(selectedIndex);

	}

	public static String nvl(Object obj, String def) {
		return (obj == null) ? def : obj.toString();
	}

	public static void centerFrame(java.awt.Component c) {
		java.awt.Toolkit tk = java.awt.Toolkit.getDefaultToolkit();
		c.setLocation(
				(int) ((tk.getScreenSize().getWidth() - c.getWidth()) / 2),
				(int) ((tk.getScreenSize().getHeight() - c.getHeight()) / 2));
	}

	/**
	 * Replace s2 with s1 in s3
	 **/
	public static String string_replace(String s1, String s2, String s3) {
		String string = "";
		string = "";

		if (s2 == null || s3 == null || s2.length() == 0)
			return s3;

		int pos_i = 0; // posizione corrente.
		int pos_f = 0; // posizione corrente finale

		int len = s2.length();
		while ((pos_f = s3.indexOf(s2, pos_i)) >= 0) {
			string += s3.substring(pos_i, pos_f) + s1;
			// +string.substring(pos+ s2.length());
			pos_f = pos_i = pos_f + len;

		}

		string += s3.substring(pos_i);
		return string;
	}

	public static java.awt.Image loadImageFromFile(String path) {
		java.io.File file = new java.io.File(path);
		if (file.exists()) {
			java.awt.Toolkit tk = java.awt.Toolkit.getDefaultToolkit();
			java.awt.Image img = tk.createImage(path);
			try {
				java.awt.MediaTracker mt = new java.awt.MediaTracker(
						new javax.swing.JPanel());
				mt.addImage(img, 0);
				mt.waitForID(0);
			} catch (Exception ex) {
				return null;
			}
			return img;
		}
		return null;
	}

	/**
	 * This method inserts a blank character between to consecutive newline
	 * characters if encoutered. Also appends a blank character at the beginning
	 * of the text, if the first character is a newline character and at the end
	 * of the text, if the last character is also a newline. This is useful when
	 * trying to layout the paragraphs. Thanks to Teodor Danciu for this this
	 * method (c) 2003 Teodor Danciu
	 */
	public static String treatNewLineChars(String source) {
		String result = source;

		if (source != null && source.length() > 0) {
			StringBuffer sbuffer = new StringBuffer(source);

			// insert a blank character between every two consecutives
			// newline characters
			int offset = source.length() - 1;
			int pos = source.lastIndexOf("\n\n", offset);
			while (pos >= 0 && offset > 0) {
				sbuffer = sbuffer.insert(pos + 1, " ");
				offset = pos - 1;
				pos = source.lastIndexOf("\n\n", offset);
			}

			// append a blank character at the and of the text
			// if the last character is a newline character
			if (sbuffer.charAt(sbuffer.length() - 1) == '\n') {
				sbuffer.append(' ');
			}

			// append a blank character at the begining of the text
			// if the first character is a newline character
			if (sbuffer.charAt(0) == '\n') {
				sbuffer.insert(0, ' ');
			}

			result = sbuffer.toString();
		}

		// remove this if you want to treat the tab characters in a special way
		result = replaceTabWithBlank(result);

		return result;
	}

	/**
	 * Thanks to Teodor Danciu for this method (c) 2003 Teodor Danciu
	 */
	public static String replaceTabWithBlank(String source) {
		String result = source;

		if (source != null && source.length() > 0) {
			StringBuffer sbuffer = new StringBuffer(source);

			int offset = 0;
			int pos = source.indexOf("\t", offset);
			while (pos >= 0) {
				sbuffer.setCharAt(pos, ' ');
				offset = pos + 1;
				pos = source.indexOf("\t", offset);
			}

			result = sbuffer.toString();
		}

		return result;
	}

	public static String toHTML(String s) {
		s = Misc.string_replace("&gt;", ">", s);
		s = Misc.string_replace("&lt;", "<", s);
		s = Misc.string_replace("&nbsp;", " ", s);
		s = Misc.string_replace("&nbsp;&nbsp;&nbsp;&nbsp;", "\t", s);
		s = Misc.string_replace("<br>", "\n", s);
		return s;
	}

	static public String getShortFileName(String filename) {
		if (filename.length() > 50) {
			java.io.File f = new java.io.File(filename);
			if (nvl(f.getParentFile(), "").length() > 10) {
				String dir = f.getParentFile().getPath()
						+ java.io.File.separatorChar;

				String shortDir = dir.substring(0, dir
						.indexOf(java.io.File.separatorChar) + 1);
				dir = dir
						.substring(dir.indexOf(java.io.File.separatorChar) + 1);
				if (dir.indexOf(java.io.File.separatorChar) > 0) {
					shortDir += dir.substring(0, dir
							.indexOf(java.io.File.separatorChar) + 1);
				}
				return shortDir + "..." + java.io.File.separatorChar
						+ f.getName();
			}
		}

		return filename;

	}

	/**
	 * Thanx to Jackie Manning j.m@programmer.net for this method!!
	 */
	public static String getJdbcTypeClass(java.sql.ResultSetMetaData rsmd, int t) {
		String cls = "java.lang.Object";

		try {
			cls = rsmd.getColumnClassName(t);
			cls = getJRFieldType(cls);

		} catch (Exception ex) {
			// if getColumnClassName is not supported...
			try {
				int type = rsmd.getColumnType(t);
				switch (type) {
				case java.sql.Types.TINYINT:
				case java.sql.Types.BIT:
					cls = "java.lang.Byte";
					break;
				case java.sql.Types.SMALLINT:
					cls = "java.lang.Short";
					break;
				case java.sql.Types.INTEGER:
					cls = "java.lang.Integer";
					break;
				case java.sql.Types.FLOAT:
				case java.sql.Types.REAL:
				case java.sql.Types.DOUBLE:
				case java.sql.Types.NUMERIC:
				case java.sql.Types.DECIMAL:
					cls = "java.lang.Double";
					break;
				case java.sql.Types.CHAR:
				case java.sql.Types.VARCHAR:
					cls = "java.lang.String";
					break;

				case java.sql.Types.BIGINT:
					cls = "java.lang.Long";
					break;
				case java.sql.Types.DATE:
					cls = "java.util.Date";
					break;
				case java.sql.Types.TIME:
					cls = "java.sql.Time";
					break;
				case java.sql.Types.TIMESTAMP:
					cls = "java.sql.Timestamp";
					break;
				}
			} catch (Exception ex2) {
				ex2.printStackTrace();
			}
		}
		return cls;
	}

	/**
	 * Return the correct field type...
	 * 
	 */
	public static String getJRFieldType(String type) {

		if (type == null)
			return "java.lang.Object";

		if (type.equals("java.lang.Boolean") || type.equals("boolean"))
			return "java.lang.Boolean";
		if (type.equals("java.lang.Byte") || type.equals("byte"))
			return "java.lang.Byte";
		if (type.equals("java.lang.Integer") || type.equals("int"))
			return "java.lang.Integer";
		if (type.equals("java.lang.Long") || type.equals("long"))
			return "java.lang.Long";
		if (type.equals("java.lang.Double") || type.equals("double"))
			return "java.lang.Double";
		if (type.equals("java.lang.Float") || type.equals("float"))
			return "java.lang.Float";
		if (type.equals("java.lang.Short") || type.equals("short"))
			return "java.lang.Short";
		if (type.startsWith("["))
			return "java.lang.Object";
		/*
		 * if (type.equals("java.util.Date") ||
		 * type.equals("java.sql.Timestamp") ||
		 * type.equals("java.io.InputStream") ||
		 * type.equals("java.math.BigDecimal") ||
		 * type.equals("java.lang.String") || type.equals("java.sql.Time"))
		 * return type;
		 * 
		 * return "java.lang.Object";
		 */
		return type;
	}

	public static long getLastWriteTime(String filename) {
		try {
			java.io.File f = new java.io.File(filename);
			if (f.exists()) {
				return f.lastModified();
			}
		} catch (Exception ex) {

		}
		return -1;
	}

	/**
	 *Method used to grab the Frame which is above this component in the
	 * hierarchy. This allows programmers to make any component the parent of
	 * any window or dialog easier.
	 * 
	 * @param comp
	 *            the component to get the Frame for
	 *@return the Frame above this component in the hierarchy
	 */
	public static java.awt.Frame frameFromComponent(java.awt.Component parent) {
		java.awt.Frame f = (java.awt.Frame) javax.swing.SwingUtilities
				.getAncestorOfClass(java.awt.Frame.class, parent);
		return f;
	}// end frameFromComponent

	// ErtanO 12.03.2004
	public static java.util.List getAvailablePLAF() {
		java.util.List l = new java.util.ArrayList();
		l.add("System");
		l.add("TinyLAF");
		l.add("TonicLAF");
		l.add("JGoodiesLAF-PlasticXP");
		l.add("JGoodiesLAF-Plastic");
		l.add("JGoodiesLAF-Plastic3D");
		l.add("JGoodiesLAF-ExtWindows");
		l.add("JGoodiesLAF-ExtWindows");
		// l.add("KunststofLAF");

		javax.swing.UIManager.LookAndFeelInfo[] lfinfo = javax.swing.UIManager
				.getInstalledLookAndFeels();

		for (int i = 0; i < lfinfo.length; ++i) {
			l.add(lfinfo[i].getName());
		}

		return l;
	}

	public static String changeFileExtension(String filename,
			String newExtension) {
		if (!newExtension.startsWith("."))
			newExtension = "." + newExtension;
		if (filename == null || filename.length() == 0) {
			return newExtension;
		}

		int index = filename.lastIndexOf(".");
		if (index >= 0) {
			filename = filename.substring(0, index);
		}
		return filename += newExtension;
	}

	/**
	 * Take a string like _it_IT or it_IT or it and return the right locale
	 * Default return value is Locale.getDefault()
	 */
	static public java.util.Locale getLocaleFromString(String localeName) {
		return getLocaleFromString(localeName, Locale.getDefault());
	}

	/**
	 * Take a string like _it_IT or it_IT or it and return the right locale
	 * 
	 */
	static public java.util.Locale getLocaleFromString(String localeName,
			Locale defaultLocale) {
		String language = "";
		String country = "";
		String variant = "";
		Locale locale = defaultLocale;

		if (localeName == null || localeName.length() == 0)
			return locale;
		if (localeName.startsWith("_"))
			localeName = localeName.substring(1);
		if (localeName.indexOf("_") > 0) {
			language = localeName.substring(0, localeName.indexOf("_"));
			localeName = localeName.substring(localeName.indexOf("_") + 1);

			if (localeName.indexOf("_") > 0) {
				country = localeName.substring(0, localeName.indexOf("_"));
				localeName = localeName.substring(localeName.indexOf("_") + 1);

				if (localeName.indexOf("_") > 0) {
					variant = localeName.substring(0, localeName.indexOf("_"));
					localeName = localeName
							.substring(localeName.indexOf("_") + 1);
				} else {
					variant = localeName;
				}
			} else {
				country = localeName;
			}
		} else {
			language = localeName;
		}

		locale = new Locale(language, country, variant);

		return locale;
	}

	/**
	 * Find the DefaultMutableTreeNode containing the userObject as UserObject
	 * 
	 * Returns null if node == null or userObject == null
	 */
	public static DefaultMutableTreeNode findNodeWithUserObject(
			Object userObject, javax.swing.tree.TreeNode node) {
		if (node == null || userObject == null) {
			return null;
		}

		if (node instanceof DefaultMutableTreeNode) {
			DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) node;
			if (dmtn.getUserObject() != null
					&& dmtn.getUserObject().equals(userObject))
				return dmtn;
		}

		// look in the children...
		for (int i = 0; i < node.getChildCount(); ++i) {
			DefaultMutableTreeNode dmtn = findNodeWithUserObject(userObject,
					node.getChildAt(i));
			if (dmtn != null)
				return dmtn;
		}

		return null;

	}

	/**
	 * Add the properties in resourceUri to props. If resourceUri does not
	 * exists, nothing happen.
	 * 
	 */
	public static void addProperties(String resourceUri,
			java.util.Properties props) {
		try {

			InputStream is = Misc.class.getResourceAsStream(resourceUri);
			if (is == null)
				return;
			props.load(is);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Get the content of a text resource file...
	 * 
	 */
	public static String getResourceContent(String resourceUri) {
		String content = "";
		if (resourceUri == null)
			return content;
		try {

			InputStream is = Misc.class.getResourceAsStream(resourceUri);
			if (is == null)
				return content;

			LineNumberReader lnr = new LineNumberReader(new InputStreamReader(
					is));
			String line = null;
			boolean first = true;
			while ((line = lnr.readLine()) != null) {
				if (!first)
					content += "\n";
				content += line;
				first = false;
			}

			lnr.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return content;
	}

	public static HashMap opTimes = new HashMap();

	/**
	 * This function is used for debug porpuses. It is used to misure the time
	 * of an operation. Sample usage:
	 * 
	 * <pre>
	 * <code>
	 *   ....some code
	 *   Misc.optime("My operation");  // Start the timer
	 *   ....block to measure....
	 *   Misc.optime("My operation");  // Stop the timer
	 * </code>
	 * </pre>
	 * 
	 * Result on video:
	 * 
	 * <pre>
	 * <code>
	 *    My operation START (1)
	 *    My operation END (1)	20ms
	 * </code>
	 * </pre>
	 */
	public static void optime(String opName) {
		long t = new java.util.Date().getTime();
		if (opTimes.containsKey(opName)) {
			long t0 = ((Long) opTimes.get(opName)).longValue();
			long opCounter = ((Long) opTimes.get(opName + "_coutner"))
					.longValue();

			opTimes.remove(opName);
			System.out.println(opName + " END (" + opCounter + ")\t" + (t - t0)
					+ "ms");
			System.out.flush();

		} else {
			long opCounter = 0;
			opTimes.put(opName, new Long(t));
			if (opTimes.containsKey(opName + "_coutner")) {
				opCounter = ((Long) opTimes.get(opName + "_coutner"))
						.longValue();
			}
			opCounter++;
			opTimes.put(opName + "_coutner", new Long(opCounter));
			System.out.println(opName + " START (" + opCounter + ")");
			System.out.flush();
		}
	}

	/**
	 * This method validates URL like:
	 * 
	 * 123.123.123[:port] domain.domain.dom[:port]
	 * 
	 */
	public static boolean isValidUrl(String url) {
		String strRegex = "((([0-9]{1,3}\\.){3})[0-9]{1,3})" + // IP-
																// 199.194.52.184
				"|" + // allows either IP or domain
				"(([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\.?)++" + // domain pice
				"(:[0-9]{1,4})?"; // port number- :80

		return url.matches(strRegex);
	}

	public static final KeyListener ARABIC_KEY_LISTENER = new KeyListener() {

		boolean pressedRightShift = false;
		boolean pressedRightCtl = false;

		public void keyTyped(KeyEvent e) {
		}

		public void keyPressed(KeyEvent e) {

			if (pressedRightShift && pressedRightCtl)
				return;

			if (e.getKeyCode() == KeyEvent.VK_SHIFT
					&& e.getKeyLocation() == KeyEvent.KEY_LOCATION_RIGHT) {
				pressedRightShift = true;
			} else if (e.getKeyCode() == KeyEvent.VK_CONTROL
					&& e.getKeyLocation() == KeyEvent.KEY_LOCATION_RIGHT) {
				pressedRightCtl = true;
			}

			if (pressedRightShift && pressedRightCtl) {
				if (e.getSource() instanceof JComponent) {
					((JComponent) e.getSource())
							.setComponentOrientation((((JComponent) e
									.getSource()).getComponentOrientation()
									.equals(ComponentOrientation.RIGHT_TO_LEFT)) ? java.awt.ComponentOrientation.LEFT_TO_RIGHT
									: java.awt.ComponentOrientation.RIGHT_TO_LEFT);
				}
			}
		}

		public void keyReleased(KeyEvent e) {

			if (e.getKeyCode() == KeyEvent.VK_SHIFT
					&& e.getKeyLocation() == KeyEvent.KEY_LOCATION_RIGHT) {
				pressedRightShift = false;
				// System.out.println("Released: RIGHT SHIFT");
			}
			if (e.getKeyCode() == KeyEvent.VK_CONTROL
					&& e.getKeyLocation() == KeyEvent.KEY_LOCATION_RIGHT) {
				pressedRightCtl = false;
				// System.out.println("Released: RIGHT CTL");
			}
		}
	};

	public JFrame getMainFrame(JPanel pnl) {
		Container parent = pnl.getParent();

		while ((parent != null) && (!(parent instanceof JFrame))) {
			parent = parent.getParent();
		}

		return (parent != null) ? (JFrame) parent : null;
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void requestFocusFor(final JComponent comp) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				comp.setRequestFocusEnabled(true);
				comp.requestFocus();
			}
		});
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}// end class Misc
