package ru.otus.korneev.hmw15.app;

import ru.otus.korneev.hmw12.service.UserProfile;
import ru.otus.korneev.hmw15.messageSystem.Addressee;

public interface FrontendService extends Addressee {
    void init();

    void handleRequest(String login);

    void addUser(int id, String name);

    void getUserFromAccountService(UserProfile user);
}

