package assignment2;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import se.his.it401g.todo.Task;
import se.his.it401g.todo.TaskListener;

public class TaskHandler implements TaskListener {
	private JPanel taskList;
	private JScrollPane scrollPane;
	private JLabel taskProgress;
	private int totalTaskCount;
	private int completedTaskCount;
	
	public TaskHandler() {
		taskList = new JPanel();
		taskList.setLayout(new BoxLayout(taskList, BoxLayout.PAGE_AXIS));
		
		scrollPane = new JScrollPane();
		scrollPane.getViewport().add(taskList);
		
		totalTaskCount = 0;
		completedTaskCount = 0;
		taskProgress = new JLabel();
		taskProgress.setText(completedTaskCount + "/" + taskList.getComponentCount());
	}
	
	public JScrollPane GetScrollPane() {
		return scrollPane;
	}
	
	public JLabel GetTaskProgress() {
		return taskProgress;
	}

	@Override
	public void taskChanged(Task t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void taskCompleted(Task t) {
		// TODO Auto-generated method stub
		completedTaskCount++;
		taskProgress.setText(completedTaskCount + "/" + taskList.getComponentCount());
		
		//update the frame to show currently visible tasks.
		SwingUtilities.updateComponentTreeUI(taskList);
	}

	@Override
	public void taskUncompleted(Task t) {
		// TODO Auto-generated method stub
		completedTaskCount--;
		taskProgress.setText(completedTaskCount + "/" + taskList.getComponentCount());
		
		//update the frame to show currently visible tasks.
		SwingUtilities.updateComponentTreeUI(taskList);
	}

	@Override
	public void taskCreated(Task t) {
		// TODO Auto-generated method stub
		t.setTaskListener(this);
		taskList.add(t.getGuiComponent());
		
		totalTaskCount++;
		taskProgress.setText(completedTaskCount + "/" + taskList.getComponentCount());
		
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
		
		totalTaskCount--;
		taskProgress.setText(completedTaskCount + "/" + taskList.getComponentCount());
		
		//update the frame to show currently visible tasks.
		SwingUtilities.updateComponentTreeUI(taskList);
	}

}
