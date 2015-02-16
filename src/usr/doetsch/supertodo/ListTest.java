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
		
		ListFactory f = new ListFactory();
		ListReader lr = new ListReader();
		List l1 = f.createList("HEAD");
		List l2 = f.createList("1");
		List l3 = f.createList("2");
		
		l1.addSubList(l2);
		l1.addSubList(l3);
		
		System.out.println(l1);
		
		assertFalse(lr.hasUrgentItems(l1));
		
		l3.setUrgent(true);
		
		assertTrue(lr.hasUrgentItems(l1));
		
		l3.addSubList(f.createList(""));
		l3.addSubList(f.createList(""));
		
		assertTrue(lr.hasUrgentItems(l1));
		
		
	}

}
