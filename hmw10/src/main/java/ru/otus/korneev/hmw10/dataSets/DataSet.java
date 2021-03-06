package ru.otus.korneev.hmw10.dataSets;


import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public abstract class DataSet{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataSet dataSet = (DataSet) o;
        return id == dataSet.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
