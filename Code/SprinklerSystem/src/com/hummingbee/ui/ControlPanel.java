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

public class ControlPanel {
	
	public static class UserInterface extends JFrame {
		private static final int WIDTH = 1400;
		private static final int HEIGHT = 1000;
		
		private static final int BUTTON_WIDTH = 200;
		private static final int BUTTON_HEIGHT = 40;
		
		private JPanel northClusterPanel;
		private JPanel eastClusterPanel;
		private JPanel southClusterPanel;
		private JPanel westClusterPanel;
		
		private JPanel housePanel;
		
		private JPanel controlPanel;
		
		private JPanel southPanel;
		
		private JButton btnIncTemp;
		private JButton btnDecTemp;
		private JButton btnConfig;
		private JButton btnUsage;
		
		private JLabel lblTemp;
		
		public UserInterface() {
			super("User Interface");
			setPreferredSize(new Dimension(WIDTH, HEIGHT));
			
			Container container = getContentPane();
			container.setLayout(new BorderLayout());
			
			northClusterPanel = new JPanel();
			eastClusterPanel = new JPanel();
			southClusterPanel = new JPanel();
			westClusterPanel = new JPanel();
			
			housePanel = new JPanel();
			
			controlPanel = new JPanel();
			
			southPanel = new JPanel(new BorderLayout());
			
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
			
			northClusterPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT / 5));
			eastClusterPanel.setPreferredSize(
					new Dimension(WIDTH / 6, (int) (HEIGHT * (5.0 / 8))));
			southClusterPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT / 5));
			westClusterPanel.setPreferredSize(
					new Dimension(WIDTH / 6, (int) (HEIGHT * (5.0 / 8))));
			
			//controlPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT / 10));
			
			northClusterPanel.setBackground(Color.BLUE);
			eastClusterPanel.setBackground(Color.RED);
			southClusterPanel.setBackground(Color.YELLOW);
			westClusterPanel.setBackground(Color.GREEN);
			
			
			housePanel.setBackground(Color.BLACK);
			
			controlPanel.setBackground(Color.GRAY);
			
			controlPanel.add(btnUsage);
			controlPanel.add(btnConfig);
			controlPanel.add(btnDecTemp);
			controlPanel.add(btnIncTemp);
			controlPanel.add(lblTemp, BorderLayout.EAST);
			
			southPanel.add(southClusterPanel, BorderLayout.NORTH);
			southPanel.add(controlPanel, BorderLayout.SOUTH);
			
			container.add(northClusterPanel, BorderLayout.NORTH);
			container.add(eastClusterPanel, BorderLayout.EAST);
			container.add(southPanel, BorderLayout.SOUTH);
			container.add(westClusterPanel, BorderLayout.WEST);
			
			container.add(housePanel, BorderLayout.CENTER);
			
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
	}
	
	private static String getTemperatureFormatter(double degrees) {
		return "Temperature: " + degrees + " °F";
	}
	
	public static void main(String[] args) {
		new UserInterface();
	}
}
