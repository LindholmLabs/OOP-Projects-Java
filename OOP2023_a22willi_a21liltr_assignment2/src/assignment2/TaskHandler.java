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
	private GUI gui;
	
	public TaskHandler(GUI gui) {
		this.gui = gui;
		gui.updateTaskProgress();
	}
	
	
	public void Sort(String sortingOption) {
		List<Task> sortedList = new ArrayList<Task>();
		for (int i = 0; i < gui.getTaskCount(); i++) {
			gui.addTask(gui.getTaskItem(i));
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
		gui.clearTaskList();
		
		for (int i = 0; i < sortedList.size(); i++) {
			gui.addTask(sortedList.get(i));
			
		}
	}

	@Override
	public void taskChanged(Task t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void taskCompleted(Task t) {
		// TODO Auto-generated method stub
		gui.increaseTaskCount(1);
		gui.updateTaskProgress();
	}

	@Override
	public void taskUncompleted(Task t) {
		// TODO Auto-generated method stub
		
		gui.decreaseTaskCount(1);
		gui.updateTaskProgress();
	}

	@Override
	public void taskCreated(Task t) {
		// TODO Auto-generated method stub
		t.setTaskListener(this);
		gui.addTask(t);
		
		//update the frame to show currently visible tasks.
		gui.updateTaskProgress();
	}

	@Override
	public void taskRemoved(Task t) {
		// TODO Auto-generated method stub
		gui.removeTask(t);
		gui.updateTaskProgress();
	}
}
