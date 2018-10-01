package ru.otus.korneev.hmw15.accountservice;

import ru.otus.korneev.hmw12.service.UserProfile;
import ru.otus.korneev.hmw15.app.FrontendService;
import ru.otus.korneev.hmw15.app.MsgToFrontend;
import ru.otus.korneev.hmw15.messageSystem.Address;

public class MsgGetUserByLoginAnswer extends MsgToFrontend {

    private final UserProfile user;

    public MsgGetUserByLoginAnswer(final Address from, final Address to, final UserProfile user) {
        super(from, to);
        this.user = user;
    }

    @Override
    public void exec(final FrontendService frontendService) {
        frontendService.getUserFromAccountService(user);
    }
}
