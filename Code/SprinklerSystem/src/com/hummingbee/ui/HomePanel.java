package com.hummingbee.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class HomePanel extends JPanel {
	private JPanel northClusterPanel;
	private JPanel eastClusterPanel;
	private JPanel southClusterPanel;
	private JPanel westClusterPanel;
	
	private JPanel housePanel;

	public HomePanel(int width, int height) {
		super(new BorderLayout());
		
		setPreferredSize(new Dimension(width, height));
		
		northClusterPanel = new JPanel();
		eastClusterPanel = new JPanel();
		southClusterPanel = new JPanel();
		westClusterPanel = new JPanel();
		
		housePanel = new JPanel();
		
		northClusterPanel.setPreferredSize(new Dimension(width, (int) (height * 0.15)));
		eastClusterPanel.setPreferredSize(
				new Dimension((int) (width * 0.15), (int) (height * 0.7)));
		southClusterPanel.setPreferredSize(new Dimension(width, (int) (height * 0.15)));
		westClusterPanel.setPreferredSize(
				new Dimension((int) (width * 0.15), (int) (height * 0.7)));
		
		northClusterPanel.setBackground(Color.BLUE);
		eastClusterPanel.setBackground(Color.RED);
		southClusterPanel.setBackground(Color.YELLOW);
		westClusterPanel.setBackground(Color.GREEN);
		
		housePanel.setBackground(Color.BLACK);
		
		add(northClusterPanel, BorderLayout.NORTH);
		add(eastClusterPanel, BorderLayout.EAST);
		add(southClusterPanel, BorderLayout.SOUTH);
		add(westClusterPanel, BorderLayout.WEST);
		
		add(housePanel, BorderLayout.CENTER);
	}
}
