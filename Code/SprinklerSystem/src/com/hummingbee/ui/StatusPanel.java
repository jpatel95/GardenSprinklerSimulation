package com.hummingbee.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class StatusPanel extends JPanel{
	private JButton btnBack;
	private JScrollPane jScrollPaneFunctional, jScrollPaneNonFunctional;
    private JTextArea textAreaFunctional, textAreaNonFunctional;
    private StringBuilder functionalBuilder,nonFunctionalBuilder;
    
	public StatusPanel(int width, int height){
		super(new BorderLayout());
		setPreferredSize(new Dimension(width, height));
		
		btnBack = new JButton("Back");

		functionalBuilder = new StringBuilder();
		nonFunctionalBuilder = new StringBuilder();
		
		textAreaFunctional = new JTextArea();
		textAreaFunctional.setColumns(40);
		textAreaFunctional.setLineWrap(true);
		textAreaFunctional.setRows(5);
		textAreaFunctional.setWrapStyleWord(true);
		jScrollPaneFunctional = new JScrollPane(textAreaFunctional);
        textAreaFunctional.setEditable(false);
      
        
        textAreaNonFunctional = new JTextArea();
		textAreaNonFunctional.setColumns(40);
		textAreaNonFunctional.setLineWrap(true);
		textAreaNonFunctional.setRows(5);
		textAreaNonFunctional.setWrapStyleWord(true);
		jScrollPaneNonFunctional = new JScrollPane(textAreaNonFunctional);
        textAreaNonFunctional.setEditable(false);
        
        setStringBuilders();
        
        textAreaFunctional.setText(functionalBuilder.toString());
        textAreaNonFunctional.setText(nonFunctionalBuilder.toString());
        
        JPanel textAreaPanel = new JPanel();
        textAreaPanel.add(jScrollPaneFunctional);
        textAreaPanel.add(jScrollPaneNonFunctional);
        
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnBack);
        
        this.add(textAreaPanel, BorderLayout.NORTH);
        this.add(btnPanel, BorderLayout.CENTER);
	}
	
	//TODO
	private void setStringBuilders(){
		functionalBuilder.append("Functional Sprinklers:\n");
		nonFunctionalBuilder.append("Non-Functional Sprinklers:\n");
	}
	
	private void setActionListeners(){
		btnBack.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnBack pressed");
			}
		});
	}
}
