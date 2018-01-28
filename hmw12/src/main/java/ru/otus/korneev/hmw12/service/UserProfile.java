package ru.otus.korneev.hmw12.service;

public class UserProfile {
    private final String login;
    private final String pass;

    public UserProfile(final String login, final String pass) {
        this.login = login;
        this.pass = pass;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }
}

