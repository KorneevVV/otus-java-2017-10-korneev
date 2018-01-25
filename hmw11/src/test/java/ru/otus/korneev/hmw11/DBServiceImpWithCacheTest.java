package ru.otus.korneev.hmw11;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.otus.korneev.hmw10.implementation.myORM.userDataSet.UserDataSet;
import ru.otus.korneev.hmw11.DBServiceWithCache.DBServiceImpWithCache;

import java.math.BigDecimal;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DBServiceImpWithCacheTest {

    private static DBServiceImpWithCache dbService;

    @Before
    public void createTable() throws SQLException {
        dbService = new DBServiceImpWithCache(2, 1000, 0, false);
    }

    @After
    public void dropTable() throws Exception {
        dbService.close();
    }

    @Test
    public void loadTest() throws Exception {
        UserDataSet userActual1 = new UserDataSet();
        userActual1.setAge(22);
        userActual1.setName("Ivan1");
        userActual1.setSalary(BigDecimal.TEN);
        UserDataSet userActual2 = new UserDataSet();
        userActual2.setAge(23);
        userActual2.setName("Ivan2");
        userActual2.setSalary(BigDecimal.TEN);
        UserDataSet userActual3 = new UserDataSet();
        userActual3.setAge(24);
        userActual3.setName("Ivan3");
        userActual3.setSalary(BigDecimal.TEN);
        dbService.save(userActual1);
        dbService.save(userActual2);
        dbService.save(userActual3);

        UserDataSet userExcepted1 = dbService.load(1, UserDataSet.class);
        UserDataSet userExcepted2 = dbService.load(2, UserDataSet.class);
        UserDataSet userExcepted3 = dbService.load(3, UserDataSet.class);
        assertEquals(2, dbService.getHitCount());
        assertEquals(1, dbService.getMissCount());
    }
}