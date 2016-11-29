package com.hummingbee.system;

import com.hummingbee.enums.Direction;

public class Garden {
	private static SprinklerCluster northCluster = null;
	private static SprinklerCluster eastCluster = null;
	private static SprinklerCluster southCluster = null;
	private static SprinklerCluster westCluster = null;

	public Garden() {
		Thermometer.getInstance();
	}
	
	public static SprinklerCluster getCluster(Direction direction) {
		SprinklerCluster result;
		switch (direction) {
		case NORTH :
			if (northCluster == null) {
				northCluster = new SprinklerCluster(Direction.NORTH);
				for (int i = 0; i < 3; i++) {
					northCluster.addSprinkler();
				}
			}
			result = northCluster;
			break;
		case EAST :
			if (eastCluster == null) {
				eastCluster = new SprinklerCluster(Direction.EAST);
				for (int i = 0; i < 3; i++) {
					eastCluster.addSprinkler();
				}
			}
			result = eastCluster;
			break;
		case SOUTH :
			if (southCluster == null) {
				southCluster = new SprinklerCluster(Direction.SOUTH);
				for (int i = 0; i < 3; i++) {
					southCluster.addSprinkler();
				}
			}
			result = southCluster;
			break;
		default :
			if (westCluster == null) {
				westCluster = new SprinklerCluster(Direction.WEST);
				for (int i = 0; i < 3; i++) {
					westCluster.addSprinkler();
				}
			}
			result = westCluster;
		}
		
		return result;
	}
	
	public static double getTotalClusterUsage(Direction direction) {
		double result;
		switch (direction) {
		case NORTH :
			if (northCluster == null) {
				northCluster = new SprinklerCluster(Direction.NORTH);
				for (int i = 0; i < 3; i++) {
					northCluster.addSprinkler();
				}
			}
			result = northCluster.getTotalUsage();
			break;
		case EAST :
			if (eastCluster == null) {
				eastCluster = new SprinklerCluster(Direction.EAST);
				for (int i = 0; i < 3; i++) {
					eastCluster.addSprinkler();
				}
			}
			result = eastCluster.getTotalUsage();
			break;
		case SOUTH :
			if (southCluster == null) {
				southCluster = new SprinklerCluster(Direction.SOUTH);
				for (int i = 0; i < 3; i++) {
					southCluster.addSprinkler();
				}
			}
			result = southCluster.getTotalUsage();
			break;
		default :
			if (westCluster == null) {
				westCluster = new SprinklerCluster(Direction.WEST);
				for (int i = 0; i < 3; i++) {
					westCluster.addSprinkler();
				}
			}
			result = westCluster.getTotalUsage();
		}
		
		return result;
	}
	
	public static double getTemperature() {
		return Thermometer.getInstance().getTemperature();
	}
	
	public static void incrementTemperature() {
		Thermometer.getInstance().incrementTemperature();
	}
	
	public static void decrementTemperature() {
		Thermometer.getInstance().decrementTemperature();
	}
}
