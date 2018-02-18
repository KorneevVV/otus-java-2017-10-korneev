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

	private List<String> myList = new MyArrayList<>(10);

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
	public void addAllForIndex() {
		List<String> arList = new ArrayList<>();
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
		List<String> exList = new ArrayList<>();
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
	public void addAllForIndex2() {
		List<String> arList = new ArrayList<>();
		arList.add("91");
		arList.add("82");
		assertEquals(true, myList.addAll(2, arList));
		List<String> exList = new ArrayList<>();
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
	public void size() {
		assertEquals(5, myList.size());
	}

	@Test
	public void isEmpty() {
		assertEquals(false, myList.isEmpty());
		myList.clear();
		assertEquals(true, myList.isEmpty());
	}

	@Test
	public void contains() {
		assertEquals(true, myList.contains("0"));
		assertEquals(false, myList.contains("14"));
	}

	@Test
	public void toArray() {
		assertArrayEquals(new String[]{"0", "1", "2", "3", "4"}, myList.toArray());
	}

	@Test
	public void toArrayGeneric() {
		String[] testArray = new String[5];
		String[] expectedArray = new String[]{"0", "1", "2", "3", "4"};
		assertArrayEquals(expectedArray, myList.toArray(testArray));
	}

	@Test
	public void add() {
		assertEquals("Test Boolean Add", true, myList.add("5"));
	}

	@Test
	public void addCheckSize() {
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
	public void constructor() {
		assertEquals(5, myList.size());
		assertEquals(0, new MyArrayList<>(0).toArray().length);
	}

	@Test
	public void remove() {
		assertEquals("0", myList.remove(0));
		assertEquals("3", myList.remove(2));
		assertEquals("4", myList.remove(2));
	}

	@Test
	public void removeBoolean() {
		assertEquals(true, myList.remove("0"));
		assertEquals(false, myList.remove("9"));
	}

	@Test
	public void containsAll() {
		List<String> testList = myList;
		assertEquals(true, myList.containsAll(testList));
	}

	@Test
	public void addAll() {
		List<String> arList = new ArrayList<>();
		arList.add("0");
		arList.add("4");
		arList.add("0");
		arList.add("4");
		arList.add("0");
		arList.add("4");
		arList.add("0");
		arList.add("4");
		assertEquals(true, myList.addAll(arList));
		List<String> exList = new ArrayList<>();
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
	public void removeAll() {
		List<String> arList = new ArrayList<>();
		arList.add("0");
		arList.add("4");
		assertEquals(true, myList.removeAll(arList));
		assertEquals(false, myList.removeAll(arList));
		List<String> exList = new ArrayList<>();
		exList.add("1");
		exList.add("2");
		exList.add("3");
		assertEquals(exList, myList);
	}

	@Test
	public void retainAll() {
		List<String> arList = new ArrayList<>();
		arList.add("0");
		arList.add("2");
		arList.add("4");
		myList.addAll(myList);
		assertEquals(true, myList.retainAll(arList));
		List<String> exList = new ArrayList<>();
		exList.add("0");
		exList.add("2");
		exList.add("4");
		exList.add("0");
		exList.add("2");
		exList.add("4");
		assertEquals(exList, myList);
	}

	@Test
	public void clear() {
		assertEquals(false, myList.isEmpty());
		myList.clear();
		assertEquals(true, myList.isEmpty());
	}

	@Test
	public void get() {
		assertEquals("1", myList.get(1));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void getThrowException() {
		assertEquals("0", myList.get(12));
	}

	@Test
	public void set() {
		assertEquals("2", myList.set(2, "set"));
		assertEquals("set", myList.get(2));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void setThrowException() {
		assertEquals("2", myList.set(15, "set"));

	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void addIndex() {
		myList.add(10, "11");
	}

	@Test
	public void removeIndex() {
		assertEquals("0", myList.remove(0));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void removeIndexThrowException() {
		assertEquals("0", myList.remove(11));
	}

	@Test
	public void indexOf() {
		assertEquals(1, myList.indexOf("1"));
	}

	@Test
	public void lastIndexOf() {
		myList.add("0");
		assertEquals(5, myList.lastIndexOf("0"));
	}

	// ITERATOR
	@Test
	public void hasNextIterator() {
		ListIterator itr = myList.listIterator();
		assertEquals(true, itr.hasNext());
	}

	@Test(expected = ConcurrentModificationException.class)
	public void previousIterator() {
		ListIterator itr = myList.listIterator();
		itr.next();
		assertEquals("0", itr.previous());
		myList.add("failed");
		itr.previous();
	}

	@Test(expected = ConcurrentModificationException.class)
	public void nextIterator() {
		ListIterator itr = myList.listIterator();
		assertEquals("0", itr.next());
		myList.add("failed");
		itr.next();
	}

	@Test
	public void nextIteratorIndex() {
		ListIterator itr = myList.listIterator(2);
		assertEquals("2", itr.next());
	}

	@Test(expected = ConcurrentModificationException.class)
	public void removeIterator() {
		ListIterator itr = myList.listIterator();
		itr.remove();
		assertEquals("1", myList.get(0));
		itr.next();
		myList.add("failed");
		itr.remove();
	}

	@Test(expected = ConcurrentModificationException.class)
	public void setIterator() {
		ListIterator<String> itr = myList.listIterator();
		itr.next();
		itr.set("TEST");
		assertEquals("TEST", myList.get(0));
		myList.add("failed");
		itr.set("");
	}

	@Test(expected = ConcurrentModificationException.class)
	public void addIterator() {
		ListIterator<String> itr = myList.listIterator();
		itr.next();
		itr.add("TEST");
		assertEquals("TEST",myList.get(1));
		assertEquals("1", myList.get(2));
		myList.add("failed");
		itr.add("");
	}
}