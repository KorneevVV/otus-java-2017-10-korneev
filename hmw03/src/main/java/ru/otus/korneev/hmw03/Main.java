package ru.otus.korneev.hmw03;

import ru.otus.korneev.hmw03.collection.MyArrayList;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {
		MyArrayList<Integer> myList = new MyArrayList<>();
		myList.add(0);
		myList.add(1);
		myList.add(2);
		myList.add(3);
		myList.add(4);
		myList.add(5);
		myList.add(6);
		Collections.sort(myList);
		System.out.println(myList);
//		ArrayList<Integer> myList2 = new ArrayList<>();
//		myList2.add(7);
//		myList2.add(8);
//		Collections.copy(myList2, myList);
//		System.out.println(myList);
//		Collections.addAll(myList, 10, 15);
//		System.out.println(myList);
	}
}
