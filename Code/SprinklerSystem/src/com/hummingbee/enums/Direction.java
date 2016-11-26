package com.hummingbee.enums;

public enum Direction {
	NORTH("North"),
	EAST("East"),
	SOUTH("South"),
	WEST("West");
	
	private String direction;
	
	private Direction(String direction) {
		this.direction = direction;
	}
	
	@Override
	public String toString() {
		return direction;
	}
}
