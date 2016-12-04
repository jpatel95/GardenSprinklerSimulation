package com.hummingbee.system;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import com.hummingbee.enums.Direction;

public class SprinklerCluster implements ISprinkler {
	// data members
	// map of sprinkler ids to sprinklers in cluster
	private HashMap<String, Sprinkler> sprinklerMap;
	// sprinkler cluster location
	private Direction clusterId;
	// next sprinkler id
	private static HashMap<Direction, Integer> nextId;
	
	public SprinklerCluster() {
		sprinklerMap = new HashMap<String, Sprinkler>();
		clusterId = null;
	}
	
	public SprinklerCluster(Direction location) {
		sprinklerMap = new HashMap<String, Sprinkler>();
		clusterId = location;
		nextId = new HashMap<Direction, Integer>();
		nextId.put(Direction.NORTH, 1);
		nextId.put(Direction.EAST, 1);
		nextId.put(Direction.SOUTH, 1);
		nextId.put(Direction.WEST, 1);
	}

	/**
	 * returns true if at least 1 sprinkler in the cluster is active,
	 * false otherwise
	 */
	@Override
	public boolean isActive() {
		Iterator<Sprinkler> sprinklerIterator = sprinklerMap.values().iterator();
		while (sprinklerIterator.hasNext()) {
			Sprinkler current = sprinklerIterator.next();
			if (current.isActive()) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * returns true if all sprinklers are functional,
	 * false otherwise
	 */
	@Override
	public boolean isFunctional() {
		Iterator<Sprinkler> sprinklerIterator = sprinklerMap.values().iterator();
		while (sprinklerIterator.hasNext()) {
			Sprinkler current = sprinklerIterator.next();
			if (!current.isFunctional()) {
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public void setFunctional(boolean functionality) {
		Iterator<Sprinkler> iterator = getIterator();
		while (iterator.hasNext()) {
			Sprinkler sprinkler = iterator.next();
			sprinkler.setFunctional(functionality);
		}
	}

	/**
	 * returns the total water flow of the cluster
	 */
	@Override
	public double getWaterFlow() {
		double result = 0;
		
		Iterator<Sprinkler> sprinklerIterator = sprinklerMap.values().iterator();
		while (sprinklerIterator.hasNext()) {
			Sprinkler current = sprinklerIterator.next();
			result += current.getWaterFlow();
		}
		
		return result;
	}

	@Override
	public double getTotalUsage() {
		double result = 0;
		
		Iterator<Sprinkler> sprinklerIterator = sprinklerMap.values().iterator();
		while (sprinklerIterator.hasNext()) {
			Sprinkler current = sprinklerIterator.next();
			result += current.getTotalUsage();
		}
		
		return result;
	}
	
	@Override
	public LinkedList<DayUsage> getUsageHistory(int daysLookback) {
		LinkedList<DayUsage> result = new LinkedList<DayUsage>();
		Iterator<Sprinkler> sprinklerIterator = sprinklerMap.values().iterator();
		
		// loop through all spinklers in the cluster
		while (sprinklerIterator.hasNext()) {
			Sprinkler current = sprinklerIterator.next();
			LinkedList<DayUsage> sprinklerUsage = current.getUsageHistory(daysLookback);
			// loop through all days in the sprinkler's lookback history
			for (int i = 0; i < sprinklerUsage.size(); i++) {
				DayUsage day = sprinklerUsage.get(i);
				
				if (result.size() == i) {
					result.add(new DayUsage(day.getDay(), 0));
				}
				
				DayUsage dayUsage = result.get(i);
				dayUsage.addUsage(day.getUsage());
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

	@Override
	public String getId() {
		return clusterId.toString();
	}

	/**
	 * turns on all sprinklers
	 */
	@Override
	public void activate() {
		Iterator<Sprinkler> sprinklerIterator = sprinklerMap.values().iterator();
		while (sprinklerIterator.hasNext()) {
			Sprinkler current = sprinklerIterator.next();
			current.activate();
		}
	}

	@Override
	public void deactivate() {
		Iterator<Sprinkler> sprinklerIterator = sprinklerMap.values().iterator();
		while (sprinklerIterator.hasNext()) {
			Sprinkler current = sprinklerIterator.next();
			current.deactivate();
		}
		
	}
	
	public void addSprinkler() {
		int id = nextId.get(clusterId);
		Sprinkler sprinkler = new Sprinkler(clusterId.toString() + id);
		nextId.put(clusterId, ++id);
		
		sprinklerMap.put(sprinkler.getId(), sprinkler);
	}
	
	public int getCount() {
		return sprinklerMap.size();
	}
	
	public Iterator<Sprinkler> getIterator() {
		return sprinklerMap.values().iterator();
	}
	
	public Sprinkler getSprinkler(String id) {
		return sprinklerMap.containsKey(id) ? sprinklerMap.get(id) : null;
	}
}
