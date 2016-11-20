package com.hummingbee.system;

/**
 * Interface to expose state, water flow and usage
 * @author Nick
 *
 */
public interface ISprinkler {
	public boolean isActive();
	public boolean isFunctional();
	public double getWaterFlow();
	public double getUsage();
	public String getId();
	public void activate();
	public void deactivate();
}
