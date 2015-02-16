package usr.doetsch.supertodo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/*
 * List is a mutable realization of a to-do or checklist
 * item. The items are called lists whereas a list
 * item with sub-items would be considered a list itself.  
 * 
 * @author Jacob Wesley Doetsch
 */
class List implements Iterable<List> {

	private String title;
	private String description;
	private boolean urgent;
	private boolean marked;
	private String deadline;				//follows YYYY-MM-DD from XML's date datatype
	private Collection<List> subLists;
	
	/*
	 * Instantiates a List with the given title.
	 * 
	 * @param title the list title
	 */
	List (String title) {
		setTitle(title);
		setDescription("");
		setUrgent(false);
		setMarked(false);
		subLists = new ArrayList<List>();
	}
	
	/*
	 * Instantiates a List with the given title, description,
	 * urgency flag, and marked flag.
	 * 
	 * @param title the list title
	 * @param description the list description
	 * @param isUrgent the list's urgency flag (true if urgent)
	 * @param isMarked the list's marked flag (true if marked)
	 */
	List  (String title, String description, boolean isUrgent, boolean isMarked) {
		setTitle(title);
		setDescription(description);
		setUrgent(isUrgent);
		setMarked(isMarked);
		subLists = new ArrayList<List>();
	}

	
	/*
	 * Returns the title of the list/item
	 * 
	 * @return the title
	 */
	String getTitle() {
		return title;
	}

	/*
	 * Sets the title of the list/item
	 * 
	 * @param title the new title
	 */
	void setTitle(String title) {
		this.title = title;
	}

	/*
	 * Returns the description of the list/item
	 * 
	 * @return the description
	 */
	String getDescription() {
		return description;
	}

	/*
	 * Sets the description of the list/item
	 * 
	 * @param description the description to set
	 */
	void setDescription(String description) {
		this.description = description;
	}

	/*
	 * Returns the urgency of the list/item
	 * 
	 * @return true if urgent
	 */
	boolean isUrgent() {
		return urgent;
	}

	/*
	 * Sets the urgency of the list/item
	 * 
	 * @param isUrgent the urgent to set
	 */
	void setUrgent(boolean isUrgent) {
		this.urgent = isUrgent;
	}

	/*
	 * Returns the marked status of the list/item
	 * 
	 * @return true if marked
	 */
	boolean isMarked() {
		return marked;
	}

	/*
	 * Sets the marked status fo the list/item
	 * 
	 * @param marked true if marked
	 */
	void setMarked(boolean marked) {
		this.marked = marked;
	}

	/*
	 * Returns the deadline of the list/item
	 * 
	 * @return the deadline
	 */
	String getDeadline() {
		return deadline;
	}

	/*
	 * Sets the deadline of the list/item
	 * 
	 * @param deadline the deadline to set
	 */
	void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	/*
	 * Returns a Collection containing all the list's
	 * sub-list's
	 * 
	 * @return a Collection containing the sub-lists
	 */
	Collection<List> getSubLists () {
		return subLists;
	}
	
	/*
	 * Adds a sub-list to this list
	 * 
	 * @param l the sub-list to add
	 */
	void addSubList (List l) {
		subLists.add(l);
	}
	
	/*
	 * Returns an Iterator that iterates over the list's
	 * sub-lists.
	 * 
	 * @return the sub-list Iterator
	 */
	public Iterator<List> iterator() {
		return subLists.iterator();
	}
	
	@Override
	public String toString () {
		return toString(this, "");
	}
	
	private String toString (List list, String tab) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(tab + list.getTitle() + ", " + list.getDescription());
		
		sb.append("[");
		sb.append(list.isMarked());
		sb.append(", ");
		sb.append(list.isUrgent());
		sb.append(", ");
		sb.append(list.getDeadline());

		sb.append("] ");
		
		
		sb.append("(");
		sb.append(list.getSubLists().size());
		sb.append(")\n");
		
		for (List l : list.getSubLists()) {
			sb.append(toString(l, tab + "    "));
		}
		
		return sb.toString();
	}
	
}
