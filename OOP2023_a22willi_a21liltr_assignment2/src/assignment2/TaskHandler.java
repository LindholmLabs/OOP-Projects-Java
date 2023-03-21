package assignment2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import se.his.it401g.todo.Task;
import se.his.it401g.todo.TaskListener;

public class TaskHandler implements TaskListener, Comparator<Task> {
	private JPanel taskList;
	private JScrollPane scrollPane;
	private JLabel taskProgress;
	private int completedTaskCount;
	
	/**
	 * Constructor for taskHandler class.
	 * 
	 * Creates tasks and adds them to the Scrollpane
	 * Also handles the tasks states (completed, uncompleted)
	 * And worktasks color.
	 */
	public TaskHandler() {
		taskList = new JPanel();
		taskList.setLayout(new BoxLayout(taskList, BoxLayout.PAGE_AXIS));
		
		scrollPane = new JScrollPane();
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.getViewport().add(taskList);
		
		completedTaskCount = 0;
		taskProgress = new JLabel();
		taskProgress.setHorizontalAlignment(JLabel.CENTER);
		updateTaskProgress();
	}
	
	/**
	 * @return scrollPane of type JScrollPane
	 */
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	
	/**
	 * @return taskProgress of type JLabel
	 */
	public JLabel getTaskProgress() {
		return taskProgress;
	}
	
	/**
	 * @return taskList of type JPanel
	 */
	public JPanel getTaskList() {
		return taskList;
	}
	
	/**
	 * Sorts components in taskList according to specific requirements.
	 * @param sortingOption
	 */
	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void sort(String sortingOption) {
		List<Task> sortedList = new ArrayList<Task>();
		for (int i = 0; i < taskList.getComponentCount(); i++) {
			sortedList.add((Task)taskList.getComponent(i));
		}
		
		switch (sortingOption)
		{
		    case "Alphabetical":
		    	Collections.sort(sortedList, 
						(o1, o2) -> 
							o1.getText().toLowerCase()
								.compareTo(o2.getText().toLowerCase()));
		    	break;
		    case "Type":
		    	Collections.sort(sortedList, 
						(o1, o2) -> 
							o1.getTaskType()
								.compareTo(o2.getTaskType()));
		    	break;
		    case "Completed":
		    	Collections.sort(sortedList, 
						(o1, o2) ->
							Boolean.compare(o1.isComplete(), o2.isComplete()));
		    	break;
		    default:
		    	
		}
		taskList.removeAll();
		
		for (int i = 0; i < sortedList.size(); i++) {
			taskList.add(sortedList.get(i).getGuiComponent());
			
		}
		
		//update the frame to show currently visible tasks.
		SwingUtilities.updateComponentTreeUI(taskList);
	}
	
	/**
	 * Runs when a task is marked as complete
	 * updates task progress label
	 * and updates the swing component
	 */
	@Override
	public void taskCompleted(Task t) {
		// TODO Auto-generated method stub
		completedTaskCount++;
		updateTaskProgress();
		
		//update the frame to show currently visible tasks.
		SwingUtilities.updateComponentTreeUI(taskList);
	}

	/**
	 * Runs when a task is unmarked as completed
	 * updates task progress label
	 * and updates the swing component
	 */
	@Override
	public void taskUncompleted(Task t) {
		// TODO Auto-generated method stub
		completedTaskCount--;
		updateTaskProgress();
		
		//update the frame to show currently visible tasks.
		SwingUtilities.updateComponentTreeUI(taskList);
	}

	/**
	 * Runs when a task is created
	 * updates task progress label
	 * and updates the swing component
	 */
	@Override
	public void taskCreated(Task t) {
		// TODO Auto-generated method stub
		t.setTaskListener(this);
		taskList.add(t.getGuiComponent());
		
		updateTaskProgress();
		
		//update the frame to show currently visible tasks.
		SwingUtilities.updateComponentTreeUI(taskList);
	}
	
	/**
	 * Runs when a task is removed
	 * updates task progress label
	 * and updates the swing component
	 */
	@Override
	public void taskRemoved(Task t) {
		taskList.remove(t.getGuiComponent());
		if (t.isComplete()) {
			completedTaskCount--;
		}
		
		updateTaskProgress();
		
		//update the frame to show currently visible tasks.
		SwingUtilities.updateComponentTreeUI(taskList);
	}
	
	/*
	 * Updates the task progress to show the current amount
	 * of tasks.
	 */
	private void updateTaskProgress() {
		taskProgress.setText(completedTaskCount + " out of " + taskList.getComponentCount() + " tasks completed.");
	}

	@Override
	public void taskChanged(Task t) {
		// TODO Auto-generated method stub
		
	}
}
