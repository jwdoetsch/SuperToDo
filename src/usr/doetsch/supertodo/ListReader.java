package usr.doetsch.supertodo;

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
	
	/**
	 * Determines whether or not the given list contains
	 * urgent sublists/items
	 * 
	 * @param list
	 * @return
	 */
	public boolean hasUrgentItems (List list) {

		if (hasSubLists(list)) {
			for (List sublist : list) {
				
				if (sublist.isUrgent() || hasUrgentItems(sublist)) {
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
	
	public int getListCount (List headlist) {
		int i = 0;
		
		//count the current list and any non-item sub lists
		for (List sublist : headlist.getSubLists()) {
			if (hasSubLists(sublist)) {
				i += 1 + getListCount(sublist);
			}
		}
		
		return i;
	}
	
	public int getItemCount (List headlist) {
		
		//count the items in each list, provided this list has items
		if (hasSubLists(headlist)) {
			
			int counter = 0;
			for (List sublist : headlist.getSubLists()) {
				counter += getItemCount(sublist);
			}
			
			return counter;
			
		} else {
			return 1;
		}
	}

}