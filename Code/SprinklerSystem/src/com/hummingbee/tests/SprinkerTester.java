package com.hummingbee.tests;

import java.util.concurrent.TimeUnit;

import com.hummingbee.system.ISprinkler;
import com.hummingbee.system.Sprinkler;
import com.hummingbee.system.Usage;

public class SprinkerTester {
	public static void main(String[] args) {
		Usage.resetUsages();
		
		ISprinkler sprinkler = new Sprinkler("N1");
		System.out.println("usage 1: " + sprinkler.getTotalUsage());
		sprinkler.activate();
		
		try {
			TimeUnit.SECONDS.sleep((long) 2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sprinkler.deactivate();
		System.out.println("usage 2: " + sprinkler.getTotalUsage());
		System.out.println(sprinkler.getId() + " is active: " + sprinkler.isActive());
		System.out.println(sprinkler.getId() + " is functional: " + sprinkler.isFunctional());
		
		sprinkler.activate();
		
		try {
			TimeUnit.SECONDS.sleep((long) 25);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sprinkler.deactivate();
		System.out.println("usage 3: " + sprinkler.getTotalUsage());
	}
}
