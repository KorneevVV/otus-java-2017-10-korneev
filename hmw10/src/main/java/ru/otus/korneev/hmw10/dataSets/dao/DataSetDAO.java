package ru.otus.korneev.hmw10.dataSets.dao;

import ru.otus.korneev.hmw10.dataSets.DataSet;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public abstract class DataSetDAO {

    abstract public void createTable(final Object connection) throws SQLException;

    abstract public void deleteTable(final Object connection) throws SQLException;

    abstract public   <T extends DataSet> void save(final T user, final Object connection) throws SQLException, IllegalAccessException, NoSuchFieldException;

    abstract public <T extends DataSet> T load(final long id, final Class<T> clazz, final Object connection) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SQLException, NoSuchFieldException;

    public <T extends DataSet> T setId(final long id, final Class<T> clazz, final T user) throws NoSuchFieldException, IllegalAccessException {
        Field field = clazz.getSuperclass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(user, id);
        field.setAccessible(false);
        return user;
    }
}
