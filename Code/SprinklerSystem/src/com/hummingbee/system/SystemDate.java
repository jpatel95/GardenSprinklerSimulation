package com.hummingbee.system;

import java.time.LocalDate;

/**
 * Date of the sprinkler system. Necessary for simulation purposes
 * @author Nick
 *
 */
public class SystemDate {
	private static LocalDate date = null;
	
	public static LocalDate getDate() {
		if (date == null) {
			date = LocalDate.now();
		}
		
		return date;
	}
	
	public static void addDays(int days) {
		if (date == null) {
			date = LocalDate.now();
		}
		
		date = date.plusDays(days);
	}
	
	public static void subtractDays(int days) {
		if (date == null) {
			date = LocalDate.now();
		}
		
		date = date.minusDays(days);
	}
}
