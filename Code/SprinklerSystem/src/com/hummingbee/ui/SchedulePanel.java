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
	private JComboBox<String> daysStartComboBox, daysEndComboBox;
	private JComboBox <Integer> hoursStartComboBox, minutesStartComboBox, hoursEndComboBox, minutesEndComboBox;
	private JButton btnAddInterval, btnCommit, btnBack;
	
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
		
		StartConfigPanel startPanel = new StartConfigPanel();
		EndConfigPanel endPanel = new EndConfigPanel();
		
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
		
		btnBack.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnBack pressed");
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
	
	private class StartConfigPanel extends JPanel{
		public StartConfigPanel(){
			lblStartDay = new JLabel("Start Day");
			lblStartHour = new JLabel("Start Hour");
			lblStartMinute = new JLabel("Start Minute");
			
			daysStartComboBox = new JComboBox<String>(days);
			hoursStartComboBox = new JComboBox<Integer>((Integer[]) hours.toArray());
			minutesStartComboBox = new JComboBox<Integer>((Integer[]) minutes.toArray());

			daysStartComboBox.setSelectedIndex(0);
			hoursStartComboBox.setSelectedIndex(0);
			minutesStartComboBox.setSelectedIndex(0);

			this.add(lblStartDay);
			this.add(daysStartComboBox);
			this.add(lblStartHour);
			this.add(hoursStartComboBox);
			this.add(lblStartMinute);
			this.add(minutesStartComboBox);
		}
	}
	
	private class EndConfigPanel extends JPanel{
		
		public EndConfigPanel(){
			super(new BorderLayout());
			
			lblEndDay = new JLabel("End Day");
			lblEndHour = new JLabel("End Hour");
			lblEndMinute = new JLabel("End Minute");
			
			daysEndComboBox = new JComboBox<String>(days);
			hoursEndComboBox = new JComboBox<Integer>((Integer[]) hours.toArray());
			minutesEndComboBox = new JComboBox<Integer>((Integer[]) minutes.toArray());

			daysEndComboBox.setSelectedIndex(0);
			hoursEndComboBox.setSelectedIndex(0);
			minutesEndComboBox.setSelectedIndex(0);
			
			btnAddInterval = new JButton("Add");
			btnCommit = new JButton("Commit Changes");
			btnBack = new JButton("Back");
			
			JPanel btnPanel = new JPanel();
			btnPanel.add(btnAddInterval);
			btnPanel.add(btnCommit);
			btnPanel.add(btnBack);
			
			JPanel comboPanel = new JPanel();
			comboPanel.add(lblEndDay);
			comboPanel.add(daysEndComboBox);
			comboPanel.add(lblEndHour);
			comboPanel.add(hoursEndComboBox);
			comboPanel.add(lblEndMinute);
			comboPanel.add(minutesEndComboBox);

			this.add(comboPanel, BorderLayout.NORTH);
			this.add(btnPanel, BorderLayout.CENTER);
		}
	}
}
