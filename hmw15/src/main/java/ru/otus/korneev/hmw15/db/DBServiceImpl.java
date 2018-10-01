package ru.otus.korneev.hmw15.db;

import ru.otus.korneev.hmw11.Cache.CacheEngineImpl;
import ru.otus.korneev.hmw11.DBServiceWithCache.DBServiceImpWithCache;
import ru.otus.korneev.hmw15.app.DBService;
import ru.otus.korneev.hmw15.app.MessageSystemContext;
import ru.otus.korneev.hmw15.messageSystem.Address;
import ru.otus.korneev.hmw15.messageSystem.MessageSystem;

import java.sql.SQLException;

public class DBServiceImpl extends DBServiceImpWithCache implements DBService {
    private final Address address;
    private final MessageSystemContext context;

    public DBServiceImpl(final MessageSystemContext context, final Address address) throws SQLException {
        super(new CacheEngineImpl<>(2, 10000, 0, false));
        this.context = context;
        this.address = address;
    }

    public void init() {
        context.getMessageSystem().addAddressee(this);
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public MessageSystem getMS() {
        return context.getMessageSystem();
    }

    public int getUserId(String name) {
        //todo: load id from db
        return name.hashCode();
    }
}
