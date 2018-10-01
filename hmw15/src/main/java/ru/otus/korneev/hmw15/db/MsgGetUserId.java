package ru.otus.korneev.hmw15.db;

import ru.otus.korneev.hmw15.app.DBService;
import ru.otus.korneev.hmw15.app.MsgToDB;
import ru.otus.korneev.hmw15.messageSystem.Address;

public class MsgGetUserId extends MsgToDB {
    private final String login;

    public MsgGetUserId(Address from, Address to, String login) {
        super(from, to);
        this.login = login;
    }

    @Override
    public void exec(DBService dbService) {
        int id = dbService.getUserId(login);
        dbService.getMS().sendMessage(new MsgGetUserIdAnswer(getTo(), getFrom(), login, id));
    }
}
