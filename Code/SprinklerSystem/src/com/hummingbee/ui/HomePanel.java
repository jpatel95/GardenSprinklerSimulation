package com.hummingbee.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.sun.prism.Image;

public class HomePanel extends JPanel {
	private NorthClusterPanel northClusterPanel;
	private EastClusterPanel eastClusterPanel;
	private SouthClusterPanel southClusterPanel;
	private WestClusterPanel westClusterPanel;
	private int width, height;
	private HousePanel housePanel;

	public HomePanel(int width, int height) {
		super(new BorderLayout());

		this.width = width;
		this.height = height;
		
		setPreferredSize(new Dimension(width, height));
		
		northClusterPanel = new NorthClusterPanel();
		eastClusterPanel = new EastClusterPanel();
		southClusterPanel = new SouthClusterPanel();
		westClusterPanel = new WestClusterPanel();
		
		housePanel = new HousePanel();
		
		add(northClusterPanel, BorderLayout.NORTH);
		add(eastClusterPanel, BorderLayout.EAST);
		add(southClusterPanel, BorderLayout.SOUTH);
		add(westClusterPanel, BorderLayout.WEST);
		
		add(housePanel, BorderLayout.CENTER);
	}
	
	private class HousePanel extends JPanel {
		private BufferedImage image = null;
		
		public HousePanel(){
			try {                
				image = ImageIO.read(new File("resources/house.gif"));
			} catch (IOException ex) {
				System.out.println("IMAGE READ ERROR!");
			}
			this.setBackground(new Color(35,155,61));
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
		    if (image != null) {
		    	g.drawImage(image,150,0,600,450,this);
		    }
		}
	}
	
	private class NorthClusterPanel extends JPanel {
		private BufferedImage image = null;
		
		public NorthClusterPanel(){
			this.setPreferredSize(new Dimension(width, (int) (height * 0.15)));
			try {                
				image = ImageIO.read(new File("resources/grass.jpg"));
			} catch (IOException ex) {
				System.out.println("IMAGE READ ERROR!");
			}
			this.setBackground(Color.BLUE);
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
		    if (image != null) {
		        g.drawImage(image,0,0,this);
		        g.drawImage(image,250,0,this);
		        g.drawImage(image,500,0,this);
		        g.drawImage(image,750,0,this);
		        g.drawImage(image,1000,0,this);
		        g.drawImage(image,1250,0,this);
		    }
		}
	}
	
	private class EastClusterPanel extends JPanel {
		private BufferedImage image = null;
		
		public EastClusterPanel(){
			this.setPreferredSize(new Dimension((int) (width * 0.15), (int) (height * 0.7)));
			try {                
				image = ImageIO.read(new File("resources/grass.jpg"));
			} catch (IOException ex) {
				System.out.println("IMAGE READ ERROR!");
			}
			this.setBackground(Color.GREEN);
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
		    if (image != null) {
		        g.drawImage(image,0,0,this);
		        g.drawImage(image,0,150,this);
		        g.drawImage(image,0,300,this);
		        g.drawImage(image,0,450,this);
		        g.drawImage(image,0,600,this);
		    }
		}
	}
	
	private class SouthClusterPanel extends JPanel {
		private BufferedImage image = null;
		
		public SouthClusterPanel(){
			this.setPreferredSize(new Dimension(width, (int) (height * 0.15)));
			try {                
				image = ImageIO.read(new File("resources/grass.jpg"));
			} catch (IOException ex) {
				System.out.println("IMAGE READ ERROR!");
			}
			this.setBackground(Color.GREEN);
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
		    if (image != null) {
		        g.drawImage(image,0,0,this);
		        g.drawImage(image,250,0,this);
		        g.drawImage(image,500,0,this);
		        g.drawImage(image,750,0,this);
		        g.drawImage(image,1000,0,this);
		        g.drawImage(image,1250,0,this);
		    }
		}
	}
	
	private class WestClusterPanel extends JPanel {
		private BufferedImage image = null;
		
		public WestClusterPanel(){
			this.setPreferredSize(new Dimension((int) (width * 0.15), (int) (height * 0.7)));
			try {                
				image = ImageIO.read(new File("resources/grass.jpg"));
			} catch (IOException ex) {
				System.out.println("IMAGE READ ERROR!");
			}
			this.setBackground(Color.GREEN);
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
		    if (image != null) {
		    	g.drawImage(image,0,0,this);
		        g.drawImage(image,0,150,this);
		        g.drawImage(image,0,300,this);
		        g.drawImage(image,0,450,this);
		        g.drawImage(image,0,600,this);
		    }
		}
	}
}
