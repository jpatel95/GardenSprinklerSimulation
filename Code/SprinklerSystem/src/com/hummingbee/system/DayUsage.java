package com.hummingbee.system;

import java.io.Serializable;
import java.time.LocalDate;

public class DayUsage implements Serializable {
	// data members
	private LocalDate day;
	private double usage;
	
	public DayUsage() {
		day = null;
		usage = 0;
	}
	
	public DayUsage(LocalDate day) {
		this.day = day;
		usage = 0;
	}
	
	public DayUsage(LocalDate day, double usage) {
		this.day = day;
		this.usage = usage;
	}
	
	public void addUsage(double usage) {
		this.usage += usage;
	}
	
	public double getUsage() {
		return usage;
	}
	
	public LocalDate getDay() {
		return day;
	}
}
