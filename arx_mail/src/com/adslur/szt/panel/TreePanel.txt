package com.adslur.szt.panel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.Locale;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.sql.*;
import java.io.*;

public class TreePanel extends JPanel implements TreeSelectionListener {
	public static String numTlf = null;
	public static String dmnd_idTlf = null;
	private static final long serialVersionUID = -1282280858252793250L;
	public JEditorPane htmlPane;
	private JTree tree;
	Connection conn;
	private static String SQL1 = "  select  do.num_order  num   from demands_ur_orders do  , demand_refine_ur  dr "
			+ "  where  do.dmnd_dmnd_id = dr.dmnd_dmnd_id   "
			+ "  and    do.rfn_rfn_id = dr.rfn_rfn_id  "
			+ "  and    dr.start_date  < sysdate    "
			+ "   and    dr.end_date    > sysdate  "
			+ "  and    do.start_date  < sysdate   "
			+ "  and    do.end_date    > sysdate  "
			+ "  and    dr.rfn_rfn_id  = 10  " + "  and    do.dmnd_dmnd_id=  ";

	private static String SQL2 = "  select  do.num_order  num   from demands_ur_orders do  , demand_refine_ur  dr "
			+ "  where  do.dmnd_dmnd_id = dr.dmnd_dmnd_id   "
			+ "  and    do.rfn_rfn_id = dr.rfn_rfn_id  "
			+ "  and    dr.start_date  < sysdate    "
			+ "   and    dr.end_date    > sysdate  "
			+ "  and    do.start_date  < sysdate   "
			+ "  and    do.end_date    > sysdate  "
			+ "  and    dr.rfn_rfn_id  = 9  " + "  and    do.dmnd_dmnd_id=  ";

	private static String SQL3 = "  select  do.num_order  num   from demands_ur_orders do  , demand_refine_ur  dr "
			+ "  where  do.dmnd_dmnd_id = dr.dmnd_dmnd_id   "
			+ "  and    do.rfn_rfn_id = dr.rfn_rfn_id  "
			+ "  and    dr.start_date  < sysdate    "
			+ "   and    dr.end_date    > sysdate  "
			+ "  and    do.start_date  < sysdate   "
			+ "  and    do.end_date    > sysdate  "
			+ "  and    dr.rfn_rfn_id  = 11  " + "  and    do.dmnd_dmnd_id=  ";
	String s = null;

	public TreePanel() {
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.219.89:1521:testdb4", "inqdb",
					"pfzdrb");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

		init();

	}

	private void init() {
		this.setLayout(new GridLayout(1, 0));

		EmptyBorder border = new EmptyBorder(new Insets(0, 0, 0, 5));
		this.setLayout(new BorderLayout());

		JPanel panel2 = new JPanel(new BorderLayout());
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.setLayout(new GridLayout(1, 0));
		panel1.setBorder(BorderFactory.createCompoundBorder(BorderFactory
				.createEtchedBorder(), BorderFactory.createEmptyBorder(20, 5,
				20, 5)));

		// /////////////
		DefaultMutableTreeNode top = new DefaultMutableTreeNode(
				"Заявка  (номер телефона : " + numTlf + ")");
		createNodes(top);

		tree = new JTree(top);
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(this);

		JScrollPane treeView = new JScrollPane(tree);

		htmlPane = new JEditorPane();
		htmlPane.setEditable(false);

		JScrollPane htmlView = new JScrollPane(htmlPane);
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setTopComponent(treeView);
		splitPane.setBottomComponent(htmlView);
		Dimension minimumSize = new Dimension(100, 50);
		htmlView.setMinimumSize(minimumSize);
		treeView.setMinimumSize(minimumSize);
		splitPane.setDividerLocation(300);
		splitPane.setDividerSize(1);
		splitPane.setPreferredSize(new Dimension(500, 500));
		panel1.add(splitPane);
		// htmlPane.setText("" );

		// ////////////

		// add(panel1, BorderLayout.WEST);
		add(panel1, BorderLayout.CENTER);

	}

	private void createNodes(DefaultMutableTreeNode top) {
		DefaultMutableTreeNode category = null;
		DefaultMutableTreeNode book = null;

		category = new DefaultMutableTreeNode("Атрибуты заявки");
		top.add(category);

		book = new DefaultMutableTreeNode(new BookInfo("Смена тарифа"));
		category.add(book);

		book = new DefaultMutableTreeNode(
				new BookInfo("Приостановление услуги"));
		category.add(book);

		book = new DefaultMutableTreeNode(new BookInfo("Отказ от услуги"));
		category.add(book);

	}

	private class BookInfo {
		public String bookName;

		public BookInfo(String book) {
			bookName = book;

		}

		public String toString() {
			return bookName;
		}
	}

	public void valueChanged(TreeSelectionEvent e) {

		String num = null;
		String htmlText = null;
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
				.getLastSelectedPathComponent();
		System.out.println("TEST  CHARCHE NODE");
		if (node == null)
			return;

		Object nodeInfo = node.getUserObject();
		System.out.println("TEST  GET OBLECT  NODE");
		if (node.isLeaf()) {

			BookInfo book = (BookInfo) nodeInfo;
			if (book.toString().equals("Смена тарифа")) {
				// num =executeSelect(SQL , "4608335") ;
				num = executeSelect(SQL1, numTlf, dmnd_idTlf);
				if (num == null)
					num = "";

				System.out.print("TRACE  --  EXESUTE  GET_NUM NAR :" + num
						+ "\n");
				System.out.println("TRACE CHECHE TRIF");
				htmlText = " ************************************************************* "
						+ "\n"
						+ "    Номер телефона  : "
						+ numTlf
						+ "\n"
						+ "       Номер заявки  : "
						+ dmnd_idTlf
						+ "\n"
						+ "             Номер наряда на смену тарифа : "
						+ num
						+ "\n"
						+ " ************************************************************** "
						+ "\n";
				htmlPane.setText(htmlText);

			} else if (book.toString().equals("Приостановление услуги")) {
				// num =executeSelect(SQL , "4608335") ;
				num = executeSelect(SQL2, numTlf, dmnd_idTlf);
				if (num == null)
					num = "";

				System.out.print("TRACE  --  EXESUTE  GET_NUM NAR :" + num
						+ "\n");
				System.out.println("TRACE CHECHE TRIF");
				htmlText = " ************************************************************* "
						+ "\n"
						+ "    Номер телефона  : "
						+ numTlf
						+ "\n"
						+ "       Номер заявки  : "
						+ dmnd_idTlf
						+ "\n"
						+ "               Номер наряда на <Временное приостановление> : "
						+ num
						+ "\n"
						+ " ************************************************************** "
						+ "\n";
				htmlPane.setText(htmlText);

			} else if (book.toString().equals("Отказ от услуги")) {
				// num =executeSelect(SQL , "4614683") ;
				num = executeSelect(SQL3, numTlf, dmnd_idTlf);
				if (num == null)
					num = "";

				System.out.print("TRACE  --  EXESUTE  GET_NUM NAR :" + num
						+ "\n");
				System.out.println("TRACE CHECHE TRIF");
				htmlText = " ************************************************************* "
						+ "\n"
						+ "    Номер телефона  : "
						+ numTlf
						+ "\n"
						+ "       Номер заявки  : "
						+ dmnd_idTlf
						+ "\n"
						+ "              Номер наряда на <Отказ от услуги> : "
						+ num
						+ "\n"
						+ " ************************************************************** "
						+ "\n";
				htmlPane.setText(htmlText);

			} else {

				htmlPane.setText(book.toString());
			}
		}

	}

	public String executeSelect(String sql, String nTlf, String idDem) {
		String flag = null;

		int rows = 0;
		ResultSet rslt = null;
		Statement stmt = null;

		try {
			stmt = conn.createStatement();
			rslt = stmt.executeQuery(sql + idDem);
			while (rslt.next()) {

				flag = rslt.getString(1);
				// System.out.print("ANALIZE FLAG :" + flag + "\n");
			}
			// System.out.println(Integer.toString(rows) + " rows selected");
			// System.out.println(" ");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if (rslt != null)
				try {
					rslt.close();
				} catch (SQLException ignore) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException ignore) {
				}
		}
		return flag;
	}

	public static void main(String[] s) {
		JFrame frame = new JFrame("JDateChooser");

		frame.addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
			}

			public void windowClosed(WindowEvent e) {
			}

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

			public void windowDeactivated(WindowEvent e) {
			}

			public void windowDeiconified(WindowEvent e) {
			}

			public void windowIconified(WindowEvent e) {
			}

			public void windowOpened(WindowEvent e) {
			}
		});

		TreePanel browser = new TreePanel();

		frame.getContentPane().add(browser);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(400, 400);

		frame.setVisible(true);
	}
}
