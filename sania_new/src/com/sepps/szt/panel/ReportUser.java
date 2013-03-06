package com.sepps.szt.panel;



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*; //import java.util.ArrayList;   
import java.util.EventObject;
import javax.swing.border.*;
import java.util.*;
import java.sql.*;
import java.text.*;


import com.sepps.szt.column.ReportUserColumn;
import com.sepps.szt.domain.UserAdv;
import com.sepps.szt.jdbc.MvbOracleConnection;
import com.sepps.szt.table.DatabaseTableColumn;
import com.sepps.szt.table.DatabaseTableModel;
import com.sepps.szt.renderers.demandTableRenderer;
import com.sepps.szt.table.TableSorter;
import com.sepps.szt.utils.Messager;

public class ReportUser extends JPanel {
	private UserAdv user_report = null;
	// private ManagerGroupColumn manager_column = null;
//	private MvbOracleConnection mvb = MvbOracleConnection.getInstance();
	private ReportUserColumn report_column = ReportUserColumn
			.getInstance();
	static TableColumnModel columnModel;

	protected JPanel managerPane = null;
	protected JScrollPane tablePane = null;
	public JTable table = null;
	protected JPanel controlPane = null;
	protected Container ground;
	public DatabaseTableModel model;

	JLabel labelTlf;
	JLabel labelTlfStatus;
	public JTextField dTlf;

	Dimension longField = new Dimension(240, 20);
	Dimension min = new Dimension(0, 0);
	Dimension minTbl = new Dimension(800, 60);
	Dimension scrPain;
	Dimension scrPainNew;
	double x;
	double y;
	DemandTlfFocusTraversalPolicy newPolicy2;

	public ReportUser() {

		newPolicy2 = new DemandTlfFocusTraversalPolicy();
		initPerson();

		this.setLayout(new BorderLayout());

		JPanel p = new JPanel();

		JPanel middlePanel = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		System.out.println("AFTER  - top1Panel");
		p.add(top1Panel());
		p.add(top2Panel());

		p.add(Box.createVerticalStrut(900));
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
		middlePanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEtchedBorder(),
				BorderFactory.createEmptyBorder(20, 5, 20, 5)));
		middlePanel.add(p);
		// middlePanel.add(Box.createHorizontalStrut(100));

		this.add(middlePanel);

		try {
			emptyTable();

		} catch (Exception exc) {
			exc.printStackTrace();

		}

		scrPain = tablePane.getPreferredSize();
		x = scrPain.getWidth();
		y = 200;
		scrPainNew = new Dimension((int) x, (int) y);
		tablePane.setPreferredSize(scrPainNew);
		try {
			createTable();

		} catch (Exception exc) {
			exc.printStackTrace();

		}
	}

	void initPerson() {
		user_report = new UserAdv();
				
		report_column = new ReportUserColumn();
		
	}

	public void createTable() throws Exception {
		
		
		ArrayList data = user_report.findAll();

		DatabaseTableColumn[] columns = report_column.getColumns();

		String keyField = report_column.getKeyField();
		System.out.println("get_key_field:" + keyField);

		model = new PersonTableModel(data, columns, keyField, true);
		TableSorter sorter = new TableSorter(model);

		table.setModel(sorter);
		sorter.setTableHeader(table.getTableHeader());

		ResizeHeaderTable(table);
	}

	private JPanel top1Panel() {
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints с = new GridBagConstraints();

		с.insets = new Insets(4, 4, 4, 4);

		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridBagLayout());
		panel1.setBorder(new TitledBorder(new EtchedBorder(), ""));

		labelTlf = new JLabel("√руппа управлени€ :");
		с.gridx = 0;
		с.gridy = 0;
		с.gridwidth = 1;
		с.gridheight = 1;
		с.anchor = GridBagConstraints.WEST;
		layout.setConstraints(labelTlf, с);
	//	panel1.add(labelTlf, с);

		dTlf = new JTextField();
		dTlf.setPreferredSize(longField);

		с.gridx = 1;
		с.gridy = 0;
		с.gridwidth = 1;
		с.gridheight = 1;
		с.weightx = 1.0;
		с.anchor = GridBagConstraints.WEST;
		layout.setConstraints(dTlf, с);
		
		panel1.setPreferredSize(min);
		return panel1;
	}

	private JPanel top2Panel() {

		table = new JTable(model);

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		panel1.setBorder(new TitledBorder(new EtchedBorder(), ""));
		tablePane = new JScrollPane(table,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tablePane.setBorder(new BevelBorder(BevelBorder.LOWERED));
		tablePane.setWheelScrollingEnabled(false);
		panel1.add(tablePane);
		return panel1;

	}



	private void ClearField() {

		tablePane.setVisible(false);

	}

	public void emptyTable() throws Exception {
		ArrayList data = new ArrayList();
		DatabaseTableColumn[] columns = report_column.getColumns();
		String keyField = report_column.getKeyField();
		model = new PersonTableModel(data, columns, keyField, true);
		table.setModel(model);
		ResizeHeaderTable(table);
	}

	void ResizeHeaderTable(JTable table) {

		columnModel = table.getColumnModel();
		int e = columnModel.getColumnCount();
		for (int k = 0; k < e; k++) {

			TableColumn column = (TableColumn) columnModel.getColumn(k);
			// column.setHeaderRenderer( new demandTableRenderer());
			if (k == 1) {

				column.setMinWidth(250);

			} else if (k == 0) {

				column.setMinWidth(150);

			}

			else if (k == 5) {

				column.setMinWidth(400);

			} else {

				column.setMinWidth(400);

			}

		}

	}

	public class DemandTlfFocusTraversalPolicy extends FocusTraversalPolicy {

		public Component getComponentAfter(Container focusCycleRoot,
				Component aComponent) {
			return dTlf;

		}

		public Component getComponentBefore(Container focusCycleRoot,
				Component aComponent) {

			return dTlf;
		}

		public Component getDefaultComponent(Container focusCycleRoot) {
			return dTlf;
		}

		public Component getLastComponent(Container focusCycleRoot) {
			return dTlf;
		}

		public Component getFirstComponent(Container focusCycleRoot) {
			return dTlf;
		}
	}

	public static void main(String args[]) {
		JFrame frame = new JFrame("test");
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

		UserSearch browser = new UserSearch();

		frame.getContentPane().add(browser);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}

}

class ReportUserTableModel extends DatabaseTableModel {
	public ReportUserTableModel(ArrayList data, DatabaseTableColumn[] columns,
			String keyField, boolean modifiable) {
		super(data, columns, keyField, modifiable);
	}

	public boolean isCellEditable(int row, int col) {

		if (col == keyCol)
			return false;
		else if (getColumnName(col).equals("groupp"))
			return false;
		else
			return modifiable;
	}
}
