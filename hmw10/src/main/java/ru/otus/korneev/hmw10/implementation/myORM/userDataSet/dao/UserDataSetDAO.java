package ru.otus.korneev.hmw10.implementation.myORM.userDataSet.dao;

import ru.otus.korneev.hmw10.dataSets.*;
import ru.otus.korneev.hmw10.dataSets.dao.DataSetDAO;
import ru.otus.korneev.hmw10.implementation.myORM.helpers.ReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDataSetDAO extends DataSetDAO{

    private static final String INSERT = "INSERT INTO USR VALUES(DEFAULT, %s)";
    private static final String SELECT = "SELECT * FROM USR WHERE id = %s";
    private static final String CREATE = "CREATE TABLE USR (id int auto_increment primary key, name varchar, age int, salary NUMBER)";
    private static final String DELETE = "DROP TABLE USR";

    public UserDataSetDAO() {
    }

    @Override
    public void createTable(final Object connection) throws SQLException {
        Statement statement = ((Connection)connection).createStatement();
        statement.execute(CREATE);
    }

    @Override
    public void deleteTable(Object connection) throws SQLException {
        Statement statement = ((Connection)connection).createStatement();
        statement.executeUpdate(DELETE);
    }

    public   <T extends DataSet> void save(final T user, final Object connection) throws SQLException, IllegalAccessException {
        Statement statement = ((Connection)connection).createStatement();
        String fieldValue = ReflectionHelper.objectToString(user);
        statement.execute(String.format(INSERT, fieldValue));
    }

    public <T extends DataSet> T load(final long id, final Class<T> clazz, final Object connection) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SQLException {
        Statement statement = ((Connection)connection).createStatement();
        statement.execute(String.format(SELECT, id));
        ResultSet resultSet = statement.getResultSet();
        resultSet.next();
        T dataSet = ReflectionHelper.fillingObject(resultSet, clazz);
        dataSet.setId(id);
        return dataSet;
    }
}
