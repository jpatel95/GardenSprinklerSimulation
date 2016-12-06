package com.hummingbee.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.joda.time.Interval;

import com.hummingbee.enums.Days;

public class SetDayPanel extends JPanel {	
	private JLabel lblDay, lblMonth, lblDate, lblYear;
	private JComboBox<String> dayComboBox;
	private JComboBox <Integer> comboMonth, comboDate, comboYear;
	private JButton btnCommit, btnBack;
	
	private String [] days = {Days.SUNDAY.toString(), Days.MONDAY.toString(), Days.TUESDAY.toString(),
			Days.WEDNESDAY.toString(), Days.THURSDAY.toString(), Days.FRIDAY.toString(),
			Days.SATURDAY.toString()};
	
	private Integer [] months = {1,2,3,4,5,6,7,8,9,10,11,12};
	private Integer [] date = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,
			20,21,22,23,24,25,26,27,28,29,30,31};
	private Integer [] year = {2016,2017,2018,2019,2020};
	
	
	public SetDayPanel(int width, int height){
		super(new BorderLayout());
		setPreferredSize(new Dimension(width, height));
		
		lblDay = new JLabel("Day");
		lblMonth = new JLabel("Month");
		lblDate = new JLabel("Date");
		lblYear = new JLabel("Year");
		
		dayComboBox = new JComboBox<String>(days);
		comboMonth = new JComboBox<Integer>(months);
		comboDate = new JComboBox<Integer>(date);
		comboYear = new JComboBox<Integer>(year);
		
		dayComboBox.setSelectedIndex(0);
		comboMonth.setSelectedIndex(0);
		comboDate.setSelectedIndex(0);
		comboYear.setSelectedIndex(0);
		
		btnCommit = new JButton("Set Date");
		btnBack = new JButton("Back");
		
		JPanel datePanel = new JPanel();
		datePanel.add(lblDay);
		datePanel.add(dayComboBox);
		datePanel.add(lblMonth);
		datePanel.add(comboMonth);
		datePanel.add(lblDate);
		datePanel.add(comboDate);
		datePanel.add(lblYear);
		datePanel.add(comboYear);
		
		
		JPanel btnPanel = new JPanel();
		btnPanel.add(btnCommit);
		btnPanel.add(btnBack);
		
		setActionListeners();
		
		this.add(datePanel, BorderLayout.NORTH);
		this.add(btnPanel, BorderLayout.CENTER);
	}
	
	private void setActionListeners(){
		btnCommit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnCommit pressed");
			}
		});
		
		btnBack.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnBack pressed");
			}
		});
	}
}
