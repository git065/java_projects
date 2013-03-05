package com.adslur.szt.domen;

import java.util.Date;

public class DemandResult {

	public int getDmnd_id() {
		return dmnd_id;
	}
	public void setDmnd_id(int dmndId) {
		dmnd_id = dmndId;
	}
	public String getEquip() {
		return equip;
	}
	public void setEquip(String equip) {
		this.equip = equip;
	}
	public String getUvo() {
		return uvo;
	}
	public void setUvo(String uvo) {
		this.uvo = uvo;
	}
	public String getDz() {
		return dz;
	}
	public void setDz(String dz) {
		this.dz = dz;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSost() {
		return sost;
	}
	public void setSost(String sost) {
		this.sost = sost;
	}
	public int getErrCode1() {
		return errCode1;
	}
	public void setErrCode1(int errCode1) {
		this.errCode1 = errCode1;
	}
	public String getErrText1() {
		return errText1;
	}
	public void setErrText1(String errText1) {
		this.errText1 = errText1;
	}
	public int getErrCode2() {
		return errCode2;
	}
	public void setErrCode2(int errCode2) {
		this.errCode2 = errCode2;
	}
	public String getErrText2() {
		return errText2;
	}
	public void setErrText2(String errText2) {
		this.errText2 = errText2;
	}
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

}
