package ru.otus.korneev.hmw15.accountservice;

import ru.otus.korneev.hmw12.service.UserProfile;
import ru.otus.korneev.hmw15.app.AccountService;
import ru.otus.korneev.hmw15.app.MsgToAccountService;
import ru.otus.korneev.hmw15.messageSystem.Address;

public class MsgAddNewUser extends MsgToAccountService {

    private UserProfile userProfile;

    public MsgAddNewUser(final Address from, final Address to, final String login, final String pass) {
        super(from, to);
        this.userProfile = new UserProfile(login, pass);
    }

    @Override
    public void exec(final AccountService accountService) {
        accountService.addNewUser(userProfile);
    }
}
