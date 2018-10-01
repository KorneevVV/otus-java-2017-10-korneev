package ru.otus.korneev.hmw15.app;

import ru.otus.korneev.hmw15.messageSystem.Address;
import ru.otus.korneev.hmw15.messageSystem.MessageSystem;

public class MessageSystemContext {
    private final MessageSystem messageSystem;

    private Address frontAddress;
    private Address dbAddress;
    private Address accountService;

    public MessageSystemContext(MessageSystem messageSystem) {
        this.messageSystem = messageSystem;
    }

    public MessageSystem getMessageSystem() {
        return messageSystem;
    }

    public Address getFrontAddress() {
        return frontAddress;
    }

    public void setFrontAddress(Address frontAddress) {
        this.frontAddress = frontAddress;
    }

    public Address getDbAddress() {
        return dbAddress;
    }

    public void setDbAddress(Address dbAddress) {
        this.dbAddress = dbAddress;
    }

    public Address getAccountService() {
        return accountService;
    }

    public void setAccountService(Address accountService) {
        this.accountService = accountService;
    }
}
