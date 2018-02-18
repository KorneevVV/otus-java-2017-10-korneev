package ru.otus.korneev.hmw14;

import java.util.PriorityQueue;

public class KSortedArray {
    @SuppressWarnings("unchecked")
    public static <E extends Comparable<E>> E[] mergeKSortedArray(Object[][] arr, E[] result) {
        PriorityQueue<ArrayContainer<E>> queue = new PriorityQueue<>();

        for (Object[] anArr : arr) {
            queue.add(new ArrayContainer<>((E[]) anArr, 0));
        }

        int m = 0;
        while (!queue.isEmpty()) {
            ArrayContainer<E> ac = queue.poll();
            result[m++] = ac.arr[ac.index];
            if (ac.index < ac.arr.length - 1) {
                queue.add(new ArrayContainer(ac.arr, ac.index + 1));
            }
        }
        return result;
    }
}

class ArrayContainer<E extends Comparable<E>> implements Comparable<ru.otus.korneev.hmw14.ArrayContainer<E>> {
    E[] arr;
    int index;

    ArrayContainer(E[] arr, int index) {
        this.arr = arr;
        this.index = index;
    }

    @Override
    public int compareTo(ArrayContainer<E> o) {
        return this.arr[this.index].compareTo(o.arr[o.index]);
    }
}
