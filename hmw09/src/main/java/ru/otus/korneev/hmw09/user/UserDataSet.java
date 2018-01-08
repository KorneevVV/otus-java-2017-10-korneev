package ru.otus.korneev.hmw09.user;

import java.math.BigDecimal;
import java.util.Objects;

public class UserDataSet extends DataSet {

    private String name;
    private int age;
    private BigDecimal salary;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserDataSet that = (UserDataSet) o;
        return age == that.age &&
                Objects.equals(name, that.name) &&
                Objects.equals(salary, that.salary);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), name, age, salary);
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
