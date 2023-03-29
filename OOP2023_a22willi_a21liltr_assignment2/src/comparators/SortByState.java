package comparators;

import java.util.Comparator;

import se.his.it401g.todo.Task;

public class SortByState implements Comparator<Task> {

	@Override
	public int compare(Task o1, Task o2) {
		//sort based on completion.
		return Boolean.compare(o1.isComplete(), o2.isComplete());
	}

}
