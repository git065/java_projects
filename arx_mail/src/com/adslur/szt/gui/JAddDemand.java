package com.adslur.szt.gui;

import java.util.Date;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.sql.Timestamp;

import com.adslur.szt.jdbc.MvbOracleConnection;
import com.adslur.szt.panel.DemandAdd;
import com.adslur.szt.panel.DemandDopInf;
import com.adslur.szt.panel.StatusBars;



import com.adslur.szt.domen.DemandResult;
import com.adslur.szt.domen.DataFromRegist;
import com.adslur.szt.domen.UndoTemp;
import com.adslur.szt.domen.DataInputDemand;

import com.adslur.szt.renderers.UIDefaultsPopup;
import com.adslur.szt.exeption.ErrorInputText;
import com.adslur.szt.exeption.InputDemandException;
import com.adslur.szt.utils.Messager;

public class JAddDemand extends JInternalFrame implements ActionListener,
		MouseListener {
	private MvbOracleConnection mvb = MvbOracleConnection.getInstance();

	DemandAdd demAdd1Panel;
	DemandDopInf demAdd2Panel;
	StatusBars sb = StatusBars.getInstance();

	private JTextArea textArea = new JTextArea();
	private JScrollPane scrollPane = new JScrollPane();

	private JTabbedPane tabbedPane = null;

	ArrayList lst;
	Timer timer;
	public static int count = 2;
	ImageIcon image1 = new ImageIcon("Images/dem_addd1.gif");
	ImageIcon image2 = new ImageIcon("Images/dem_addd2.gif");

	WorkerSymData workerSymData = null;
	WorkerRegistTemp workerTemp = null;
	WorkerUndoTemp workerUndo = null;
	DemandResult res = new DemandResult();
	DataInputDemand inp = new DataInputDemand();
	UndoTemp resu;
	String Itog = "";
	int IdTarif = 0;
	String naimTarif = "";
                    static  int index = 0; 

	public JAddDemand() {
		setSize(1000, 850);
		setTitle("Добавить заявку");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setResizable(true);
		setFrameIcon(image1);
		demAdd1Panel = new DemandAdd();
		demAdd1Panel.addMouseListener(this);
		demAdd1Panel.button1.addActionListener(this);
		demAdd1Panel.button2.addActionListener(this);
		demAdd1Panel.button3.addActionListener(this);
		UIDefaultsPopup popup = new UIDefaultsPopup(this, demAdd1Panel.dTlf);
		demAdd1Panel.dTlf.setComponentPopupMenu(popup);
		demAdd2Panel = new DemandDopInf();
		tabbedPane = new JTabbedPane();
		// CountdownTimerPanel _time = new CountdownTimerPanel ();

		tabbedPane.addTab("Заявка", new ImageIcon("Images/REFR.GIF"),
				demAdd1Panel, "Основные данные");
		tabbedPane.addTab("Ввод доп.информации",
				new ImageIcon("Images/dop.GIF"), demAdd2Panel,
				"Дополнительные  данные");
		tabbedPane.setFocusable(false);

		scrollPane.getViewport().add(tabbedPane);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		/*
		 * JPanel p = new JPanel(); GridBagLayout layout = new GridBagLayout();
		 * GridBagConstraints c = new GridBagConstraints(); p.setLayout(layout
		 * );
		 * 
		 * c.gridx = 0; c.gridy = 0; c.gridwidth = 1; c.gridheight = 1 ;
		 * c.weightx = 1; c.anchor = GridBagConstraints.WEST;
		 * layout.setConstraints(sb, c); p.add(sb, c );
		 * 
		 * c.gridx = 1; c.gridy = 0; c.gridwidth = 1; c.gridheight = 1 ;
		 * c.weightx = 0; c.anchor = GridBagConstraints.WEST;
		 * layout.setConstraints(_time,c); p.add(_time, c );
		 */

		getContentPane().add(sb, BorderLayout.SOUTH);
                                   //  this.setFocusTraversalPolicy(demAdd2Panel.newPolicy2 );
                                         this.setFocusTraversalPolicy(demAdd1Panel.newPolicy1);



                                         tabbedPane.addMouseListener(new MouseAdapter() {
                                                public void mouseClicked(MouseEvent e) {
 
                                                  index = ((JTabbedPane)e.getSource()).indexAtLocation(e.getX(), e.getY());
                                                       System.out.println("Index: " + index);
                                                    if(index== 0){
                                                       JAddDemand.this.setFocusTraversalPolicy( demAdd1Panel.newPolicy1 );
                                                        System.out.println("Index==========>: " + index);
                                                    }
                                                    else if(index== 1){
                                                      JAddDemand.this.setFocusTraversalPolicy(demAdd2Panel.newPolicy2);
                                                        System.out.println("Index==========>: " + index);
                                                    } 
                                                   
                                                 
                                  }
                    });


	}
                     
                  
	public void actionPerformed(ActionEvent evt) {

		if (evt.getSource() == demAdd1Panel.button1) {
			mvb.setCountRequest(mvb.getCountRequest() + 1);

			// timer = new Timer(1000, new TimerCaption());

			// timer.start();

			naimTarif = (String) demAdd1Panel.cbm.getSelectedItem();

			if (!naimTarif.equals("не определено")) {

				System.out.println("----------TRACE   WORKED SYMDATE  :"
						+ workerSymData);
				sb.setMessage("Выполняется операция бронирования");

				//
				sb.m_runner.startA();

				sb.timelbl.start();
				// 
				if (workerSymData == null) {
					(workerSymData = new WorkerSymData()).execute();

				}
			} else {
				JOptionPane
						.showMessageDialog(
								null,
								"Вы не выбрали тарифный план "
										+ "\n"
										+ "без этого параметра невозможно забронировать оборудование",
								"Не выбран тариф", JOptionPane.ERROR_MESSAGE);
			}

		} else if (evt.getSource() == demAdd1Panel.button2) {

			try {
				int reply = JOptionPane.showConfirmDialog(this,
						"Вы уверены что хотите разбронировать оборудование ?",
						"Предупреждение", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE);

				if (reply == JOptionPane.YES_OPTION) {
					mvb.setCountRequest(mvb.getCountRequest() + 1);

					System.out.println("----------TRACE   WORKED UNDO TEMP  :"
							+ workerUndo);
					if (!demAdd1Panel.dTlf.getText().equals("")) {
						if (workerUndo == null) {
							(workerUndo = new WorkerUndoTemp()).execute();
						}
					} else {
						JOptionPane
								.showMessageDialog(
										null,
										"Нет номера телефона "
												+ "\n"
												+ "без этого параметра невозможно разбронировать оборудование",
										"Нет номера телефона",
										JOptionPane.ERROR_MESSAGE);
					}
				}
			} catch (Exception e) {
			}
		}
		//
		if (evt.getSource() == demAdd1Panel.button3) {

		}
		//

	}

	public void reEnabled(String look) {

		try {

			UIManager.setLookAndFeel(look);
			SwingUtilities.updateComponentTreeUI(JAddDemand.this);

		}

		catch (ClassNotFoundException e) {
			System.err
					.println("Couldn't find class for specified look and feel:"
							+ look);

			System.err.println("Using the default look and feel.");
		}

		catch (UnsupportedLookAndFeelException e) {
			System.err.println("Can't use the specified look and feel (" + look
					+ ") on this platform.");
			System.err.println("Using the default look and feel.");
		}

		catch (Exception e) {
			System.err.println("Couldn't get specified look and feel (" + look
					+ "), for some reason.");
			System.err.println("Using the default look and feel.");
			e.printStackTrace();
		}
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mouseClicked(MouseEvent e) {
		// System.out.println("TRACE   ----- >   mouseClicked");
	}

	public void mousePressed(MouseEvent e)

	{
		if (timer != null) {
			timer.stop();
			setFrameIcon(image1);
		}
		// System.out.println("TRACE   ----- >   mousePressed ");

	}

	public void mouseReleased(MouseEvent e) {
	}

	// /
	/*
	 * public void internalFrameOpened(InternalFrameEvent e) { } ; public void
	 * internalFrameClosing(InternalFrameEvent e) {}; public void
	 * internalFrameClosed(InternalFrameEvent e) {}; public void
	 * internalFrameIconified(InternalFrameEvent e) {}; public void
	 * internalFrameDeiconified(InternalFrameEvent e){}; public void
	 * internalFrameActivated(InternalFrameEvent e) { //
	 * System.out.println("TRACE   ----- >  TIMER  STOP ");
	 * 
	 * 
	 * // timer.stop(); // setFrameIcon(image1);
	 * 
	 * }; public void internalFrameDeactivated(InternalFrameEvent e) {};
	 */

	class TimerCaption implements ActionListener {

		public void actionPerformed(ActionEvent evt) {

			if (count == 2) {
				count = count - 1;
				setFrameIcon(image2);
			} else if (count == 1) {
				count = count + 1;
				setFrameIcon(image1);
			}

		}

	}

	// ///////
	public Timestamp getSysDate() {
		Timestamp d;

		d = mvb.getSysDate();

		return d;

	}

	public int getIdSrv(String naim) {
		int result;
		if (naim.equals("не определено")) {

			result = 0;
		}
		result = mvb.getIdSrv(naim);

		return result;

	}

	public int getIdVpn(String naim) {
		int result;

		result = mvb.getVpnId(naim);

		return result;

	}

	public UndoTemp UndoTempProc(String tlf) {

		int errCode;

		resu = new UndoTemp();

		resu = mvb.undoRegistTemp(tlf);
		// System.out.println(" TRACE  START  -  findTv");

		return resu;

	}

	private DataInputDemand GetDataFromDemand(Timestamp start, int idsrv,
			int idvpn, String temp) throws InputDemandException {

		DataInputDemand in = new DataInputDemand();

		in.setTlf(demAdd1Panel.getTlf().trim());
		if (demAdd1Panel.getTlf().equals("")) {
			Itog = Itog + ErrorInputText.msgTlf + "\n";
		}

		in.setTempok(temp);
		if (temp.equals("Y")) {
			demAdd1Panel.button1.setEnabled(false);
			Itog = Itog + ErrorInputText.msgTempOk + "\n";
		}

		in.setEmail(demAdd2Panel.getEmail().trim()); // email
		if (demAdd2Panel.getEmail().equals("")) {
			Itog = Itog + ErrorInputText.msgEmail + "\n";
		}

		in.setFamdop(demAdd2Panel.getFamDov().trim()); // дов.лицо - фамилия
		if (demAdd2Panel.getFamDov().equals("")) {
			Itog = Itog + ErrorInputText.msgFamDov + "\n";
		}

		in.setImdop(demAdd2Panel.getImDov().trim()); // дов.лицо - имя
		if (demAdd2Panel.getImDov().equals("")) {
			Itog = Itog + ErrorInputText.msgImDov + "\n";
		}
		// System.out.println("TRACE   ----- >  4 ");
		in.setOtdop(demAdd2Panel.getOtDov().trim()); // дов.лицо - отчество
		if (demAdd2Panel.getOtDov().equals("")) {
			Itog = Itog + ErrorInputText.msgOtDov + "\n";
		}

		// System.out.println("TRACE  OtDov   ----- >  5 ");

		in.setNtdop(demAdd2Panel.getTlfDop().trim()); // доп. инф-я - телефон
		if (demAdd2Panel.getTlfDop().equals("")) {
			Itog = Itog + ErrorInputText.msgNtDop + "\n";
		}

		in.setFax(demAdd2Panel.getFax().trim()); // факс
		if (demAdd2Panel.getFax().equals("")) {
			Itog = Itog + ErrorInputText.msgFax + "\n";
		}

		in.setStartDate(start);

		in.setIpQuan((String) demAdd1Panel.cbmNetwIp.getSelectedItem());

		in.setFamOper(demAdd2Panel.getFamOper()); // фамилия оператора
		in.setImOper(demAdd2Panel.getImOper()); // имя оператора
		in.setOtOper(demAdd2Panel.getOtOper()); // отчество оператора


                                /*
		in.setFamResp(demAdd2Panel.getFamPred().trim()); // отв. представитель -
															// фамилия
		if (demAdd2Panel.getFamPred().equals("")) {
			Itog = Itog + ErrorInputText.msgFamResp + "\n";
		}

		in.setImResp(demAdd2Panel.getImPred().trim()); // отв. представитель -
														// имя
		if (demAdd2Panel.getImPred().equals("")) {
			Itog = Itog + ErrorInputText.msgImResp + "\n";
		}

		in.setOtResp(demAdd2Panel.getOtPred().trim()); // отв. представитель -
														// отчество
		if (demAdd2Panel.getOtPred().equals("")) {
			Itog = Itog + ErrorInputText.msgOtResp + "\n";
		}
                                    */
		try {
			in.setNetQ(demAdd2Panel.getCountKomp()); // кол-во комп. местной
														// сети
		} catch (NumberFormatException e) {
			Itog = Itog + ErrorInputText.msgCountKomp + "\n";
		}

		naimTarif = (String) demAdd1Panel.cbm.getSelectedItem();

		if (naimTarif.equals("не определено")) {
			throw new InputDemandException(ErrorInputText.msgTarif); // идент.
																		// тарифа
		} else {
			in.setIdSrv(idsrv); // идент. тарифа
		}

		System.out.println("TRACE  ID TARIF ----- >   " + idsrv);

		if (!Itog.equals("")) {

			throw new InputDemandException(Itog);

		}

		in.setMiniAts(demAdd2Panel.getMinAtsYes()); // имеется мини АТС
		in.setService(demAdd2Panel.getKontMinAts().trim()); // конт. инф-я мини
															// АТС
		in.setRegNet(demAdd2Panel.getNetYes()); // имеется лок.сеть
		in.setImNetOwn(demAdd2Panel.getKontInfNetw().trim()); // контактная ин-я
																// о владельце
																// сети
		in.setConFeat(demAdd1Panel.getDopLine().trim()); // особенности тел.
															// линии

		in.setNavUser(mvb.getUserName().trim()); // Код оператора
		in.setIdVpn(idvpn); // Выделенный доступ
		System.out.println("TRACE  ID VPN ----- >   " + idsrv);
		in.setAcs((String) demAdd1Panel.cbmAsc.getSelectedItem()); // технология
																	// доступа
		System.out.println("TRACE  ASC ----- >   " + in.getAcs());
		in.setInterf((String) demAdd1Panel.cbmInterf.getSelectedItem()); // интерфейс
																			// доступа
		System.out.println("TRACE  INTERF  ----- >   " + in.getInterf());

		in.setErrtxt(Itog);

		return in;

	}

	private String isRegistTempYes(String tlf) {
		String yes_ok;
		String result;
		yes_ok = mvb.getRegistTempYes(tlf);

		result = yes_ok;

		return result;

	}

	private void OutResult(DemandResult dt) {
		Integer dmnd;
		String errTxt;
		dmnd = dt.getDmnd_id();
		demAdd1Panel.dIdDemand.setText(dmnd.toString());

		if (dt.getStatus().equals("0")) {
			demAdd1Panel.dStatus.setText("первоначальное определение");

		} else if (dt.getStatus().equals("1")) {
			demAdd1Panel.dStatus.setText("временное бронирование");
		}

		errTxt = dt.getErrText1();
		System.out.println("----------TRACE OUTRESULT  ERRT  :"
				+ dt.getErrText1());

		if (errTxt != null) {
			demAdd1Panel.dDopRes.setText(dt.getErrText1());
		}

	}

	private void OutResultUndo(UndoTemp dt) {
		Integer dmnd;
		dmnd = dt.getDmnd_id();
		demAdd1Panel.dIdDemand.setText(dmnd.toString());

		demAdd1Panel.dStatus.setText(dt.getStatus());
		demAdd1Panel.dDopRes.setText(dt.getErrText());

	}

	public DemandResult RegistrDemTemp(String tlf, String email, String famdop,
			String imdop, String otdop, String ntdop, String fax,
			java.sql.Timestamp startDate, String miniAts, String service,
			int netQ, String regNet, String imNetOwn, String famResp,
			String imResp, String otResp, String conFeat, int idVpn,
			String acs, String interf, int idSrv, String ipQuan,
			String famOper, String imOper, String otOper, String navUser,
			java.sql.Timestamp createDate, String locNet, String sparType,
			String dop) {

		DemandResult result = new DemandResult();

		result = mvb.RegistDemand(tlf, email, famdop, imdop, otdop, ntdop, fax,
				startDate, miniAts, service, netQ, regNet, imNetOwn, famResp,
				imResp, otResp, conFeat, idVpn, acs, interf, idSrv, ipQuan,
				famOper, imOper, otOper, navUser, createDate, locNet, sparType,
				dop);

		return result;

	}

	public class WorkerSymData extends SwingWorker<DataFromRegist, Void> {
		DataFromRegist dt = new DataFromRegist();
		String okTemp;

		@Override
		protected DataFromRegist doInBackground() {

			if (!isCancelled()) {

				dt.setStart(getSysDate());
				dt.setIdsrv(getIdSrv((String) demAdd1Panel.cbm
						.getSelectedItem()));
				dt.setIdvpn(getIdVpn((String) demAdd1Panel.cbmVpn
						.getSelectedItem()));
				dt.setTemp(isRegistTempYes(demAdd1Panel.getTlf().trim()));
				return dt;
			}

			return dt;
		}

		@Override
		protected void done() {
			System.out.println("----------TRACE DONE SYSDATE  :"
					+ dt.getStart());
			System.out.println("----------TRACE DONE IDSRV  :" + dt.getIdsrv());
			System.out.println("----------TRACE DONE IDVPN  :" + dt.getIdvpn());
			System.out.println("----------TRACE DONE TEMP  :" + dt.getTemp());

			System.out.println("TRACE  SYM DATA  :" + mvb.getCountRequest());
			//
			if (dt.getTemp().equals("Y")) {

				sb.setMessage("Операция завершена");
				sb.m_runner.stopA();
				sb.timelbl.stop();
			}

			//

			try {
				inp = GetDataFromDemand(dt.getStart(), dt.getIdsrv(), dt
						.getIdvpn(), dt.getTemp());

				if (mvb.getCountRequest() != 0) {
					mvb.incCountRequest(1);
				}
				if (mvb.getCountRequest() == 0) {
					mvb.close();
				}
				System.out.println("----------TRACE   WORKED TEMP  REGISTR :"
						+ workerTemp);
				if (workerTemp == null) {

					mvb.setCountRequest(mvb.getCountRequest() + 1);

					(workerTemp = new WorkerRegistTemp()).execute();

				}

			} catch (InputDemandException e) {

				Messager.warning(null, " " + e.getMessage());
				Itog = "";
				workerSymData = null;

			}

			workerSymData = null;

		}
	}

	// ////////
	public class WorkerUndoTemp extends SwingWorker<UndoTemp, Void> {

		@Override
		protected UndoTemp doInBackground() {

			if (!isCancelled()) {

				resu = UndoTempProc(demAdd1Panel.dTlf.getText().trim());
				return resu;
			}

			return resu;
		}

		@Override
		protected void done() {
			try {

				OutResultUndo(resu);
				workerUndo = null;

				if (mvb.getCountRequest() != 0) {
					mvb.incCountRequest(1);
				}
				if (mvb.getCountRequest() == 0) {
					mvb.close();
				}

			} catch (Exception ignore) {
			}

		}

	}

	// ///////
	public class WorkerRegistTemp extends SwingWorker<DemandResult, Void> {
		DemandResult dt = new DemandResult();

		// String okTemp ;
		@Override
		protected DemandResult doInBackground() {

			if (!isCancelled()) {

				System.out
						.println("TRACE INPUT PARAM TLF                   ---->  :"
								+ "|" + inp.getTlf() + "|");
				System.out
						.println("TRACE INPUT PARAM EMAIL              ---->  :"
								+ "|" + inp.getEmail() + "|");
				System.out.println("TRACE INPUT PARAM FAMDOP         ---->  :"
						+ "|" + inp.getFamdop() + "|");
				System.out
						.println("TRACE INPUT PARAM IMDOP             ---->  :"
								+ "|" + inp.getImdop() + "|");
				System.out
						.println("TRACE INPUT PARAM OTDOP            ---->  :"
								+ "|" + inp.getOtdop() + "|");
				System.out
						.println("TRACE INPUT PARAM NTDOP             ---->  :"
								+ "|" + inp.getNtdop() + "|");
				System.out
						.println("TRACE INPUT PARAM FAX                   ---->  :"
								+ "|" + inp.getFax() + "|");
				System.out.println("TRACE INPUT PARAM STARTDATE  ---->  :"
						+ "|" + inp.getStartDate() + "|");
				System.out
						.println("TRACE INPUT PARAM  MINATS           ---->  :"
								+ "|" + inp.getMiniAts() + "|");
				System.out.println("TRACE INPUT PARAM SERVICE         ---->  :"
						+ "|" + inp.getService() + "|");
				System.out
						.println("TRACE INPUT PARAM NETQ -             --->  :"
								+ "|" + inp.getNetQ() + "|");
				System.out.println("TRACE INPUT PARAM REGNET         ---->  :"
						+ "|" + inp.getRegNet() + "|");
				System.out.println("TRACE INPUT PARAM IMNETOWN    ---->  :"
						+ "|" + inp.getImNetOwn() + "|");
				System.out.println("TRACE INPUT PARAM FAMRESP       ---->  :"
						+ "|" + inp.getFamResp() + "|");
				System.out
						.println("TRACE INPUT PARAM IMRESP           ---->  :"
								+ "|" + inp.getImResp() + "|");
				System.out.println("TRACE INPUT PARAM OTRESP          ---->  :"
						+ "|" + inp.getOtResp() + "|");
				System.out.println("TRACE INPUT PARAM CONFEAT       ---->  :"
						+ "|" + inp.getConFeat() + "|");
				System.out
						.println("TRACE INPUT PARAM IDVPN              ---->  :"
								+ "|" + inp.getIdVpn() + "|");
				System.out
						.println("TRACE INPUT PARAM ASC                  ---->  :"
								+ "|" + inp.getAcs() + "|");
				System.out
						.println("TRACE INPUT PARAM INTERF            ---->  :"
								+ "|" + inp.getInterf() + "|");
				System.out
						.println("TRACE INPUT PARAM IDSRV              ---->  :"
								+ "|" + inp.getIdSrv() + "|");
				System.out
						.println("TRACE INPUT PARAM IPQUAN           ---->  :"
								+ "|" + inp.getIpQuan() + "|");
				System.out.println("TRACE INPUT PARAM FAMOPER       ---->  :"
						+ "|" + inp.getFamOper() + "|");
				System.out
						.println("TRACE INPUT PARAM IMOPER           ---->  :"
								+ "|" + inp.getImOper() + "|");
				System.out.println("TRACE INPUT PARAM OTOPER          ---->  :"
						+ "|" + inp.getOtOper() + "|");
				System.out.println("TRACE INPUT PARAM NAVUSER      ---->  :"
						+ "|" + inp.getNavUser() + "|");
				System.out.println("TRACE INPUT PARAM CREATEDAT  ---->  :"
						+ "|" + inp.getCreateDate() + "|");
				System.out.println("TRACE INPUT PARAM LOCNET          ---->  :"
						+ "|" + inp.getLocNet() + "|");
				System.out
						.println("TRACE INPUT PARAM DOP                  ---->  :"
								+ "|" + inp.getDop() + "|");
				System.out
						.println("TRACE INPUT PARAM  TEMP                   ---->  :"
								+ "|" + inp.getTempok() + "|");

				System.out
						.println("TRACE  START REGISTR       TEMP_OK            ---->  :"
								+ "|" + inp.getTempok() + "|");
				if (inp.getTempok().equals("Y")) {
					sb.setMessage("Операция завершена");
				} else if (inp.getTempok().equals("N")) {

					dt = RegistrDemTemp(inp.getTlf().trim(), inp.getEmail(),
							inp.getFamdop(), inp.getImdop(), inp.getOtdop(),
							inp.getNtdop(), inp.getFax(), inp.getStartDate(),
							inp.getMiniAts(), inp.getService(), inp.getNetQ(),
							inp.getRegNet(), inp.getImNetOwn(), inp
									.getFamResp(), inp.getImResp(), inp
									.getOtResp(), inp.getConFeat(), inp
									.getIdVpn(), inp.getAcs(), inp.getInterf(),
							inp.getIdSrv(), inp.getIpQuan(), inp.getFamOper(),
							inp.getImOper(), inp.getOtOper(), inp.getNavUser(),
							inp.getCreateDate(), inp.getLocNet(), inp
									.getSparType(), inp.getDop());
				}

				/*
				 * ( "5282383", "inser@mail" ,"петров " , "петр " , "петрович "
				 * , "223322", "2222" , dt ,"" ,"Конт.инф.обсл.миниАТС" , 2 ,"",
				 * "" , "петров" ,"петр" ,"петрович" ,
				 * "ОПИСАНИЕ ОСОБЕННОСТЕЙ ПОДКЛЮЧЕНИЯ" , 1 , "ADSL", "Ethernet",
				 * 8 , "" , "ФАМИЛ-ОПЕРАТОРА" ,"ИМ-ОПЕРАТОРА" , "ОТ-ОПЕРАТОРА",
				 * "u0230380" ,dt , "" , "Квартирный ЛН" , "Доп.информация" ) ;
				 */

				return null;
			}

			return dt;
		}

		@Override
		protected void done() {

			System.out.println("TRACE  DONE TEMP RESERV ---->  :"
					+ mvb.getCountRequest());

			if (mvb.getCountRequest() != 0) {
				mvb.incCountRequest(1);
			}
			if (mvb.getCountRequest() == 0) {
				mvb.close();
			}

			OutResult(dt);

			sb.setMessage("Операция завершена");

			sb.m_runner.stopA();
			sb.timelbl.stop();

			workerTemp = null;

		}
	}

} // /