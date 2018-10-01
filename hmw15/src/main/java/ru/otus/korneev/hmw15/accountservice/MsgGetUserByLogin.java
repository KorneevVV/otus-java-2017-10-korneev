package ru.otus.korneev.hmw15.accountservice;

import ru.otus.korneev.hmw12.service.UserProfile;
import ru.otus.korneev.hmw15.app.AccountService;
import ru.otus.korneev.hmw15.app.MsgToAccountService;
import ru.otus.korneev.hmw15.messageSystem.Address;

public class MsgGetUserByLogin extends MsgToAccountService {
    private String login;

    public MsgGetUserByLogin(final Address from, final Address to, final String login) {
        super(from, to);
        this.login = login;
    }

    @Override
    public void exec(final AccountService accountService) {
        UserProfile user = accountService.getUserByLogin(login);
        accountService.getMS().sendMessage(new MsgGetUserByLoginAnswer(getTo(), getFrom(), user));
    }
}
