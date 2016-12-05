package com.hummingbee.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.hummingbee.system.Garden;
import com.hummingbee.utils.Formatter;

public class ThresholdTempPanel extends JPanel{
	
	private JLabel lblMin, lblMax;
	private JComboBox <Integer> comboMin, comboMax;
	private JButton btnCommit;
	
	private Integer [] tempArray;
	
	public ThresholdTempPanel(int width, int height){
		super(new BorderLayout());
		setPreferredSize(new Dimension(width, height));
		lblMin = new JLabel("Set Min Temp");
		lblMax = new JLabel("Set Max Temp");
		
		tempArray = new Integer[126];
		for(int i=0;i<126;++i){
			tempArray[i]=i;
		}
		
		comboMin = new JComboBox<Integer>(tempArray);
		comboMax = new JComboBox<Integer>(tempArray);
		
		comboMin.setSelectedIndex(50);
		comboMax.setSelectedIndex(90);

		btnCommit = new JButton("Commit Changes");
		
		setActionListeners();
		
		JPanel btnPanel = new JPanel();
		btnPanel.add(btnCommit);
		
		JPanel settingPanel = new JPanel();
		settingPanel.add(lblMin);
		settingPanel.add(comboMin);
		settingPanel.add(lblMax);
		settingPanel.add(comboMax);
		
		
		this.add(settingPanel, BorderLayout.NORTH);
		this.add(btnPanel, BorderLayout.CENTER);	
	}
	
	private void setActionListeners(){
		btnCommit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnCommit pressed");
				int max = (int) comboMax.getSelectedItem();
				int min = (int) comboMin.getSelectedItem();
				
				Garden.getInstance().setMaxThreshold(max);
				Garden.getInstance().setMinThreshold(min);
			}
		});
	}
}
