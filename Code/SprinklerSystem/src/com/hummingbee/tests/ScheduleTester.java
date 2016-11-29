package com.hummingbee.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.joda.time.Interval;

import com.hummingbee.enums.Days;
import com.hummingbee.system.Schedule;

public class ScheduleTester {
	public static void main(String[] args) {
		testMergeValid();
	}
	
	public static void testMergeValid(){
		Schedule s1 = new Schedule();
		
		Map<Days, List<Interval>> schedule = new HashMap<Days, List<Interval>>();
		schedule.put(Days.MONDAY, new ArrayList<Interval>());
		schedule.put(Days.TUESDAY, new ArrayList<Interval>());
		schedule.put(Days.WEDNESDAY, new ArrayList<Interval>());
		schedule.put(Days.THURSDAY, new ArrayList<Interval>());
		schedule.put(Days.FRIDAY, new ArrayList<Interval>());
		schedule.put(Days.SATURDAY, new ArrayList<Interval>());
		schedule.put(Days.SUNDAY, new ArrayList<Interval>());
		
		schedule.get(Days.MONDAY).add(new Interval(System.currentTimeMillis(), System.currentTimeMillis()+1000));
		schedule.get(Days.MONDAY).add(new Interval(System.currentTimeMillis(), System.currentTimeMillis()+5000));
		
		s1.setSchedule(schedule);
		
		for(Entry<Days, List<Interval>> entry: s1.getSchedule().entrySet()){
			System.out.println(entry.getKey());
			for(Interval i : entry.getValue()){
				System.out.println("\t " + i.getStart() + ", " + i.getEnd());
			}
		}
		
	}
	
}
