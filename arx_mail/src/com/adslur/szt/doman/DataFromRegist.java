package com.adslur.szt.domen;

import java.sql.Timestamp;

public class DataFromRegist {

	Timestamp start;
	int idsrv;
	int idvpn;
	String temp;

	public void setStart(Timestamp start) {
		this.start = start;
	}

	public Timestamp getStart() {
		return start;
	}

	public void setIdsrv(int idsrv) {
		this.idsrv = idsrv;
	}

	public int getIdsrv() {
		return idsrv;
	}

	public void setIdvpn(int idvpn) {
		this.idvpn = idvpn;
	}

	public int getIdvpn() {
		return idvpn;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getTemp() {
		return temp;
	}

}