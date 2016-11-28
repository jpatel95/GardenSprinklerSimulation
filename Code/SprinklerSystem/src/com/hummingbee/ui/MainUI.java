package com.hummingbee.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.hummingbee.system.Garden;
import com.hummingbee.system.Thermometer;

public class MainUI {
	
	public static class UserInterface extends JFrame {
		private static final int WIDTH = 1200;
		private static final int HEIGHT = 700;
		
		public UserInterface() {
			super("User Interface");
			setPreferredSize(new Dimension(WIDTH, HEIGHT));
			
			Container container = getContentPane();
			container.setLayout(new BorderLayout());
			
			//HomePanel homePanel = new HomePanel(WIDTH, HEIGHT - 100);
			UsagePanel usagePanel = new UsagePanel(WIDTH, HEIGHT - 100);
			
			ControlPanel controlPanel = new ControlPanel(WIDTH, 100);
			
			//container.add(homePanel, BorderLayout.NORTH); 
			container.add(usagePanel, BorderLayout.NORTH);
			container.add(controlPanel, BorderLayout.SOUTH);
			
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
	}
	
	public static void main(String[] args) {
		new UserInterface();
	}
}
