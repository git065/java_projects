package com.adslur.szt.panel;

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

import com.adslur.szt.domen.Person;
import com.adslur.szt.jdbc.MvbOracleConnection;
import com.adslur.szt.table.DatabaseTableColumn;
import com.adslur.szt.table.DatabaseTableModel;
import com.adslur.szt.renderers.demandTableRenderer;
import com.adslur.szt.table.TableSorter;
import com.adslur.szt.utils.Messager;
import com.adslur.szt.validation.ValidationUtil;

public class DemandTlf extends JPanel {
	private Person person = null;
	private MvbOracleConnection mvb = MvbOracleConnection.getInstance();
	private DatabaseTableColumn[] columns = null;
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

	public DemandTlf() {

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
		person = new Person();
		person.open();
	}

	public void createTable(String tlf) throws Exception {

		if (!ValidationUtil.isLong(tlf)) {

			// JOptionPane.showMessageDialog(null,
			// "Не правильно заполнено поле  <номер телефона>",
			// "Внимание!!",JOptionPane.WARNING_MESSAGE);
			Messager.exception(this,
					"Не правильно заполнено поле  <номер телефона>");

		}
		ArrayList data = person.findAll(tlf);

		DatabaseTableColumn[] columns = person.getColumns();

		String keyField = person.getKeyField();
		System.out.println("get_key_field:" + keyField);

		model = new PersonTableModel(data, columns, keyField, true);
		TableSorter sorter = new TableSorter(model);

		table.setModel(sorter);
		sorter.setTableHeader(table.getTableHeader());

		TableColumn column1 = table.getColumnModel().getColumn(3);
		column1.setCellRenderer(new demandTableRenderer());
		TableColumn column2 = table.getColumnModel().getColumn(2);
		column2.setCellRenderer(new demandTableRenderer());

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

		labelTlf = new JLabel("№ Телефона :");
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
		DatabaseTableColumn[] columns = person.getColumns();
		String keyField = person.getKeyField();
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

				column.setMinWidth(350);

			} else if (k == 0) {

				column.setMinWidth(150);

			}

			else if (k == 5) {

				column.setMinWidth(700);

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

		DemandTlf browser = new DemandTlf();

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
