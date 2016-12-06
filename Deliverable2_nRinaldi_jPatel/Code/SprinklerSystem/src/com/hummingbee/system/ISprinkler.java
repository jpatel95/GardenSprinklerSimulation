package com.hummingbee.system;

import java.util.LinkedList;

import java.util.Observable;
import java.util.Observer;

/**
 * Interface to expose state, water flow and usage
 * @author Nick
 *
 */
public interface ISprinkler {
	public boolean isActive();
	public boolean isFunctional();
	public void setFunctional(boolean functional);
	public double getWaterFlow();
	public double getTotalUsage();
	public double getUsage(int dayLookback);
	public LinkedList<DayUsage> getUsageHistory(int dayLookback);
	public String getId();
	public void activate();
	public void deactivate();
	public void addObserver(Observer o);
}
