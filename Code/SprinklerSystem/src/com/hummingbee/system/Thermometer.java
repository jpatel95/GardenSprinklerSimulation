package com.hummingbee.system;

/**
 * The following class is a class that will be used to maintain the ambient temperature
 * for the system. It will also maintain the min and max temperature thresholds that will
 * automatically turn on/off the system.
 * 
 * @author jimmypatel
 *
 */
public class Thermometer {
	private double degreesFarenheit;
	private double minThreshold;
	private double maxThreshold;
	private final int INCREMENTOR = 10;
	private final int MIN_TEMP = 0;
	private final int MAX_TEMP = 125;
	
	public Thermometer() {
		this.degreesFarenheit = 60.0;
		minThreshold = 50.0;
		maxThreshold = 90.0;
	}
	
	public Thermometer(int minThreshold, int maxThreshold){
		this.degreesFarenheit = 60.0;
		this.minThreshold = minThreshold;
		this.maxThreshold = maxThreshold;
	}
	
	//Method to increment the temperature
	public boolean incrementTemperature(){
		if(degreesFarenheit+INCREMENTOR<=MAX_TEMP){
			degreesFarenheit += INCREMENTOR;
			return true;
		}
		return false;
	}
	
	public boolean decrementTemperature(){
		if(degreesFarenheit-INCREMENTOR>=MIN_TEMP){
			degreesFarenheit -= INCREMENTOR;
			return true;
		}
		return false;
	}

	public boolean shouldTurnOn(){
		return degreesFarenheit>=maxThreshold;
	}
	
	public boolean shouldTurnOff(){
		return degreesFarenheit<=minThreshold;
	}
	
	@Override
	public String toString(){
		StringBuilder b = new StringBuilder();
		b.append("Temperature: " + degreesFarenheit);
		b.append(", minThreshold: " + minThreshold);
		b.append(", maxThreshold: " + maxThreshold);
		b.append(", isOn: " + shouldTurnOn());
		b.append(", isOff: " + shouldTurnOff());
		return b.toString();
	}
	
	//Setters and getters
	public boolean setTemperature(double degreesF){
		this.degreesFarenheit = degreesF;
		return true;
	}
	
	public boolean setMinThreshold(double degreesF){
		if(degreesF<MIN_TEMP || degreesF>MAX_TEMP){
			return false;
		}
		this.minThreshold = degreesF;
		return true;
	}
	
	public boolean setMaxThreshold(double degreesF){
		if(degreesF<MIN_TEMP || degreesF>MAX_TEMP){
			return false;
		}
		this.maxThreshold = degreesF;
		return true;
	}
	
	public double getTemperature(){
		return this.degreesFarenheit;
	}
	
	public double getMinTheshold(){
		return this.minThreshold;
	}
	
	public double getMaxThreshold(){
		return this.maxThreshold;
	}
}
