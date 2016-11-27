package com.hummingbee.ui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.hummingbee.system.DayUsage;
import com.hummingbee.system.SystemDate;

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
		
		HashMap<String, Double> testTotalUsages = new HashMap<String, Double>();
		HashMap<String, LinkedList<DayUsage>> testUsageHistory = new HashMap<String, LinkedList<DayUsage>>();
		testTotalUsages.put("NORTH", 15.5);
		testTotalUsages.put("EAST", 10.3);
		testTotalUsages.put("SOUTH", 11.0);
		testTotalUsages.put("WEST", 7.8);
		
		LinkedList<DayUsage> usageList = new LinkedList<DayUsage>();
		usageList.add(new DayUsage(LocalDate.now(), 3.0));
		usageList.add(new DayUsage(LocalDate.now().minusDays(1), 3.5));
		usageList.add(new DayUsage(LocalDate.now().minusDays(2), 4.5));
		usageList.add(new DayUsage(LocalDate.now().minusDays(3), 1.5));
		usageList.add(new DayUsage(LocalDate.now().minusDays(4), 0.5));
		usageList.add(new DayUsage(LocalDate.now().minusDays(5), 1.2));
		testUsageHistory.put("NORTH", usageList);
		
		
		BarChart usagesChart = new BarChart(testTotalUsages, (int) (width * 0.7), (int) (height * 0.35));
		GraphPanel graphPanel = new GraphPanel(testUsageHistory, (int) (width * 0.7), (int) (height * 0.35));
		
		add(btnNorthCluster, BorderLayout.NORTH);
		add(btnEastCluster, BorderLayout.EAST);
		add(btnSouthCluster, BorderLayout.SOUTH);
		add(btnWestCluster, BorderLayout.WEST);
		
		usages.add(graphPanel, BorderLayout.NORTH);
		usages.add(usagesChart, BorderLayout.SOUTH);
		
		add(usages, BorderLayout.CENTER);
	}
	
	class GraphPanel extends JPanel {
		private int width;
	    private int height;
	    private int padding = 35;
	    private int labelPadding = 25;
	    private Color lineColor = new Color(44, 102, 230, 180);
	    private Color pointColor = new Color(100, 100, 100, 180);
	    private Color gridColor = new Color(200, 200, 200, 200);
	    private final Stroke GRAPH_STROKE = new BasicStroke(2f);
	    private int pointWidth = 4;
	    private int numberYDivisions = 10;
	    private HashMap<String, LinkedList<DayUsage>> usages;

	    public GraphPanel(HashMap<String, LinkedList<DayUsage>> usages, int width, int height) {
	    	super(new BorderLayout());
	        this.usages = usages;
	        this.width = width;
	        this.height = height;
	        setPreferredSize(new Dimension(width, height));
	        JLabel title = new JLabel("Usage Past Week");
	        title.setFont(new Font("Arial", Font.BOLD, 20));
	        title.setHorizontalAlignment(JLabel.CENTER);
	        add(title, BorderLayout.NORTH);
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        Graphics2D g2 = (Graphics2D) g;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	        double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / (getMaxLength() - 1);
	        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMaxUsage() - getMinUsage());

	        HashMap<String, ArrayList<Point>> graphPoints = new HashMap<String, ArrayList<Point>>();
	        Iterator<String> sprinklerIterator = usages.keySet().iterator();
	        while (sprinklerIterator.hasNext()) {
	        	String key = sprinklerIterator.next();
	        	ArrayList<Point> sprinklerGraphPoints = new ArrayList<Point>();
	        	// displays whole history as line graph or max of a week
	        	List<DayUsage> sprinklerUsages = usages.get(key);
		        for (int i = 0; i < sprinklerUsages.size(); i++) {
		        	int latest = sprinklerUsages.size() - (i + 1);
		        	int x1 = (int) (i * xScale + padding + labelPadding);
		        	int y1 = (int) ((getMaxUsage() - sprinklerUsages.get(latest).getUsage()) * yScale + padding);
		            sprinklerGraphPoints.add(new Point(x1, y1));
		        }
		        graphPoints.put(key, sprinklerGraphPoints);
	        }

	        // draw white background
	        g2.setColor(Color.WHITE);
	        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
	        g2.setColor(Color.BLACK);

	        // create hatch marks and grid lines for y axis.
	        for (int i = 0; i < numberYDivisions + 1; i++) {
	            int x0 = padding + labelPadding;
	            int x1 = pointWidth + padding + labelPadding;
	            int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
	            int y1 = y0;
	            if (usages.size() > 0) {
	                g2.setColor(gridColor);
	                g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
	                g2.setColor(Color.BLACK);
	                String yLabel = ((int) ((getMinUsage() + (getMaxUsage() - getMinUsage()) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "";
	                FontMetrics metrics = g2.getFontMetrics();
	                int labelWidth = metrics.stringWidth(yLabel);
	                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
	            }
	            g2.drawLine(x0, y0, x1, y1);
	        }

	        // and for x axis
	        for (int i = 0; i < getMaxLength(); i++) {
	            if (getMaxLength() > 1) {
	                int x0 = i * (getWidth() - padding * 2 - labelPadding) / (getMaxLength() - 1) + padding + labelPadding;
	                int x1 = x0;
	                int y0 = getHeight() - padding - labelPadding;
	                int y1 = y0 - pointWidth;
	                if ((i % ((int) ((getMaxLength() / 20.0)) + 1)) == 0) {
	                    g2.setColor(gridColor);
	                    g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
	                    g2.setColor(Color.BLACK);
	                    String xLabel = SystemDate.getDate().minusDays(getMaxLength() - (i + 1)) + "";
	                    FontMetrics metrics = g2.getFontMetrics();
	                    int labelWidth = metrics.stringWidth(xLabel);
	                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
	                }
	                g2.drawLine(x0, y0, x1, y1);
	            }
	        }

	        // create x and y axes 
	        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
	        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);

	        Stroke oldStroke = g2.getStroke();
	        g2.setColor(lineColor);
	        g2.setStroke(GRAPH_STROKE);
	        
	        sprinklerIterator = graphPoints.keySet().iterator();
	        while (sprinklerIterator.hasNext()) {
	        	String key = sprinklerIterator.next();
	        	List<Point> pointsList = graphPoints.get(key);
		        for (int i = 0; i < pointsList.size() - 1; i++) {
		            int x1 = pointsList.get(i).x;
		            int y1 = pointsList.get(i).y;
		            int x2 = pointsList.get(i + 1).x;
		            int y2 = pointsList.get(i + 1).y;
		            g2.drawLine(x1, y1, x2, y2);
		        }
	        }

	        g2.setStroke(oldStroke);
	        g2.setColor(pointColor);
	        
	        sprinklerIterator = graphPoints.keySet().iterator();
	        while (sprinklerIterator.hasNext()) {
	        	String key = sprinklerIterator.next();
	        	List<Point> pointsList = graphPoints.get(key);
		        for (int i = 0; i < pointsList.size(); i++) {
		            int x = pointsList.get(i).x - pointWidth / 2;
		            int y = pointsList.get(i).y - pointWidth / 2;
		            int ovalW = pointWidth;
		            int ovalH = pointWidth;
		            g2.fillOval(x, y, ovalW, ovalH);
		        }
	        }
	    }

	    private double getMinUsage() {
	        double minScore = 0;
//	        Iterator<String> iterator = usages.keySet().iterator();
//	        while (iterator.hasNext()) {
//	        	String key = iterator.next();
//		        for (DayUsage dayUsage : usages.get(key)) {
//		            minScore = Math.min(minScore, dayUsage.getUsage());
//		        }
//	        }
	        return minScore;
	    }

	    private double getMaxUsage() {
	        double maxScore = Double.MIN_VALUE;
	        Iterator<String> iterator = usages.keySet().iterator();
	        while (iterator.hasNext()) {
	        	String key = iterator.next();
		        for (DayUsage dayUsage : usages.get(key)) {
		        	maxScore = Math.max(maxScore, dayUsage.getUsage());
		        }
	        }
	        return maxScore;
	    }

	    private int getMaxLength() {
	    	int maxLength = 0;
	    	Iterator<String> iterator = usages.keySet().iterator();
	        while (iterator.hasNext()) {
	        	String key = iterator.next();
	        	maxLength = Math.max(maxLength, usages.get(key).size());
	        }
	        return maxLength;
	    }
	    
	    public void setUsages(HashMap<String, LinkedList<DayUsage>> usages) {
	        this.usages = usages;
	        invalidate();
	        this.repaint();
	    }

	    public HashMap<String, LinkedList<DayUsage>> getScores() {
	        return usages;
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
