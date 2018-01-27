package ru.otus.korneev.hmw11.DBServiceWithCache;

import ru.otus.korneev.hmw10.dataSets.DataSet;
import ru.otus.korneev.hmw10.implementation.myORM.DBServiceImpl;
import ru.otus.korneev.hmw11.Cache.CacheEngine;
import ru.otus.korneev.hmw11.Cache.CacheEngineImpl;
import ru.otus.korneev.hmw11.Cache.MyElement;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class DBServiceImpWithCache extends DBServiceImpl {

    private CacheEngine<Long, Object> cache;

    public DBServiceImpWithCache(int maxElements, long lifeTimeMs, long idleTimeMs, boolean isEternal) throws SQLException {
        super();
        cache = new CacheEngineImpl<>(maxElements, lifeTimeMs, idleTimeMs, isEternal);
    }

    @Override
    public <T extends DataSet> void save(T data) throws SQLException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        super.save(data);
        cache.put(data.getId(), data);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends DataSet> T load(long id, Class<T> clazz) throws InvocationTargetException, NoSuchMethodException, InstantiationException, SQLException, IllegalAccessException, NoSuchFieldException {
        Object element = cache.get(id);
        if (element != null) {
            return (T) element;
        }
        return super.load(id, clazz);
    }

    public int getHitCount() {
        return cache.getHitCount();
    }

    public int getMissCount() {
        return cache.getMissCount();
    }

    public void disposeCache() {
        cache.dispose();
    }
}
