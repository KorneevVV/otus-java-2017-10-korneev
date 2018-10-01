package ru.otus.korneev.hmw15.app;

import ru.otus.korneev.hmw15.messageSystem.Address;
import ru.otus.korneev.hmw15.messageSystem.Addressee;
import ru.otus.korneev.hmw15.messageSystem.Message;

public abstract class MsgToAccountService extends Message {
    public MsgToAccountService(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Addressee addressee) {
        if (addressee instanceof AccountService) {
            exec((AccountService) addressee);
        }
    }

    public abstract void exec(AccountService accountService);
}
