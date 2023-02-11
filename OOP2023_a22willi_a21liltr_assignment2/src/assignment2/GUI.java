package assignment2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.*;

import se.his.it401g.todo.HomeTask;
import se.his.it401g.todo.StudyTask;
import se.his.it401g.todo.Task;

public class GUI implements ActionListener {
	//create new instance of JFrame
	private JFrame frame = new JFrame();
	private JButton btnNewStudyTask, btnNewHomeTask, btnSort;
	private JPanel menu;
	private Dimension menuBar = new Dimension(50, 50);
	private Dimension window = new Dimension(450, 500);
	private TaskHandler taskHandler;
	
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
		
		btnNewStudyTask = new JButton("Add StudyTask");
		btnNewStudyTask.addActionListener(this);
		menu.add(btnNewStudyTask);
		
		btnNewHomeTask = new JButton("Add HomeTask");
		btnNewHomeTask.addActionListener(this);
		menu.add(btnNewHomeTask);
		
		frame.add(menu, BorderLayout.NORTH);
		
		taskHandler = new TaskHandler();
		frame.add(taskHandler.GetScrollPane());
		frame.add(taskHandler.GetTaskProgress(), BorderLayout.SOUTH);
	}	

	@Override
	public void actionPerformed(ActionEvent e) {		
		if (e.getSource() == btnSort){
			
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
