package comparators;

import java.util.Comparator;

import se.his.it401g.todo.Task;

public class SortByName implements Comparator<Task> {

	@Override
	public int compare(Task o1, Task o2) {
		//compare names (after converting to lowercase
		return o1.getText().toLowerCase().compareTo(o2.getText().toLowerCase());
	}

}
