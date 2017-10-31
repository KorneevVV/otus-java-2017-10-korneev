package ru.otus.korneev.hmw03.collection;

import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyArrayList<E> implements List<E> {

	private Object[] array;

	private int size = 0;

	private int count = 0;

	private static final int DEFAULT_SIZE = 10;

	MyArrayList(int size) {
		if (size < 1) {
			throw new IllegalArgumentException("Size is wrong");
		}
		this.size = size;
		this.array = new Object[size];
	}

	MyArrayList() {
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
	public boolean addAll(Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
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
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings("unchecked")
	private E array(int index) {
		return (E) array[index];
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < count; i++) {
			sb.append(array[i]).append(" ");
		}
		return sb.toString();
	}

	public class ListIteratorImpl implements ListIterator<E> {

		private int countIterator = 0;

		private int checkCount = count;

		@Override
		public boolean hasNext() {
			return count > countIterator;
		}

		@Override
		public E next() {
			checkModification();
			countIterator++;
			return array(countIterator - 1);
		}

		@Override
		public boolean hasPrevious() {
			return countIterator > 0;
		}

		@Override
		public E previous() {
			checkModification();
			countIterator--;
			return array(countIterator);
		}

		@Override
		public int nextIndex() {
			return countIterator;
		}

		@Override
		public int previousIndex() {
			return count <= countIterator ? -1 : countIterator - 1;
		}

		@Override
		public void remove() {
			checkModification();
			countIterator--;
			MyArrayList.this.remove(countIterator);
		}

		@Override
		public void set(E e) {
			checkModification();
			MyArrayList.this.set(countIterator - 1, e);
			countIterator++;
		}

		@Override
		public void add(E e) {
			checkModification();
			MyArrayList.this.add(countIterator++, e);
		}

		private void checkModification() {
			if (count != checkCount) {
				throw new ConcurrentModificationException("MyArrayList has been modified");
			}
		}
	}
}
