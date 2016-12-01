package com.hummingbee.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;

import com.hummingbee.ui.MainUI.UserInterface;

/**
 * Usage class that reads and writes serialized file storing all sprinkler usages
 * @author Nick
 *
 */
public class Usage {
	// constants
	// total usages serialized file path
	private static final String TOTAL_USAGES_FILE_PATH = "resources/total_usages.ser";
	// sprinkler usages serialized file path
	private static final String SPRINKLER_USAGES_FILE_PATH = "resources/sprinkler_usages.ser";
	
	// usages map
	private static HashMap<String, Double> totalUsages = null;
	// first element is the day of last update. As you traverse right, you get older days
	private static HashMap<String, LinkedList<DayUsage>> sprinklerUsages = null;
	
	// updates the stored usages and GUI
	public static void update(String sprinklerId, double usage) {
		// retrieve usages map if the system is starting up
		if (totalUsages == null) {
			totalUsages = readTotalUsages();
		}
		if (sprinklerUsages == null) {
			sprinklerUsages = readSprinklerUsages();
		}
		
		// update the total value for the sprinkler
		Double oldUsage = totalUsages.containsKey(sprinklerId) ? totalUsages.get(sprinklerId) : 0;
		totalUsages.put(sprinklerId, oldUsage + usage);
		// update the value for the sprinkler
		LinkedList<DayUsage> sprinklerUsage = sprinklerUsages.containsKey(sprinklerId) ?
				sprinklerUsages.get(sprinklerId) : new LinkedList<DayUsage>();
				
		DayUsage lastUpdate;
		if (sprinklerUsage.isEmpty()) {
			lastUpdate = new DayUsage(LocalDate.now(), usage);
			sprinklerUsage.addFirst(lastUpdate);
		}
		else {
			lastUpdate = sprinklerUsage.getFirst();
			// if the day of last update isn't today
			if (!lastUpdate.getDay().equals(Garden.getInstance().getDate())) {
				DayUsage dayUsage = new DayUsage(Garden.getInstance().getDate(), usage);
				sprinklerUsage.addFirst(dayUsage);
			}
			else {
				lastUpdate.addUsage(usage);
			}
		}
		
		sprinklerUsages.put(sprinklerId, sprinklerUsage);
		
		
		// write usages map
		writeTotalUsage(totalUsages);
		writeSprinklerUsage(sprinklerUsages);
		
		// update display
		UserInterface.getInstance().update();
	}
	
	/**
	 * gets the usage of a particular sprinkler
	 * @param sprinklerId of the sprinkler usage to retrieve
	 * @return sprinkler usage
	 */
	public static double getTotalUsage(String sprinklerId) {
		// retrieve usages map if the system is starting up
		if (totalUsages == null) {
			totalUsages = readTotalUsages();
		}
		
		return totalUsages.containsKey(sprinklerId) ? totalUsages.get(sprinklerId) : 0;
	}
	
	/**
	 * gets the past usage of how many days specified
	 * @param daysLookback
	 * @return linked list of past and current day usage, most recent day first
	 */
	public static LinkedList<DayUsage> getSprinklerUsage(String sprinklerId, int daysLookback) {
		// retrieve usages map if the system is starting up
		if (totalUsages == null) {
			totalUsages = readTotalUsages();
		}
		if (sprinklerUsages == null) {
			sprinklerUsages = readSprinklerUsages();
		}
		
		LocalDate lookBack = Garden.getInstance().getDate();
		LinkedList<DayUsage> result = new LinkedList<DayUsage>();
		LinkedList<DayUsage> sprinklerUsage = sprinklerUsages.containsKey(sprinklerId) ?
				sprinklerUsages.get(sprinklerId) : new LinkedList<DayUsage>();
				
		int sprinklerUsageIndex = 0;
		while (daysLookback >= 0 && sprinklerUsageIndex < sprinklerUsage.size()) {
			DayUsage dayUsage = sprinklerUsage.get(sprinklerUsageIndex);

			if (lookBack.isEqual(dayUsage.getDay())) {
				result.add(dayUsage);
				sprinklerUsageIndex++;
			}
			
			daysLookback--;
			lookBack = lookBack.minusDays(1);
		}
		
		return result;
	}
	
	/**
	 * reads usages from serialized file in resources folder
	 * @return HashMap<String, Double> of serialized usages, constructs a new HashMap if file doesn't exist
	 */
	@SuppressWarnings("unchecked")
	public static HashMap<String, Double> readTotalUsages() {
		HashMap<String, Double> map;
		File usagesFile = new File(TOTAL_USAGES_FILE_PATH);
		if (usagesFile.exists()) {
			try {
				FileInputStream fis = new FileInputStream(TOTAL_USAGES_FILE_PATH);
				ObjectInputStream ois = new ObjectInputStream(fis);
				map = (HashMap<String, Double>) ois.readObject();
				ois.close();
			} catch (IOException e) {
				// what to do on IOException? display a message?
				map = new HashMap<String, Double>();
				e.printStackTrace();
			} catch (Exception e) {
				map = new HashMap<String, Double>();
				e.printStackTrace();
			}
		}
		else {
			map = new HashMap<String, Double>();
		}
		
		return map;
	}
	
	/**
	 * reads sprinkler usages from serialized file in resources folder
	 * @return HashMap<String, LinkedList<DayUsage>> of serialized usages
	 * constructs a new HashMap if file doesn't exist
	 */
	@SuppressWarnings("unchecked")
	public static HashMap<String, LinkedList<DayUsage>> readSprinklerUsages() {
		HashMap<String, LinkedList<DayUsage>> map;
		File usagesFile = new File(SPRINKLER_USAGES_FILE_PATH);
		if (usagesFile.exists()) {
			try {
				FileInputStream fis = new FileInputStream(SPRINKLER_USAGES_FILE_PATH);
				ObjectInputStream ois = new ObjectInputStream(fis);
				map = (HashMap<String, LinkedList<DayUsage>>) ois.readObject();
				ois.close();
			} catch (IOException e) {
				// what to do on IOException? display a message?
				map = new HashMap<String, LinkedList<DayUsage>>();
				e.printStackTrace();
			} catch (Exception e) {
				map = new HashMap<String, LinkedList<DayUsage>>();
				e.printStackTrace();
			}
		}
		else {
			map = new HashMap<String, LinkedList<DayUsage>>();
		}
		
		return map;
	}
	
	/**
	 * serializes the HashMap and writes it to .ser file
	 * @param map the HashMap to serialize
	 */
	private static void writeTotalUsage(HashMap<String, Double> map) {
		try {
			FileOutputStream fos = new FileOutputStream(TOTAL_USAGES_FILE_PATH);
	        ObjectOutputStream oos = new ObjectOutputStream(fos);
	        oos.writeObject(map);
	        oos.close();
		} catch (IOException e) {
			// what to do on IOException? display a message?
			e.printStackTrace();
		}
	}
	
	/**
	 * serializes the HashMap and writes it to .ser file
	 * @param map the HashMap to serialize
	 */
	private static void writeSprinklerUsage(HashMap<String, LinkedList<DayUsage>> map) {
		try {
			FileOutputStream fos = new FileOutputStream(SPRINKLER_USAGES_FILE_PATH);
	        ObjectOutputStream oos = new ObjectOutputStream(fos);
	        oos.writeObject(map);
	        oos.close();
		} catch (IOException e) {
			// what to do on IOException? display a message?
			e.printStackTrace();
		}
	}
	
	/**
	 * resets usages, data will be lost
	 */
	public static void resetUsages() {
		writeTotalUsage(new HashMap<String, Double>());
		writeSprinklerUsage(new HashMap<String, LinkedList<DayUsage>>());
	}

}
