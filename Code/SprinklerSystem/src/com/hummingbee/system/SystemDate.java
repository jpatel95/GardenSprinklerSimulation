package com.hummingbee.system;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.joda.time.Interval;

import com.hummingbee.enums.Days;

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
				Garden garden = Garden.getInstance();
				if (!garden.isActive()) {
					LocalTime currentTime = getTime();
					Days current = Days.valueOf(date.getDayOfWeek().toString());
					Schedule schedule = Garden.getInstance().getSchedule();
					for (Interval interval : schedule.getScheduleForDay(current)) {
						LocalTime start = LocalTime.of(interval.getStart().getHourOfDay(),
								interval.getStart().getMinuteOfHour(),
								interval.getStart().getSecondOfMinute());
						LocalTime end = LocalTime.of(interval.getEnd().getHourOfDay(),
								interval.getEnd().getMinuteOfHour(),
								interval.getEnd().getSecondOfMinute());
						if (start.isBefore(currentTime) && end.isAfter(currentTime)) {
							// activate all sprinklers
							System.out.println("Current time is in the interval");
							garden.activateSprinklers();
						}
					}
				}
			}
		}, 0, 1000);
	}
}
