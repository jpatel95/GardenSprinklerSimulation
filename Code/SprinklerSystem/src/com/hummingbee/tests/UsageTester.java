package com.hummingbee.tests;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import com.hummingbee.enums.Direction;
import com.hummingbee.system.DayUsage;
import com.hummingbee.system.Sprinkler;
import com.hummingbee.system.SprinklerCluster;
import com.hummingbee.system.Usage;

public class UsageTester {
	public static void main(String[] args) {
//		Usage.resetUsages();
//		HashMap<String, Double> map;
//		HashMap<String, LinkedList<DayUsage>> sprinklerMap;
//		n1.activate();
//		try {
//			TimeUnit.SECONDS.sleep((long) 2);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		n2.activate();
//		try {
//			TimeUnit.SECONDS.sleep((long) 15);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		n1.deactivate();
//		n2.deactivate();
//		
//		map = Usage.readTotalUsages();
//		System.out.println("Total Usages: ");
//		printMap(map);
		
//		sprinklerMap = Usage.readSprinklerUsages();
//		System.out.println("Sprinkler Usages: ");
//		printSprinklerMap(sprinklerMap);
//		SprinklerCluster northCluster = new SprinklerCluster(Direction.NORTH);
//		northCluster.addSprinkler();
//		northCluster.addSprinkler();
//		northCluster.activate();
//		try {
//			TimeUnit.SECONDS.sleep((long) 15);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		northCluster.deactivate();
//		
//		LinkedList<DayUsage> usages = northCluster.getUsageHistory(7);
//		Iterator<DayUsage> iterator = usages.iterator();
//		while (iterator.hasNext()) {
//			DayUsage dayUsage = iterator.next();
//			System.out.println(dayUsage.getDay() + ": " + dayUsage.getUsage());
//		}
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
