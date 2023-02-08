package assignment2;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.*;

import se.his.it401g.todo.HomeTask;
import se.his.it401g.todo.StudyTask;
import se.his.it401g.todo.Task;

public class GUI implements ActionListener {
	//create new instance of JFrame
	private JFrame frame = new JFrame();
	private JButton btnNewStudyTask, btnNewHomeTask;
	private JPanel menu, taskList;
	private Dimension menuBar = new Dimension(50, 50);
	private Dimension window = new Dimension(400, 500);
	
	/*
	 * Initialize the gui using Swing
	 */
	public GUI() {		
		//set frame variables and make frame visible
		frame.setSize(window);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//add buttons for creating new tasks (inside panel)
		menu = new JPanel();
		menu.setSize(menuBar);
		taskList = new JPanel();
		taskList.setLayout(new BoxLayout(taskList, BoxLayout.PAGE_AXIS));
		
		btnNewStudyTask = new JButton("Add StudyTask");
		btnNewStudyTask.addActionListener(this);
		menu.add(btnNewStudyTask);
		
		btnNewHomeTask = new JButton("Add HomeTask");
		btnNewHomeTask.addActionListener(this);
		menu.add(btnNewHomeTask);
		
		frame.add(menu, BorderLayout.NORTH);
		frame.add(taskList);
	}
	
	/*
	 * Adds a component to the frame.
	 * 
	 * @param component	The component to add.
	 */
	private void addTask(Task task) {
		taskList.add(task.getGuiComponent());
		
		//update the frame to show currently visible tasks.
		SwingUtilities.updateComponentTreeUI(taskList);
	}
	
	public void removeTask() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnNewHomeTask) {
			addTask(new HomeTask());
		} 
		else if (e.getSource() == btnNewStudyTask) {
			addTask(new StudyTask());
		}
	}
}
