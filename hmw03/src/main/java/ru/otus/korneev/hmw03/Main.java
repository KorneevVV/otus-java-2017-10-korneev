package ru.otus.korneev.hmw03;

import ru.otus.korneev.hmw03.collection.MyArrayList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	private static MyArrayList<Integer> myList = new MyArrayList<>();
	private static ArrayList<Integer> list = new ArrayList<>();

	public static void main(String[] args) {
		fillingList(myList);
		fillingList(list);
		checkCort();
		checkCopy();
		checkAddAll();
	}

	private static void fillingList(List<Integer> list) {
		list.add(15);
		list.add(1);
		list.add(2);
		list.add(9);
		list.add(4);
		list.add(5);
		list.add(0);
	}

	private static void checkCort() {
		Collections.sort(myList);
		Collections.sort(list);
		System.out.print(list.equals(myList) ?
				"sorting check done -> " : "sorting check failed -> ");
		System.out.println(myList);
	}

	private static void checkCopy() {
		ArrayList<Integer> srcList = new ArrayList<>();
		srcList.add(7);
		srcList.add(8);

		Collections.copy(myList, srcList);
		Collections.copy(list, srcList);
		System.out.print(list.equals(myList) ?
				"copy check done -> " : "copy check failed -> ");
		System.out.println(myList);

	}

	private static void checkAddAll() {
		Collections.addAll(myList, 10, 16);
		Collections.addAll(list, 10, 16);
		System.out.print(list.equals(myList) ?
				"add all check done -> " : "add all check failed -> ");
		System.out.println(myList);
	}
}
