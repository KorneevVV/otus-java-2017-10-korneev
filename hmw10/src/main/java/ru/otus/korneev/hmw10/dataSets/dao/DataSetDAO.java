package ru.otus.korneev.hmw10.dataSets.dao;

import ru.otus.korneev.hmw10.dataSets.DataSet;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class DataSetDAO {

    abstract public void createTable(final Connection connection) throws SQLException;

    abstract public void deleteTable(final Connection connection) throws SQLException;

    abstract public   <T extends DataSet> void save(final T user, final Connection connection) throws SQLException, IllegalAccessException;

    abstract public <T extends DataSet> T load(final long id, final Class<T> clazz, final Connection connection) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SQLException;
}
