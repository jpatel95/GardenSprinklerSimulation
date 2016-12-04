package com.hummingbee.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.hummingbee.enums.Direction;
import com.hummingbee.system.Garden;
import com.hummingbee.system.ISprinkler;
import com.hummingbee.system.Sprinkler;
import com.hummingbee.system.SprinklerCluster;

public class EnablePanel extends JPanel {
	private ClusterPanel northClusterPanel, eastClusterPanel, southClusterPanel, westClusterPanel;
	private JLabel titleLabel;
	
	public EnablePanel(int width, int height){
		super(new BorderLayout());
		setPreferredSize(new Dimension(width, height));
		
		northClusterPanel = new ClusterPanel(Direction.NORTH, width, (int) (height * 0.15));
		eastClusterPanel = new ClusterPanel(Direction.EAST, (int) (width * 0.15), (int) (height * 0.7));
		southClusterPanel = new ClusterPanel(Direction.SOUTH, width, (int) (height * 0.15));
		westClusterPanel = new ClusterPanel(Direction.WEST, (int) (width * 0.15), (int) (height * 0.7));
		
		titleLabel = new JLabel("Toggle sprinkler and sprinkler cluster functionality");
		titleLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setVerticalAlignment(JLabel.CENTER);
	
		add(northClusterPanel, BorderLayout.NORTH);
		add(eastClusterPanel, BorderLayout.EAST);
		add(southClusterPanel, BorderLayout.SOUTH);
		add(westClusterPanel, BorderLayout.WEST);
		add(titleLabel, BorderLayout.CENTER);
		
		add(titleLabel);
	}
	
	class ClusterPanel extends JPanel {
		private SprinklerButton[] btnsSprinkler;
		
		public ClusterPanel(Direction direction, int width, int height) {
			setPreferredSize(new Dimension(width, height));
			
			boolean isHorizontal = (height >= width);
			
			SprinklerCluster cluster = Garden.getInstance().getCluster(direction);
			
			int btnCount = cluster.getCount() + 1;
			
			int btnWidth, btnHeight;
			
			if (isHorizontal) {
				setLayout(new GridLayout(btnCount, 1));
				btnWidth = width / btnCount;
				btnHeight = height;
			}
			else {
				setLayout(new GridLayout(1, btnCount));
				btnWidth = width;
				btnHeight = height / btnCount;
			}
			
			btnsSprinkler = new SprinklerButton[cluster.getCount() + 1];
			btnsSprinkler[0] = new SprinklerButton(cluster, btnWidth, btnHeight);
			
			add(btnsSprinkler[0]);
			
			int btnsIndex = 1;
			Iterator<Sprinkler> iterator = cluster.getIterator();
			while (iterator.hasNext()) {
				Sprinkler sprinkler = iterator.next();
				btnsSprinkler[btnsIndex] = new SprinklerButton(sprinkler, btnWidth, btnHeight);
				add(btnsSprinkler[btnsIndex]);
				btnsIndex++;
			}
		}
		
		public void setBtnTitles() {
			for (int i = 0; i < btnsSprinkler.length; i++) {
				btnsSprinkler[i].setBtnTitle();
			}
		}
		
		class SprinklerButton extends JButton {
			private ISprinkler sprinkler;
			
			public SprinklerButton(ISprinkler sprinkler, int width, int height) {
				setPreferredSize(new Dimension(width, height));
				this.sprinkler = sprinkler;
				setText(getButtonTitle(sprinkler));
				
				addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (sprinkler.isFunctional()) {
							sprinkler.setFunctional(false);
						}
						else {
							sprinkler.setFunctional(true);
						}
						setBtnTitles();
					}
				});
			}
			
			public void setBtnTitle() {
				setText(getButtonTitle(sprinkler));
			}
			
			private String getButtonTitle(ISprinkler sprinkler) {
				String verb;
				if (sprinkler.isFunctional()) {
					verb = "Disable ";
				}
				else {
					verb = "Enable ";
				}
				
				if (sprinkler instanceof Sprinkler) {
					return verb + sprinkler.getId() + " Sprinkler";
				}
				else {
					return verb + sprinkler.getId() + " Cluster";
				}
			}
			
		}
		
	}
	
}
