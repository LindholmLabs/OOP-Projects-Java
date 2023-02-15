package assignment2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import se.his.it401g.todo.Task;
import se.his.it401g.todo.TaskListener;

public class TaskHandler implements TaskListener {
	private JPanel taskList;
	private JScrollPane scrollPane;
	private JLabel taskProgress;
	private int completedTaskCount;
	
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
	
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	
	public JLabel getTaskProgress() {
		return taskProgress;
	}
	
	public JPanel getTaskList() {
		return taskList;
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

	@Override
	public void taskChanged(Task t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void taskCompleted(Task t) {
		// TODO Auto-generated method stub
		completedTaskCount++;
		updateTaskProgress();
		
		//update the frame to show currently visible tasks.
		SwingUtilities.updateComponentTreeUI(taskList);
	}

	@Override
	public void taskUncompleted(Task t) {
		// TODO Auto-generated method stub
		completedTaskCount--;
		updateTaskProgress();
		
		//update the frame to show currently visible tasks.
		SwingUtilities.updateComponentTreeUI(taskList);
	}

	@Override
	public void taskCreated(Task t) {
		// TODO Auto-generated method stub
		t.setTaskListener(this);
		taskList.add(t.getGuiComponent());
		
		updateTaskProgress();
		
		//update the frame to show currently visible tasks.
		SwingUtilities.updateComponentTreeUI(taskList);
	}

	@Override
	public void taskRemoved(Task t) {
		// TODO Auto-generated method stub
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

}
