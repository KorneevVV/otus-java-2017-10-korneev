package ru.otus.korneev.hmw10.implementation.hibernate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.otus.korneev.hmw10.implementation.hibernate.userDataSet.AddressDataSet;
import ru.otus.korneev.hmw10.implementation.hibernate.userDataSet.UserDataSetH;

import static org.junit.Assert.assertEquals;

public class DBServiceHibernateImplTest {

    private static DBServiceHibernateImpl dbService;

    @Before
    public void createTable() {
        dbService = new DBServiceHibernateImpl();
    }

    @After
    public void dropTable() {
        dbService.close();
    }

    @Test
    public void loadTest() throws Exception {
        UserDataSetH userActual = new UserDataSetH();
        userActual.setAge(22);
        userActual.setName("Ivan2");
        AddressDataSet address = new AddressDataSet();
        address.setStreet("address");
        userActual.setAddressDataSet(address);
        dbService.save(userActual);
        UserDataSetH userExcepted = dbService.load(1, UserDataSetH.class);
        assertEquals(userExcepted, userActual);
    }

    @Test
    public void saveTest() throws Exception {
        UserDataSetH userActual = new UserDataSetH();
        userActual.setAge(22);
        userActual.setName("Ivan2");
        AddressDataSet address = new AddressDataSet();
        address.setStreet("address");
        userActual.setAddressDataSet(address);
        dbService.save(userActual);
        UserDataSetH userExcepted = dbService.load(1, UserDataSetH.class);
        assertEquals(userExcepted, userActual);
    }
}
