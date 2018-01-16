package ru.otus.korneev.hmw10.implementation.myORM.userDataSet;

import ru.otus.korneev.hmw10.dataSets.DataSet;

import java.math.BigDecimal;
import java.util.Objects;

public class UserDataSet extends DataSet{

    private String name;
    private int age;
    private BigDecimal salary;

    public UserDataSet() throws Exception {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal solary) {
        this.salary = solary;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
