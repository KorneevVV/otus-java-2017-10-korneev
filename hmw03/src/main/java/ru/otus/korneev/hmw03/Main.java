package ru.otus.korneev.hmw03;

import ru.otus.korneev.hmw03.collection.MyArrayList;

import java.util.Collections;

public class Main {

	public static void main(String[] args) {
		MyArrayList<Integer> myList = new MyArrayList<>();
		myList.add(15);
		myList.add(1);
		myList.add(2);
		myList.add(9);
		myList.add(4);
		myList.add(5);
		myList.add(0);
		Collections.sort(myList);
		System.out.println(myList);
        MyArrayList<Integer> myList2 = new MyArrayList<>();
		myList2.add(7);
		myList2.add(8);
		Collections.copy(myList, myList2);
		System.out.println(myList);
		Collections.addAll(myList, 10, 16);
		System.out.println(myList);
	}
}
