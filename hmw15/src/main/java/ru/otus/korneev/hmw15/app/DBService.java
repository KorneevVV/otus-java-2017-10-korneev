package ru.otus.korneev.hmw15.app;

import ru.otus.korneev.hmw15.messageSystem.Addressee;

public interface DBService extends Addressee, ru.otus.korneev.hmw10.dbService.DBService {
    void init();

    int getUserId(String name); // todo delete

//    CacheEngine<Long, Object> getCacheEngine();
}
