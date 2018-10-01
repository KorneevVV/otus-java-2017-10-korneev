package ru.otus.korneev.hmw15.app;

import ru.otus.korneev.hmw15.messageSystem.Addressee;

public interface AccountService extends Addressee, ru.otus.korneev.hmw12.service.AccountService {
    void init();
}
