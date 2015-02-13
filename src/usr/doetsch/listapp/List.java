package usr.doetsch.listapp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class List implements Iterable<List> {

	private String title;
	private String description;
	private boolean urgent;
	private boolean marked;
	private String deadline;
	private Collection<List> subLists;
	
	List (String title) {
		setTitle(title);
		setDescription("");
		setUrgent(false);
		setMarked(false);
		subLists = new ArrayList<List>();
	}
	
	List  (String title, String description, boolean isUrgent, boolean isMarked) {
		setTitle(title);
		setDescription(description);
		setUrgent(isUrgent);
		setMarked(isMarked);
		subLists = new ArrayList<List>();
	}

	public String getTitle () {
		return title;
	}

	public void setTitle (String title) {
		this.title = title;
	}

	public String getDescription () {
		return description;
	}

	public void setDescription (String description) {
		this.description = description;
	}

	public boolean isUrgent () {
		return urgent;
	}

	public void setUrgent (boolean isUrgent) {
		this.urgent = isUrgent;
	}

	public boolean isMarked () {
		return marked;
	}

	public void setMarked (boolean isCompleted) {
		this.marked = isCompleted;
	}
	
	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public Collection<List> getSubLists () {
		return subLists;
	}
	
	public void addSubList (List l) {
		subLists.add(l);
	}
	
	@Override
	public Iterator<List> iterator() {
		return subLists.iterator();
	}
	
}
