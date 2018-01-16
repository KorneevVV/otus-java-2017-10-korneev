package ru.otus.korneev.hmw10.implementation.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.otus.korneev.hmw10.dataSets.DataSet;
import ru.otus.korneev.hmw10.dataSets.dao.DataSetDAO;
import ru.otus.korneev.hmw10.dbService.DBService;
import ru.otus.korneev.hmw10.implementation.hibernate.userDataSet.AddressDataSet;
import ru.otus.korneev.hmw10.implementation.hibernate.userDataSet.UserDataSetH;
import ru.otus.korneev.hmw10.implementation.myORM.helpers.RegistryDataSet;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.function.Function;

public class DBServiceHibernateImpl implements DBService {

	private static SessionFactory sessionFactory = null;

	public DBServiceHibernateImpl() {
		Configuration configuration = new Configuration();

		configuration.addAnnotatedClass(UserDataSetH.class);
		configuration.addAnnotatedClass(AddressDataSet.class);

		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
		configuration.setProperty("hibernate.connection.url", "jdbc:h2:./test2");
		configuration.setProperty("hibernate.connection.username", "tully");
		configuration.setProperty("hibernate.connection.password", "tully");
		configuration.setProperty("hibernate.show_sql", "true");
		configuration.setProperty("hibernate.hbm2ddl.auto", "create");
		configuration.setProperty("hibernate.connection.useSSL", "false");
		configuration.setProperty("hibernate.enable_lazy_load_no_trans", "true");

		sessionFactory = createSessionFactory(configuration);

		RegistryDataSet.register();
	}

	private static SessionFactory createSessionFactory(Configuration configuration) {
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
		builder.applySettings(configuration.getProperties());
		ServiceRegistry serviceRegistry = builder.build();
		return configuration.buildSessionFactory(serviceRegistry);
	}

	@Override
	public <T extends DataSet> void save(final T data) throws SQLException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException, NoSuchFieldException {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			DataSetDAO dao = RegistryDataSet.getDataSetDAO(data.getClass()).getDeclaredConstructor().newInstance();
			dao.save(data, session);
			transaction.commit();
		}
	}

	@Override
	public <T extends DataSet> T load(final long id, final Class<T> clazz) throws InvocationTargetException, NoSuchMethodException, InstantiationException, SQLException, IllegalAccessException, NoSuchFieldException {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			DataSetDAO dao = RegistryDataSet.getDataSetDAO(clazz).getDeclaredConstructor().newInstance();
			T dataSet = dao.load(id, clazz, session);
			transaction.commit();
			return dataSet;
		}
	}

	@Override
	public void close() throws Exception {
		sessionFactory.close();
	}

	public String getLocalStatus() {
		return runInSession(session -> session.getTransaction().getStatus().name());
	}

	private <R> R runInSession(Function<Session, R> function) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			R result = function.apply(session);
			transaction.commit();
			return result;
		}
	}

	public static Session getSession() {
		return sessionFactory.openSession();
	}
}
