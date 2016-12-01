package com.hummingbee.utils;

import java.time.LocalDate;

public class Formatter {
	public static String dateLabelFormatter(LocalDate date) {
		return "System Date: " + date;
	}
	
	public static String minThresholdFormatter(double threshold) {
		return "Min Threshold: " + threshold + " ºF";
	}
	
	public static String maxThresholdFormatter(double threshold) {
		return "Max Threshold: " + threshold + " ºF";
	}
	
	public static String getTemperatureFormatter(double degrees) {
		return "Temperature: " + degrees + " ºF";
	}
}
