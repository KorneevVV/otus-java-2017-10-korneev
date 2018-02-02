package ru.otus.korneev.hmw11;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.otus.korneev.hmw10.implementation.myORM.userDataSet.UserDataSet;
import ru.otus.korneev.hmw11.Cache.CacheEngine;
import ru.otus.korneev.hmw11.Cache.CacheEngineImpl;
import ru.otus.korneev.hmw11.DBServiceWithCache.DBServiceImpWithCache;

import java.math.BigDecimal;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class DBServiceImpWithCacheTest {

    private static DBServiceImpWithCache dbService;
    private static CacheEngine<Long, Object> cache;


    @Before
    public void createTable() throws SQLException {
        cache = new CacheEngineImpl<>(2, 100, 0, false);
        dbService = new DBServiceImpWithCache(cache);
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
        dbService.save(userActual1);
        dbService.save(userActual1);
        dbService.save(userActual1);
        dbService.save(userActual1);
        UserDataSet userExcepted1 = dbService.load(1, UserDataSet.class);
        UserDataSet userExcepted2 = dbService.load(2, UserDataSet.class);
        UserDataSet userExcepted3 = dbService.load(3, UserDataSet.class);
        UserDataSet userExcepted4 = dbService.load(4, UserDataSet.class);
        assertEquals("Hit count",2, cache.getHitCount());
        assertEquals("Miss count",2, cache.getMissCount());
    }
}