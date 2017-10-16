package ru.otus.korneev.hmw01;

public class StopWatch {
    private static long startTime = 0;

    public static long start() {
        startTime = System.currentTimeMillis();
        return startTime;
    }

    public static long getElapsedTime() {
        if (startTime == 0) {
            System.out.println("getElapsedTime is wrong, you need first use start()");
        }
        long elaspedTime = System.currentTimeMillis() - startTime;
        startTime = 0;
        return elaspedTime;
    }
}
