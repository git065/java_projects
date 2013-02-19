package com.adslur.szt.domen;

import java.sql.Timestamp;

import com.jgoodies.binding.beans.Model;


public class DataInputDemand     extends Model {
       public static final String PROPERTYNAME_TLF       = "tlf";
       public static final String PROPERTYNAME_TLF_DOP       = "ntdop";
       public static final String PROPERTYNAME_FAX      = "fax";
       public static final String PROPERTYNAME_EMAIL      = "email";
       public static final String PROPERTYNAME_FAM_DOP      = "famdop";
       public static final String PROPERTYNAME_IM_DOP      = "imdop";
       public static final String PROPERTYNAME_OT_DOP      = "otdop";
 



	public String getTlf() {
		return tlf;
	}

                    public void setTlf(String tlf) {
		String oldValue = getTlf();
                                       this.tlf = tlf;
                                       firePropertyChange(PROPERTYNAME_TLF, oldValue, tlf);
	}

   

	public String getEmail() {
		return email;
	}

	
                    public void setEmail(String email)  {
		String oldValue =  getEmail();
                                      this.email = email;
                                       firePropertyChange(PROPERTYNAME_EMAIL , oldValue, email);
	}

	public String getFamdop() {
		return famdop;
	}

	
                    public void setFamdop(String famdop)  {
		String oldValue =  getFamdop();
                                       this.famdop = famdop;
                                        firePropertyChange(PROPERTYNAME_FAM_DOP , oldValue, famdop);
	}



	public String getImdop() {
		return imdop;
	}

	 public void setImdop(String imdop)  {
		String oldValue =  getImdop();
                                        this.imdop = imdop;
                                        firePropertyChange(PROPERTYNAME_IM_DOP , oldValue, imdop);
	}

	public String getOtdop() {
		return otdop;
	}

	

                   public void setOtdop(String otdop) {
		String oldValue =  getOtdop();
                                        this.otdop = otdop;
                                        firePropertyChange(PROPERTYNAME_OT_DOP , oldValue, otdop);
	}


	public String getNtdop() {
		return ntdop;
	}

	

                    public void setNtdop(String ntdop) {
		String oldValue = getNtdop();
                                        this.ntdop = ntdop;
                                        firePropertyChange(PROPERTYNAME_TLF_DOP, oldValue, ntdop);
	}


	public String getFax() {
		return fax;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	
                     public void setFax(String fax) {
		String oldValue = getFax();
                                       this.fax = fax;
                                        firePropertyChange(PROPERTYNAME_FAX, oldValue, fax);
	}

	public String getMiniAts() {
		return miniAts;
	}

	public void setMiniAts(String miniAts) {
		this.miniAts = miniAts;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public int getNetQ() {
		return netQ;
	}

	public void setNetQ(int netQ) {
		this.netQ = netQ;
	}

	public String getRegNet() {
		return regNet;
	}

	public void setRegNet(String regNet) {
		this.regNet = regNet;
	}

	public String getImNetOwn() {
		return imNetOwn;
	}

	public void setImNetOwn(String imNetOwn) {
		this.imNetOwn = imNetOwn;
	}

	public String getFamResp() {
		return famResp;
	}

	public void setFamResp(String famResp) {
		this.famResp = famResp;
	}

	public String getImResp() {
		return imResp;
	}

	public void setImResp(String imResp) {
		this.imResp = imResp;
	}

	public String getOtResp() {
		return otResp;
	}

	public void setOtResp(String otResp) {
		this.otResp = otResp;
	}

	public String getConFeat() {
		return conFeat;
	}

	public void setConFeat(String conFeat) {
		this.conFeat = conFeat;
	}

	public int getIdVpn() {
		return idVpn;
	}

	public void setIdVpn(int idVpn) {
		this.idVpn = idVpn;
	}

	public String getAcs() {
		return acs;
	}

	public void setAcs(String acs) {
		this.acs = acs;
	}

	public String getInterf() {
		return interf;
	}

	public void setInterf(String interf) {
		this.interf = interf;
	}

	public int getIdSrv() {
		return idSrv;
	}

	public void setIdSrv(int idSrv) {
		this.idSrv = idSrv;
	}

	public String getIpQuan() {
		return ipQuan;
	}

	public void setIpQuan(String ipQuan) {
		this.ipQuan = ipQuan;
	}

	public String getFamOper() {
		return famOper;
	}

	public void setFamOper(String famOper) {
		this.famOper = famOper;
	}

	public String getImOper() {
		return imOper;
	}

	public void setImOper(String imOper) {
		this.imOper = imOper;
	}

	public String getOtOper() {
		return otOper;
	}

	public void setOtOper(String otOper) {
		this.otOper = otOper;
	}

	public String getNavUser() {
		return navUser;
	}

	public void setNavUser(String navUser) {
		this.navUser = navUser;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getLocNet() {
		return locNet;
	}

	public void setLocNet(String locNet) {
		this.locNet = locNet;
	}

	public String getSparType() {
		return sparType;
	}

	public void setSparType(String sparType) {
		this.sparType = sparType;
	}

	public String getDop() {
		return dop;
	}

	public void setDop(String dop) {
		this.dop = dop;
	}

	public String getTempok() {
		return tempok;
	}

	public void setTempok(String tempok) {
		this.tempok = tempok;
	}

	public String getErrtxt() {
		return errtxt;
	}

	public void setErrtxt(String errtxt) {
		this.errtxt = errtxt;
	}

	String tlf;
	String email;
	String famdop;
	String imdop;
	String otdop;
	String ntdop;

	String fax;
	Timestamp startDate;
	String miniAts;
	String service;
	int netQ;
	String regNet;

	String imNetOwn;
	String famResp;
	String imResp;
	String otResp;
	String conFeat;
	int idVpn;
	String acs;
	String interf;
	int idSrv;
	String ipQuan;
	String famOper;
	String imOper;
	String otOper;

	String navUser;
	Timestamp createDate;
	String locNet;
	String sparType;
	String dop;
	String tempok;
	String errtxt;

}