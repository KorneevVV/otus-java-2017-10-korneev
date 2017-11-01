package ru.otus.korneev.hmw03.collection;

import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyArrayList<E> implements List<E> {

	private Object[] array;

	private int size = 0;

	private int count = 0;

	private static final int DEFAULT_SIZE = 10;

	public MyArrayList(int size) {
		if (size < 1) {
			throw new IllegalArgumentException("Size is wrong");
		}
		this.size = size;
		this.array = new Object[size];
	}

	public MyArrayList() {
		this(DEFAULT_SIZE);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return count == 0;
	}

	@Override
	public boolean contains(Object o) {
		return (indexOf(o) >= 0);
	}

	@Override
	public Iterator<E> iterator() {
		return new ListIteratorImpl();
	}

	@Override
	public Object[] toArray() {
		Object[] result = new Object[count];
		System.arraycopy(array, 0, result, 0, count);
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		if (a.length < size) {
			return (T[]) Arrays.copyOf(array, size, a.getClass());
		}
		System.arraycopy(array, 0, a, 0, size);
		if (a.length > size) {
			a[size] = null;
		}
		return a;
	}

	@Override
	public boolean add(E e) {
		checkSize(count);
		array[count] = e;
		count++;
		return true;
	}

	private void checkSize(int count) {
		if (size <= count) {
			size = (int) (size == 1 ? ++size * 1.5 : size * 1.5);
			newArray(size);
		}
	}

	private void newArray(int newSizeDA) {
		Object[] arrayTemp = new Object[newSizeDA];
		System.arraycopy(array, 0, arrayTemp, 0, array.length);
		array = arrayTemp;
	}

	@Override
	public boolean remove(Object o) {
		for (int i = 0; i < count; i++) {
			if (array[i].equals(o)) {
				System.arraycopy(array, i + 1, array, i, count - i - 1);
				count--;

				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object e : c)
			if (!contains(e))
				return false;
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> col) {
		Object[] arrCol = col.toArray();
		if (size < (size + arrCol.length)) {
			size = count + arrCol.length;
			Object[] arrayTemp = new Object[size];
			System.arraycopy(array, 0, arrayTemp, 0, array.length);
			array = arrayTemp;
		}
		System.arraycopy(arrCol, 0, array, count, arrCol.length);
		count = count + arrCol.length;
		return arrCol.length != 0;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> col) {
		Object[] arrCol = col.toArray();
		if (arrCol.length == 0) {
			return false;
		}
		if (size < (count + arrCol.length)) {
			size = count + arrCol.length;
			Object[] arrayTemp = new Object[size];
			System.arraycopy(array, 0, arrayTemp, 0, array.length);
			array = arrayTemp;
		}
		System.arraycopy(array, index, array, index + arrCol.length, count - index);
		System.arraycopy(arrCol, 0, array, index, arrCol.length);
		count = count + arrCol.length;
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> col) {
		Object[] arr = col.toArray();
		boolean deleting = false;
		for (int i = 0; i < arr.length; i++) {
			boolean temp = remove(arr[i]);
			if (temp && !deleting) {
				deleting = true;
			}
		}
		return deleting;
	}

	@Override
	public boolean retainAll(Collection<?> col) {
		Object[] arrList = toArray();
		boolean changed = false;
		for (int i = 0; i < count; i++) {
			if (!col.contains(arrList[i])) {
				if (!changed) {
					changed = true;
				}
				boolean del = true;
				while (del) {
					del = remove(arrList[i]);
					i = i == 0 ? 0 : i--;
				}
			}
		}
		return changed;
	}

	@Override
	public void clear() {
		for (int i = 0; i < size; i++) {
			array[i] = null;
		}
		count = 0;
	}

	@Override
	public E get(int index) {
		checkIncreasingDA(index);
		return array(index);
	}

	private void checkIncreasingDA(int index) {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException("Max index: " + count + ", Input: " + index);
		}
	}

	@Override
	public E set(int index, E element) {
		checkIncreasingDA(index);
		E tempObject = array(index);
		array[index] = element;
		return tempObject;
	}

	@Override
	public void add(int index, E element) {
		checkIncreasingDA(index);
		checkSize(count + 1);
		System.arraycopy(array, index, array, index + 1, count - index);
		array[index] = element;
		count++;
	}

	@Override
	public E remove(int i) {
		checkIncreasingDA(i);
		E resultObject = array(i);
		System.arraycopy(array, i + 1, array, i, count - i - 1);
		count--;
		return resultObject;
	}

	@Override
	public int indexOf(Object o) {
		for (int i = 0; i < count; i++) {
			if (array[i].equals(o)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		for (int i = count - 1; i > -1; i--) {
			if (array[i].equals(o)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public ListIterator<E> listIterator() {
		return new ListIteratorImpl();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return new ListIteratorImpl(index);
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException(); // todo
	}

	@SuppressWarnings("unchecked")
	private E array(int index) {
		return (E) array[index];
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof List))
			return false;

		ListIterator<E> e1 = listIterator();
		ListIterator<?> e2 = ((List<?>) o).listIterator();
		while (e1.hasNext() && e2.hasNext()) {
			E o1 = e1.next();
			Object o2 = e2.next();
			if (!(o1 == null ? o2 == null : o1.equals(o2)))
				return false;
		}
		return !(e1.hasNext() || e2.hasNext());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < count; i++) {
			sb.append(array[i]).append(" ");
		}
		return sb.toString();
	}


	private class IteratorImpl implements Iterator<E> {

		private int countIterator = 0;

		private int checkCount = count;

		@Override
		public boolean hasNext() {
			return countIterator != size;
		}

		@Override
		public E next() {
			checkModification();
			int i = countIterator;
			if (i >= size)
				throw new NoSuchElementException();
			Object[] array = MyArrayList.this.array;
			if (i >= array.length)
				throw new ConcurrentModificationException();
			countIterator++;
			return array(countIterator - 1);
		}

		@Override
		public void remove() {
			checkModification();
			countIterator--;
			MyArrayList.this.remove(countIterator);
		}

		private void checkModification() {
			if (count != checkCount) {
				throw new ConcurrentModificationException("MyArrayList has been modified");
			}
		}
	}

	public class ListIteratorImpl extends IteratorImpl implements ListIterator<E> {

		public ListIteratorImpl() {
		}

		public ListIteratorImpl(int index) {
			super();
			super.countIterator = index;
		}


		@Override
		public boolean hasPrevious() {
			return super.countIterator > 0;
		}

		@Override
		public E previous() {
			super.checkModification();
			super.countIterator--;
			return array(super.countIterator);
		}

		@Override
		public int nextIndex() {
			return super.countIterator;
		}

		@Override
		public int previousIndex() {
			return count <= super.countIterator ? -1 : super.countIterator - 1;
		}

		@Override
		public void set(E e) {
			super.checkModification();
			MyArrayList.this.set(super.countIterator - 1, e);
			super.countIterator++;
		}

		@Override
		public void add(E e) {
			super.checkModification();
			MyArrayList.this.add(super.countIterator++, e);
		}
	}
}
