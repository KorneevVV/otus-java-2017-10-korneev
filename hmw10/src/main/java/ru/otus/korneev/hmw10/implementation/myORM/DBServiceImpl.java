package ru.otus.korneev.hmw10.implementation.myORM;

import ru.otus.korneev.hmw10.Cache.CacheEngine;
import ru.otus.korneev.hmw10.dbService.DBService;
import ru.otus.korneev.hmw10.dataSets.dao.DataSetDAO;
import ru.otus.korneev.hmw10.dataSets.DataSet;
import ru.otus.korneev.hmw10.implementation.myORM.helpers.RegistryDataSet;
import ru.otus.korneev.hmw10.implementation.myORM.helpers.*;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

public class DBServiceImpl implements DBService {

    private Connection connection;

    public DBServiceImpl() throws SQLException {
        connection = ConnectionHelper.getConnection();
        RegistryDataSet.register();
        createTable();
    }

    private void createTable() {
        RegistryDataSet.getREGISTRY().forEach((key, value) -> {
            try {
                value.getDeclaredConstructor().newInstance().createTable(connection);
            } catch (Exception e) {
                e.printStackTrace(); // todo handle exception
            }
        });
    }

    public Connection getConnection(){
        return connection;
    }

    public void deleteAllTables(){
        RegistryDataSet.getREGISTRY().forEach((key, value) -> {
            try {
                value.getDeclaredConstructor().newInstance().deleteTable(connection);
            } catch (Exception e) {
                e.printStackTrace(); // todo handle exception
            }
        });
    }

    @Override
    public <T extends DataSet> void save(final T data) throws SQLException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        DataSetDAO dao = RegistryDataSet.getDataSetDAO(data.getClass()).getDeclaredConstructor().newInstance();
        dao.save(data, connection);
    }

    @Override
    public <T extends DataSet> T load(final long id, final Class<T> clazz) throws InvocationTargetException, NoSuchMethodException, InstantiationException, SQLException, IllegalAccessException, NoSuchFieldException {
        DataSetDAO dao = RegistryDataSet.getDataSetDAO(clazz).getDeclaredConstructor().newInstance();
        return dao.load(id, clazz, connection);
    }

    @Override
    public String getLocalStatus() {
        return null;
    }

    @Override
    public CacheEngine getCacheEngine() {
        return null;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
