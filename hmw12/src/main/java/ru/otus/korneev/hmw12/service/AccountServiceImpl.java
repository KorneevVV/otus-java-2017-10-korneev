package ru.otus.korneev.hmw12.service;

import java.util.HashMap;
import java.util.Map;

public class AccountServiceImpl implements AccountService {
    private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionIdToProfile;

    public AccountServiceImpl() {
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();
    }

    @Override
    public void addNewUser(final UserProfile userProfile) {
        loginToProfile.put(userProfile.getLogin(), userProfile);
    }

    @Override
    public UserProfile getUserByLogin(final String login) {
        return loginToProfile.get(login);
    }

    @Override
    public UserProfile getUserBySessionId(final String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    @Override
    public void addSession(final String sessionId, final UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    @Override
    public void deleteSession(final String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}

