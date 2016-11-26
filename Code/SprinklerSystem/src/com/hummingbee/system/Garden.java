package com.hummingbee.system;

import com.hummingbee.enums.Direction;

public class Garden {
	private static SprinklerCluster northCluster = null;
	private static SprinklerCluster eastCluster = null;
	private static SprinklerCluster southCluster = null;
	private static SprinklerCluster westCluster = null;
	
	public static SprinklerCluster getNorthCluster() {
		if (northCluster == null) {
			northCluster = new SprinklerCluster(Direction.NORTH);
			for (int i = 0; i < 3; i++) {
				northCluster.addSprinkler();
			}
		}
		return northCluster;
	}
	public static SprinklerCluster getEastCluster() {
		if (eastCluster == null) {
			eastCluster = new SprinklerCluster(Direction.EAST);
			for (int i = 0; i < 3; i++) {
				eastCluster.addSprinkler();
			}
		}
		return eastCluster;
	}
	public static SprinklerCluster getSouthCluster() {
		if (southCluster == null) {
			southCluster = new SprinklerCluster(Direction.SOUTH);
			for (int i = 0; i < 3; i++) {
				southCluster.addSprinkler();
			}
		}
		return southCluster;
	}
	public static SprinklerCluster getWestCluster() {
		if (westCluster == null) {
			westCluster = new SprinklerCluster(Direction.WEST);
			for (int i = 0; i < 3; i++) {
				westCluster.addSprinkler();
			}
		}
		return westCluster;
	}
	
	public static double getNorthClusterUsage(int dayLookback) {
		if (northCluster == null) {
			getNorthCluster();
		}
		
		return northCluster.getUsage(dayLookback);
	}
	
	public static double getEastClusterUsage(int dayLookback) {
		if (eastCluster == null) {
			getEastCluster();
		}
		
		return eastCluster.getUsage(dayLookback);
	}
	
	public static double getSouthClusterUsage(int dayLookback) {
		if (southCluster == null) {
			getSouthCluster();
		}
		
		return southCluster.getUsage(dayLookback);
	}
	
	public static double getWestClusterUsage(int dayLookback) {
		if (westCluster == null) {
			getWestCluster();
		}
		
		return westCluster.getUsage(dayLookback);
	}
}
