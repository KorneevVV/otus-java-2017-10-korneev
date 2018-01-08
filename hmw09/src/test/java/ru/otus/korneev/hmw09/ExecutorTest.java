package ru.otus.korneev.hmw09;

import org.junit.BeforeClass;
import org.junit.Test;

import ru.otus.korneev.hmw09.connection.ConnectionHelper;
import ru.otus.korneev.hmw09.user.UserDataSet;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.*;

public class ExecutorTest {

    private static Executor executor;

    @BeforeClass
    public static void initBD() throws SQLException {
        Connection connection = ConnectionHelper.getConnection();
        executor = new Executor(connection);
        createTable(connection);
    }

    private static void createTable(final Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE USR (id int auto_increment primary key, name varchar, age int, salary NUMBER)");
    }

    @Test
    public void loadTest() throws SQLException, IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        UserDataSet userActual = new UserDataSet();
        userActual.setAge(22);
        userActual.setName("Ivan2");
        userActual.setId(1);
        userActual.setSalary(BigDecimal.TEN);
        executor.save(userActual);
        UserDataSet userExcepted = executor.load(1, UserDataSet.class);
        assertEquals(userExcepted, userActual);
    }

    @Test
    public void saveTest() throws SQLException, IllegalAccessException {
        UserDataSet user1 = new UserDataSet();
        user1.setAge(55);
        user1.setName("Ivan5");
        user1.setId(1);
        user1.setSalary(BigDecimal.TEN);
        executor.save(user1);
        Statement stm = executor.getConnection().createStatement();
        ResultSet res = stm.executeQuery("SELECT * FROM USR WHERE id = 1");
        res.next();
        assertEquals(55, res.getInt("age"));
        assertEquals("Ivan5", res.getString("name"));
        assertEquals(1, res.getLong("id"));
        stm.executeUpdate("TRUNCATE TABLE USR");
    }
}
