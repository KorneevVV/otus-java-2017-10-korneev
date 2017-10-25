package ru.otus.korneev.hmw02;

import java.util.ArrayList;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            CalculatorForObjectMemory calculator = new CalculatorForObjectMemory(200_000);
            getSizeMemoryEmptyObject(calculator);
            getSizeMemoryFillingContainer(calculator);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getSizeMemoryEmptyObject(CalculatorForObjectMemory calculator) throws Exception {
        System.out.println(String.class + " pool size(bytes): " + calculator.calcMemory(String::new));
        System.out.println(String.class + " without String pool size(bytes): " + calculator.calcMemory(() -> new String(new char[0])));
        System.out.println("Empty Array size(bytes): " + calculator.calcMemory(() -> new Object[0]));
        System.out.println(ArrayList.class + " size(bytes): " + calculator.calcMemory(() -> new ArrayList(10)));
        System.out.println(TreeSet.class + " size(bytes): " + calculator.calcMemory(TreeSet::new));
    }

    private static void getSizeMemoryFillingContainer(final CalculatorForObjectMemory calculator) throws Exception {

        for (int i = 0; i < 11; i++) {
            int f = i;
            System.out.println("Array with element: " + i + ", size(bytes): " + calculator.calcMemory(() -> {
                Object[] array = new Object[f];
                for (int j = 0; j < f; j++) {
                    array[j] = new Object();
                }
                return array;
            }));
        }
        for (int i = 0; i < 11; i++) {
            int f = i;
            System.out.println(ArrayList.class + " with element: " + i + ", size(bytes): " + calculator.calcMemory(() -> {
                ArrayList<Object> list = new ArrayList<>(10);
                for (int j = 0; j < f; j++) {
                    list.add(new Object());
                }
                return list;
            }));
        }
        for (int i = 0; i < 11; i++) {
            int f = i;
            System.out.println(TreeSet.class + "with element: " + i + ", size(bytes): " + calculator.calcMemory(() -> {
                TreeSet<Integer> set = new TreeSet<>();
                for (int j = 0; j < f; j++) {
                    set.add(j);
                }
                return set;
            }));
        }
    }
}
