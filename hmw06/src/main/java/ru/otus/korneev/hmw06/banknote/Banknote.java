package ru.otus.korneev.hmw06.banknote;

import ru.otus.korneev.hmw06.exception.ExceptionInvalidBanknote;

public class Banknote {

    private BanknoteType type;

    public Banknote(final int value) throws ExceptionInvalidBanknote {
        type = BanknoteType.getBanknoteType(value);
    }

    public int getValue(){
     return  type.getValue();
    }

    public BanknoteType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Banknote value: " + type.getValue();
    }
}
