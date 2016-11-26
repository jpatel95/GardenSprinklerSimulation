package com.hummingbee.system;

import java.util.LinkedList;

/**
 * Interface to expose state, water flow and usage
 * @author Nick
 *
 */
public interface ISprinkler {
	public boolean isActive();
	public boolean isFunctional();
	public double getWaterFlow();
	public double getTotalUsage();
	public LinkedList<DayUsage> getUsageHistory(int dayLookback);
	public String getId();
	public void activate();
	public void deactivate();
}
