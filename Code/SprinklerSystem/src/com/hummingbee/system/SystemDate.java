package com.hummingbee.system;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Date of the sprinkler system. Necessary for simulation purposes
 * @author Nick
 *
 */
public class SystemDate {
	private LocalDateTime date;
	
	public SystemDate() {
		date = LocalDateTime.now();
		start();
	}
	
	public LocalDate getDate() {
		return date.toLocalDate();
	}
	
	public void addDays(int days) {
		date = date.plusDays(days);
	}
	
	public void minusDays(int days) {
		date = date.minusDays(days);
	}
	
	public LocalTime getTime() {
		return date.toLocalTime();
	}
	
	private void start() {
		date = LocalDateTime.now();
		Timer timer = new Timer();
		// start the timer to update a minute every second
		timer.schedule(new TimerTask() {
			public void run() {
				date = date.plusMinutes(1);
				//System.out.println(getTime());
			}
		}, 0, 1000);
	}
}
