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
	
}