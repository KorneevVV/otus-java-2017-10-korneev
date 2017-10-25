package ru.otus.korneev.hmw02;

import java.util.function.Supplier;

class CalculatorForObjectMemory {
    private final Runtime runtime;
    private final int size;
    private final Object[] arrayObject;

    CalculatorForObjectMemory(int size) {
        runtime = Runtime.getRuntime();
        this.size = size;
        arrayObject = new Object[size];
    }

    long calcMemory(Supplier<Object> supplier) {
        long res = 0;
        System.gc();
        for (int i = 0; i < size; i++) {
            arrayObject[i] = supplier.get();
        }
        System.gc();
        long memoryUsedBeforeGC = runtime.totalMemory() - runtime.freeMemory();
        clearArrayObject();
        System.gc();
        long memoryUsedAfterGC = runtime.totalMemory() - runtime.freeMemory();
        res = (memoryUsedBeforeGC - memoryUsedAfterGC) / size;
        return res;
    }

    private void clearArrayObject() {
        for (int i = 0; i < size; i++) {
            arrayObject[i] = null;
        }
    }
}
