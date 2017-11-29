package ru.otus.korneev.hmw06;

import ru.otus.korneev.hmw06.banknote.Banknote;
import ru.otus.korneev.hmw06.exception.ExceptionCashOut;
import ru.otus.korneev.hmw06.exception.ExceptionInvalidBanknote;
import ru.otus.korneev.hmw06.storage.Storage;

import java.util.ArrayList;
import java.util.List;

public class ATM {

    private Storage storage;

    public ATM() {
        storage = new Storage();
        System.out.println("ATM created");
    }

    public void cashIn(List<Banknote> banknotes) throws ExceptionInvalidBanknote {
        storage.cashIn(banknotes);
    }

    public List<Banknote> cashOut(final int amount) throws ExceptionCashOut {
        List<Banknote> result = new ArrayList<>();
        result.addAll(storage.cashOut(amount));
        return result;
    }

    public int balance() {
        return storage.getBalance();
    }
}
