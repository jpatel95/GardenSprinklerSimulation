package com.hummingbee.system;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Sprinkler class defines a single Sprinkler node
 * @author Nick
 *
 */
public class Sprinkler implements ISprinkler {
	// constants
	// water flow cubic feet per minute (second in real time)
	private static final double WATER_FLOW = 0.5;
	
	// data members
	private String id;
	private boolean active;
	private boolean functional;
	
	// constructors
	public Sprinkler() {
		id = null;
		active = false;
		functional = true;
	}
	
	public Sprinkler(String id) {
		this.id = id;
		active = false;
		functional = true;
	}
	
	// methods
	public boolean isActive() {
		return active;
	}
	
	public void deactivate() {
		if (active) {
			active = false;
			UpdateTimer.removeSprinkler(this);
		}
	}
	
	public void activate() {
		if (!active && functional) {
			active = true;
			UpdateTimer.addSprinkler(this);
		}
	}
	
	// get functionality
	public boolean isFunctional() {
		return functional;
	}
	
	public void setFunctional(boolean functional) {
		this.functional = functional;
	}
	
	public String getId() {
		return id;
	}
	
	// get the usage from serialized file
	public double getTotalUsage() {
		return Usage.getTotalUsage(id);
	}
	
	public LinkedList<DayUsage> getUsageHistory(int daysLookback) {
		LinkedList<DayUsage> list = Usage.getSprinklerUsage(id, daysLookback);
		LinkedList<DayUsage> result = new LinkedList<DayUsage>();
		int listIndex = 0;
		for (int i = 0; i < daysLookback; i++) {
			LocalDate date = Garden.getInstance().getDate().minusDays(i);
			if (listIndex < list.size() && date.isEqual(list.get(listIndex).getDay())) {
				result.add(list.get(listIndex));
				listIndex++;
			}
			else {
				result.add(new DayUsage(date, 0));
			}
		}
		
		return result;
	}
	
	public double getUsage(int daysLookback) {
		double result = 0;
		Iterator<DayUsage> iterator = getUsageHistory(daysLookback).iterator();
		
		while (iterator.hasNext()) {
			result += iterator.next().getUsage();
		}
		
		return result;	
	}
	
	// get sprinkler water flow
	public double getWaterFlow() {
		return WATER_FLOW;
	}
}
