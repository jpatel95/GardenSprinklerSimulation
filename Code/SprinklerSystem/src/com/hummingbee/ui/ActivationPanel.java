package com.hummingbee.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ActivationPanel extends JPanel{
	private JLabel lblInput;
	private JButton btnBack, btnActivate, btnDeactivate;
	private JTextField txtField;
	
	public ActivationPanel(int width, int height){
		super(new BorderLayout());
		setPreferredSize(new Dimension(width, height));
		
		lblInput = new JLabel("Sprinker ID:");
		txtField = new JTextField(20);
	
		btnBack = new JButton("Back");
		btnActivate = new JButton("Activate");
		btnDeactivate = new JButton("Deactivate");

		
		JPanel textPanel = new JPanel();
		textPanel.add(lblInput);
		textPanel.add(txtField);
		
		JPanel btnPanel = new JPanel();
		btnPanel.add(btnActivate);
		btnPanel.add(btnDeactivate);
		btnPanel.add(btnBack);
		
		setActionListeners();
		
		this.add(textPanel, BorderLayout.NORTH);
		this.add(btnPanel, BorderLayout.CENTER);
	}
	
	private void setActionListeners(){
		btnActivate.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnActivate pressed");
			}
		});
		
		btnDeactivate.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnDeactivate pressed");
			}
		});
		
		btnBack.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnBack pressed");
			}
		});
	}
}
