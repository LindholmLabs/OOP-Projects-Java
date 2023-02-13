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
		scrollPane.getViewport().add(taskList);
		
		completedTaskCount = 0;
		taskProgress = new JLabel();
		taskProgress.setHorizontalAlignment(JLabel.CENTER);
		taskProgress.setText(completedTaskCount + " out of " + taskList.getComponentCount() + " tasks completed.");
	}
	
	public JScrollPane GetScrollPane() {
		return scrollPane;
	}
	
	public JLabel GetTaskProgress() {
		return taskProgress;
	}
	
	public void Sort() {
		List<Task> sortedList = new ArrayList<Task>();
		for (int i = 0; i < taskList.getComponentCount(); i++) {
			sortedList.add((Task)taskList.getComponent(i));
		}
		
		//if or switch case for sorting method.
		
		/*Collections.sort(sortedList, 
		(o1, o2) -> 
			o1.isComplete()
				.compareTo(o2.isComplete()));
				*/

		/*Collections.sort(sortedList, 
				(o1, o2) -> 
					o1.getText()
						.compareTo(o2.getText()));
						*/
		
		Collections.sort(sortedList, 
				(o1, o2) -> 
					o1.getTaskType()
						.compareTo(o2.getTaskType()));
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
		taskProgress.setText(completedTaskCount + " out of " + taskList.getComponentCount() + " tasks completed.");
		
		//update the frame to show currently visible tasks.
		SwingUtilities.updateComponentTreeUI(taskList);
	}

	@Override
	public void taskUncompleted(Task t) {
		// TODO Auto-generated method stub
		completedTaskCount--;
		taskProgress.setText(completedTaskCount + " out of " + taskList.getComponentCount() + " tasks completed.");
		
		//update the frame to show currently visible tasks.
		SwingUtilities.updateComponentTreeUI(taskList);
	}

	@Override
	public void taskCreated(Task t) {
		// TODO Auto-generated method stub
		t.setTaskListener(this);
		taskList.add(t.getGuiComponent());
		
		taskProgress.setText(completedTaskCount + " out of " + taskList.getComponentCount() + " tasks completed.");
		
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
		
		taskProgress.setText(completedTaskCount + " out of " + taskList.getComponentCount() + " tasks completed.");
		
		//update the frame to show currently visible tasks.
		SwingUtilities.updateComponentTreeUI(taskList);
	}

}
