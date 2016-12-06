package com.hummingbee.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.hummingbee.enums.Direction;
import com.hummingbee.system.Garden;
import com.hummingbee.system.Sprinkler;

public class StatusPanel extends JPanel{
	private JScrollPane jScrollPaneFunctional, jScrollPaneNonFunctional;
    private JTextArea textAreaFunctional, textAreaNonFunctional;
    private StringBuilder functionalBuilder,nonFunctionalBuilder;
    
	public StatusPanel(int width, int height){
		super(new BorderLayout());
		setPreferredSize(new Dimension(width, height));
		
		functionalBuilder = new StringBuilder();
		nonFunctionalBuilder = new StringBuilder();
		
		textAreaFunctional = new JTextArea();
		textAreaFunctional.setColumns(40);
		textAreaFunctional.setLineWrap(true);
		textAreaFunctional.setRows(15);
		textAreaFunctional.setWrapStyleWord(true);
		jScrollPaneFunctional = new JScrollPane(textAreaFunctional);
        textAreaFunctional.setEditable(false);
      
        
        textAreaNonFunctional = new JTextArea();
		textAreaNonFunctional.setColumns(40);
		textAreaNonFunctional.setLineWrap(true);
		textAreaNonFunctional.setRows(15);
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
        
        this.add(textAreaPanel, BorderLayout.NORTH);
        this.add(btnPanel, BorderLayout.CENTER);
	}
	
	//TODO
	private void setStringBuilders(){
		functionalBuilder.append("Functional Sprinklers:\n");
		nonFunctionalBuilder.append("Non-Functional Sprinklers:\n");
		
		Garden g = Garden.getInstance();
		Iterator<Sprinkler> iterator = g.getCluster(Direction.NORTH).getIterator();
		while(iterator.hasNext()){
			Sprinkler s = iterator.next();
			if(s.isFunctional()){
				functionalBuilder.append("\t" + s.getId() + "\n");
			} else {
				nonFunctionalBuilder.append("\t" + s.getId() + "\n");
			}
		}
		
		iterator = g.getCluster(Direction.EAST).getIterator();
		while(iterator.hasNext()){
			Sprinkler s = iterator.next();
			if(s.isFunctional()){
				functionalBuilder.append("\t" + s.getId() + "\n");
			} else {
				nonFunctionalBuilder.append("\t" + s.getId() + "\n");
			}
		}
		
		iterator = g.getCluster(Direction.SOUTH).getIterator();
		while(iterator.hasNext()){
			Sprinkler s = iterator.next();
			if(s.isFunctional()){
				functionalBuilder.append("\t" + s.getId() + "\n");
			} else {
				nonFunctionalBuilder.append("\t" + s.getId() + "\n");
			}
		}
		
		iterator = g.getCluster(Direction.WEST).getIterator();
		while(iterator.hasNext()){
			Sprinkler s = iterator.next();
			if(s.isFunctional()){
				functionalBuilder.append("\t" + s.getId() + "\n");
			} else {
				nonFunctionalBuilder.append("\t" + s.getId() + "\n");
			}
		}
	}
	
	private void setActionListeners(){
		//If needed
	}
}
