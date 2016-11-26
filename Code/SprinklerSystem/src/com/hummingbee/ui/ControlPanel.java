package com.hummingbee.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.hummingbee.system.SprinklerCluster;

public class ControlPanel {
	
	public static class UserInterface extends JFrame {
		private JPanel usagesPanel;
		private JPanel controlPanel;
		private JButton btnAddSprinkler;
		private ArrayList<JButton> btnActivateSprinklers;
		
		public UserInterface() {
			super("User Interface");
			setPreferredSize(new Dimension(500, 500));
			
			Container container = getContentPane();
			container.setLayout(new BorderLayout());
			
			btnActivateSprinklers = new ArrayList<JButton>();
			usagesPanel = new JPanel();
			controlPanel = new JPanel();
			
			
			
			container.add(usagesPanel, BorderLayout.NORTH);
			container.add(controlPanel, BorderLayout.SOUTH);
			
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
	}
	
	public static class Garden {
		private SprinklerCluster sprinklerCluster;
		
	}
	
	public static void main(String[] args) {
		new UserInterface();
	}
}
