package ru.otus.korneev.hmw15.app;


import ru.otus.korneev.hmw15.messageSystem.Address;
import ru.otus.korneev.hmw15.messageSystem.Addressee;
import ru.otus.korneev.hmw15.messageSystem.Message;

public abstract class MsgToFrontend extends Message {
    public MsgToFrontend(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Addressee addressee) {
        if (addressee instanceof FrontendService) {
            exec((FrontendService) addressee);
        }
    }

    public abstract void exec(FrontendService frontendService);
}