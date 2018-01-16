package ru.otus.korneev.hmw10.implementation.myORM;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.assertEquals;

import ru.otus.korneev.hmw10.implementation.myORM.userDataSet.UserDataSet;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBServiceImplTest {

    private static DBServiceImpl dbService;

    @Before
    public void createTable() throws SQLException, InterruptedException {
        dbService = new DBServiceImpl();
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
        ResultSet res = stm.executeQuery("SELECT * FROM USR WHERE id = 1");
        res.next();
        assertEquals(55, res.getInt("age"));
        assertEquals("Ivan5", res.getString("name"));
        assertEquals(1, res.getLong("id"));
    }
}
