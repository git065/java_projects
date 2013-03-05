package com.adslur.szt.jdbc;

import javax.sql.DataSource;
import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.text.*;
import java.util.*;

import com.adslur.szt.domen.AbonentUr;
import com.adslur.szt.domen.PortProperty;
import com.adslur.szt.domen.DemandResult;
import com.adslur.szt.domen.OperFio;
import com.adslur.szt.domen.UndoTemp;

import com.adslur.szt.utils.Messager;

public class MvbOracleConnection {
	private static MvbOracleConnection _mvb = null;
	Connection con = null;

	protected boolean driverLoaded = false;
	String username;
	String password;
	static String url = "jdbc:oracle:thin:@192.168.219.89:1521:testdb4";
	volatile int countRequest = 0;

	public synchronized int getCountRequest() {
		return this.countRequest;

	}

	public synchronized void setCountRequest(int countRequest) {
		this.countRequest = countRequest;

	}

	public synchronized void addCountRequest(int countRequest) {
		this.countRequest = this.countRequest + countRequest;

	}

	public synchronized void incCountRequest(int countRequest) {
		this.countRequest = this.countRequest - countRequest;

	}

	public String getUserName() {
		return this.username;

	}

	public void setUserName(String username) {
		this.username = username;

	}

	public String getPassword() {
		return this.password;

	}

	public void setPassword(String password) {
		this.password = password;

	}

	public static synchronized MvbOracleConnection getInstance() {
		if (_mvb == null) {
			_mvb = new MvbOracleConnection();

		}

		return _mvb;
	}

	public boolean connect(String username, String password) {
		try {

			if (!driverLoaded) {
				DriverManager
						.registerDriver(new oracle.jdbc.driver.OracleDriver());
				driverLoaded = true;
			}

			con = DriverManager.getConnection(url, username, password);

			close();

			return true;
		} catch (SQLException ex) {
			return false;
		}
	}

	public Connection connect(String url, String username, String password) {
		Connection connection;
		try {

			if (!driverLoaded) {
				DriverManager
						.registerDriver(new oracle.jdbc.driver.OracleDriver());
				driverLoaded = true;
			}
			// System.out.println("TRACE  connect  <--- > :" + url +":" +
			// username +":" + password);
			connection = DriverManager.getConnection(url, username, password);

			connection.setAutoCommit(false);

			return connection;
		} catch (SQLException ex) {
			return null;
		}
	}

	public Connection getConnection() {
		// System.out.println("TRACE  getConnection1  <--- > :");
		if (con == null) {
			con = connect(url, getUserName(), getPassword());

			// System.out.println("TRACE  getConnection  <--- > :"+con );
		}
		return con;
	}

	public boolean isDriverLoaded() {
		return driverLoaded;
	}

	public void close() {
		try {
			if (con != null) {
				// System.out.println("TRACE  CLOSE   <--- > :");
				con.close();
				con = null;

			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("TRACE  CLOSE   EXEPT <--- > :" + e.toString());
		}
	}

	public ArrayList getTarifList() {

		ArrayList naim = new ArrayList();
		Statement s = null;
		ResultSet rs = null;
		try {

			con = _mvb.getConnection();

			s = con.createStatement();
			String sql = " select t.name_sh  f1 from dtd.srv_tbl t  "
					+ " where t.name_sh like " + "'" + "%CORP%" + "'"
					+ " order by t.id_srv ";

			rs = s.executeQuery(sql);

			while (rs.next()) {

				naim.add(rs.getString("f1"));
				// System.out.println("TRACE  NAIM TARIF  --- > :"+
				// rs.getString("f1"));
			}

			if (rs != null)
				rs.close();
			if (s != null)
				s.close();

		} catch (SQLException e) {

			System.out.println("TRACE SQLException:" + e.getMessage());
			// JOptionPane.showMessageDialog(null, e.getMessage( ),
			// "Ошибка ввода списка тарифов", JOptionPane.ERROR_MESSAGE);
			Messager.exception(null, "Ошибка вывода списка тарифов :"
					+ e.getMessage() + "\n" + "код ошибки  : "
					+ e.getErrorCode() + "\n" + " состояние : "
					+ e.getSQLState());
		}
		return naim;

	}

	public AbonentUr getReqFromIbs(String tlf) {

		AbonentUr ab = null;
		Statement s = null;
		ResultSet rs = null;
		try {
			con = _mvb.getConnection();

			s = con.createStatement();

			String sql = " select t.ndog f1 , t.account  f2 , t.inn f3 , t.naim f4 , t.adrust f5  "
					+ "  from table( inqdb.get_clnt_nt_ur("
					+ "'"
					+ tlf
					+ "'"
					+ ")) t";

			rs = s.executeQuery(sql);

			while (rs.next()) {
				ab = new AbonentUr();
				ab.numDgv = rs.getString("f1");

				ab.numNls = rs.getString("f2");
				ab.numInn = rs.getString("f3");
				ab.naimOrg = rs.getString("f4");
				ab.adrUst = rs.getString("f5");

			}
			if (rs != null)
				rs.close();
			if (rs != null)
				s.close();

		} catch (SQLException e) {
			System.out.println("select  ur");
			System.out.println(e.toString());
		}
		return ab;

	}

	// /////////////////////////

	public PortProperty findTV(String tlf) {
		PortProperty tv;
		String aString;
		int aNumber1 = 0;

		int aCountPort = 0;
		int aCountLibPort = 0;
		java.util.Date a_PlanInstal = null;
		String s_PlanInstal = null;
		String aName = null;
		String aNameSh = null;
		int aErrCode = 0;

		SimpleDateFormat sdf1 = new SimpleDateFormat("dd.MM.yyyy ");
		aString = tlf;
		tv = new PortProperty();
		// Connection conn = null;
		CallableStatement cstmt = null;
		try {
			/*
			 * con = _mvb.openConnection();
			 * 
			 * cstmt =
			 * con.prepareCall("{ ? = call  inqdb.DISUB_UTILS_STU.findPort( ? ) }"
			 * ); cstmt.registerOutParameter(1, Types.INTEGER);
			 * cstmt.setString(2, aString); cstmt.execute( ); aNumber1 =
			 * cstmt.getInt(1); if (cstmt.wasNull( )) aNumber1 = 0;
			 * System.out.println( " converted to a number = " +aNumber1);
			 * 
			 * if (cstmt != null) cstmt.close( ); if (_mvb != null)
			 * _mvb.close2(); con = null ;
			 */
			con = _mvb.getConnection();

			cstmt = con
					.prepareCall("{ call inqdb.DISUB_UTILS_STU.findEquip(?,?,?,?,?,?,?)}");
			cstmt.registerOutParameter(2, Types.INTEGER);
			cstmt.registerOutParameter(3, Types.INTEGER);
			cstmt.registerOutParameter(4, Types.DATE);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.VARCHAR);
			cstmt.registerOutParameter(7, Types.INTEGER);
			cstmt.setString(1, aString);
			cstmt.execute();
			// aCountPort = new Double(cstmt.getDouble(2));
			aCountPort = cstmt.getInt(2);

			aCountLibPort = cstmt.getInt(3);
			a_PlanInstal = cstmt.getDate(4);

			aName = cstmt.getString(5);
			aNameSh = cstmt.getString(6);
			aErrCode = cstmt.getInt(7);
			tv.setCount(aCountPort);
			tv.setCountLiberty(aCountLibPort);
			tv.setPlanInst(a_PlanInstal);
			tv.setErrCode(aErrCode);

			System.out.println(" cont port  = " + aCountPort);
			System.out.println(" cont  liberty port  = " + aCountLibPort);
			if (a_PlanInstal != null)
				s_PlanInstal = sdf1.format(a_PlanInstal);
			else if (a_PlanInstal == null)
				s_PlanInstal = "31.12.2999";
			System.out.println(" plan instal  = " + s_PlanInstal);
			System.out.println("name  = " + aName);
			System.out.println(" nwme_sh = " + aNameSh);
			System.out.println("err code  = " + aErrCode);

			if (cstmt != null)
				cstmt.close();
			/*
			 * if (_mvb != null){
			 * 
			 * _mvb.close2();
			 * 
			 * 
			 * }
			 */

		} catch (SQLException e) {
			System.err.println("SQL Error: " + e.getMessage());
			System.out.println("TRACE SQL EXC" + e.getMessage());

		}
		return tv;

	}

	public java.sql.Timestamp getSysDate() {

		java.sql.Timestamp result = null;
		Statement s = null;
		ResultSet rs = null;
		try {

			con = _mvb.getConnection();

			s = con.createStatement();
			String sql = " select sysdate  -  0.00069  f1 from dual  ";

			rs = s.executeQuery(sql);
			rs.next();
			result = rs.getTimestamp("f1");

			System.out.println("TRACE  SYSDATE  --- > :" + result);

			if (rs != null)
				rs.close();
			if (s != null)
				s.close();

		} catch (SQLException e) {

			System.out.println("TRACE SQLException:" + e.getMessage());

		}
		return result;

	}

	// ////////////

	public DemandResult RegistDemand(String tlf, String email, String famdop,
			String imdop, String otdop, String ntdop, String fax,
			java.sql.Timestamp start, String miniAts, String service, int netQ,
			String regNet, String imNetOwn, String famResp, String imResp,
			String otResp, String conFeat, int idVpn, String acs,
			String interf, int idSrv, String ipQuan, String famOper,
			String imOper, String otOper, String navUser,
			java.sql.Timestamp createDate, String locNet, String sparType,
			String dop) {
		DemandResult res;
		int dmnd_id;
		String equip;
		String uvo;
		String dz;
		String status;
		String sost;
		int errCode1;
		String errText1;
		int errCode2;
		String errText2;

		res = new DemandResult();
		System.out.println(" TRACE  PROCEDURE 1");
		CallableStatement cstmt = null;
		try {
			System.out.println(" TRACE  PROCEDURE 2");
			con = _mvb.getConnection();
			System.out.println(" TRACE  PROCEDURE 3");
			cstmt = con
					.prepareCall("{ call inqdb.CYZADSLUR_PK.RegistDemandUr( ?, ? , ? , ? , ?, ? , ? , ? ,? ,? ,? ,? , ? , ? ,?,? , ? , ? ,? , ? , ?, ?, ? ,? ,? , ? , ? , ?, ?,?,?, ?, ? ,? ,? , ? , ? , ?, ?,?)}");

			cstmt.registerOutParameter(31, Types.INTEGER);
			cstmt.registerOutParameter(32, Types.VARCHAR);
			cstmt.registerOutParameter(33, Types.VARCHAR);
			cstmt.registerOutParameter(34, Types.VARCHAR);
			cstmt.registerOutParameter(35, Types.VARCHAR);
			cstmt.registerOutParameter(36, Types.VARCHAR);
			cstmt.registerOutParameter(37, Types.INTEGER);
			cstmt.registerOutParameter(38, Types.VARCHAR);
			cstmt.registerOutParameter(39, Types.INTEGER);
			cstmt.registerOutParameter(40, Types.VARCHAR);

			cstmt.setString(1, tlf);
			cstmt.setString(2, email);
			cstmt.setString(3, famdop);
			cstmt.setString(4, imdop);
			cstmt.setString(5, otdop);
			cstmt.setString(6, ntdop);

			cstmt.setString(7, fax);
			cstmt.setTimestamp(8, start);
			cstmt.setString(9, miniAts);
			cstmt.setString(10, service);
			cstmt.setInt(11, netQ);
			cstmt.setString(12, regNet);

			cstmt.setString(13, imNetOwn);
			cstmt.setString(14, famResp);
			cstmt.setString(15, imResp);
			cstmt.setString(16, otResp);
			cstmt.setString(17, conFeat);
			cstmt.setInt(18, idVpn);

			cstmt.setString(19, acs);
			cstmt.setString(20, interf);
			cstmt.setInt(21, idSrv);
			cstmt.setString(22, ipQuan);
			cstmt.setString(23, famOper);
			cstmt.setString(24, imOper);
			cstmt.setString(25, otOper);

			cstmt.setString(26, navUser);
			cstmt.setTimestamp(27, createDate);
			cstmt.setString(28, locNet);
			cstmt.setString(29, sparType);
			cstmt.setString(30, dop);

			cstmt.execute();

			dmnd_id = cstmt.getInt(31);
			System.out.println("TRACE  DMNDID  = " + dmnd_id);
			equip = cstmt.getString(32);
			System.out.println(" TRACE  EQUIP  = " + equip);
			uvo = cstmt.getString(33);
			System.out.println("TRACE  UVO   = " + uvo);
			dz = cstmt.getString(34);
			System.out.println(" TRACE  DZ = " + dz);
			status = cstmt.getString(35);
			System.out.println(" TRACE  STATUS = " + status);
			sost = cstmt.getString(36);
			System.out.println(" TRACE SOST  = " + sost);
			errCode1 = cstmt.getInt(37);
			System.out.println(" TRACE  errCode1  = " + errCode1);
			errText1 = cstmt.getString(38);
			System.out.println(" TRACE errText1  = " + errText1);
			errCode2 = cstmt.getInt(39);
			System.out.println(" TRACE  errCode2 = " + errCode2);
			errText2 = cstmt.getString(40);
			System.out.println(" TRACE  errText2 = " + errText2);
			res.setDmnd_id(dmnd_id);
			res.setEquip(equip);
			res.setUvo(uvo);
			res.setDz(dz);
			res.setStatus(status);
			res.setSost(sost);
			res.setErrCode1(errCode1);
			res.setErrText1(errText1);
			res.setErrCode2(errCode2);
			res.setErrText2(errText2);

			if (cstmt != null)
				cstmt.close();

		} catch (SQLException e) {

			System.out.println("TRACE SQL EXC" + e.getMessage());

		}

		return res;

	}

	public int getIdSrv(String naim) {

		int result = 0;
		Statement s = null;
		ResultSet rs = null;
		try {

			con = _mvb.getConnection();

			s = con.createStatement();
			String sql = " select  t.id_srv  f1  from dtd.srv_tbl t  where  t.name_sh =  "
					+ "'" + naim + "'";

			rs = s.executeQuery(sql);
			rs.next();
			result = rs.getInt("f1");

			if (rs != null)
				rs.close();
			if (s != null)
				s.close();

		} catch (SQLException e) {

			System.out.println("TRACE SQLException:" + e.getMessage());
			Messager.exception(null,
					"Ошибка получения идентификаторора  тарифа :"
							+ e.getMessage() + "\n" + "код ошибки  : "
							+ e.getErrorCode() + "\n" + " состояние : "
							+ e.getSQLState());
		}

		return result;

	}

	public final static void main(String args[]) {
		boolean drv = false;
		MvbOracleConnection mvb = MvbOracleConnection.getInstance();

		/*
		 * System.out.println("TRACE EXECUTE 1"); mvb.executen("");
		 * System.out.println("TRACE EXECUTE 2"); mvb.executen("");
		 */

		mvb.connect("inqdb", "pfzdrb");
		mvb.getTarifList();
	}

	// /////////////
	public ArrayList getVpnList() {

		ArrayList naim = new ArrayList();
		Statement s = null;
		ResultSet rs = null;
		try {

			con = _mvb.getConnection();

			s = con.createStatement();
			String sql = "select t.name_sh   f1  from dtd.vpn_tbl t  order by t.id_vpn";

			rs = s.executeQuery(sql);

			while (rs.next()) {

				naim.add(rs.getString("f1"));

			}

			if (rs != null)
				rs.close();
			if (s != null)
				s.close();

		} catch (SQLException e) {

			System.out.println("TRACE SQLException:" + e.getMessage());
			// JOptionPane.showMessageDialog(null, e.getMessage( ),
			// "Ошибка ввода списка тарифов", JOptionPane.ERROR_MESSAGE);
			Messager.exception(null,
					"Ошибка вывода списка  выделенного доступа :"
							+ e.getMessage() + "\n" + "код ошибки  : "
							+ e.getErrorCode() + "\n" + " состояние : "
							+ e.getSQLState());
		}
		return naim;

	}

	public int getVpnId(String naim) {

		int result = 0;
		Statement s = null;
		ResultSet rs = null;
		try {

			con = _mvb.getConnection();

			s = con.createStatement();
			String sql = " select t.id_vpn f1   from dtd.vpn_tbl t "
					+ "  where t.name_sh =  " + "'" + naim + "'";

			rs = s.executeQuery(sql);
			rs.next();

			result = rs.getInt("f1");

			if (rs != null)
				rs.close();
			if (s != null)
				s.close();

		} catch (SQLException e) {

			System.out.println("TRACE SQLException:" + e.getMessage());

			Messager.exception(null,
					"Ошибка вывода идентификатора  выделенного доступа :"
							+ e.getMessage() + "\n" + "код ошибки  : "
							+ e.getErrorCode() + "\n" + " состояние : "
							+ e.getSQLState());
		}
		return result;

	}

	public OperFio getOperFio(String login) {

		OperFio ab = null;
		Statement s = null;
		ResultSet rs = null;
		try {
			con = _mvb.getConnection();

			s = con.createStatement();

			String sql = "  select t.fam f1    ,t.im   f2 ,t.ot   f3  from inqdb.demands_operators t  "
					+ "  where lower(t.id_oper) ="
					+ "'"
					+ login
					+ "'"
					+ " and      t.start_date < sysdate "
					+ "and      t.end_date >= sysdate";

			rs = s.executeQuery(sql);

			while (rs.next()) {
				ab = new OperFio();
				ab.setFam(rs.getString("f1"));
				System.out.println("TRACE FIO -:" + rs.getString("f1"));
				ab.setIm(rs.getString("f2"));
				ab.setOt(rs.getString("f3"));

			}
			if (rs != null)
				rs.close();
			if (rs != null)
				s.close();

		} catch (SQLException e) {
			System.out.println("select  ur");
			System.out.println(e.toString());
		}
		return ab;

	}

	public String getRegistTempYes(String tlf) {

		String result = "N";
		String msisdn;
		Statement s = null;
		ResultSet rs = null;
		try {

			con = _mvb.getConnection();

			s = con.createStatement();
			String sql = "  select    a.msisdn   f1 from inqdb.demands_ur a  "
					+ "  where    a.start_date < sysdate  "
					+ "  and      a.end_date >= sysdate  "
					+ "  and      a.dems_dems_id = 1  "
					+ "  and      a.msisdn  = " + "'" + tlf + "'";
			System.out.println("TRACE EXECUTE  FIND TEMP SQL <--- > :" + sql);

			rs = s.executeQuery(sql);
			while (rs.next()) {

				msisdn = rs.getString("f1");
				System.out.println("TRACE EXECUTE  FIND TEMP msisdn <--- > :"
						+ msisdn);
				result = "Y";
			}
			if (rs != null)
				rs.close();
			if (s != null)
				s.close();

		} catch (SQLException e) {

			System.out.println("TRACE SQLException:" + e.getMessage());

		}
		System.out
				.println("TRACE EXECUTE  FIND TEMP REGISTR <--- > :" + result);
		return result;

	}

	public UndoTemp undoRegistTemp(String tlf) {
		UndoTemp res;
		int ErrCode = 0;
		res = new UndoTemp();

		CallableStatement cstmt = null;

		try {

			con = _mvb.getConnection();

			cstmt = con
					.prepareCall("{ call inqdb.CYZADSLUR_PK .UndoReservTempDemandUr(?,?,?,?,?)}");
			cstmt.registerOutParameter(2, Types.INTEGER);
			cstmt.registerOutParameter(3, Types.VARCHAR);
			cstmt.registerOutParameter(4, Types.INTEGER);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.setString(1, tlf);
			cstmt.execute();

			res.setDmnd_id(cstmt.getInt(2));
			System.out.println(" TRACE  ECESUTE UNDO TEMP DMNDID = "
					+ res.getDmnd_id());
			res.setStatus(cstmt.getString(3));
			System.out.println(" TRACE  ECESUTE UNDO TEMP STATUS = "
					+ res.getDmnd_id());
			res.setErrCode(cstmt.getInt(4));
			System.out.println(" TRACE  ECESUTE UNDO TEMP ERRC= "
					+ res.getErrCode());
			res.setErrText(cstmt.getString(5));
			System.out.println(" TRACE  ECESUTE UNDO TEMP ERRTXT = "
					+ res.getErrText());

			if (cstmt != null)
				cstmt.close();

		} catch (SQLException e) {
			System.err.println("SQL Error: " + e.getMessage());
			System.out.println("TRACE SQL EXC" + e.getMessage());
			Messager.exception(null,
					"Ошибка при разбронировании оборудования :"
							+ e.getMessage() + "\n" + "код ошибки  : "
							+ e.getErrorCode() + "\n" + " состояние : "
							+ e.getSQLState());
		}
		return res;

	}

}
