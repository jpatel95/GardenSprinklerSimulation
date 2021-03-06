package com.hummingbee.system;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class UpdateTimer {
	// constants
	// the program will update the usage every 10 minutes (10 seconds in real time)
	private static final int UPDATE_INTERVAL = 10;
	
	// data members
	private static HashMap<Sprinkler, Date> activeSprinklers = null;
	private static Timer timer = null;
	
	private static void initialize() {
		if (activeSprinklers == null) {
			activeSprinklers = new HashMap<Sprinkler, Date>();
		}
	}
	
	/**
	 * start the timer with a task to update the usage every interval
	 */
	private static void start() {
		timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				//System.out.println("usage update interval");
				Iterator<Sprinkler> iterator = activeSprinklers.keySet().iterator();
				while (iterator.hasNext()) {
					Sprinkler sprinkler = iterator.next();
					Date lastUpdate = activeSprinklers.get(sprinkler);
					Date now = new Date();
					double elapsedSeconds = (now.getTime() - lastUpdate.getTime()) / 1000;
					Usage.update(sprinkler.getId(), sprinkler.getWaterFlow() * elapsedSeconds);
					activeSprinklers.put(sprinkler, now);
				}
			}
		}, UPDATE_INTERVAL * 1000, UPDATE_INTERVAL * 1000);
	}
	
	/**
	 * stop the timer
	 */
	private static void stop() {
		timer.cancel();
	}
	
	/**
	 * adds a sprinkler to the map of running sprinklers
	 * @param sprinkler
	 */
	public static void addSprinkler(Sprinkler sprinkler) {
		initialize();
		activeSprinklers.put(sprinkler, new Date());
		// if the map was empty
		if (activeSprinklers.size() == 1) {
			start();
		}
	}
	
	/**
	 * removes a sprinkler from the map of running sprinklers
	 * @param sprinkler
	 */
	public static void removeSprinkler(Sprinkler sprinkler) {
		initialize();
		// get the time of termination
		long stopTime = (new Date()).getTime();
		// get the time of last update
		long lastUpdate = activeSprinklers.containsKey(sprinkler) ?
				activeSprinklers.get(sprinkler).getTime() : stopTime;
		// get the number of minutes (seconds in real time)
		// that have passed since the sprinkler started
		double time = (stopTime - lastUpdate) / 1000;
		// update the sprinkler usage
		Usage.update(sprinkler.getId(), sprinkler.getWaterFlow() * time);
		
		// remove the sprinkler
		activeSprinklers.remove(sprinkler);
		// see if we can stop the timer
		if (activeSprinklers.isEmpty()) {
			stop();
		}
	}
}
