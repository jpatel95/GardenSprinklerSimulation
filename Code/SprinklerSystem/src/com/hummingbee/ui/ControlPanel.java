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
import com.hummingbee.utils.Formatter;

public class ControlPanel extends JPanel {
	private static final int BUTTON_WIDTH = 160;
	private static final int BUTTON_HEIGHT = 40;
	private int width, height;
	
	private JButton btnIncTemp;
	private JButton btnDecTemp;
	private JButton btnConfig;
	private JButton btnUsage;
	private JButton btnHome;
	private JLabel lblTemp;
	
	public ControlPanel(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		
		btnIncTemp = new JButton("Increase Temp");
		btnDecTemp = new JButton("Decrease Temp");
		btnConfig = new JButton("Config");
		btnUsage = new JButton("View Usage");
		btnHome = new JButton("Home");
		
		lblTemp = new JLabel(Formatter.getTemperatureFormatter(Garden.getInstance().getTemperature()));
		
		btnIncTemp.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		btnDecTemp.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		btnConfig.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		btnUsage.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		btnHome.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		
		btnIncTemp.setFont(new Font("Arial", Font.PLAIN, 16));
		btnDecTemp.setFont(new Font("Arial", Font.PLAIN, 16));
		btnConfig.setFont(new Font("Arial", Font.PLAIN, 16));
		btnUsage.setFont(new Font("Arial", Font.PLAIN, 16));
		btnHome.setFont(new Font("Arial", Font.PLAIN, 16));
		
		lblTemp.setFont(new Font("Arial", Font.PLAIN, 16));
		lblTemp.setForeground(Color.WHITE);
		
		
		setActionListeners();
		
		setBackground(Color.GRAY);
		
		add(btnHome);
		add(btnUsage);
		add(btnConfig);
		add(btnDecTemp);
		add(btnIncTemp);
		add(lblTemp);
	}
	
	private void setActionListeners(){
		btnHome.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				UserInterface.clearContainer();
				JPanel screen = new HomePanel(UserInterface.getUIWidth(),
						UserInterface.getUIHeight() - 70);
				UserInterface.addToContainer(screen, BorderLayout.NORTH);
				UserInterface.addToContainer(new ControlPanel(width, 100), BorderLayout.SOUTH);
				UserInterface.getInstance().setCurrentScreen(screen);
			}
		});
		
		btnIncTemp.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Garden.getInstance().incrementTemperature();
				lblTemp.setText(Formatter.getTemperatureFormatter(Garden.getInstance().getTemperature()));
			}
		});
		
		btnDecTemp.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Garden.getInstance().decrementTemperature();
				lblTemp.setText(Formatter.getTemperatureFormatter(Garden.getInstance().getTemperature()));
			}
		});
		
		btnConfig.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				UserInterface.clearContainer();
				JPanel screen = new ConfigPanel(UserInterface.getUIWidth(),
						UserInterface.getUIHeight() - 70);
				UserInterface.addToContainer(screen, BorderLayout.NORTH);
				UserInterface.addToContainer(new ControlPanel(width, 100), BorderLayout.SOUTH);
				UserInterface.getInstance().setCurrentScreen(screen);
			}
		});
		
		btnUsage.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				UserInterface.clearContainer();
				JPanel screen = new UsagePanel(UserInterface.getUIWidth(),
						UserInterface.getUIHeight() - 70);
				UserInterface.addToContainer(screen, BorderLayout.NORTH);
				UserInterface.addToContainer(new ControlPanel(width, 100), BorderLayout.SOUTH);
				UserInterface.getInstance().setCurrentScreen(screen);
			}
		});
	}
}
