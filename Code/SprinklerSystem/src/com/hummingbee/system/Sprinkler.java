package com.hummingbee.system;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.print.attribute.standard.DateTimeAtCompleted;

/**
 * Sprinkler class defines a single Sprinkler node
 * @author Nick
 *
 */
public class Sprinkler implements ISprinkler {
	// constants
	// water flow cubic feet per minute (second in real time)
	private static final double WATER_FLOW = 0.5;
	
	// data members
	private String id;
	private boolean active;
	private boolean functional;
	private SprinklerTimer timer;
	
	// constructors
	public Sprinkler() {
		id = null;
		active = false;
		functional = true;
	}
	
	public Sprinkler(String id) {
		this.id = id;
		active = false;
		functional = true;
	}
	
	// methods
	public boolean isActive() {
		return active;
	}
	
	public void deactivate() {
		if (active) {
			active = false;
			stopTimer();
		}
	}
	
	public void activate() {
		if (!active) {
			active = true;
			startTimer();
		}
	}
	
	// get functionality
	public boolean isFunctional() {
		return functional;
	}
	
	public void setFunctional(boolean functional) {
		this.functional = functional;
	}
	
	public String getId() {
		return id;
	}
	
	// get the usage from serialized file
	public double getUsage() {
		return Usage.getUsage(id);
	}
	
	// get sprinkler water flow
	public double getWaterFlow() {
		return WATER_FLOW;
	}
	
	private void startTimer() {
		timer = new SprinklerTimer();
		timer.start();
	}
	
	private void stopTimer() {
		timer.stop();
	}
	
	/**
	 * Inner class to help sprinkler class record and update usage while active
	 * @author Nick
	 *
	 */
	class SprinklerTimer extends Timer {
		// constants
		// the program will update the usage every 10 minutes (10 seconds in real time)
		private static final int UPDATE_INTERVAL = 10;
		
		// data members
		private Date startTime;
		
		public SprinklerTimer() {
			startTime = null;
		}
		
		/**
		 * start the timer with a task to update the usage every interval
		 */
		public void start() {
			startTime = new Date();
			
			schedule(new TimerTask() {
				public void run() {
					startTime = new Date();
					Usage.update(id, WATER_FLOW * UPDATE_INTERVAL);
				}
			}, UPDATE_INTERVAL * 1000);
		}
		
		/**
		 * stop the timer and update the left over usage since last update
		 */
		public void stop() {
			// get the time of termination
			Date stopTime = new Date();
			// get the number of minutes (seconds in real time)
			// that have passed since the sprinkler started
			double time = (stopTime.getTime() - startTime.getTime()) / 1000;
			// update the sprinkler usage
			Usage.update(id, WATER_FLOW * time);
			cancel();
		}
	}
}
