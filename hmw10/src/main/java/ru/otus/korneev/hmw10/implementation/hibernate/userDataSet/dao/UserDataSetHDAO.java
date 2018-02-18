package ru.otus.korneev.hmw10.implementation.hibernate.userDataSet.dao;

import org.hibernate.Session;
import ru.otus.korneev.hmw10.dataSets.DataSet;
import ru.otus.korneev.hmw10.dataSets.dao.DataSetDAO;

public class UserDataSetHDAO extends DataSetDAO {


	public UserDataSetHDAO() {
	}

	@Override
    public void createTable(final Object connection) {
		//no-op
	}

	@Override
    public void deleteTable(final Object connection) {
		//no-op
	}

	@Override
    public <T extends DataSet> void save(final T user, final Object connection) {
		((Session) connection).save(user);
	}

	@Override
    public <T extends DataSet> T load(final long id, Class<T> clazz, final Object connection) {
		return ((Session) connection).load(clazz, id);
	}
}
