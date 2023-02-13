package assignment2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.*;

import se.his.it401g.todo.HomeTask;
import se.his.it401g.todo.StudyTask;
import se.his.it401g.todo.Task;

public class GUI implements ActionListener {
	// TaskHandler creates a new scrollPane and taskList and puts the taskList on the scrollPane.
	// TaskHandler also tracks the amount of tasks created, and the amount of tasks marked completed.
	private TaskHandler taskHandler;
	private JFrame frame = new JFrame();
	private JButton btnNewStudyTask, btnNewHomeTask, btnSort;
	private JPanel menu;
	private Dimension menuBar = new Dimension(50, 50);
	private Dimension window = new Dimension(450, 500);
	
	/*
	 * Initialize the GUI using Swing
	 */
	public GUI() {		
		//set frame variables and make frame visible
		frame.setSize(window);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//add buttons for creating new tasks (inside panel)
		menu = new JPanel();
		menu.setSize(menuBar);
		
		btnNewStudyTask = new JButton("Add StudyTask");
		btnNewStudyTask.addActionListener(this);
		menu.add(btnNewStudyTask);
		
		btnNewHomeTask = new JButton("Add HomeTask");
		btnNewHomeTask.addActionListener(this);
		menu.add(btnNewHomeTask);
		
		btnSort = new JButton("Sort");
		btnSort.addActionListener(this);
		menu.add(btnSort);
		
		frame.add(menu, BorderLayout.NORTH);
		
		taskHandler = new TaskHandler();
		frame.add(taskHandler.GetScrollPane());
		frame.add(taskHandler.GetTaskProgress(), BorderLayout.SOUTH);
	}	

	@Override
	public void actionPerformed(ActionEvent e) {		
		if (e.getSource() == btnSort){
			taskHandler.Sort();
		}
		else {
			Task t;	
			if (e.getSource() == btnNewHomeTask) {
				t = new HomeTask();		
			} 
			else if (e.getSource() == btnNewStudyTask) {
				t = new StudyTask();
			}
			else {
				t = new WorkTask();
			}
			
			taskHandler.taskCreated(t);	
		}	
	}
}
