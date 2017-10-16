package ru.otus.korneev.hmw01;

import org.apache.commons.collections4.list.TreeList;

import java.util.LinkedList;

import static ru.otus.korneev.hmw01.StopWatch.getElapsedTime;
import static ru.otus.korneev.hmw01.StopWatch.start;

public class Main {
    public static void main(String[] args) {
        testPerformanceAL();
        testPerformanceLL();
    }
    private static void testPerformanceAL() {
        start();
        TreeList<Integer> testTLB = new TreeList<>();
        for (int i = 0; i < 10_000_000; i++) {
            testTLB.add(i);
        }
        System.out.println("Time add TreeList begin = " + getElapsedTime());

        start();
        for (int i = 1_000; i >= 0; i--) {
            testTLB.remove(0);
        }
        System.out.println("Time remove TreeList begin = " + getElapsedTime());

        start();
        TreeList<Integer> testTLM = new TreeList<>();
        testTLM.add(0);
        testTLM.add(0);
        for (int i = 2; i < 200_000; i++) {
            testTLM.add(i / 2, i);
        }
        System.out.println("Time add TreeList middle = " + getElapsedTime());

        start();
        for (int i = 199_999; i > 0; i--) {
            testTLM.remove(i / 2);
        }
        System.out.println("Time remove TreeList middle = " + getElapsedTime());

        start();
        TreeList<Integer> testTLE = new TreeList<>();
        for (int i = 0; i < 19_000_001; i++) {
            testTLE.add(i, i);
        }
        System.out.println("Time add TreeList ending = " + getElapsedTime());

        start();
        for (int i = 19_000_000; i > 0; i--) {
            testTLE.remove(i);
        }
        System.out.println("Time remove TreeList ending = " + getElapsedTime());
    }

    private static void testPerformanceLL() {
        start();
        LinkedList<Integer> testLLB = new LinkedList<>();
        for (int i = 0; i < 10_000_000; i++) {
            testLLB.add(0, i);
        }
        System.out.println("Time add LinkedList begin = " + getElapsedTime());

        start();
        for (int i = 1_000; i >= 0; i--) {
            testLLB.remove(0);
        }
        System.out.println("Time remove LinkedList begin = " + getElapsedTime());

        start();
        LinkedList<Integer> testLLM = new LinkedList<>();
        testLLM.add(0);
        testLLM.add(0);
        for (int i = 2; i < 200_000; i++) {
            testLLM.add((i / 2), i);

        }
        System.out.println("Time add LinkedList middle = " + getElapsedTime());

        start();
        for (int i = 199_999; i > 0; i--) {
            testLLM.remove(i / 2);
        }
        System.out.println("Time remove LinkedList middle = " + getElapsedTime());


        start();
        LinkedList<Integer> testLLE = new LinkedList<>();
        for (int i = 0; i < 19_000_001; i++) {
            testLLE.add(i);
        }
        System.out.println("Time add LinkedList ending = " + getElapsedTime());

        start();
        for (int i = 19_000_000; i > 0; i--) {
            testLLE.remove(i);
        }
        System.out.println("Time remove LinkedList ending = " + getElapsedTime());
    }
}
