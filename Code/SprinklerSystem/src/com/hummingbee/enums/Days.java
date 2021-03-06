package com.hummingbee.enums;

/**
 * The following enum is for the 7 calendar days.
 * 
 * @author jimmypatel
 *
 */
public enum Days {
	SUNDAY("Sunday"),
	MONDAY("Monday"),
	TUESDAY("Tuesday"),
	WEDNESDAY("Wednesday"),
	THURSDAY("Thursday"),
	FRIDAY("Friday"),
	SATURDAY("Saturday");	
	
	private String dayString;
	
	
	private Days(String str){
		this.dayString = str;
	}
	
	@Override
	public String toString(){
		return dayString;
	}
	
	public static Days fromString(String text) {
	    if (text != null) {
	      for (Days b : Days.values()) {
	        if (text.equalsIgnoreCase(b.toString())) {
	          return b;
	        }
	      }
	    }
	    return null;
	  }
}
