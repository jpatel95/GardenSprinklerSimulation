package com.hummingbee.system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.hummingbee.enums.Days;
import org.joda.time.Interval;

/**
 * The schedule class is a singleton class that is used to maintain the watering schedule of
 * the sprinkler system. The data structure that is used is a HashMap with a day enum as a key
 * and a list of intervals as the value. The interval object, provided by the Joda jar, are then 
 * merged and put in the schedule map.
 * 
 * 
 * @author jimmypatel
 *
 */
public class Schedule {
	private static Schedule instance = null;
	private Map<Days, List<Interval>> schedule;
	
	//Private constructor for the singleton class.
	private Schedule(){
		initializeSchedule();
	}
	
	//Returns a map, the 7 day schedule.
	public Map<Days, List<Interval>> getSchedule(){
		return schedule;
	}
	
	//Returns a list of intervals, the schedule for a particular day.
	public List<Interval> getScheduleForDay(Days day){
		return schedule.get(day);
	}
	
	//Getter for the single instance of the class.
	public static Schedule getInstance() {
		if(instance == null) {
				instance = new Schedule();
		}
		return instance;
	}
	
	//Sets the schedule map with that passed in the param.
	public void setSchedule(Map<Days, List<Interval>> newSchedule){
		for(Entry<Days, List<Interval>> entry: newSchedule.entrySet()){
			schedule.put(entry.getKey(), mergeIntervals(entry.getValue()));
		}
	}
	
	//Merges the intervals
	public List<Interval> mergeIntervals(List<Interval> intervals){
		List<Interval> result = new ArrayList<Interval>();

		if(intervals==null || intervals.size()==0){
			return result;
		}
		
		Collections.sort(intervals, new IntervalComparator());
		Interval prev = intervals.get(0);
		for(int i=0; i<intervals.size()-1; ++i){
			Interval curr = intervals.get(i);
			if(curr.getStartMillis()>prev.getEndMillis()){
				result.add(prev);
				prev = curr;
			}else{
				Interval merged = new Interval(prev.getStartMillis(), Math.max(prev.getEndMillis(), curr.getEndMillis()));
				prev = merged;
			}
			
		}
		result.add(prev);
		return result;
	}
	
	//Clears the entire map and reinitializes it
	public void removeAll(){
		schedule.clear();
		initializeSchedule();
	}
	
	//Removes a specific day then reinitializes it
	public void removeDay(Days day){
		schedule.remove(day);
		schedule.put(day, new ArrayList<Interval>());
	}
	
	@Override
	public String toString(){
		StringBuilder b = new StringBuilder();
		for(Entry<Days, List<Interval>> entry: schedule.entrySet()){
			b.append(entry.getKey());
			for(Interval interval: entry.getValue()){
				b.append(" " + interval.toString());
			}
			b.append("\n");
		}
		return b.toString();
	}
	
	//Helper function to initialize a list for each of the 7 days
	private void initializeSchedule(){
		schedule = new HashMap<Days, List<Interval>>();
		schedule.put(Days.SUNDAY, new ArrayList<Interval>());
		schedule.put(Days.MONDAY, new ArrayList<Interval>());
		schedule.put(Days.TUESDAY, new ArrayList<Interval>());
		schedule.put(Days.WEDNESDAY, new ArrayList<Interval>());
		schedule.put(Days.THURSDAY, new ArrayList<Interval>());
		schedule.put(Days.FRIDAY, new ArrayList<Interval>());
		schedule.put(Days.SATURDAY, new ArrayList<Interval>());
	}
}

/**
 * The following class is a custom Comparator that is used when merging two intervals
 * that overlap.
 * 
 * @author jimmypatel
 *
 */
class IntervalComparator implements Comparator<Interval>{
	@Override
	public int compare(Interval i1, Interval i2) {
		return (int) (i1.getStartMillis() - i2.getStartMillis());
	}
}
