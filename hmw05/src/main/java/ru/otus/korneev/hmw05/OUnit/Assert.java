package ru.otus.korneev.hmw05.OUnit;

import java.util.Arrays;

public class Assert {
    public static void assertEquals(String testName, boolean expected, boolean actual) {
        if (expected == actual) {
            System.out.println(testName + " passed");
        } else {
            System.out.println(testName + " failed: expected " + expected + ", actual " + actual);
        }
    }

    public static void assertEquals(String testName, int expected, int actual) {
        if (expected == actual) {
            System.out.println(testName + " passed");
        } else {
            System.out.println(testName + " failed: expected " + expected + ", actual " + actual);
        }
    }

    public static void assertEquals(String testName, String expected, String actual) {
        if (expected.equals(actual)) {
            System.out.println(testName + " passed");
        } else {
            System.out.println(testName + " failed: expected " + expected + ", actual " + actual);
        }
    }

    public static void assertEquals(String testName, Object expected, Object actual) {
        if (expected == null & actual == null) {
            System.out.println(testName + " passed");
        } else if (expected.equals(actual)) {
            System.out.println(testName + " passed");
        } else {
            System.out.println(testName + " failed: expected " + expected + ", actual " + actual);
        }
    }

    public static void assertEquals(String testName, int[] expected, int[] actual) {
        if (Arrays.equals(expected, actual)) {
            System.out.println(testName + " passed");
        } else {
            System.out.println(testName + " failed: expected " + Arrays.toString(expected) + ", actual " + Arrays.toString(actual));
        }
    }

    public static void assertEquals(String testName, double[] expected, double[] actual) {
        if (Arrays.equals(expected, actual)) {
            System.out.println(testName + " passed");
        } else {
            System.out.println(testName + " failed: expected " + Arrays.toString(expected) + ", actual " + Arrays.toString(actual));
        }
    }

    public static void assertEquals(String testName, double expected, double actual) {
        if (expected - actual < 0.0000000001) {
            System.out.println(testName + " passed");
        } else {
            System.out.println(testName + " failed: expected " + expected + ", actual " + actual);
        }
    }

    public static void assertEquals(String testName, char expected, char actual) {
        if (expected == actual) {
            System.out.println(testName + " passed");
        } else {
            System.out.println(testName + " failed: expected " + expected + ", actual " + actual);
        }
    }

    public static void assertEquals(String testName, int[][] expected, int[][] actual) {
        if (Arrays.deepEquals(expected, actual)) {
            System.out.println(testName + " passed");
        } else {
            System.out.println(testName + " failed: expected " + Arrays.deepToString(expected) + ", actual " + Arrays.deepToString(actual));
        }
    }

    public static void assertEquals(String testName, Object[] expected, Object[] actual) {
        if (Arrays.equals(expected, actual)) {
            System.out.println(testName + " passed");
        } else {
            System.out.println(testName + " failed: expected " + Arrays.toString(expected) + ", actual " + Arrays.toString(actual));
        }
    }

    public static void fail(String message) {
        throw new AssertionError(message);
    }

    public static void equals(String message, String messageException) {
        if (!message.equals(messageException)) {
            throw new AssertionError("failed: expected " + "\"" + message + "\"" + ", actual " + "\"" + messageException + "\"");
        }
        System.out.println("Test trow Exception passed");
    }

    public static void equals(Object message, Object messageException) {
        if (message != (messageException)) {
            throw new AssertionError("failed: expected " + "\"" + message + "\"" + ", actual " + "\"" + messageException + "\"");
        }
        System.out.println("Test trow Exception passed");
    }

    public static void assertEquals(String testName, Integer[] expected, Integer[] actual) {
        if (Arrays.deepEquals(expected, actual)) {
            System.out.println(testName + " passed");
        } else {
            System.out.println(testName + " failed: expected " + Arrays.toString(expected) + ", actual " + Arrays.toString(actual));
        }
    }
}
