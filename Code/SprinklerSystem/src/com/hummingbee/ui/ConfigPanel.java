package com.hummingbee.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.hummingbee.system.Garden;
import com.hummingbee.system.SystemDate;
import com.hummingbee.ui.MainUI.UserInterface;
import com.hummingbee.utils.Formatter;

public class ConfigPanel extends JPanel{
	private static final int BUTTON_WIDTH = 150;
	private static final int BUTTON_HEIGHT = 50;
	private static final int LABEL_SPACING = 15;
	private int width;
	private int height;
	
	private JPanel buttonPanel;
	private JPanel labelPanel;
	
	private JButton btnIncrementDay;
	private JButton btnDecrementDay;
	private JButton btnSetSchedule;
	private JButton btnSetThresholdTemp;
	
	private JButton btnEnableDisable;
	private JButton btnActivation;
	private JButton btnStatus;
	
	private JLabel lblSystemDate;
	private JLabel lblMinThreshold;
	private JLabel lblMaxThreshold;
	
	public ConfigPanel(int width, int height){
		super();
		this.width = width;
		this.height = height;
		
		setPreferredSize(new Dimension(width, height));

		buttonPanel = new JPanel();
		labelPanel = new JPanel();
		
		buttonPanel.setPreferredSize(new Dimension(width, height / 2));
		labelPanel.setPreferredSize(new Dimension(width, height / 2));
		
		buttonPanel.setBackground(Color.GRAY);
		labelPanel.setBackground(Color.GRAY);
		
		btnIncrementDay = new JButton("+ Day");
		btnDecrementDay = new JButton("- Day");
		btnSetSchedule = new JButton("Set Schedule");
		btnSetThresholdTemp = new JButton("Set Threshold");
		
		btnEnableDisable = new JButton("Enable");
		btnActivation = new JButton("Activate");
		btnStatus = new JButton("Status");
		
		lblSystemDate = new JLabel(Formatter.dateLabelFormatter(Garden.getInstance().getDate()));
		lblMinThreshold = new JLabel(Formatter.minThresholdFormatter(Garden.getInstance().getMinThreshold()));
		lblMaxThreshold = new JLabel(Formatter.maxThresholdFormatter(Garden.getInstance().getMaxThreshold()));
		
		btnIncrementDay.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		btnDecrementDay.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		btnSetSchedule.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		btnSetThresholdTemp.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		
		btnEnableDisable.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		btnActivation.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		btnStatus.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		
		btnIncrementDay.setFont(new Font("Arial", Font.PLAIN, 20));
		btnDecrementDay.setFont(new Font("Arial", Font.PLAIN, 20));
		btnSetSchedule.setFont(new Font("Arial", Font.PLAIN, 20));
		btnSetThresholdTemp.setFont(new Font("Arial", Font.PLAIN, 20));
		
		btnEnableDisable.setFont(new Font("Arial", Font.PLAIN, 20));
		btnActivation.setFont(new Font("Arial", Font.PLAIN, 20));
		btnStatus.setFont(new Font("Arial", Font.PLAIN, 20));
		
		lblSystemDate.setFont(new Font("Arial", Font.PLAIN, 20));
		lblMinThreshold.setFont(new Font("Arial", Font.PLAIN, 20));
		lblMaxThreshold.setFont(new Font("Arial", Font.PLAIN, 20));
		
		lblSystemDate.setForeground(Color.WHITE);
		lblMinThreshold.setForeground(Color.WHITE);
		lblMaxThreshold.setForeground(Color.WHITE);
		
		lblSystemDate.setBorder(new EmptyBorder(0, LABEL_SPACING, 0, LABEL_SPACING));
		lblMinThreshold.setBorder(new EmptyBorder(0, LABEL_SPACING, 0, LABEL_SPACING));
		lblMaxThreshold.setBorder(new EmptyBorder(0, LABEL_SPACING, 0, LABEL_SPACING));
		
		setBackground(Color.GRAY);
		
		setActionListeners();

		buttonPanel.add(btnDecrementDay);
		buttonPanel.add(btnIncrementDay);
		buttonPanel.add(btnSetSchedule);
		buttonPanel.add(btnSetThresholdTemp);
		
		buttonPanel.add(btnEnableDisable);
		buttonPanel.add(btnActivation);
		buttonPanel.add(btnStatus);
		
		labelPanel.add(lblSystemDate);
		labelPanel.add(lblMinThreshold);
		labelPanel.add(lblMaxThreshold);
		
		add(buttonPanel, BorderLayout.NORTH);
		add(labelPanel, BorderLayout.CENTER);
	}
	
	private void setActionListeners(){
		btnIncrementDay.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Garden.getInstance().addDays(1);
				lblSystemDate.setText(Formatter.dateLabelFormatter(Garden.getInstance().getDate()));
			}		
		});
		
		btnDecrementDay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Garden.getInstance().minusDays(1);
				lblSystemDate.setText(Formatter.dateLabelFormatter(Garden.getInstance().getDate()));
			}
		});
		
		btnSetSchedule.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnSetSchedule Pressed");
				UserInterface.clearContainer();
				UserInterface.addToContainer(new SchedulePanel(UserInterface.getUIWidth(),
						UserInterface.getUIHeight() - 87), BorderLayout.NORTH);
				UserInterface.addToContainer(new ControlPanel(width, 100), BorderLayout.SOUTH);
			}
		});
		
		btnSetThresholdTemp.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnSetThresholdTemp Pressed");
				UserInterface.clearContainer();
				UserInterface.addToContainer(new ThresholdTempPanel(UserInterface.getUIWidth(),
						UserInterface.getUIHeight() - 87), BorderLayout.NORTH);
				UserInterface.addToContainer(new ControlPanel(width, 100), BorderLayout.SOUTH);
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
				UserInterface.clearContainer();
				UserInterface.addToContainer(new ActivationPanel(UserInterface.getUIWidth(),
						UserInterface.getUIHeight() - 87), BorderLayout.NORTH);
				UserInterface.addToContainer(new ControlPanel(width, 100), BorderLayout.SOUTH);
			}		
		});
		
		btnStatus.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnSetStatus Pressed");
				UserInterface.clearContainer();
				UserInterface.addToContainer(new StatusPanel(UserInterface.getUIWidth(),
						UserInterface.getUIHeight() - 87), BorderLayout.NORTH);
				UserInterface.addToContainer(new ControlPanel(width, 100), BorderLayout.SOUTH);
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
