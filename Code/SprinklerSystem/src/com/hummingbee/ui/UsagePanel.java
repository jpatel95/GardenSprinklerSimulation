package com.hummingbee.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class UsagePanel extends JPanel {

	private JButton btnNorthCluster;
	private JButton btnEastCluster;
	private JButton btnSouthCluster;
	private JButton btnWestCluster;
	
	private JPanel usages;
	
	public UsagePanel(int width, int height) {
		super(new BorderLayout());
		
		setPreferredSize(new Dimension(width, height));
		
		btnNorthCluster = new JButton("View North Cluster Usage");
		btnEastCluster = new JButton("<html>View East<br>Cluster Usage</html>");
		btnSouthCluster = new JButton("View South Cluster Usage");
		btnWestCluster = new JButton("<html>View West<br>Cluster Usage</html>");
		
		usages = new JPanel(new BorderLayout());
		
		btnNorthCluster.setPreferredSize(new Dimension(width, (int) (height * 0.15)));
		btnEastCluster.setPreferredSize(
				new Dimension((int) (width * 0.15), (int) (height * 0.7)));
		btnSouthCluster.setPreferredSize(new Dimension(width, (int) (height * 0.15)));
		btnWestCluster.setPreferredSize(
				new Dimension((int) (width * 0.15), (int) (height * 0.7)));
		
		btnNorthCluster.setFont(new Font("Arial", Font.PLAIN, 20));
		btnEastCluster.setFont(new Font("Arial", Font.PLAIN, 20));
		btnSouthCluster.setFont(new Font("Arial", Font.PLAIN, 20));
		btnWestCluster.setFont(new Font("Arial", Font.PLAIN, 20));
		
		btnNorthCluster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnEastCluster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnSouthCluster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnWestCluster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		HashMap<String, Double> testUsages = new HashMap<String, Double>();
		testUsages.put("NORTH", 15.5);
		testUsages.put("EAST", 10.3);
		testUsages.put("SOUTH", 11.0);
		//testUsages.put("WEST", 7.8);
		
		BarChart usagesChart = new BarChart(testUsages, (int) (width * 0.7), (int) (height * 0.35));
		
		add(btnNorthCluster, BorderLayout.NORTH);
		add(btnEastCluster, BorderLayout.EAST);
		add(btnSouthCluster, BorderLayout.SOUTH);
		add(btnWestCluster, BorderLayout.WEST);
		
		usages.add(usagesChart, BorderLayout.SOUTH);
		
		add(usages, BorderLayout.CENTER);
	}
	
	class LineGraph extends JPanel {
		
		public LineGraph() {
			setBackground(Color.WHITE);
		}
		
	}
	
	class BarChart extends JPanel {
		private BarPanel barsPanel;
		private JPanel labelsPanel;
		private JLabel lblTitle;
		
		public BarChart(HashMap<String, Double> usages, int width, int height) {
			super(new BorderLayout());
			setPreferredSize(new Dimension(width, height));
			setBackground(Color.WHITE);
			
			int spaceBetweenBars = 20;
			int barPanelWidth = width - 200;
			int barPanelHeight = height;
			int barWidth = (barPanelWidth - (spaceBetweenBars * (usages.size() - 1))) / usages.size();

			labelsPanel = new JPanel();
			lblTitle = new JLabel("Total Water Usage");
			lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
			lblTitle.setHorizontalAlignment(JLabel.CENTER);
			labelsPanel.setBackground(Color.WHITE);
			
			int scale = 1;
			double max = -1;
			Iterator<String> keyIterator = usages.keySet().iterator();
			
			while (keyIterator.hasNext()) {
				String key = keyIterator.next();
				double value = usages.get(key);
				if (value > max) {
					max = value;
				}
				
				JLabel label = new JLabel(key);
				label.setPreferredSize(new Dimension(barWidth + spaceBetweenBars, 20));
				//label.setHorizontalAlignment(JLabel.CENTER);
				labelsPanel.add(label);
			}
			if (max > 0) {
				scale = (int) ((barPanelHeight / max) - 4);
			}
			
			barsPanel = new BarPanel(barPanelWidth, barPanelHeight, scale, spaceBetweenBars, barWidth, usages);
			
			add(lblTitle, BorderLayout.NORTH);
			add(barsPanel, BorderLayout.CENTER);
			add(labelsPanel, BorderLayout.SOUTH);
		}
		
		class BarPanel extends JPanel {
			int width;
			int height;
			int scale;
			int spaceBetweenBars;
			int barWidth;
			HashMap<String, Double> usages;
			
			public BarPanel(int width, int height, int scale, int spaceBetweenBars, int barWidth, HashMap<String, Double> usages) {
				super();
				setPreferredSize(new Dimension(width, height));
				
				this.width = width;
				this.height = height;
				this.scale = scale;
				this.spaceBetweenBars = spaceBetweenBars;
				this.usages = usages;
				this.barWidth = barWidth;
				
				setBackground(Color.WHITE);
			}
			
			public void paintComponent(Graphics gp) {
				super.paintComponent(gp);
				
				Graphics2D g = (Graphics2D) gp;
				drawBars(g);
			}
			
			private void drawBars(Graphics2D g) {
				Iterator<String> iterator = usages.keySet().iterator();
				int barStart = 20;
				
				while (iterator.hasNext()) {
					g.setPaint(Color.CYAN);
					String key = iterator.next();
					double data = usages.get(key);
					int barHeight = (int) ((data + 0.5) * scale);
					g.fillRect(barStart, height - barHeight, barWidth, barHeight);
					g.setPaint(Color.BLACK);
					g.setFont(new Font("Arial", Font.PLAIN, 15));
					g.drawString(Double.toString(data) + " Gallons", barStart + (barWidth / 4), 20);
					barStart += (barWidth + spaceBetweenBars);
				}
				
			}
			
		}
		
	}
	
}
