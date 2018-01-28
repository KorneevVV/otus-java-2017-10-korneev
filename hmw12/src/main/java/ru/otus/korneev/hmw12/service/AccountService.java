package ru.otus.korneev.hmw12.service;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionIdToProfile;

    public AccountService() {
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();
    }

    public void addNewUser(final UserProfile userProfile) {
        loginToProfile.put(userProfile.getLogin(), userProfile);
    }

    public UserProfile getUserByLogin(final String login) {
        return loginToProfile.get(login);
    }

    public UserProfile getUserBySessionId(final String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(final String sessionId, final UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public void deleteSession(final String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}

