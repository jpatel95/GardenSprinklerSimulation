package com.hummingbee.system;

import java.util.HashMap;

import com.hummingbee.enums.Direction;

public class Garden {
	private HashMap<Direction, SprinklerCluster> clusters;
	
	private Thermometer thermometer;
	private Schedule schedule;
	
	private static Garden garden = null;
	
	public static Garden getInstance() {
		if (garden == null) {
			garden = new Garden();
		}
		
		return garden;
	}
	
	private Garden() {
		clusters = new HashMap<Direction, SprinklerCluster>();
		clusters.put(Direction.NORTH, new SprinklerCluster(Direction.NORTH));
		clusters.put(Direction.EAST, new SprinklerCluster(Direction.EAST));
		clusters.put(Direction.SOUTH, new SprinklerCluster(Direction.SOUTH));
		clusters.put(Direction.WEST, new SprinklerCluster(Direction.WEST));
		
		initializeSprinklersInClusters(3);
		
		thermometer = new Thermometer();
		schedule = new Schedule();
	}
	
	private void initializeSprinklersInClusters(int sprinklersPerCluster) {
		for (int i = 0; i < sprinklersPerCluster; i++) {
			clusters.get(Direction.NORTH).addSprinkler();
			clusters.get(Direction.EAST).addSprinkler();
			clusters.get(Direction.SOUTH).addSprinkler();
			clusters.get(Direction.WEST).addSprinkler();
		}
	}
	
	public SprinklerCluster getCluster(Direction direction) {
		return clusters.get(direction);
	}
	
	public double getTotalClusterUsage(Direction direction) {
		return clusters.get(direction).getTotalUsage();
	}
	
	public double getTemperature() {
		return thermometer.getTemperature();
	}
	
	public void incrementTemperature() {
		thermometer.incrementTemperature();
	}
	
	public void decrementTemperature() {
		thermometer.decrementTemperature();
	}
	
	public double getMinThreshold() {
		return thermometer.getMinThreshold();
	}
	
	public double getMaxThreshold() {
		return thermometer.getMaxThreshold();
	}
	
	public void setMinThreshold(double threshold) {
		thermometer.setMinThreshold(threshold);
	}
	
	public void setMaxThreshold(double threshold) {
		thermometer.setMaxThreshold(threshold);
	}
}
