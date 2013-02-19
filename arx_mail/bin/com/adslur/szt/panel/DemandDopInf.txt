package com.adslur.szt.panel;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.KeyboardFocusManager;

import com.adslur.szt.domen.OperFio;
import com.adslur.szt.jdbc.MvbOracleConnection;

import com.adslur.szt.model.DataInputDemandModel;
import com.adslur.szt.domen.DataInputDemand;


import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.builder.PanelBuilder;

import com.jgoodies.validation.ValidationResultModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.validation.view.ValidationComponentUtils;


public class DemandDopInf extends JPanel {

	private MvbOracleConnection mvb = MvbOracleConnection.getInstance();
                    private final DataInputDemandModel   DemandModel;	
	OperFio fio = new OperFio();
	// components of the window
	JLabel labelFam;
	JLabel labelIm;
	JLabel labelOt;
	JLabel labelTlfDop;
	JLabel labelFax;
	JLabel labelEmail;
	JLabel labelFamDov;
	JLabel labelImDov;
	JLabel labelOtDov;
	JLabel labelFamPred;
	JLabel labelImPred;
	JLabel labelOtPred;
	JLabel labelDopInfPred;

	JCheckBox chkNetYes;
	JLabel labelCountKomp;
	JLabel labelKontInfNetw;

	JCheckBox chkMinAtsYes;
	JLabel labelKontMinAts;

	JTextField dFam;
	JTextField dIm;
	JTextField dOt;
	JTextField dTlfDop;
	JTextField dFax;
	JTextField dEmail;
	JTextField dFamDov;
	JTextField dImDov;
	JTextField dOtDov;
	JTextField dFamPred;
	JTextField dImPred;
	JTextField dOtPred;
	JTextField dDopInfPred;
	JTextField dCountKomp;

	JTextArea aKontInfNetw;
	JTextArea aKontMinAts;

                    JScrollPane scrolN  ;
                    JScrollPane scrolA  ;

	private JTabbedPane tabbedPane = null;

	Dimension shortField = new Dimension(40, 20);
	Dimension mediumField = new Dimension(180, 20);
	Dimension longField = new Dimension(240, 20);
	Dimension longmField = new Dimension(540, 20);
	Dimension hugeField = new Dimension(340, 80);

	EmptyBorder border = new EmptyBorder(new Insets(0, 0, 0, 5));
	EmptyBorder border1 = new EmptyBorder(new Insets(0, 100, 0, 10));
	EmptyBorder border2 = new EmptyBorder(new Insets(5, 0, 0, 0));

	GridBagConstraints c1 = new GridBagConstraints();
	WorkerFio worker = null;
                  public static  DopInfTraversalPolicy  newPolicy2;


                         private void initComponent() {

                        
                                          dFam                  =              new JTextField();
                                          dIm                        =              new JTextField();
	                      dOt           =              new JTextField();
	                         // dTlfDop        =              new JTextField();
                                          dTlfDop                      =  BasicComponentFactory.createTextField(DemandModel.getModel( DataInputDemand.PROPERTYNAME_TLF_DOP), false);
	                     // dFax         =              new JTextField();
	                    //  dEmail     =              new JTextField();
                                           dFax                      =  BasicComponentFactory.createTextField(DemandModel.getModel( DataInputDemand.PROPERTYNAME_FAX), false);
                                           dEmail                     =  BasicComponentFactory.createTextField(DemandModel.getModel( DataInputDemand.PROPERTYNAME_EMAIL), false);
	                    // dFamDov     =              new JTextField();
	                  //   dImDov     =              new JTextField();
	                  //   dOtDov     =              new JTextField();
                                          dFamDov                      =  BasicComponentFactory.createTextField(DemandModel.getModel( DataInputDemand.PROPERTYNAME_FAM_DOP), false);
                                          dImDov                      =  BasicComponentFactory.createTextField(DemandModel.getModel( DataInputDemand.PROPERTYNAME_IM_DOP), false);
                                           dOtDov                     =  BasicComponentFactory.createTextField(DemandModel.getModel( DataInputDemand.PROPERTYNAME_OT_DOP), false);
	                     dFamPred     =              new JTextField();
	                     dImPred      =              new JTextField();
	                     dOtPred    =              new JTextField();
	                     dDopInfPred   =              new JTextField();
	                  
	                      chkNetYes = new JCheckBox("Имеется локальная сеть");
                                          dCountKomp = new JTextField();

                                          aKontInfNetw           =     new JTextArea();
		 scrolN     = new JScrollPane(aKontInfNetw);

                                         chkMinAtsYes = new JCheckBox("Имеется  Мини - АТС");

                                         aKontMinAts           =     new JTextArea();
		 scrolA    = new JScrollPane(aKontMinAts);
	                     System.out.println("----------TRACE   ===> COMPLETE INIT:" );   
                                              

                     }

                      private void initComponentAnnotations() {
        
                                 ValidationComponentUtils.setMandatory(dTlfDop , true);
                                 ValidationComponentUtils.setMessageKey(dTlfDop , "TLF.TLFDOP No");
                                 ValidationComponentUtils.setMandatory(dFax , true);
                                 ValidationComponentUtils.setMessageKey(dFax , "TLF.FAX No");
                                 ValidationComponentUtils.setMandatory(dEmail  , true);
                                 ValidationComponentUtils.setMessageKey(dEmail  , "TLF.EMAIL No");
    
                                  ValidationComponentUtils.setMandatory(dFamDov , true);
                                 ValidationComponentUtils.setMessageKey(dFamDov, "TLF.FAMDOP No");
                                 ValidationComponentUtils.setMandatory(dImDov , true);
                                 ValidationComponentUtils.setMessageKey(dImDov , "TLF.IMDOP No");
                                 ValidationComponentUtils.setMandatory(dOtDov  , true);
                                 ValidationComponentUtils.setMessageKey(dOtDov , "TLF.OTDOP No");
    }

	public DemandDopInf() {
                                         DemandModel  = new DataInputDemandModel(new DataInputDemand());   
		this.setLayout(new BorderLayout());
                                             initComponent();
                                             initComponentAnnotations() ;
   
                                         newPolicy2 = new DopInfTraversalPolicy();
		tabbedPane = new JTabbedPane();
		add(tabbedPane, BorderLayout.CENTER);

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
                                       


	}

       private JPanel top1Panel() {
                                            FormLayout layout = new FormLayout(  "left:pref, 3dlu , 120dlu , 30dlu , left:pref ,  2dlu , 120dlu , 30dlu , left:pref ,  2dlu, 120dlu , 2dlu",
                                                                                                                 "p,12dlu, p");
       
                         JPanel panel = new JPanel(layout);
  
                    PanelBuilder builder = new PanelBuilder(layout);
                    builder.setDefaultDialogBorder();  
                    CellConstraints cc = new CellConstraints();
                   builder.addSeparator("Торговый представитель (оператор ЦОК)",  cc.xyw(1,  1, 11));
                    builder.addLabel( "Фамилия :"  ,  cc.xy (1,  3));
                    builder.add(dFam,     cc.xy(3,  3));
                    builder.addLabel("Имя :",  cc.xy (5,  3));
                     builder.add(dIm,     cc.xy(7, 3));
                     builder.addLabel("Отчество :",  cc.xy (9,  3));
                       builder.add(  dOt,     cc.xy(11,  3));
		
		if (worker == null) {
			System.out.println("TRACE    execute worked  FIO :");
			mvb.setCountRequest(mvb.getCountRequest() + 1);
			(worker = new WorkerFio()).execute();

		}

	         return builder.getPanel();	
	}

       private JComponent  top2Panel() {
                          FormLayout layout = new FormLayout(  "  left:pref, 3dlu , 120dlu ,193dlu , left:pref ,  2dlu , 120dlu , 30dlu" ,
                                                                                                                 "p , 12dlu , p , 3dlu , p , 3dlu , p");
       
        JPanel panel = new JPanel(layout);
  
        PanelBuilder builder = new PanelBuilder(layout);
         builder.setDefaultDialogBorder();  
         CellConstraints cc = new CellConstraints();
          builder.addSeparator("Дополнительная   информация    доверенного лица",  cc.xyw(1,  1,7));
          builder.addLabel( "Телефон  :"  ,  cc.xy (1,  3));
          builder.add(dTlfDop,     cc.xy(3,  3));
          builder.addLabel("Факс  :",  cc.xy (1,  5));
          builder.add(dFax,     cc.xy(3, 5));
          builder.addLabel("E-mail  :",  cc.xy (1,  7));
          builder.add(  dEmail,     cc.xy(3,  7));

          
          builder.addLabel( "Фамилия  :"  ,  cc.xy (5,  3));
          builder.add( dFamDov,     cc.xy(7,  3));
          builder.addLabel("Имя  :",  cc.xy (5,  5));
          builder.add( dImDov,     cc.xy(7, 5));
          builder.addLabel("Отчество  :",  cc.xy (5, 7));
          builder.add(   dOtDov,     cc.xy(7, 7));


                return new IconFeedbackPanel(
                DemandModel.getValidationResultModel(),
                builder.getPanel()
                );

	}

     private JPanel top3Panel() {
                           FormLayout layout = new FormLayout(  "  left:pref, 3dlu , 120dlu ,323dlu " ,
                                                                                                                 "p , 12dlu , p , 3dlu , p , 3dlu , p , 3dlu , p");
       
        JPanel panel = new JPanel(layout);
  
        PanelBuilder builder = new PanelBuilder(layout);
          builder.setDefaultDialogBorder(); 
         CellConstraints cc = new CellConstraints();
          builder.addSeparator("Ответственный представитель",  cc.xyw(1,  1,4));
          builder.addLabel( "Фамилия  :"  ,  cc.xy (1,  3));
          builder.add(dFamPred,     cc.xyw(3,  3,1));
          builder.addLabel("Имя  :",  cc.xy (1,  5));
          builder.add(dImPred,     cc.xyw(3, 5,1));
          builder.addLabel("Отчество  :",  cc.xy (1,  7));
          builder.add(  dOtPred,     cc.xyw(3,  7,1));

          
          builder.addLabel( "Доп.информация  :"  ,  cc.xy (1, 9));
          builder.add( dDopInfPred ,     cc.xyw(3, 9,2));
         


          return builder.getPanel();
		

	}

         private JPanel top4_1Panel() {
                            FormLayout layout = new FormLayout(  "  left:pref, 3dlu , 80dlu ,80dlu , 45dlu ,  left:pref , 3dlu ,130dlu " ,
                                                                                                                 "p , 12dlu , p , 3dlu , p , 3dlu , p , 3dlu , p, 3dlu , p, 3dlu , p, 3dlu , p");
       
        JPanel panel = new JPanel(layout);
  
        PanelBuilder builder = new PanelBuilder(layout);
         builder.setDefaultDialogBorder();   
         CellConstraints cc = new CellConstraints();
          builder.addSeparator("Локальная сеть",  cc.xyw(1,  1,4));


           builder.add(chkNetYes,     cc.xyw(1,  3,1));

          builder.addLabel( "Кол-во компьютеров : "  ,  cc.xy (1, 5));
          builder.add(dCountKomp,     cc.xyw(3,  5,1));

          builder.addLabel("Контактная информация :",  cc.xy (1,  7));
          builder.add(scrolN,     cc.xywh(3, 7,2,6));
          
           builder.addSeparator("Мини - АТС",  cc.xyw(6,  1,3));
           builder.add(chkMinAtsYes,     cc.xy(6,  3));
           builder.addLabel("Контактная информация :",  cc.xy (6,  5));
          builder.add(scrolA,     cc.xywh(8, 5,1,6));


          return builder.getPanel();
		
	}

	
	private JPanel top4Panel() {

		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		p.add(top4_1Panel());
		
		return p;

	}

	// ////////////////////////////
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * JTextField dDopInfPred;
	 */

	public String getEmail() {
		return dEmail.getText();
	}

	public String getFamDov() {
		return dFamDov.getText();
	}

	public String getImDov() {
		return dImDov.getText();
	}

	public String getOtDov() {
		return dOtDov.getText();
	}

	public String getTlfDop() {
		return dTlfDop.getText();
	}

	public String getFax() {
		return dFax.getText();
	}

	public String getMinAtsYes() {
		String result = "N";

		if (chkMinAtsYes.isSelected()) {
			result = "Y";
		}

		return result;

	}

	public String getKontMinAts() {
		return aKontMinAts.getText();
	}

	public int getCountKomp() throws NumberFormatException {
		int result = 0;
		if (dCountKomp.getText().equals("")) {
			return result;
		} else {
			return Integer.valueOf(dCountKomp.getText());
		}
	}

	public String getNetYes() {
		String result = "N";

		if (chkNetYes.isSelected()) {
			result = "Y";
		}

		return result;

	}

	public String getKontInfNetw() {
		return aKontInfNetw.getText();
	}

	public String getFamPred() {
		return dFamPred.getText();
	}

	public String getImPred() {
		return dImPred.getText();
	}

	public String getOtPred() {
		return dOtPred.getText();
	}

	public String getFamOper() {
		return dFam.getText();
	}

	public String getImOper() {
		return dIm.getText();
	}

	public String getOtOper() {
		return dOt.getText();
	}
                     // //////////////////////////////

	public class DopInfTraversalPolicy extends FocusTraversalPolicy {
                                      
		public Component getComponentAfter(Container focusCycleRoot,Component aComponent) {
                                          
			if (aComponent.equals(dTlfDop)) {
                                                                    System.out.println("----------TRACE A   ===> RETURN  FAX:" );
				return dFax;
			}  else if (aComponent.equals(dFax)) {
                                                                       System.out.println("----------TRACE A   ===> RETURN  EMAIL:" );
				return dEmail;
			}  else if (aComponent.equals(dEmail)) {
                                                                         System.out.println("----------TRACE A   ===> RETURN  FAMDOV:" );
				return dFamDov;
			}  else if (aComponent.equals(dFamDov)) {
                                                                        System.out.println("----------TRACE A   ===> RETURN  IMDOV:" );
				return dImDov;
			}  else if (aComponent.equals(dImDov)) {
				return dOtDov;
			}  else if (aComponent.equals(dOtDov)) {
				return dFamPred;
			} else if (aComponent.equals(dFamPred)) {
				return dImPred;
			
                                                            } else if (aComponent.equals(dImPred)) {
				return dOtPred;
			} else if (aComponent.equals(dOtPred)) {
				return dDopInfPred;
			
                                                            } 

                                                            else if (aComponent.equals(dDopInfPred)) {
				return chkNetYes;
			} else if (aComponent.equals(chkNetYes)) {
				return dCountKomp;
			
                                                            } else if (aComponent.equals(dCountKomp)) {
				return dTlfDop;
			}
                                                           




			return dTlfDop;
		}

		public Component getComponentBefore(Container focusCycleRoot,
				                                       Component aComponent) {
                                        
			if (aComponent.equals(dTlfDop)) {
                                                                    System.out.println("----------TRACE  B  ===> RETURN  COUNT :" );
				return dCountKomp;
			} else if (aComponent.equals(dCountKomp)) {
                                                                      System.out.println("----------TRACE  B  ===> RETURN  NETY :" );
				return chkNetYes;
			} else if (aComponent.equals(chkNetYes)) {
                                                                       System.out.println("----------TRACE  B  ===> RETURN  DOPINTRF :" );
				return dDopInfPred;
			} else if (aComponent.equals(dDopInfPred)) {
                                                                         System.out.println("----------TRACE  B  ===> RETURN  OTPRED :" );
				return dOtPred;
			 } else if (aComponent.equals(dOtPred)) {
                                                                         System.out.println("----------TRACE  B  ===> RETURN  IMPRED :" );
				return dImPred;
			
                                                            } else if (aComponent.equals(dImPred)) {
                                                                            System.out.println("----------TRACE  B  ===> RETURN FAMPRED  :" );
				return dFamPred;
			
                                                            } else if (aComponent.equals(dFamPred)) {
				return dOtDov;
			
                                                            } else if (aComponent.equals(dOtDov)) {
				return dImDov;
			
                                                            } else if (aComponent.equals(dImDov)) {
				return dFamDov;
			
                                                            } else if (aComponent.equals(dFamDov)) {
				return dEmail;
			
                                                            } else if (aComponent.equals(dEmail)) {
				return dFax;
			
                                                            } else if (aComponent.equals(dFax)) {
				return dTlfDop;
			}
                                                                return dTlfDop;
                                                            

		}

		public Component getDefaultComponent(Container focusCycleRoot) {
                                                         System.out.println("----------TRACE   ===> RETURN  DEFAULT:" );
			return dTlfDop;
		}

		public Component getLastComponent(Container focusCycleRoot) {
			return aKontMinAts;
		}

		public Component getFirstComponent(Container focusCycleRoot) {
                                                        System.out.println("----------TRACE   ===> RETURN  FIRST:" );
			return dTlfDop;
		}
	}

	// /////////////////////////
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

		DemandDopInf browser = new DemandDopInf();

		frame.getContentPane().add(browser);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(1200, 1200);
		// frame.pack();
		frame.setVisible(true);
		// m_nanos = System.nanoTime();

	}

	public class WorkerFio extends SwingWorker<OperFio, Void> {

		@Override
		protected OperFio doInBackground() {
			fio = new OperFio();
			System.out.println("TRACE    BACKGROUND FIOOOOOOO :");

			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			if (!isCancelled()) {
				System.out.println("TRACE    BACKGROUND FIOOOOOOO  USER :"
						+ mvb.getUserName());

				fio = mvb.getOperFio(mvb.getUserName().toLowerCase());

				System.out.println("TRACE    BACKGROUND FIO :" + fio.getFam());

				return null;
			}
			System.out.println("TRACE    BACKGROUND  IM:" + fio.getIm());
			return fio;
		}

		@Override
		protected void done() {
			try {

				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				System.out.println("TRACE    DONE  WORKED  FIO :"
						+ fio.getFam());
				dFam.setText(fio.getFam());
				dIm.setText(fio.getIm());
				dOt.setText(fio.getOt());

				if (mvb.getCountRequest() != 0) {
					mvb.incCountRequest(1);
				}
				if (mvb.getCountRequest() == 0) {
					mvb.close();
				}

				System.out.println("TRACE    DONE  FIO :"
						+ mvb.getCountRequest());
			} catch (Exception ignore) {
			}

		}
	}

}
