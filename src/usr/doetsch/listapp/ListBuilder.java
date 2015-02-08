package usr.doetsch.listapp;

import java.net.URI;

public class ListBuilder {

	public List createList (String title) {
		return createList(title, "", false, false);
	}
	
	public List createList (String title, String description, boolean isUrgent, boolean isMarked) {
		return new List(title, description, isUrgent, isMarked);
	}
	
	public List buildList (URI path) {
		//TODO
		return null;
	}
	
}
