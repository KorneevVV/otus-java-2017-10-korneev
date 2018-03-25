package ru.otus.korneev.hmw13.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.korneev.hmw10.dbService.DBService;
import ru.otus.korneev.hmw11.Cache.CacheEngine;
import ru.otus.korneev.hmw11.Cache.CacheEngineImpl;
import ru.otus.korneev.hmw11.DBServiceWithCache.DBServiceImpWithCache;
import ru.otus.korneev.hmw12.service.AccountService;
import ru.otus.korneev.hmw12.service.AccountServiceImpl;

import java.sql.SQLException;

@Configuration
public class AppConfig {

    @Autowired
    private CacheEngine<Long, Object> cacheEngine;

    @Bean
    public AccountService getAccountService() {
        return new AccountServiceImpl();
    }

    @Bean
    public CacheEngine<Long, Object> getCacheEngine() {
        return new CacheEngineImpl<>(2, 10000, 0, false);
    }

    @Bean
    public DBService getDBService() throws SQLException {
        return new DBServiceImpWithCache(cacheEngine);
    }
}
