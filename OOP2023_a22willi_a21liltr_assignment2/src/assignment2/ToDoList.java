package assignment2;

import java.util.ArrayList;
import java.util.Collections;

import se.his.it401g.todo.Task;

public class ToDoList {
	private ArrayList<Task> todoList = new ArrayList<Task>();
	
	/*
	 * Append a task to the list of tasks
	 * 
	 * @param task the task to add of type Task
	 */
	public void addTask(Task task) {
		todoList.add(task);
	}
	
	public boolean removeTask(Task task) {
		return todoList.remove(task);
	}
	
	/* 
	 * @return returns all tasks as an ArrayList. 
	 */
	public ArrayList<Task> getTodoList() {
		return todoList;
	}
}
