package ru.otus.korneev.hmw03.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
	public void size() throws Exception {
		assertEquals(10, myList.size());
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
	public void iterator() throws Exception {
		throw new UnsupportedOperationException();
	}

	@Test
	public void toArray() throws Exception {
		String[] expectedArray = new String[]{"0", "1", "2", "3", "4"};
		Object[] myArray = myList.toArray();
		for (int i = 0; i < myArray.length; i++) {
			assertEquals(expectedArray[i], myArray[i]);
		}
	}

	@Test
	public void toArray1() throws Exception {
		String[] testArray = new String[7];
		Object[] myArray = myList.toArray(testArray);
		String[] expectedArray = new String[]{"0", "1", "2", "3", "4", null, null, null, null, null};
		for (int i = 0; i < myArray.length; i++) {
			assertEquals(expectedArray[i],
					myArray[i]);
		}
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
		assertEquals("Test Check increasing MyArrayList ", 15, myList.size());
	}

	@Test
	public void constructor() throws Exception {
		assertEquals("TestSize default constructor", 10, myList.size());
		assertEquals("TestSize constructor = 15", 15, new MyArrayList<>(15).size());
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
		throw new UnsupportedOperationException();
	}

	@Test
	public void addAll1() throws Exception {
		throw new UnsupportedOperationException();
	}

	@Test
	public void removeAll() throws Exception {
		throw new UnsupportedOperationException();
	}

	@Test
	public void retainAll() throws Exception {
		throw new UnsupportedOperationException();
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

	@Test
	public void listIterator() throws Exception {
		throw new UnsupportedOperationException();
	}

	@Test
	public void listIterator1() throws Exception {
		throw new UnsupportedOperationException();
	}

	@Test
	public void subList() throws Exception {
		throw new UnsupportedOperationException();
	}

}