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

import com.sepps.szt.column.SearchUserColumn;
import com.sepps.szt.domain.User;
import com.sepps.szt.jdbc.MvbOracleConnection;
import com.sepps.szt.table.DatabaseTableColumn;
import com.sepps.szt.table.DatabaseTableModel;
import com.sepps.szt.renderers.demandTableRenderer;
import com.sepps.szt.table.TableSorter;
import com.sepps.szt.utils.Messager;


public class UserSearch extends JPanel {
	private User person = null;
	private MvbOracleConnection mvb = MvbOracleConnection.getInstance();
	private DatabaseTableColumn[] columns = null;
                private SearchUserColumn  user_column = SearchUserColumn
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
	Dimension minTbl = new Dimension(800, 60);
	Dimension scrPain;
	Dimension scrPainNew;
	double x;
	double y;
	DemandTlfFocusTraversalPolicy newPolicy2;

	public UserSearch() {

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
		middlePanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory
				.createEtchedBorder(), BorderFactory.createEmptyBorder(20, 5,
				20, 5)));
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

	}

	void initPerson() {
		person = new User();
                                user_column = new SearchUserColumn(); 
		//person.open();
	}

	public void createTable(String tlf) throws Exception {

		
		ArrayList data = person.findAll(tlf);
		
		if (data.size()== 0) {
		
			 data = person.findAll_n(tlf);
			
		}
		

		DatabaseTableColumn[] columns = user_column.getColumns();

		String keyField = user_column.getKeyField();
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

		JPanel panel2 = new JPanel();
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridBagLayout());
		panel1.setBorder(new TitledBorder(new EtchedBorder(), ""));

		labelTlf = new JLabel("Имя пользователя :");
		с.gridx = 0;
		с.gridy = 0;
		с.gridwidth = 1;
		с.gridheight = 1;
		с.anchor = GridBagConstraints.WEST;
		layout.setConstraints(labelTlf, с);
		panel1.add(labelTlf, с);

		dTlf = new JTextField();
		dTlf.setPreferredSize(longField);

		dTlf.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					dTlf.setCursor(Cursor
							.getPredefinedCursor(Cursor.WAIT_CURSOR));

					try {
						createTable(dTlf.getText());

					} catch (Exception exc) {
						exc.printStackTrace();

					}

					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					dTlf.setCursor(Cursor
							.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}

		});
		с.gridx = 1;
		с.gridy = 0;
		с.gridwidth = 1;
		с.gridheight = 1;
		с.weightx = 1.0;
		с.anchor = GridBagConstraints.WEST;
		layout.setConstraints(dTlf, с);
		panel1.add(dTlf, с);

		return panel1;
	}

	private JPanel top2Panel() {

		table = new JTable(model);

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		panel1.setBorder(new TitledBorder(new EtchedBorder(), ""));
		tablePane = new JScrollPane(table,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tablePane.setBorder(new BevelBorder(BevelBorder.LOWERED));
		tablePane.setWheelScrollingEnabled(false);
		panel1.add(tablePane);
		return panel1;

	}

	private JPanel top3Panel() {

		JPanel panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		labelTlfStatus = new JLabel("№ Телефона   заявки:");
		panel1.add(labelTlfStatus);
		panel1.setBorder(new TitledBorder(new EtchedBorder(), ""));

		return panel1;

	}

	private void ClearField() {

		tablePane.setVisible(false);

	}

	public void emptyTable() throws Exception {
		ArrayList data = new ArrayList();
		DatabaseTableColumn[] columns = user_column.getColumns();
		String keyField = user_column.getKeyField();
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

				column.setMinWidth(300);

			} else {

				column.setMinWidth(200);

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


class PersonTableModel extends DatabaseTableModel {
	public PersonTableModel(ArrayList data, DatabaseTableColumn[] columns,
			String keyField, boolean modifiable) {
		super(data, columns, keyField, modifiable);
	}

	public boolean isCellEditable(int row, int col) {

		if (col == keyCol)
			return false;
		else if (getColumnName(col).equals("id"))
			return false;
		else
			return modifiable;
	}
}
