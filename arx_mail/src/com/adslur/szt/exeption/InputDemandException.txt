package com.adslur.szt.exeption;

public class InputDemandException extends Exception {
	public InputDemandException() {
		super();
	}

	public InputDemandException(String msg) {
		super(msg);
		this.msg = msg;

	}

	public String getMessage(String msg) {
		return this.msg;
	}

	String msg;
}