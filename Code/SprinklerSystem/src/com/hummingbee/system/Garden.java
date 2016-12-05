package com.hummingbee.system;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Iterator;

import com.hummingbee.enums.Direction;

public class Garden {
	private HashMap<Direction, SprinklerCluster> clusters;
	private SystemDate date;
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
		
		date = new SystemDate();
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
	
	public LocalDate getDate() {
		return date.getDate();
	}
	
	public void addDays(int days) {
		date.addDays(days);
	}
	
	public void minusDays(int days) {
		date.minusDays(days);
	}
	
	public LocalTime getTime() {
		return date.getTime();
	}
	
	public Schedule getSchedule(){
		return schedule;
	}
	
	public boolean isActive() {
		Iterator<SprinklerCluster> clusterIterator = clusters.values().iterator();
		while (clusterIterator.hasNext()) {
			SprinklerCluster cluster = clusterIterator.next();
			Iterator<Sprinkler> sprinklerIterator = cluster.getIterator();
			while (sprinklerIterator.hasNext()) {
				Sprinkler sprinkler = sprinklerIterator.next();
				if (!sprinkler.isActive()) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void activateSprinklers() {
		Iterator<SprinklerCluster> clusterIterator = clusters.values().iterator();
		while (clusterIterator.hasNext()) {
			SprinklerCluster cluster = clusterIterator.next();
			cluster.activate();
		}
	}
}
