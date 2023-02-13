package assignment2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import se.his.it401g.todo.HomeTask;
import se.his.it401g.todo.StudyTask;
import se.his.it401g.todo.Task;

public class GUI implements ActionListener {
	// TaskHandler creates a new scrollPane and taskList and puts the taskList on the scrollPane.
	// TaskHandler also tracks the amount of tasks created, and the amount of tasks marked completed.
	private TaskHandler taskHandler;
	private JFrame frame = new JFrame();
	private JPanel menu;
	private Dimension menuBar = new Dimension(50, 50);
	private Dimension window = new Dimension(600, 500);
	private Map<String, JButton> buttons = new HashMap<String, JButton>();
	
	/*
	 * Initialize the GUI using Swing
	 */
	public GUI() {		
		//set frame variables and make frame visible
		frame.setSize(window);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//add buttons for creating new tasks (inside panel)
		menu = new JPanel();
		menu.setSize(menuBar);
		
		createButton("study", "New StudyTask");
		createButton("home", "New HomeTask");
		createButton("work", "New WorkTask");
		createButton("sort", "Sort");
		
		frame.add(menu, BorderLayout.NORTH);
		
		taskHandler = new TaskHandler();
		frame.add(taskHandler.GetScrollPane());
		frame.add(taskHandler.GetTaskProgress(), BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}
	
	/*
	 * 
	 */
	private void createButton(String key, String btnText) {
		buttons.put(key, new JButton(btnText)); //Create new button and add to Map using key value
		buttons.get(key).addActionListener(this); //add actionlistener to button
		menu.add(buttons.get(key)); // add button to GUI
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == buttons.get("work")) {
			System.out.println("Test worked");
		}
		
		if (e.getSource().equals(buttons.get("sort"))) {
			taskHandler.Sort();
		}
		else {
			Task t;	
			if (e.getSource().equals(buttons.get("home"))) {
				t = new HomeTask();
			} 
			else if (e.getSource().equals(buttons.get("study"))) {
				t = new StudyTask();
			}
			else {
				t = new WorkTask();
			}
			
			taskHandler.taskCreated(t);	
		}	
	}
}
