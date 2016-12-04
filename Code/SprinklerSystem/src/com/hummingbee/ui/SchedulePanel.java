package com.hummingbee.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.LocalTime;
import org.joda.time.Period;

import com.hummingbee.enums.Days;
import com.hummingbee.system.Garden;
import com.hummingbee.utils.Formatter;
import com.hummingbee.utils.TimeInterval;

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
	private JScrollPane jScrollPaneSchedule;
	private JTextArea textAreaSchedule;
	private StringBuilder builder;
	
	public SchedulePanel(int width, int height){
		super(new BorderLayout());
		setPreferredSize(new Dimension(width, height));
		
		builder = new StringBuilder();
		hours = new ArrayList<Integer>();
		minutes = new ArrayList<Integer>();
		for(Integer i=0;i<24; ++i){
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
				
				builder.append("\t" + daysStartComboBox.getSelectedItem() + ", "
						+ Formatter.integerFormatter((int)hoursStartComboBox.getSelectedItem()) + ":"
						+ Formatter.integerFormatter((int)minutesStartComboBox.getSelectedItem()) + " to "
						+ daysStartComboBox.getSelectedItem() + ", "
						+ Formatter.integerFormatter((int)hoursEndComboBox.getSelectedItem()) + ":"
						+ Formatter.integerFormatter((int)minutesEndComboBox.getSelectedItem()) + "\n");
				
				LocalTime tStart = new LocalTime(hoursStartComboBox.getSelectedItem() + ":" + minutesStartComboBox.getSelectedItem() + ":00");
				LocalTime tEnd = new LocalTime(hoursStartComboBox.getSelectedItem() + ":" + minutesStartComboBox.getSelectedItem() + ":00");
				TimeInterval interval = new TimeInterval(tStart, tEnd);

				//System.out.println("Combobox value: " + daysStartComboBox.getSelectedItem());
				schedule.get(Days.fromString((String) daysStartComboBox.getSelectedItem())).add(interval.toInterval());
				textAreaSchedule.setText(builder.toString());
			}
		});
		
		btnCommit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnCommit pressed schedule beign set.");
				System.out.println(schedule);
				Garden.getInstance().getSchedule().setSchedule(schedule);
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
			
			textAreaSchedule = new JTextArea();
			textAreaSchedule.setColumns(40);
			textAreaSchedule.setLineWrap(true);
			textAreaSchedule.setRows(5);
			textAreaSchedule.setWrapStyleWord(true);
			jScrollPaneSchedule = new JScrollPane(textAreaSchedule);
	        textAreaSchedule.setEditable(false);
	        builder.append(" Intervals to add:\n");
	        textAreaSchedule.setText(builder.toString());
	        
			JPanel btnPanel = new JPanel();
			btnPanel.add(btnAddInterval);
			btnPanel.add(btnCommit);
			
			JPanel comboPanel = new JPanel(new BorderLayout());
			JPanel innerComboPanel = new JPanel();
			JPanel innerTextAreaPanel = new JPanel();
			innerComboPanel.add(lblEndHour);
			innerComboPanel.add(hoursEndComboBox);
			innerComboPanel.add(lblEndMinute);
			innerComboPanel.add(minutesEndComboBox);
			innerTextAreaPanel.add(jScrollPaneSchedule);
			comboPanel.add(innerComboPanel, BorderLayout.NORTH);
			comboPanel.add(innerTextAreaPanel, BorderLayout.CENTER);
	        
			this.add(comboPanel, BorderLayout.NORTH);
			this.add(btnPanel, BorderLayout.CENTER);
		}
	}
}
