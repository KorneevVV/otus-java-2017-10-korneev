package ru.otus.korneev.hmw14;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

public class MultiThreadedSortingTest {

    private static Integer[] arrayIntUnSorting;

    @Before
    public void createArray() {
        Random rnd = new Random();
        arrayIntUnSorting = new Integer[1024 * 1024];
        for (int i = 0; i < arrayIntUnSorting.length; i++) {
            arrayIntUnSorting[i] = rnd.nextInt(arrayIntUnSorting.length * 5);
        }
    }

    @Test
    public void sortTest() throws InterruptedException {
        Integer[] arrayIntSorting = Arrays.copyOf(arrayIntUnSorting, arrayIntUnSorting.length);
        Arrays.sort(arrayIntSorting);
        MultiThreadedSorting.sort(arrayIntUnSorting, 6);
        assertArrayEquals(arrayIntSorting, arrayIntUnSorting);
    }
}
