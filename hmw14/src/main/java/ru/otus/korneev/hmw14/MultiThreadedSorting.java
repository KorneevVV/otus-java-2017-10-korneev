package ru.otus.korneev.hmw14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultiThreadedSorting {

    public static <E extends Comparable<E>> E[] sort(E[] arr, int numberOfThread) throws InterruptedException {
        int size = arr.length;
        if (size <= numberOfThread) {
            Arrays.sort(arr);
            return arr;
        }
        int countElementInSubarray = size / numberOfThread;
        Object[][] grandArray = new Object[numberOfThread][];
        int countFromElement = 0;
        int countToElement = countElementInSubarray;
        List<Thread> threadList = new ArrayList<>(numberOfThread);
        for (int i = 0; i < grandArray.length; i++) {
            grandArray[i] = Arrays.copyOfRange(arr, countFromElement, countToElement);
            int finalI = i;
            Thread thread = new Thread(() -> Arrays.sort(grandArray[finalI]));
            thread.setName(String.format("Sorting arr №%s", i));
            thread.start();
            threadList.add(thread);
            countFromElement = countToElement;
            countToElement += countElementInSubarray;
            if (i == grandArray.length - 2) {
                countToElement = arr.length;
            }
        }
        for (Thread thread : threadList) {
            thread.join();
        }
        return KSortedArray.mergeKSortedArray(grandArray, arr);
    }
}
