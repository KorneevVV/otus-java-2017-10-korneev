package ru.otus.korneev.hmw11;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.otus.korneev.hmw10.implementation.myORM.userDataSet.UserDataSet;
import ru.otus.korneev.hmw11.DBServiceWithCache.DBServiceImpWithCache;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class DBServiceImpWithCacheTest {

    private static DBServiceImpWithCache dbService;

    @Before
    public void createTable() throws SQLException, InterruptedException {
        dbService = new DBServiceImpWithCache(2, 1000, 0, false);
    }

    @After
    public void dropTable() throws Exception {
        dbService.close();
    }

    @Test
    public void loadTest() throws Exception {
        UserDataSet userActual = new UserDataSet();
        userActual.setAge(22);
        userActual.setName("Ivan2");
        userActual.setSalary(BigDecimal.TEN);
        dbService.save(userActual);
        UserDataSet userExcepted = dbService.load(1, UserDataSet.class);
        assertEquals(userExcepted, userActual);
    }

    @Test
    public void saveTest() throws Exception {
        UserDataSet user1 = new UserDataSet();
        user1.setAge(55);
        user1.setName("Ivan5");
        user1.setSalary(BigDecimal.TEN);
        dbService.save(user1);
        Statement stm = dbService.getConnection().createStatement();
        ResultSet res = stm.executeQuery("SELECT * FROM USER WHERE id = 1");
        res.next();
        assertEquals(55, res.getInt("age"));
        assertEquals("Ivan5", res.getString("name"));
        assertEquals(1, res.getLong("id"));
    }
}