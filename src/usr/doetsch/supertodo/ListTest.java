package usr.doetsch.supertodo;

import static org.junit.Assert.*;

import org.junit.Test;

public class ListTest {

	/**
	 * 
	 * List 1
	 *     List 2
	 *         List 4
	 *         		Anon list
	 *     List 3
	 * 
	 */
	@Test
	public void test() {
		
		ListReader lr = new ListReader();
		List l1 = new List("List 1");
		List l2 = new List("List 2");
		List l3 = new List("List 3");
		List l4 = new List("List 4");
		
		l1.addSubList(l2);
		l1.addSubList(l3);
		
		l2.addSubList(l4);
		
		assertTrue(lr.hasSubLists(l1));
		assertTrue(lr.hasSubLists(l2));
		assertFalse(lr.hasSubLists(l3));
		assertFalse(lr.hasSubLists(l4));
		
		assertFalse(lr.isComplete(l1));
		assertFalse(lr.isComplete(l2));
		assertFalse(lr.isComplete(l3));
		assertFalse(lr.isComplete(l4));
		
		l4.setMarked(true);
		assertFalse(lr.isComplete(l1));
		assertTrue(lr.isComplete(l2));
		assertFalse(lr.isComplete(l3));
		assertTrue(lr.isComplete(l4));
		
		l3.setMarked(true);
		
		assertTrue(lr.isComplete(l1));
		
		//l4.addSubList(new List(""));
		
		//assertFalse(lr.isCompleted(l1));
		
		assertFalse(lr.hasUrgentItems(l1));
		
		l4.setUrgent(true);
		
		assertTrue(l4.isUrgent());
		assertTrue(lr.hasUrgentItems(l4));
		assertTrue(lr.hasUrgentItems(l1));
		
		l4.setMarked(false);
		System.out.println(l1);
		
	}

}
