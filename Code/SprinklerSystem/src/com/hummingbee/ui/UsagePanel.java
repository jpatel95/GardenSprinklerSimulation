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
import java.awt.geom.AffineTransform;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.hummingbee.enums.Direction;
import com.hummingbee.system.DayUsage;
import com.hummingbee.system.Garden;
import com.hummingbee.system.Sprinkler;
import com.hummingbee.system.SprinklerCluster;
import com.hummingbee.system.SystemDate;

public class UsagePanel extends JPanel {

	private JButton btnNorthCluster;
	private JButton btnEastCluster;
	private JButton btnSouthCluster;
	private JButton btnWestCluster;
	
	private JPanel usages;
	
	private int width;
	private int height;
	
	public UsagePanel(int width, int height) {
		super(new BorderLayout());
		
		setPreferredSize(new Dimension(width, height));
		
		this.width = width;
		this.height = height;
		
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
		
		addActionListeners();
		
		HashMap<String, Double> testTotalUsages = new HashMap<String, Double>();
		HashMap<String, LinkedList<DayUsage>> testUsageHistory = new HashMap<String, LinkedList<DayUsage>>();
		testTotalUsages.put("NORTH", 15.5);
		testTotalUsages.put("EAST", 10.3);
		testTotalUsages.put("SOUTH", 11.0);
		testTotalUsages.put("WEST", 7.8);
		
		LinkedList<DayUsage> northUsageList = new LinkedList<DayUsage>();
		northUsageList.add(new DayUsage(LocalDate.now(), 3.0));
		northUsageList.add(new DayUsage(LocalDate.now().minusDays(1), 3.5));
		northUsageList.add(new DayUsage(LocalDate.now().minusDays(2), 4.5));
		northUsageList.add(new DayUsage(LocalDate.now().minusDays(3), 1.5));
		northUsageList.add(new DayUsage(LocalDate.now().minusDays(4), 0.5));
		northUsageList.add(new DayUsage(LocalDate.now().minusDays(5), 1.2));
		testUsageHistory.put("NORTH", northUsageList);
		LinkedList<DayUsage> eastUsageList = new LinkedList<DayUsage>();
		eastUsageList.add(new DayUsage(LocalDate.now(), 1.0));
		eastUsageList.add(new DayUsage(LocalDate.now().minusDays(1), 0.0));
		eastUsageList.add(new DayUsage(LocalDate.now().minusDays(2), 3.4));
		eastUsageList.add(new DayUsage(LocalDate.now().minusDays(3), 0.7));
		eastUsageList.add(new DayUsage(LocalDate.now().minusDays(4), 1.4));
		eastUsageList.add(new DayUsage(LocalDate.now().minusDays(5), 2.3));
		testUsageHistory.put("EAST", eastUsageList);
		
		
		BarChart usagesChart = new BarChart(testTotalUsages, (int) (width * 0.7), (int) (height * 0.3));
		GraphPanel graphPanel = new GraphPanel(testUsageHistory, (int) (width * 0.7), (int) (height * 0.4));
		
		add(btnNorthCluster, BorderLayout.NORTH);
		add(btnEastCluster, BorderLayout.EAST);
		add(btnSouthCluster, BorderLayout.SOUTH);
		add(btnWestCluster, BorderLayout.WEST);
		
		usages.add(graphPanel, BorderLayout.NORTH);
		usages.add(usagesChart, BorderLayout.SOUTH);
		
		add(usages, BorderLayout.CENTER);
	}
	
	private void addActionListeners() {
		btnNorthCluster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawGraphs(Direction.NORTH);
			}
		});
		btnEastCluster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawGraphs(Direction.EAST);
			}
		});
		btnSouthCluster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawGraphs(Direction.SOUTH);
			}
		});
		btnWestCluster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawGraphs(Direction.WEST);
			}
		});
	}
	
	private void drawGraphs(Direction direction) {
		HashMap<String, Double> totalUsages = new HashMap<String, Double>();
		HashMap<String, LinkedList<DayUsage>> usageHistory = new HashMap<String, LinkedList<DayUsage>>();
		
		SprinklerCluster northCluster = Garden.getInstance().getCluster(direction);
		Iterator<Sprinkler> iterator = northCluster.getIterator();
		
		boolean usagesRecorded = false;
		
		while (iterator.hasNext()) {
			Sprinkler sprinkler = iterator.next();
			double usage = sprinkler.getTotalUsage();
			
			if (usage > 0) {
				usagesRecorded = true;
			}
			
			totalUsages.put(sprinkler.getId(), usage);
			usageHistory.put(sprinkler.getId(), sprinkler.getUsageHistory(7));
		}
		
		remove(usages);
		
		usages = new JPanel(new BorderLayout());
		
		if (!usagesRecorded) {
			JLabel label = new JLabel("No Usages recorded for this cluster");
			label.setFont(new Font("Arial", Font.PLAIN, 20));
			label.setHorizontalAlignment(JLabel.CENTER);
			usages.add(label, BorderLayout.CENTER);
			usages.setBackground(Color.WHITE);
		}
		else {
			BarChart usagesChart = new BarChart(totalUsages, (int) (width * 0.7), (int) (height * 0.3));
			GraphPanel graphPanel = new GraphPanel(usageHistory, (int) (width * 0.7), (int) (height * 0.4));
			
			usages.add(graphPanel, BorderLayout.NORTH);
			usages.add(usagesChart, BorderLayout.SOUTH);
		}
		
		add(usages, BorderLayout.CENTER);
		revalidate();
		repaint();
	}
	
	class GraphPanel extends JPanel {
		private int width;
	    private int height;
	    private int padding = 35;
	    private int labelPadding = 15;
	    private Color lineColor = new Color(44, 102, 230, 180);
	    private Color pointColor = new Color(100, 100, 100, 180);
	    private Color gridColor = new Color(200, 200, 200, 200);
	    private final Stroke GRAPH_STROKE = new BasicStroke(2f);
	    private int pointWidth = 4;
	    private int numberYDivisions = 10;
	    private HashMap<String, LinkedList<DayUsage>> usages;
	    private HashMap<String, Color> colorCodes;
	    private JPanel legendPanel;

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
	        
	        colorCodes = new HashMap<String, Color>();
	        
	        Iterator<String> iterator = usages.keySet().iterator();
	        int hue = 0;
	        while (iterator.hasNext()) {
	        	String key = iterator.next();
	        	colorCodes.put(key, new Color(hue));
	        	hue += (360 / usages.size());
	        }
	        
	        legendPanel = new JPanel();
	        legendPanel.add(new JLabel("Legend: "));
	        iterator = usages.keySet().iterator();
	        while (iterator.hasNext()) {
	        	String key = iterator.next();
	        	JLabel label = new JLabel(key);
	        	label.setBorder(BorderFactory.createLineBorder(colorCodes.get(key)));
	        	legendPanel.add(label);
	        }
	        
	        add(legendPanel, BorderLayout.SOUTH);
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
	        g2.setStroke(GRAPH_STROKE);
	        
	        sprinklerIterator = graphPoints.keySet().iterator();
	        while (sprinklerIterator.hasNext()) {
	        	String key = sprinklerIterator.next();
	        	g2.setColor(colorCodes.get(key));
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
	
	public class BarChart extends JPanel {
		 
	    //offsets (padding of actual chart to its border)
	    int leftOffset = 30;
	    int topOffset = 30;
	    int bottomOffset = 30;
	    int rightOffset = 30;
	 
	    //height of X labels (must be significantly smaller than bottomOffset)
	    int xLabelOffset = 25; 
	    //width of Y labels (must be significantly smaller than leftOffset)
	    int yLabelOffset = 15; 
	 
	    //tick widths
	    int majorTickWidth = 10;
	    int secTickWidth = 5;
	    int minorTickWidth = 2;
	 
	    String xAxis = "";
	    String yAxisStr = "Gallons";
	    String title = "Total Usage";
	 
	    int width; //total width of the component
	    int height; //total height of the component
	 
	    Color textColor = Color.BLACK;
	    Color backgroundColor = Color.WHITE;
	 
	    Font textFont = new Font("Arial", Font.BOLD, 20);
	    Font yFont = new Font("Arial", Font.PLAIN, 12);
	    Font xFont = new Font("Arial", Font.BOLD, 12);
	    Font titleFont = new Font("Arial", Font.BOLD, 18);
	 
	    Font yCatFont = new Font("Arial", Font.BOLD, 12);
	    Font xCatFont = new Font("Arial", Font.BOLD, 12);
	 
	    ArrayList<Bar> bars;
	    Axis yAxis;
	    int barWidth = 10;
	 
	    BarChart(HashMap<String, Double> usages, int width, int height) {
	    	setPreferredSize(new Dimension(width, height));
	    	bars = new ArrayList<Bar>();
	    	Iterator<String> keyIterator = usages.keySet().iterator();
	    	
	    	double maxValue = Double.MIN_VALUE;
	    	
	    	while (keyIterator.hasNext()) {
	    		String key = keyIterator.next();
	    		double usage = usages.get(key);
	    		
	    		maxValue = Math.max(usage, maxValue);
	    		
	    		bars.add(new Bar((int) (usage + 0.5), Color.CYAN, key));
	    	}
	    	
	    	int max = (int) (maxValue + 0.5) + 2;
	    	yAxis = new Axis(max, 0, max / 4, 0, 0, "Gallons");
	    	this.yAxisStr = yAxis.yLabel;
	        this.width = width;
	        this.height = height;
	    }
	 
	    @Override
	    protected void paintComponent(Graphics g) {
	 
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-alias!
	                RenderingHints.VALUE_ANTIALIAS_ON);
	 
	        g.drawRect(0, 0, width, height);
	        g2d.setColor(backgroundColor);
	        g.fillRect(0, 0, width, height);
	        g2d.setColor(Color.BLACK);
	 
	        int heightChart = height - (topOffset + bottomOffset);
	        int widthChart = width - (leftOffset + rightOffset);
	 
	        //left
	        g.drawLine(leftOffset, topOffset, leftOffset, heightChart + topOffset);
	 
	        //bottom
	        g.drawLine(leftOffset, heightChart + topOffset, leftOffset + widthChart, heightChart + topOffset);
	 
	        if (this.yAxis.primaryIncrements != 0)
	            drawTick(heightChart, this.yAxis.primaryIncrements, g, Color.BLACK, majorTickWidth);
	        if (this.yAxis.secondaryIncrements != 0)
	            drawTick(heightChart, this.yAxis.secondaryIncrements, g, Color.BLACK, secTickWidth);
	        if (this.yAxis.tertiaryIncrements != 0)
	            drawTick(heightChart, this.yAxis.tertiaryIncrements, g, Color.BLACK, minorTickWidth);
	 
	        drawYLabels(heightChart, this.yAxis.primaryIncrements, g, Color.BLACK);
	 
	        drawBars(heightChart, widthChart, g);
	 
	        drawLabels(heightChart, widthChart, g);
	    }
	 
	    private void drawTick(int heightChart, int increment, Graphics g, Color c, int tickWidth) {
	 
	        int incrementNo = yAxis.maxValue / increment;
	 
	        double factor = ((double) heightChart / (double) yAxis.maxValue);
	 
	        double incrementInPixel = (double) (increment * factor);
	 
	        g.setColor(c);
	 
	        for (int i = 0; i <= incrementNo; i++) {
	            int fromTop = heightChart + topOffset - (int) (i * incrementInPixel);
	            g.drawLine(leftOffset, fromTop, leftOffset + tickWidth, fromTop);
	        }
	    }
	 
	    private void drawYLabels(int heightChart, int increment, Graphics g, Color c) {
	 
	        int incrementNo = yAxis.maxValue / increment;
	 
	        double factor = ((double) heightChart / (double) yAxis.maxValue);
	 
	        int incrementInPixel = (int) (increment * factor);
	 
	        g.setColor(c);
	        FontMetrics fm = getFontMetrics(yCatFont);
	 
	        for (int i = 0; i <= incrementNo; i++) {
	            int fromTop = heightChart + topOffset - (i * incrementInPixel);
	 
	            String yLabel = "" + (i * increment);
	 
	            int widthStr = fm.stringWidth(yLabel);
	            int heightStr = fm.getHeight();
	 
	            g.setFont(yCatFont);
	            g.drawString(yLabel, (leftOffset - yLabelOffset) + (yLabelOffset/2 - widthStr/2), fromTop + (heightStr / 2));
	        }
	    }
	 
	    private void drawBars(int heightChart, int widthChart, Graphics g) {
	 
	        int i = 0;
	        int barNumber = bars.size();
	 
	        int pointDistance = (int) (widthChart / (barNumber + 1));
	 
	        for (Bar bar : bars) {
	 
	            i++;
	 
	            double factor = ((double) heightChart / (double) yAxis.maxValue);
	 
	            int scaledBarHeight = (int) (bar.value * factor);
	 
	            int j = topOffset + heightChart - scaledBarHeight;
	 
	            g.setColor(bar.color);
	            g.fillRect(leftOffset + (i * pointDistance) - (barWidth / 2), j, barWidth, scaledBarHeight);
	 
	            //draw tick
	            g.drawLine(leftOffset + (i * pointDistance),
	                    topOffset + heightChart,
	                    leftOffset + (i * pointDistance),
	                    topOffset + heightChart + 2);
	 
	            FontMetrics fm = getFontMetrics(xCatFont);
	            int widthStr = fm.stringWidth(bar.name);
	            int heightStr = fm.getHeight();
	 
	            g.setFont(xCatFont);
	            g.setColor(Color.BLACK);
	 
	            int xPosition = leftOffset + (i * pointDistance) - (widthStr / 2);
	            int yPosition = topOffset + heightChart + xLabelOffset - heightStr/2;
	 
	            //draw tick
	            g.drawString(bar.name, xPosition, yPosition);
	        }
	    }
	 
	    private void drawLabels(int heightChart, int widthChart, Graphics g) {
	 
	        Graphics2D g2d = (Graphics2D)g;
	 
	        AffineTransform oldTransform = g2d.getTransform();
	 
	        FontMetrics fmY = getFontMetrics(yFont);
	        int yAxisStringWidth = fmY.stringWidth(yAxisStr);
	        int yAxisStringHeight = fmY.getHeight();
	 
	        FontMetrics fmX = getFontMetrics(xFont);
	        int xAxisStringWidth = fmX.stringWidth(yAxisStr);
	        int xAxisStringHeight = fmX.getHeight();
	 
	        FontMetrics fmT = getFontMetrics(titleFont);
	        int titleStringWidth = fmT.stringWidth(title);
	        int titleStringHeight = fmT.getHeight();
	 
	        g2d.setColor(Color.BLACK);
	        //draw tick
	        g2d.rotate(Math.toRadians(270)); //rotates to above out of screen.
	 
	        int translateDown = -leftOffset -(topOffset + heightChart/2 + yAxisStringWidth/2);
	 
	        //starts off being "topOffset" off, so subtract that first
	        int translateLeft = -topOffset + (leftOffset-yLabelOffset)/2 + yAxisStringHeight/2;
	 
	        //pull down, which is basically the left offset, topOffset, then middle it by 
	        //usin chart height and using text height.
	        g2d.translate(translateDown, translateLeft);
	 
	        g2d.setFont(yFont);
	        g2d.drawString(yAxisStr, leftOffset, topOffset);
	 
	        //reset
	        g2d.setTransform(oldTransform);
	 
	        int xAxesLabelHeight = bottomOffset - xLabelOffset;
	 
	        //x label        
	        g2d.setFont(xFont);
	        g2d.drawString(xAxis, widthChart/2 + leftOffset - xAxisStringWidth/2, topOffset + heightChart + xLabelOffset + xAxesLabelHeight/2);
	 
	                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-alias!
	                RenderingHints.VALUE_ANTIALIAS_ON);
	        //title
	        g2d.setFont(titleFont);
	        int titleX = (leftOffset + rightOffset + widthChart)/2 - titleStringWidth/2;
	        int titleY = topOffset/2 + titleStringHeight/2;
	 
	        g2d.drawString(title, titleX, titleY);
	    }
	}
	
	public class Bar {
	 
	    double value; 
	    Color color;
	    String name;
	 
	    Bar(int value, Color color, String name) {
	        this.value = value;
	        this.color = color;
	        this.name = name;
	    }
	}
	
	
	public class Axis {
	 
	    int primaryIncrements = 0; 
	    int secondaryIncrements = 0;
	    int tertiaryIncrements = 0;
	 
	    int maxValue = 100;
	    int minValue = 0;
	 
	    String yLabel;
	 
	    Axis(String name) {
	        this(100, 0, 50, 10, 5, name);
	    }
	 
	    Axis(int primaryIncrements, int secondaryIncrements, int tertiaryIncrements, String name) {
	        this(100, 0, primaryIncrements, secondaryIncrements, tertiaryIncrements, name);
	    }
	 
	    Axis(Integer maxValue, Integer minValue, int primaryIncrements, int secondaryIncrements, int tertiaryIncrements, String name) {
	 
	        this.maxValue = maxValue; 
	        this.minValue = minValue;
	        this.yLabel = name;
	 
	        if (primaryIncrements != 0)
	            this.primaryIncrements = primaryIncrements; 
	        if (secondaryIncrements != 0)
	            this.secondaryIncrements = secondaryIncrements;
	        if (tertiaryIncrements != 0)
	            this.tertiaryIncrements = tertiaryIncrements;
	    }
	}	
}
