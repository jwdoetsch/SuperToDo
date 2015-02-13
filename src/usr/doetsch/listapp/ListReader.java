package usr.doetsch.listapp;

public class ListReader {

	public boolean isFinished (List list) {
		return isComplete(list);
	}
	
	public boolean isComplete (List list) {
		if (hasSubLists(list)) {
			for (List sublist : list) {
				if (!isComplete(sublist)) {
					return false;
				}
			}
			return true;
		} else {
			return list.isMarked();
		}
	}
	
	public boolean hasSubLists (List list) {
		return (list.getSubLists().size() > 0);		
	}
	
	public boolean hasUrgentItems (List list) {
		if (hasSubLists(list)) {
			for (List sublist : list) {
				if (hasUrgentItems(sublist)) {
					return true;
				}
			}
			return false;
		} else {
			return list.isUrgent();
		}		
	}
	
	public int getSubListCount (List list) {
		return list.getSubLists().size();
	}
	
	public String printList (List list) {
		return printList(list, "");
	}
	
	private String printList (List list, String tab) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(tab + list.getTitle() + ": " + list.getDescription());
		
		sb.append("[");
		sb.append(isFinished(list));
//		sb.append(list.isMarked());
		sb.append(", ");
		sb.append(list.isUrgent());
		sb.append(", ");
		sb.append(list.getDeadline());

		sb.append("] ");
		
		
		sb.append("(");
		sb.append(list.getSubLists().size());
		sb.append(")\n");
		
		for (List l : list.getSubLists()) {
			sb.append(printList(l, tab + "    "));
		}
		
		
		return sb.toString();
	}
	
	
	
}