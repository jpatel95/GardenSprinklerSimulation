package com.hummingbee.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.hummingbee.ui.MainUI.UserInterface;

public class ConfigPanel extends JPanel{
	private static final int BUTTON_WIDTH = 150;
	private static final int BUTTON_HEIGHT = 50;
	
	private JButton btnSetDay;
	private JButton btnSetSchedule;
	private JButton btnSetThresholdTemp;
	
	private JButton btnEnableDisable;
	private JButton btnActivation;
	private JButton btnStatus;
	private JButton btnBack;
	
	public ConfigPanel(int width, int height){
		super();
		setPreferredSize(new Dimension(width, height));

		btnSetDay = new JButton("Set Day");
		btnSetSchedule = new JButton("Set Schedule");
		btnSetThresholdTemp = new JButton("Set Threshold");
		
		btnEnableDisable = new JButton("Enable");
		btnActivation = new JButton("Activation");
		btnStatus = new JButton("Status");
		
		//btnBack = new JButton("Back");
		
		btnSetDay.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		btnSetSchedule.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		btnSetThresholdTemp.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		
		btnEnableDisable.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		btnActivation.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		btnStatus.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		
		//btnBack.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		
		btnSetDay.setFont(new Font("Arial", Font.PLAIN, 20));
		btnSetSchedule.setFont(new Font("Arial", Font.PLAIN, 20));
		btnSetThresholdTemp.setFont(new Font("Arial", Font.PLAIN, 20));
		
		btnEnableDisable.setFont(new Font("Arial", Font.PLAIN, 20));
		btnActivation.setFont(new Font("Arial", Font.PLAIN, 20));
		btnStatus.setFont(new Font("Arial", Font.PLAIN, 20));
		
		
		//btnBack.setFont(new Font("Arial", Font.PLAIN, 20));
		
		setBackground(Color.GRAY);
		
		setActionListeners();
		
		add(btnSetDay);
		add(btnSetSchedule);
		add(btnSetThresholdTemp);
		
		add(btnEnableDisable);
		add(btnActivation);
		add(btnStatus);
		
		//add(btnBack);
	}
	
	private void setActionListeners(){
		btnSetDay.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnSetDay Pressed");
			}		
		});
		
		btnSetSchedule.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnSetSchedule Pressed");
				
			}
		});
		
		btnSetThresholdTemp.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnSetThresholdTemp Pressed");
			}		
		});
		
		btnEnableDisable.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnEnableDisable Pressed");
			}		
		});
		
		btnActivation.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnActivation Pressed");				
			}		
		});
		
		btnStatus.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnSetStatus Pressed");				
			}		
		});
		
//		btnBack.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				UserInterface.clearContainer();
//			}		
//		});
	}
}
