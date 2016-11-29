package com.hummingbee.tests;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import com.hummingbee.enums.Direction;
import com.hummingbee.system.DayUsage;
import com.hummingbee.system.Garden;
import com.hummingbee.system.Sprinkler;
import com.hummingbee.system.SprinklerCluster;
import com.hummingbee.system.Usage;

public class UsageTester {
	public static void main(String[] args) {
		Usage.resetUsages();
		Garden garden = Garden.getInstance();
		SprinklerCluster northCluster = garden.getCluster(Direction.NORTH);
		northCluster.activate();
		try {
			Thread.sleep(5000);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		northCluster.deactivate();
	}
	
	private static void printMap(HashMap<String, Double> map) {
		Iterator<String> keysIterator = map.keySet().iterator();
		while (keysIterator.hasNext()) {
			String key = keysIterator.next();
			System.out.println(key + ": " + map.get(key));
		}
	}
	
	public static void printSprinklerMap(HashMap<String, LinkedList<DayUsage>> map) {
		Iterator<String> sprinklerIterator = map.keySet().iterator();
		while (sprinklerIterator.hasNext()) {
			String key = sprinklerIterator.next();
			Iterator<DayUsage> historyIterator = map.get(key).iterator();
			System.out.print(key + "-> ");
			while (historyIterator.hasNext()) {
				DayUsage day = historyIterator.next();
				System.out.print(day.getDay() + ": " + day.getUsage() + ", ");
			}
			System.out.println("");
		}
	}
}
