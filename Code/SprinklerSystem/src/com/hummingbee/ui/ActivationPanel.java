package com.hummingbee.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.hummingbee.enums.Direction;
import com.hummingbee.system.Garden;
import com.hummingbee.system.ISprinkler;
import com.hummingbee.system.Sprinkler;
import com.hummingbee.system.SprinklerCluster;

public class ActivationPanel extends JPanel{
	private ClusterPanel northClusterPanel, eastClusterPanel, southClusterPanel, westClusterPanel;
	private JLabel titleLabel;
	
	public ActivationPanel(int width, int height){
		super(new BorderLayout());
		setPreferredSize(new Dimension(width, height));
		
		northClusterPanel = new ClusterPanel(Direction.NORTH, width, (int) (height * 0.15));
		eastClusterPanel = new ClusterPanel(Direction.EAST, (int) (width * 0.15), (int) (height * 0.7));
		southClusterPanel = new ClusterPanel(Direction.SOUTH, width, (int) (height * 0.15));
		westClusterPanel = new ClusterPanel(Direction.WEST, (int) (width * 0.15), (int) (height * 0.7));
	
		add(northClusterPanel, BorderLayout.NORTH);
		add(eastClusterPanel, BorderLayout.EAST);
		add(southClusterPanel, BorderLayout.SOUTH);
		add(westClusterPanel, BorderLayout.WEST);
	}
	
	class ClusterPanel extends JPanel {
		//private SprinklerButton[] btnsSprinkler;
		
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
			
//			btnsSprinkler = new SprinklerButton[cluster.getCount() + 1];
//			btnsSprinkler[0] = new SprinklerButton(cluster);
			
//			int btnsIndex = 1;
			
			add(new SprinklerButton(cluster, width, height));
			
			Iterator<Sprinkler> iterator = cluster.getIterator();
			while (iterator.hasNext()) {
				Sprinkler sprinkler = iterator.next();
				add(new SprinklerButton(sprinkler, width, height));
//				btnsSprinkler[btnsIndex] = new SprinklerButton(sprinkler);
//				btnsIndex++;
			}
		}
		
		class SprinklerButton extends JButton {
			private ISprinkler sprinkler;
			
			public SprinklerButton(ISprinkler sprinkler, int width, int height) {
				setText(getButtonTitle(sprinkler));
				setPreferredSize(new Dimension(width, height));
				this.sprinkler = sprinkler;
				addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						sprinkler.activate();
					}
				});
			}
			
			private String getButtonTitle(ISprinkler sprinkler) {
				if (sprinkler instanceof Sprinkler) {
					return "Activate " + sprinkler.getId() + " Sprinkler";
				}
				else {
					return "Activate " + sprinkler.getId() + " Cluster";
				}
			}
			
		}
		
	}
	
}
