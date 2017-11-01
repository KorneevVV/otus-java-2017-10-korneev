package ru.otus.korneev.hmw03.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.ListIterator;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class MyArrayListTest {

	private MyArrayList<String> myList = new MyArrayList<>(10);

	@Before
	public void createMyList() {
		if (!myList.isEmpty()) {
			myList.clear();
		}
		myList.add("0");
		myList.add("1");
		myList.add("2");
		myList.add("3");
		myList.add("4");
	}

	@Test
	public void addAllForIndex() throws Exception {
		ArrayList<String> arList = new ArrayList<>();
		arList.add("91");
		arList.add("82");
		arList.add("93");
		arList.add("84");
		arList.add("95");
		arList.add("86");
		arList.add("97");
		arList.add("88");
		arList.add("99");
		arList.add("810");
		assertEquals(true, myList.addAll(2, arList));
		ArrayList<String> exList = new ArrayList<>();
		exList.add("0");
		exList.add("1");
		exList.add("91");
		exList.add("82");
		exList.add("93");
		exList.add("84");
		exList.add("95");
		exList.add("86");
		exList.add("97");
		exList.add("88");
		exList.add("99");
		exList.add("810");
		exList.add("2");
		exList.add("3");
		exList.add("4");
		assertEquals(exList, myList);
	}

	@Test
	public void addAllForIndex2() throws Exception {
		ArrayList<String> arList = new ArrayList<>();
		arList.add("91");
		arList.add("82");
		assertEquals(true, myList.addAll(2, arList));
		ArrayList<String> exList = new ArrayList<>();
		exList.add("0");
		exList.add("1");
		exList.add("91");
		exList.add("82");
		exList.add("2");
		exList.add("3");
		exList.add("4");
		assertEquals(exList, myList);
	}

	@Test
	public void size() throws Exception {
		assertEquals(5, myList.size());
	}

	@Test
	public void isEmpty() throws Exception {
		assertEquals(false, myList.isEmpty());
		myList.clear();
		assertEquals(true, myList.isEmpty());
	}

	@Test
	public void contains() throws Exception {
		assertEquals(true, myList.contains("0"));
		assertEquals(false, myList.contains("14"));
	}

	@Test
	public void toArray() throws Exception {
		assertArrayEquals(new String[]{"0", "1", "2", "3", "4"}, myList.toArray());
	}

	@Test
	public void toArrayGeneric() throws Exception {
		String[] testArray = new String[7];
		String[] expectedArray = new String[]{"0", "1", "2", "3", "4", null, null};
		assertArrayEquals(expectedArray, myList.toArray(testArray));
	}

	@Test
	public void add() throws Exception {
		assertEquals("Test Boolean Add", true, myList.add("5"));
	}

	@Test
	public void addCheckSize() throws Exception {
		myList.add("5");
		myList.add("6");
		myList.add("7");
		myList.add("8");
		myList.add("9");
		myList.add("10");
		myList.add("11");
		assertEquals(12, myList.size());
	}

	@Test
	public void constructor() throws Exception {
		assertEquals(5, myList.size());
		assertEquals(0, new MyArrayList<>(0).toArray().length);
	}

	@Test
	public void remove() throws Exception {
		assertEquals("0", myList.remove(0));
		assertEquals("3", myList.remove(2));
		assertEquals("4", myList.remove(2));
	}

	@Test
	public void removeBoolean() throws Exception {
		assertEquals(true, myList.remove("0"));
		assertEquals(false, myList.remove("9"));
	}

	@Test
	public void containsAll() throws Exception {
		List<String> testList = myList;
		assertEquals(true, myList.containsAll(testList));
	}

	@Test
	public void addAll() throws Exception {
		ArrayList<String> arList = new ArrayList<>();
		arList.add("0");
		arList.add("4");
		arList.add("0");
		arList.add("4");
		arList.add("0");
		arList.add("4");
		arList.add("0");
		arList.add("4");
		assertEquals(true, myList.addAll(arList));
		ArrayList<String> exList = new ArrayList<>();
		exList.add("0");
		exList.add("1");
		exList.add("2");
		exList.add("3");
		exList.add("4");
		exList.add("0");
		exList.add("4");
		exList.add("0");
		exList.add("4");
		exList.add("0");
		exList.add("4");
		exList.add("0");
		exList.add("4");
		assertEquals(exList, myList);
	}

	@Test
	public void removeAll() throws Exception {
		ArrayList<String> arList = new ArrayList<>();
		arList.add("0");
		arList.add("4");
		assertEquals(true, myList.removeAll(arList));
		assertEquals(false, myList.removeAll(arList));
		ArrayList<String> exList = new ArrayList<>();
		exList.add("1");
		exList.add("2");
		exList.add("3");
		assertEquals(exList, myList);
	}

	@Test
	public void retainAll() throws Exception {
		ArrayList<String> arList = new ArrayList<>();
		arList.add("0");
		arList.add("2");
		arList.add("4");
		myList.addAll(myList);
		assertEquals(true, myList.retainAll(arList));
		ArrayList<String> exList = new ArrayList<>();
		exList.add("0");
		exList.add("2");
		exList.add("4");
		exList.add("0");
		exList.add("2");
		exList.add("4");
		assertEquals(exList, myList);
	}

	@Test
	public void clear() throws Exception {
		assertEquals(false, myList.isEmpty());
		myList.clear();
		assertEquals(true, myList.isEmpty());
	}

	@Test
	public void get() throws Exception {
		assertEquals("1", myList.get(1));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void getThrowException() throws Exception {
		assertEquals("0", myList.get(12));
	}

	@Test
	public void set() throws Exception {
		assertEquals("2", myList.set(2, "set"));
		assertEquals("set", myList.get(2));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void setThrowException() throws Exception {
		assertEquals("2", myList.set(15, "set"));

	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void addIndex() throws Exception {
		myList.add(10, "11");
	}

	@Test
	public void removeIndex() throws Exception {
		assertEquals("0", myList.remove(0));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void removeIndexThrowException() throws Exception {
		assertEquals("0", myList.remove(11));
	}

	@Test
	public void indexOf() throws Exception {
		assertEquals(1, myList.indexOf("1"));
	}

	@Test
	public void lastIndexOf() throws Exception {
		myList.add("0");
		assertEquals(5, myList.lastIndexOf("0"));
	}

	// ITERATOR
	@Test
	public void hasNextIterator() throws Exception {
		ListIterator itr = myList.listIterator();
		assertEquals(true, itr.hasNext());
	}

	@Test(expected = ConcurrentModificationException.class)
	public void previousIterator() throws Exception {
		ListIterator itr = myList.listIterator();
		itr.next();
		assertEquals("0", itr.previous());
		myList.add("failed");
		itr.previous();
	}

	@Test(expected = ConcurrentModificationException.class)
	public void nextIterator() throws Exception {
		ListIterator itr = myList.listIterator();
		assertEquals("0", itr.next());
		myList.add("failed");
		itr.next();
	}

	@Test
	public void nextIteratorIndex() throws Exception {
		ListIterator itr = myList.listIterator(2);
		assertEquals("2", itr.next());
	}

	@Test(expected = ConcurrentModificationException.class)
	public void removeIterator() throws Exception {
		ListIterator itr = myList.listIterator();
		itr.remove();
		assertEquals("1", myList.get(0));
		itr.next();
		myList.add("failed");
		itr.remove();
	}

	@Test(expected = ConcurrentModificationException.class)
	public void setIterator() throws Exception {
		ListIterator<String> itr = myList.listIterator();
		itr.next();
		itr.set("TEST");
		assertEquals("TEST", myList.get(0));
		myList.add("failed");
		itr.set("");
	}

	@Test(expected = ConcurrentModificationException.class)
	public void addIterator() throws Exception {
		ListIterator<String> itr = myList.listIterator();
		itr.next();
		itr.add("TEST");
		assertEquals("TEST",myList.get(1));
		assertEquals("1", myList.get(2));
		myList.add("failed");
		itr.add("");
	}
}