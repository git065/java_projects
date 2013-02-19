package com.adslur.szt.domen;

public class UndoTemp {

	public int getDmnd_id() {
		return dmnd_id;
	}

	public void setDmnd_id(int dmndId) {
		dmnd_id = dmndId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public String getErrText() {
		return errText;
	}

	public void setErrText(String errText) {
		this.errText = errText;
	}

	int dmnd_id;
	String status;
	int errCode;
	String errText;

}
