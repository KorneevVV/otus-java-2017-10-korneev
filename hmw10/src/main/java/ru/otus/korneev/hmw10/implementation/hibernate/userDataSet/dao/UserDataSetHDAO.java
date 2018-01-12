package ru.otus.korneev.hmw10.implementation.hibernate.userDataSet.dao;

import org.hibernate.Session;
import ru.otus.korneev.hmw10.dataSets.DataSet;
import ru.otus.korneev.hmw10.dataSets.dao.DataSetDAO;
import ru.otus.korneev.hmw10.implementation.hibernate.DBServiceHibernateImpl;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

public class UserDataSetHDAO extends DataSetDAO {


	public UserDataSetHDAO() {
	}

	@Override
	public void createTable(final Object connection) throws SQLException {
		//no-op
	}

	@Override
	public void deleteTable(final Object connection) throws SQLException {
		//no-op
	}

	@Override
	public <T extends DataSet> void save(final T user, final Object connection) throws SQLException, IllegalAccessException {
		((Session) connection).save(user);
	}

	@Override
	public <T extends DataSet> T load(final long id, Class<T> clazz, final Object connection) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SQLException {
		return ((Session) connection).load(clazz, id);
	}
}
