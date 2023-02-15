package assignment2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;
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
		
		createMenuButton("study", "New StudyTask");
		createMenuButton("home", "New HomeTask");
		createMenuButton("work", "New WorkTask");
		createMenuButton("sort", "Sort");
		
		frame.add(menu, BorderLayout.NORTH);
		
		taskHandler = new TaskHandler();
		frame.add(taskHandler.getScrollPane());
		frame.add(taskHandler.getTaskProgress(), BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}
	
	/*
	 * Create button, add ActionListener to button and add button to GUI.
	 * 
	 * @param key		Used for distinguishing buttons from each other, unique "name".
	 * @param btnText	Button text.
	 */
	private void createMenuButton(String key, String btnText) {
		buttons.put(key, new JButton(btnText)); //Create new button and add to Map using key value.
		buttons.get(key).addActionListener(this); //add ActionListener to button.
		menu.add(buttons.get(key)); // add button to GUI.
	}
	
	
	/*
	 * Used to create generic dialogue boxes
	 * 
	 * @param 	String[] options List of options available to the user.
	 * @param 	String label 	 Title shown to user during selection process.
	 * @return	String 			 value of selected option.
	 */
	public String selectionBox(String label, String[] options) {
		Object selected = JOptionPane.showInputDialog(null, "Choose sorting type", "Selection", JOptionPane.DEFAULT_OPTION, null, options, "0");
		if ( selected != null ) {
		    return selected.toString(); //return selected option.
		}
		//If no selection is made (user cancelled) return empty string.
		return "";
	}
	
	
	/*
	 * Handles actions performed by the user e.g. button clicks.
	 * 
	 * @param ActionEvent e tracks all occurring events.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().equals(buttons.get("sort"))) {
			if (taskHandler.getTaskList().getComponentCount() != 0) {
				//Open dialogue box where user can choose sorting algorithm
				String[] sortingOptions = {"Alphabetical", "Type", "Completed"};
				String selectedOption = selectionBox("Sort By:", sortingOptions);
				taskHandler.sort(selectedOption);
			}
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
				String[] colors = {"BLACK", "RED", "GREEN", "BLUE"};
				String selectedColor = selectionBox("Choose color", colors);
				t = new WorkTask(selectedColor);
			}
			taskHandler.taskCreated(t);	
		}	
	}
}
