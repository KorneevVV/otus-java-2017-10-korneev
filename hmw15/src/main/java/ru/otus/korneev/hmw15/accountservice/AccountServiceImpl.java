package ru.otus.korneev.hmw15.accountservice;

import ru.otus.korneev.hmw15.app.AccountService;
import ru.otus.korneev.hmw15.app.MessageSystemContext;
import ru.otus.korneev.hmw15.messageSystem.Address;
import ru.otus.korneev.hmw15.messageSystem.MessageSystem;

public class AccountServiceImpl extends ru.otus.korneev.hmw12.service.AccountServiceImpl implements AccountService {

    private final Address address;

    private final MessageSystemContext context;

    public AccountServiceImpl(final MessageSystemContext context, final Address address) {
        this.context = context;
        this.address = address;
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public MessageSystem getMS() {
        return context.getMessageSystem();
    }

    @Override
    public void init() {
        context.getMessageSystem().addAddressee(this);
    }
}
