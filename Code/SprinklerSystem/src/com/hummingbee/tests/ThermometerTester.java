package com.hummingbee.tests;

import com.hummingbee.system.Thermometer;


public class ThermometerTester {
	public static void main(String[] args) {
		testThermometer();
	}
	
	public static void testThermometer(){
		Thermometer t1 = new Thermometer();
		System.out.println(t1);
		t1.decrementTemperature();
		System.out.println(t1);
		t1.incrementTemperature();
		System.out.println(t1);
		t1.setTemperature(100.0);
		System.out.println(t1);
		t1.setMaxThreshold(120.0);
		System.out.println(t1);
		t1.setMinThreshold(110.0);
		System.out.println(t1);
	}
}
