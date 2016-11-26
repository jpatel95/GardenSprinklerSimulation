package com.hummingbee.system;

public class Garden {
	private static SprinklerCluster northCluster = null;
	private static SprinklerCluster eastCluster = null;
	private static SprinklerCluster southCluster = null;
	private static SprinklerCluster westCluster = null;
	
	public static SprinklerCluster getNorthCluster() {
		if (northCluster == null) {
			northCluster = new SprinklerCluster();
		}
		return northCluster;
	}
	public static SprinklerCluster getEastCluster() {
		if (eastCluster == null) {
			eastCluster = new SprinklerCluster();
		}
		return eastCluster;
	}
	public static SprinklerCluster getSouthCluster() {
		if (southCluster == null) {
			southCluster = new SprinklerCluster();
		}
		return southCluster;
	}
	public static SprinklerCluster getWestCluster() {
		if (westCluster == null) {
			westCluster = new SprinklerCluster();
		}
		return westCluster;
	}
	
	public static double getNorthClusterUsage(int dayLookback) {
		if (northCluster == null) {
			northCluster = new SprinklerCluster("NORTH");
		}
		
		return northCluster.getUsage(dayLookback);
	}
	
	public static double getEastClusterUsage(int dayLookback) {
		if (eastCluster == null) {
			eastCluster = new SprinklerCluster("EAST");
		}
		
		return eastCluster.getUsage(dayLookback);
	}
	
	public static double getSouthClusterUsage(int dayLookback) {
		if (southCluster == null) {
			southCluster = new SprinklerCluster("SOUTH");
		}
		
		return southCluster.getUsage(dayLookback);
	}
	
	public static double getWestClusterUsage(int dayLookback) {
		if (westCluster == null) {
			westCluster = new SprinklerCluster("WEST");
		}
		
		return westCluster.getUsage(dayLookback);
	}
}
