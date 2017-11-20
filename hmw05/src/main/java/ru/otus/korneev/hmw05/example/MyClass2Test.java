package ru.otus.korneev.hmw05.example;

import ru.otus.korneev.hmw05.OUnit.Assert;
import ru.otus.korneev.hmw05.OUnit.annotations.AfterOtus;
import ru.otus.korneev.hmw05.OUnit.annotations.BeforeOtus;
import ru.otus.korneev.hmw05.OUnit.annotations.TestOtus;

public class MyClass2Test {

    private final String SIMPLE_NAME_CLASS = this.getClass().getSimpleName();

    @BeforeOtus
    public void runBefore1() {
        System.out.println(SIMPLE_NAME_CLASS + " Run before 1");
    }

    @BeforeOtus
    public void runBefore2() {
        System.out.println(SIMPLE_NAME_CLASS + " Run before 2");
    }

    @TestOtus
    public void runTest1() {
        Assert.assertEquals("Failed runTest1 from MyClass1Test", "TEST", "test"); //FAILED
    }

    @TestOtus
    public void runTest2() {
        System.out.println(SIMPLE_NAME_CLASS + " Run test 2");
    }

    @TestOtus
    public void runTest3() {
        System.out.println(SIMPLE_NAME_CLASS + " Run test 3");
    }

    @AfterOtus
    public void runAfter1() {
        System.out.println(SIMPLE_NAME_CLASS + " Run after 1");
    }

    @AfterOtus
    public void runAfter2() {
        System.out.println(SIMPLE_NAME_CLASS + " Run after 2");
    }
}
