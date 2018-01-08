package ru.otus.korneev.hmw09;

import ru.otus.korneev.hmw09.user.DataSet;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Executor implements AutoCloseable {

    private static final String INSERT = "INSERT INTO USR VALUES(%s, %s)";
    private static final String SELECT = "SELECT * FROM USR WHERE id = %s";
    private final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    <T extends DataSet> void save(final T user) throws SQLException, IllegalAccessException {
        Statement statement = connection.createStatement();
        String fieldValue = ReflectionHelper.objectToString(user);
        statement.execute(String.format(INSERT, user.getId(), fieldValue));
        statement.close();
    }

    <T extends DataSet> T load(final long id, final Class<T> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SQLException {
        Statement statement = connection.createStatement();
        statement.execute(String.format(SELECT, id));
        ResultSet resultSet = statement.getResultSet();
        resultSet.next();
        T dataSet = ReflectionHelper.fillingObject(resultSet, clazz);
        dataSet.setId(id);
        statement.close();
        return dataSet;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    public Connection getConnection() {
        return connection;
    }
}
