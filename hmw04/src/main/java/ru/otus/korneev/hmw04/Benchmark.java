package ru.otus.korneev.hmw04;

import java.util.LinkedList;

class Benchmark {
    private volatile int size = 0;
    private static LinkedList<Object> objects = new LinkedList<>();

    @SuppressWarnings("InfiniteLoopStatement")
    void run() {

        System.out.println("Starting the loop");
        while (true) {
            int local = size;
            for (int i = 0; i < local; i++) {
                objects.add(new byte[5 * 1024]);
            }
            for (int i = 0; i < local / 2; i++) {
                objects.removeFirst();
            }
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Created " + local + " objects.");
        }
    }

    public void setSize(int size) {
        this.size = size;
    }
}