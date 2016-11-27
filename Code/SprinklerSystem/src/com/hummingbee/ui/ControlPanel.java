package com.hummingbee.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.hummingbee.system.Garden;

public class ControlPanel extends JPanel {
	private static final int BUTTON_WIDTH = 200;
	private static final int BUTTON_HEIGHT = 40;
	
	private JButton btnIncTemp;
	private JButton btnDecTemp;
	private JButton btnConfig;
	private JButton btnUsage;
	
	private JLabel lblTemp;
	
	public ControlPanel(int width, int height) {
		super();
		
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
		
		setBackground(Color.GRAY);
		
		add(btnUsage);
		add(btnConfig);
		add(btnDecTemp);
		add(btnIncTemp);
		add(lblTemp);
	}
	
	private static String getTemperatureFormatter(double degrees) {
		return "Temperature: " + degrees + " °F";
	}
	
}
