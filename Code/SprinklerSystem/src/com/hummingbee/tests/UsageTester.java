package com.hummingbee.tests;

import java.util.HashMap;
import java.util.Iterator;

import com.hummingbee.system.Usage;

public class UsageTester {
	public static void main(String[] args) {
		HashMap<String, Double> map;
		
		//Usage.update("N1", 10.5);
		map = Usage.readUsage();
		//System.out.println("1");
		printMap(map);
		
//		Usage.update("N1", 9.5);
//		Usage.update("N2", 5.4);
//		map = Usage.readUsage();
//		System.out.println("\n2");
//		printMap(map);
//		
//		Usage.resetUsages();
//		map = Usage.readUsage();
//		System.out.println("\n3");
//		printMap(map);
	}
	
	private static void printMap(HashMap<String, Double> map) {
		Iterator<String> keysIterator = map.keySet().iterator();
		Iterator<Double> valuesIterator = map.values().iterator();
		while (keysIterator.hasNext() && valuesIterator.hasNext()) {
			System.out.println(keysIterator.next() + ": " + valuesIterator.next());
		}
	}
}
