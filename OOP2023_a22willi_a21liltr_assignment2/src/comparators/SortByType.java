package comparators;

import java.util.Comparator;

import se.his.it401g.todo.Task;

public class SortByType implements Comparator<Task> {

	@Override
	public int compare(Task o1, Task o2) {
		//compare based on the type of task
		return o1.getTaskType().compareTo(o2.getTaskType());
	}

}
