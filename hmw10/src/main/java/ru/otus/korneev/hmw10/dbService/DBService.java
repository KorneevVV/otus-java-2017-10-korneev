package ru.otus.korneev.hmw10.dbService;

import ru.otus.korneev.hmw10.dataSets.DataSet;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface DBService extends AutoCloseable {

    <T extends DataSet> void save(final T data) throws SQLException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException;

    <T extends DataSet> T load(final long id, final Class<T> clazz) throws InvocationTargetException, NoSuchMethodException, InstantiationException, SQLException, IllegalAccessException;

}
