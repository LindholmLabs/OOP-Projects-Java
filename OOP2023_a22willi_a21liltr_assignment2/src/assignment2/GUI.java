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
	private JPanel menu, taskList;
	private JLabel taskProgress;
	private int completedTaskCount;
	private JScrollPane scrollPane;
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
		
		
		taskList = new JPanel();
		taskList.setLayout(new BoxLayout(taskList, BoxLayout.PAGE_AXIS));
		
		scrollPane = new JScrollPane();
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.getViewport().add(taskList);
		
		completedTaskCount = 0;
		taskProgress = new JLabel();
		taskProgress.setHorizontalAlignment(JLabel.CENTER);
		
		frame.add(menu, BorderLayout.NORTH);
		
		frame.add(scrollPane);
		frame.add(taskProgress, BorderLayout.SOUTH);
		
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
	 * Used to create generic dialogueboxes
	 * 
	 * @param 	String[] options List of options available to the user.
	 * @param 	String label 	 Title shown to user during selection process.
	 * @return	String 			 value of selected option.
	 */
	public String SelectionBox(String label, String[] options) {
		Object selected = JOptionPane.showInputDialog(null, "Choose sorting type", "Selection", JOptionPane.DEFAULT_OPTION, null, options, "0");
		if ( selected != null ) {
		    return selected.toString(); //return selected option.
		}
		//If no selection is made (user cancelled) return empty string.
		return "";
	}
	
	public void addTask(Task t) {
		taskList.add(t.getGuiComponent());
		SwingUtilities.updateComponentTreeUI(taskList);
	}
	
	public void removeTask(Task t) {
		taskList.remove(t.getGuiComponent());
		if (t.isComplete()) {
			completedTaskCount--;
		}
	}
	
	public void increaseTaskCount(int ammount) {
		completedTaskCount += ammount;
	}
	
	public void decreaseTaskCount(int ammount) {
		completedTaskCount -= ammount;
	}
	
	public int getTaskCount() {
		return taskList.getComponentCount();
	}
	

	public Task getTaskItem(int i) {
		return (Task)taskList.getComponent(i);
	}
	
	public void clearTaskList() {
		taskList.removeAll();
	}
	
	
	/*
	 * Updates the task progress to show the current amount
	 * of tasks.
	 */
	public void updateTaskProgress() {
		taskProgress.setText(completedTaskCount + " out of " + taskList.getComponentCount() + " tasks completed.");
	}

	
	
	/*
	 * Handles actions performed by the user eg buttonclicks.
	 * 
	 * @param ActionEvent e tracks all occuring events.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().equals(buttons.get("sort"))) {
			//Open dialoguebox where user can choose sorting algorithm
			String[] sortingOptions = {"Alphabetical", "Type", "Completed"};
			String selectedOption = SelectionBox("Choose Sorting type", sortingOptions);
			taskHandler.Sort(selectedOption);
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
