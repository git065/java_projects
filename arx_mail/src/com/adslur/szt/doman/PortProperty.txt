package com.adslur.szt.domen;

import java.util.Date;

public class PortProperty {
	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCountLiberty() {
		return countLiberty;
	}

	public void setCountLiberty(int countLiberty) {
		this.countLiberty = countLiberty;
	}

	public Date getPlanInst() {
		return planInst;
	}

	public void setPlanInst(Date planInst) {
		this.planInst = planInst;
	}

	int count;
	int countLiberty;
	Date planInst;
	int errCode;
}
