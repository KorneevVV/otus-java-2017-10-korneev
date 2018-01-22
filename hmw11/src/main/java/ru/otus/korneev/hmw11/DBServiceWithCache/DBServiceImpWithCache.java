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

    private DBServiceImpWithCache() throws SQLException {
    }

    public  DBServiceImpWithCache(int maxElements, long lifeTimeMs, long idleTimeMs, boolean isEternal) throws SQLException {
        super();
        cache = new CacheEngineImpl<>(maxElements, lifeTimeMs, idleTimeMs, isEternal);
    }

    @Override
    public <T extends DataSet> void save(T data) throws SQLException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        super.save(data);
        cache.put(new MyElement<>(data.getId(), data));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends DataSet> T load(long id, Class<T> clazz) throws InvocationTargetException, NoSuchMethodException, InstantiationException, SQLException, IllegalAccessException, NoSuchFieldException {
        MyElement<Long, Object> element = cache.get(id);
        if (element != null || element.getValue() != null){
            return (T)element.getValue();
        }
        return super.load(id, clazz);
    }
}
