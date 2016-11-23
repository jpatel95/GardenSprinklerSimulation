package com.hummingbee.enums;

/**
 * The following enum is for the status of the sprinklers.
 * 
 * @author jimmypatel
 *
 */
public enum Status {
	OK("OK"),
	NOTOK("NOTOK"),
	ON("ON"),
	NOTON("NOTON");
	
	private String curStatus;
	
	private Status(String str){
		this.curStatus = str;
	}
	
	@Override
	public String toString(){
		return curStatus;
	}
}
