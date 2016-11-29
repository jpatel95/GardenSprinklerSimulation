package com.hummingbee.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.hummingbee.system.Garden;
import com.hummingbee.ui.MainUI.UserInterface;

public class ControlPanel extends JPanel {
	private static final int BUTTON_WIDTH = 200;
	private static final int BUTTON_HEIGHT = 40;
	private int width, height;
	
	private JButton btnIncTemp;
	private JButton btnDecTemp;
	private JButton btnConfig;
	private JButton btnUsage;
	private JLabel lblTemp;
	
	public ControlPanel(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		
		btnIncTemp = new JButton("Increase Temp");
		btnDecTemp = new JButton("Decrease Temp");
		btnConfig = new JButton("Config");
		btnUsage = new JButton("View Usage");
		
		lblTemp = new JLabel(getTemperatureFormatter(Garden.getTemperature()));
		
		btnIncTemp.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		btnDecTemp.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		btnConfig.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		btnUsage.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		
		btnIncTemp.setFont(new Font("Arial", Font.PLAIN, 20));
		btnDecTemp.setFont(new Font("Arial", Font.PLAIN, 20));
		btnConfig.setFont(new Font("Arial", Font.PLAIN, 20));
		btnUsage.setFont(new Font("Arial", Font.PLAIN, 20));
		
		lblTemp.setFont(new Font("Arial", Font.PLAIN, 20));
		lblTemp.setForeground(Color.WHITE);
		
		
		setActionListeners();
		
		setBackground(Color.GRAY);
		add(btnUsage);
		add(btnConfig);
		add(btnDecTemp);
		add(btnIncTemp);
		add(lblTemp);
	}
	
	private static String getTemperatureFormatter(double degrees) {
		return "Temperature: " + degrees + " ºF";
	}
	
	private void setActionListeners(){
		btnIncTemp.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnIncTemp pressed");
				UserInterface.getInstance().getGarden().incrementTemperature();
				lblTemp.setText(getTemperatureFormatter(Garden.getTemperature()));
			}
		});
		
		btnDecTemp.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnDecTemp pressed");
				UserInterface.getInstance().getGarden().decrementTemperature();
				lblTemp.setText(getTemperatureFormatter(Garden.getTemperature()));
			}
		});
		
		btnConfig.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnConfig pressed");
				UserInterface.getInstance().clearContainer();
				UserInterface.getInstance().addToContainer(new ConfigPanel(width, height), BorderLayout.CENTER);
			}
		});
		
		btnUsage.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnUsage pressed");
				UserInterface.getInstance().clearContainer();
				UserInterface.getInstance().addToContainer(new UsagePanel(width, height-100), BorderLayout.CENTER);
				UserInterface.getInstance().addToContainer(new ControlPanel(width, 100), BorderLayout.SOUTH);
			}
		});
	}
}
