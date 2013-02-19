package com.adslur.szt.panel;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.KeyboardFocusManager;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.adslur.szt.panel.StatusBars;
import com.adslur.szt.domen.AbonentUr;

import com.adslur.szt.utils.OutLookToolBar;
import com.adslur.szt.jdbc.MvbOracleConnection;

import com.adslur.szt.model.DataInputDemandModel;
import com.adslur.szt.domen.DataInputDemand;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.builder.PanelBuilder;
import  com.jgoodies.forms.builder.* ;

import com.jgoodies.validation.ValidationResultModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.validation.view.ValidationComponentUtils;



public class DemandAdd extends JPanel {

	private MvbOracleConnection mvb = MvbOracleConnection.getInstance();
                     private final DataInputDemandModel   DemandModel;
	private StatusBars sb = StatusBars.getInstance();
	private SwingPropertyChangeSupport changeSupport = new SwingPropertyChangeSupport(
			sb);

	private ArrayList lstTarif = new ArrayList();
	ArrayList lstVpn = null;
	private static int coutReq = 0;

	JLabel labelTlf;
	JLabel labelNumDgv;
	JLabel labelAccount;
	JLabel labelInn;
	JLabel labelNaimOrg;
	JLabel labelAdrUst;
	JLabel labelDopPropertLine;
	JLabel labelDivisInet;
	JLabel labelTech;
	JLabel labelNaimInterf;
	JLabel labelDop;
	JLabel labelTarif;
	JLabel labelNetwIp;
	JLabel labelIdDemand;
	JLabel labelStatus;
	JLabel labeldDopRes;

	public JTextField dTlf;
	public JTextField dDgv;
	public JTextField dAccount;
	public JTextField dInn;
	public JTextArea dNaimOrg;
	public JTextArea aAdrUst;
	public JTextArea aDopLine;
	public JComboBox cboDivisInet;
	public JComboBox cboTech;
	public JComboBox cboNaimInterf;
	public JTextField dDop;
	public JComboBox cboTarif;
	public JComboBox cboNetwIp;
	public JTextField dIdDemand;
	public JTextField dStatus;
	public JTextField dDopRes;

                    public  JScrollPane scrolO ;
                    public  JScrollPane scrolA ;
                    public  JScrollPane scrolD ;

	Dimension shortField = new Dimension(40, 20);
	Dimension mediumField = new Dimension(120, 20);
	Dimension longField = new Dimension(240, 20);
	Dimension longFieldm = new Dimension(340, 20);
	Dimension hugemField = new Dimension(400, 60);
	Dimension hugeField = new Dimension(400, 80);
	Dimension hugeFieldshort = new Dimension(400, 20);
	Dimension hugeFieldshortmax = new Dimension(600, 20);

	EmptyBorder border = new EmptyBorder(new Insets(0, 0, 0, 5));
	EmptyBorder border1 = new EmptyBorder(new Insets(0, 100, 0, 10));
	EmptyBorder border2 = new EmptyBorder(new Insets(5, 0, 0, 0));

	GridBagConstraints c1 = new GridBagConstraints();
	//public  static MyOwnFocusTraversalPolicy newPolicy1;
                     public  static   DemAddPolicy newPolicy1;
	public DefaultComboBoxModel cbm = new DefaultComboBoxModel();
	public DefaultComboBoxModel cbmVpn = new DefaultComboBoxModel();
	public DefaultComboBoxModel cbmAsc = new DefaultComboBoxModel();
	public DefaultComboBoxModel cbmInterf = new DefaultComboBoxModel();
	public DefaultComboBoxModel cbmNetwIp = new DefaultComboBoxModel();

	MyWorkerAdd worker2;
	WorkerVpn worker1;
	WorkerReqIbs worker3;
	OutLookToolBar m_tlbBar = null;
	JToolBar m_toolbar;
	public JButton button1;
	public JButton button2;
	public JButton button3;



                 
	public void setWorker3() {

		worker3 = null;
	}

	public DemandAdd() {
		// mvb.setCountRequest(2);////
		mvb.setCountRequest(mvb.getCountRequest() + 2);
                                        DemandModel  = new DataInputDemandModel(new DataInputDemand());
		initComponents();
                                         initComponentAnnotations() ;
		//newPolicy1 = new MyOwnFocusTraversalPolicy();
                                           newPolicy1 = new DemAddPolicy();
                   
		this.setLayout(new BorderLayout());

		JPanel p = new JPanel();

		JPanel middlePanel = new JPanel();

		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.add(top1Panel());
		p.add(top2Panel());
		p.add(top3Panel());
		p.add(top4Panel());
		p.add(Box.createVerticalStrut(400));

		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
		middlePanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory
				.createEtchedBorder(), BorderFactory.createEmptyBorder(20, 5,
				20, 5)));
		middlePanel.add(p);
		middlePanel.add(Box.createHorizontalStrut(100));

		this.add(middlePanel);

		this.add(m_toolbar, BorderLayout.NORTH);
		this.addPropertyChangeListener(sb);

	}

      private  JComponent  top1Panel() {
                         FormLayout layout = new FormLayout(
                                            "left:pref, 3dlu, 80dlu, 100dlu, left:pref,  2dlu,left:pref ,  2dlu,200dlu ,2dlu",
                                           "p,3dlu, p,3dlu , p,3dlu, p,3dlu , 30dlu, 3dlu  ");
       
        JPanel panel = new JPanel(layout);


     
   
        PanelBuilder builder = new PanelBuilder(layout);
         

          builder.setBorder(new TitledBorder(new EtchedBorder(), ""));
        CellConstraints cc = new CellConstraints();

       builder.addLabel( "№ Телефона :"  ,  cc.xy (1,  1));
       builder.add(dTlf,     cc.xyw(3,  1,1));
       labelNumDgv  =  builder.addLabel("Номер договора :",  cc.xy (1,  3));
       builder.add(dDgv,     cc.xyw(3,  3,1));
 
      builder.addLabel("Номер лиц.счета :",  cc.xy (1,  5));
      builder.add(  dAccount,     cc.xyw(3,  5,1));
      
      builder.addLabel("ИНН организации :",  cc.xy (1,  7));
      builder.add(  dInn,     cc.xyw(3,  7,1)); 

      builder.addLabel("Организация  :",  cc.xy (7,  1));
      builder.add(  scrolO,     cc.xywh(9, 1,1,5)); 
       
      builder.addLabel("Адрес установки  :",  cc.xy (7,  7));
      builder.add( scrolA,     cc.xywh(9,  7,1,4)); 
     	
		dTlf.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (dTlf.getText().length() < 7) {

					ClearField();
				}

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					mvb.setCountRequest(mvb.getCountRequest() + 1);
					changeSupport.firePropertyChange("start", "x", "y");
					// System.out.println("TRACE  GETDATE_REQ_IBS :" );
					sb.setMessage("Операция выполняется     -   ждите");

					// sb. m_runner.startA();
					// sb._time.start();
					worker3 = null;

					if (worker3 == null) {
						// System.out.println("TRACE     GETDATE_REQ_IBS :  Worker"
						// );
						(worker3 = new WorkerReqIbs()).execute();
					}

				}

			}

		});

                       return new IconFeedbackPanel(
                DemandModel.getValidationResultModel(),
                builder.getPanel()
                );
	}

	private JPanel top2Panel() {

	  FormLayout layout = new FormLayout(  "left:pref, 3dlu,250dlu ,2dlu",
                                                                                           "p,3dlu,20dlu ");
       
                 JPanel panel = new JPanel(layout);
                 PanelBuilder builder = new PanelBuilder(layout);
         
                    builder.setBorder(new TitledBorder(new EtchedBorder(), ""));
                    CellConstraints cc = new CellConstraints();
                     builder.addLabel("Особенности тел.линии :",  cc.xy (1,  1));
                     builder.add(scrolD,     cc.xywh(3,  1,1,3));
       
                                    return builder.getPanel();
	

	}

             private JPanel top3Panel() {
                    FormLayout layout = new FormLayout(  "left:pref, 3dlu,100dlu ,70dlu,left:pref, 3dlu,200dlu , 3dlu",
                                                                                           "p, 3dlu , p , 3dlu , p, 3dlu , p ,3dlu ");
       
                 JPanel panel = new JPanel(layout);
                 PanelBuilder builder = new PanelBuilder(layout);
         
                    builder.setBorder(new TitledBorder(new EtchedBorder(), ""));
                    CellConstraints cc = new CellConstraints();
                     builder.addLabel("Выделенный  доступ :",  cc.xy (1,  1));
                     builder.add(cboDivisInet,     cc.xy(3,  1));
                     builder.addLabel("Технология доступа   :",  cc.xy (1,  3));
                     builder.add(cboTech,     cc.xy(3,  3));
                     builder.addLabel("Интерфейс доступа  :",  cc.xy (1,  5));
                     builder.add( cboNaimInterf ,     cc.xy(3, 5));
                      builder.addLabel("Примечания  :",  cc.xy (1,  7));
                     builder.add( dDop ,     cc.xywh(3,7,5,1)); 


                     builder.addLabel("Тарифный план  :",  cc.xy (5,  1));
                     builder.add( cboTarif  ,     cc.xy(7, 1));
                     builder.addLabel("Сеть  IP  :",  cc.xy (5,  3));
                     builder.add( cboNetwIp  ,     cc.xy(7, 3));
		

		//
		if (worker1 == null) {
			(worker1 = new WorkerVpn()).execute();

		}

		

		

		// /////////////////////////
		if (worker2 == null) {
			(worker2 = new MyWorkerAdd()).execute();
			
		}
                                    return builder.getPanel();
		
	}

             private JPanel top4Panel() {
                  FormLayout layout = new FormLayout(  "left:pref, 3dlu,400dlu ,3dlu",
                                                                                           "p, 12dlu , p , 3dlu , p ,3dlu , p ,3dlu , p ");
       
                 JPanel panel = new JPanel(layout);
                 PanelBuilder builder = new PanelBuilder(layout);
         
                builder.setBorder(new TitledBorder(new EtchedBorder(), ""));
                     
                    CellConstraints cc = new CellConstraints();
                     builder.addSeparator("Результат  выполнения действий по заявке",  cc.xyw(1,  1, 4));
                     builder.addLabel("Идентификатор заявки :",  cc.xy (1,  3));
                     builder.add(dIdDemand ,     cc.xy(3,  3));
                     builder.addLabel("Статус заявки  :",  cc.xy (1,  5));
                     builder.add(dStatus ,     cc.xy(3,  5));
                     builder.addLabel("Дополнительные сведения   :",  cc.xy (1,  7));
                     builder.add( dDopRes  ,     cc.xy(3, 7));
                    
                    
                                    return builder.getPanel();

		
	}

	private void initComponents() {

		m_toolbar = new JToolBar();
		m_tlbBar = new OutLookToolBar(m_toolbar);
                                          
                          //  dTlf                       = new JTextField();
                           dTlf                       =  BasicComponentFactory.createTextField(DemandModel.getModel( DataInputDemand.PROPERTYNAME_TLF), false);
                            dDgv                   = new JTextField();
                            dDgv .setEditable(false);
                            dAccount           = new JTextField();
                            dAccount.setEditable(false);
                            dInn                    = new JTextField();
                            dInn.setEditable(false);
                            dNaimOrg            = new JTextArea();
                            dNaimOrg.setEditable(false);
	       dNaimOrg.setFocusable(false);
	       dNaimOrg.setBackground(Color.WHITE);
	       dNaimOrg.setLineWrap(true);
	       dNaimOrg.setWrapStyleWord(true);
	       scrolO = new JScrollPane(dNaimOrg);
	       scrolO.setPreferredSize(hugemField);
                            aAdrUst              = new JTextArea();
                           aAdrUst.setEditable(false);
	        aAdrUst.setFocusable(false);
		aAdrUst.setBackground(Color.WHITE);
		aAdrUst.setLineWrap(true);
		aAdrUst.setWrapStyleWord(true);
		scrolA = new JScrollPane(aAdrUst);
		scrolA.setPreferredSize(hugeField);
                          
                           aDopLine = new JTextArea();
		aDopLine.setEditable(false);
		aDopLine.setFocusable(false);
		aDopLine.setBackground(Color.WHITE);
		scrolD = new JScrollPane(aDopLine);
                           cboDivisInet    = new JComboBox(cbmVpn);
                           cboTarif            = new JComboBox(cbm);  
                           cboTech           = new JComboBox(cbmAsc);
                           cbmAsc.addElement("ADSL");
                           cboNetwIp       = new JComboBox(cbmNetwIp);
                           cbmNetwIp.addElement(" не требуется (NAT)");
                           cboNaimInterf = new JComboBox(cbmInterf);
                           cbmInterf.addElement(" Ethernet");

                           dDop                 = new JTextField();
                            
                           dIdDemand    = new JTextField();
                           dIdDemand.setEditable(false);
	       dIdDemand.setFocusable(false);
                           dStatus            = new JTextField();
                           dStatus.setEditable(false);
	       dStatus.setFocusable(false);
                           dDopRes        = new JTextField();
                          dDopRes.setEditable(false);
	       dDopRes.setFocusable(false);




















		button1 = new JButton();
		button1.setFocusable(false);
		button1.setEnabled(false);
		button1.setIcon(new ImageIcon("Images/KEY04.GIF"));
		button1
				.setToolTipText("<html>Временное бронирование<br> оборудования ");
		button2 = new JButton();
		button2.setFocusable(false);
		button2.setIcon(new ImageIcon("Images/RegTemp.GIF"));
		button2.setToolTipText("<html>Разбронирование<br> оборудования ");
		button3 = new JButton();
		button3.setIcon(new ImageIcon("Images/SEARCHN.GIF"));
		button3.setFocusable(false);
		button3.setToolTipText("<html>Список номеров<br> телефонов абонента ");
		m_toolbar.add(button1);
		m_toolbar.add(button2);

		m_toolbar.add(button3);

	}

                      private void initComponentAnnotations() {
        
                             ValidationComponentUtils.setMandatory(dTlf , true);
                            ValidationComponentUtils.setMessageKey(dTlf , "TLF.TLF No");
       
                   }

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(listener);
	}

	private AbonentUr InitRekvizit(String tlf) {
		AbonentUr ab = new AbonentUr();

		ab = mvb.getReqFromIbs(tlf);
		return ab;
		/*
		 * if (ab == null) {
		 * 
		 * JOptionPane.showMessageDialog(null,
		 * "Клиент не определяется как юридическое лицо", "Ошибка ввода",
		 * JOptionPane.ERROR_MESSAGE);
		 * 
		 * button1.setEnabled(false) ; return;
		 * 
		 * }
		 * 
		 * 
		 * button1.setEnabled(true) ; dDgv.setText(ab.getNumDgv());
		 * dAccount.setText(ab.getNumNls()); dInn.setText(ab.getNumInn());
		 * dNaimOrg.setText(ab.getNaimOrg()); aAdrUst.setText(ab.getAdrUst() );
		 */
	}

	private void OutResultIbs(AbonentUr ab) {

		button1.setEnabled(true);
		dDgv.setText(ab.getNumDgv());
		dAccount.setText(ab.getNumNls());
		dInn.setText(ab.getNumInn());
		dNaimOrg.setText(ab.getNaimOrg());
		aAdrUst.setText(ab.getAdrUst());

	}

	private void ClearField() {

		dDgv.setText("");
		dAccount.setText("");
		dInn.setText("");
		dNaimOrg.setText("");
		aAdrUst.setText("");

		dIdDemand.setText("");
		dStatus.setText("");
		dDopRes.setText("");
	}
                    //////////////////////
                       public JTextField   getObjectTlf() {

		return dTlf  ;

	}

	// ////////////////////////////
	public String getTlf() {

		return dTlf.getText();

	}

	public String getTatif() {

		return (String) cbm.getSelectedItem();

	}

	public String getDopLine() {

		return aDopLine.getText();

	}

	// //////////////////////////////
                        public class DemAddPolicy extends FocusTraversalPolicy {
                                      
		public Component getComponentAfter(Container focusCycleRoot,Component aComponent) {
                                           
			if (aComponent.equals(dTlf)) {
                                                                    System.out.println("----------TRACE  A ===> RETURN DOP:" );
				return dDop;
			}
                                                             else if (aComponent.equals(dDop)) {

                                                                           System.out.println("----------TRACE  A ===> RETURN  TECH:" );
				return dTlf;
			} 
                                                          



			return dTlf;
		}

		public Component getComponentBefore(Container focusCycleRoot,
				                                       Component aComponent) {
                                        
			if (aComponent.equals(dTlf)) {
                                                                      System.out.println("----------TRACE  B ===> RETURN  DOP:" );
				return dDop;
			}
                                                         
                                                           else if (aComponent.equals(dDop)) {
                                                                  System.out.println("----------TRACE  B ===> RETURN  TLF:" );
				return dTlf;
			}


                                                                return dTlf;
                                                            

		}

		public Component getDefaultComponent(Container focusCycleRoot) {
                                                         System.out.println("----------TRACE   ===> RETURN  DEFAULT:" );
			return dTlf;
		}

		public Component getLastComponent(Container focusCycleRoot) {
			return dDop;
		}

		public Component getFirstComponent(Container focusCycleRoot) {
                                                        System.out.println("----------TRACE   ===> RETURN  FIRST:" );
			return dTlf;
		}
	}


	public static void main(String args[]) {
		JFrame frame = new JFrame("Fredy Browser");
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

		DemandAdd browser = new DemandAdd();

		frame.getContentPane().add(browser);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(1000, 1000);
		frame.setVisible(true);

	}

	// //
	public class MyWorkerAdd extends SwingWorker<ArrayList, Void> {

		@Override
		protected ArrayList doInBackground() {

			if (!isCancelled()) {

				lstTarif = mvb.getTarifList();

				// return null;
			}

			return lstTarif;
		}

		@Override
		protected void done() {
			try {

				cbm.addElement("не определено");
				for (int i = 0; i < lstTarif.size(); i++) {
					cbm.addElement(lstTarif.get(i).toString());

				}

				if (mvb.getCountRequest() != 0) {
					mvb.incCountRequest(1);
				}
				if (mvb.getCountRequest() == 0) {
					mvb.close();
				}

				// System.out.println("TRACE    DONE  LIST_TARIF :" +
				// mvb.getCountRequest() );
			} catch (Exception ignore) {
			}

		}

	}

	// //
	public class WorkerVpn extends SwingWorker<ArrayList, Void> {

		@Override
		protected ArrayList doInBackground() {
			lstVpn = new ArrayList();

			if (!isCancelled()) {

				lstVpn = mvb.getVpnList();

			}

			return lstVpn;
		}

		@Override
		protected void done() {
			try {

				for (int i = 0; i < lstVpn.size(); i++) {
					cbmVpn.addElement(lstVpn.get(i).toString());

				}

				worker2 = null;
				if (mvb.getCountRequest() != 0) {
					mvb.incCountRequest(1);
				}
				if (mvb.getCountRequest() == 0) {
					mvb.close();
				}
				// System.out.println("TRACE    DONE  LIST_VPN :" +
				// mvb.getCountRequest() );
			} catch (Exception ignore) {
			}

		}

	}

	//
	// //
	public class WorkerReqIbs extends SwingWorker<AbonentUr, Void> {
		AbonentUr ab = new AbonentUr();

		@Override
		protected AbonentUr doInBackground() {

			if (!isCancelled()) {

				ab = InitRekvizit(dTlf.getText().trim());

			}
			return ab;

		}

		@Override
		protected void done() {
			try {

				if (ab == null) {

					JOptionPane.showMessageDialog(null,
							"Клиент не определяется как юридическое лицо",
							"Ошибка ввода", JOptionPane.ERROR_MESSAGE);
					changeSupport.firePropertyChange("done", "x", "y");
					System.out.println("TRACE    DONE  ABONENT NOT UR ");

					button1.setEnabled(false);

				} else {
					System.out.println("TRACE  RUN  GET_IBS_DATE:");
					OutResultIbs(ab);
					changeSupport.firePropertyChange("done", "x", "y");
					// sb.setMessage("Операция выполнена");
					// sb._time.stop();
					// sb. m_runner.setRunning (false);
					worker3 = null;
				}

				worker3 = null;
				System.out.println("TRACE    WORKER DONE IS NULL :" + worker3);
				if (mvb.getCountRequest() != 0) {
					mvb.incCountRequest(1);
				}
				if (mvb.getCountRequest() == 0) {
					mvb.close();
				}
				System.out.println("TRACE    DONE  LIST_REQ_IBS :"
						+ mvb.getCountRequest());
			} catch (Exception ignore) {
			}

		}

	}
}
