 package hello;

import java.util.Calendar;

public class Todo {
	private int id;
	private String title;
	private String description;
	private int priority;
	private Calendar dueDate;

	public Todo(String title, String description, int priority) {
		this.title = title;
		this.description = description;
		this.priority = priority;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	} 

	public Calendar getDueDate() {
		return dueDate;
	}
	public void setDueDate(Calendar dueDate) {
		this.dueDate = dueDate;
	}

}
