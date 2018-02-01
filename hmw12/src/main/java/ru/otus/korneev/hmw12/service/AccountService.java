package ru.otus.korneev.hmw12.service;

public interface AccountService {

    void addNewUser(final UserProfile userProfile);

    UserProfile getUserByLogin(final String login);

    UserProfile getUserBySessionId(final String sessionId);

    void addSession(final String sessionId, final UserProfile userProfile);

    void deleteSession(final String sessionId);
}
