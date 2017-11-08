package ru.otus.korneev.hmw04;

public class Main {
    public static void main(String[] args) {
        int size = 1000;
        Benchmark mbean = new Benchmark();
        mbean.setSize(size);
        mbean.run();
    }
}
