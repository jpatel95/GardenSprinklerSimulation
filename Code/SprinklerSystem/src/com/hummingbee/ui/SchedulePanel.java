package com.hummingbee.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.joda.time.Interval;

import com.hummingbee.enums.Days;

public class SchedulePanel extends JPanel{
	private Map<Days, List<Interval>> schedule;
	private JLabel lblStartDay, lblStartHour, lblStartMinute;
	private JLabel lblEndDay, lblEndHour, lblEndMinute;
	private JComboBox<String> daysStartComboBox;
	private JComboBox <Integer> hoursStartComboBox, minutesStartComboBox, hoursEndComboBox, minutesEndComboBox;
	private JButton btnAddInterval, btnCommit;
	
	private String [] days = {Days.SUNDAY.toString(), Days.MONDAY.toString(), Days.TUESDAY.toString(),
			Days.WEDNESDAY.toString(), Days.THURSDAY.toString(), Days.FRIDAY.toString(),
			Days.SATURDAY.toString()};
	
	private List<Integer> hours;
	private List<Integer> minutes;
	
	public SchedulePanel(int width, int height){
		super(new BorderLayout());
		setPreferredSize(new Dimension(width, height));
		
		hours = new ArrayList<Integer>();
		minutes = new ArrayList<Integer>();
		for(Integer i=0;i<=24; ++i){
			hours.add(i);
		}
		for(Integer i=0;i<=59; ++i){
			minutes.add(i);
		}
		
		initializeSchedule();
		
		TopPanel startPanel = new TopPanel();
		BodyPanel endPanel = new BodyPanel();
		
		setActionListeners();
		
		add(startPanel, BorderLayout.NORTH);
		add(endPanel, BorderLayout.CENTER);
	}
	
	private void setActionListeners(){
		btnAddInterval.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnAddInterval pressed");
				System.out.println(daysStartComboBox.getSelectedItem() + " " +
						hoursStartComboBox.getSelectedItem() + " " + minutesStartComboBox.getSelectedItem());
			}
		});
		
		btnCommit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnCommit pressed");
				System.out.println(schedule);
			}
		});
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
	
	private class TopPanel extends JPanel{
		public TopPanel(){			
			lblStartDay = new JLabel("Day");
			daysStartComboBox = new JComboBox<String>(days);
			daysStartComboBox.setSelectedIndex(0);
			
			this.add(lblStartDay);
			this.add(daysStartComboBox);
		}
	}
	
	private class BodyPanel extends JPanel{
		public BodyPanel(){
			super(new BorderLayout());
			
			StartPanel startPanel = new StartPanel();
			EndPanel endPanel = new EndPanel();
			
			this.add(startPanel, BorderLayout.NORTH);
			this.add(endPanel, BorderLayout.CENTER);
		}
	}
	
	
	private class StartPanel extends JPanel{
		public StartPanel(){
			super();
			lblStartHour = new JLabel("Start Hour");
			lblStartMinute = new JLabel("Start Minute");
			hoursStartComboBox = new JComboBox(hours.toArray());
			minutesStartComboBox = new JComboBox(minutes.toArray());
			hoursStartComboBox.setSelectedIndex(0);
			minutesStartComboBox.setSelectedIndex(0);
			this.add(lblStartHour, BorderLayout.CENTER);
			this.add(hoursStartComboBox, BorderLayout.CENTER);
			this.add(lblStartMinute, BorderLayout.CENTER);
			this.add(minutesStartComboBox, BorderLayout.CENTER);
		}
	}
	
	private class EndPanel extends JPanel{
		public EndPanel(){
			super(new BorderLayout());

			lblEndHour = new JLabel("End Hour");
			lblEndMinute = new JLabel("End Minute");
			
			hoursEndComboBox = new JComboBox(hours.toArray());
			minutesEndComboBox = new JComboBox( minutes.toArray());

			hoursEndComboBox.setSelectedIndex(0);
			minutesEndComboBox.setSelectedIndex(0);
			
			btnAddInterval = new JButton("Add");
			btnCommit = new JButton("Commit Changes");
			
			JPanel btnPanel = new JPanel();
			btnPanel.add(btnAddInterval);
			btnPanel.add(btnCommit);
			
			JPanel comboPanel = new JPanel();
			comboPanel.add(lblEndHour);
			comboPanel.add(hoursEndComboBox);
			comboPanel.add(lblEndMinute);
			comboPanel.add(minutesEndComboBox);
			
			this.add(comboPanel, BorderLayout.NORTH);
			this.add(btnPanel, BorderLayout.CENTER);
		}
	}
}
