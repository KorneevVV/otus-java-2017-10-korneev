package ru.otus.korneev.hmw06.banknote;

import ru.otus.korneev.hmw06.exception.ExceptionInvalidBanknote;

public enum BanknoteType {
    BANKNOTE_100(100),
    BANKNOTE_500(500),
    BANKNOTE_1000(1000),
    BANKNOTE_5000(5000);

    private int value;

    BanknoteType(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static BanknoteType getBanknoteType(final int value) throws ExceptionInvalidBanknote {
        switch (value) {
            case 100:
                return BANKNOTE_100;
            case 500:
                return BANKNOTE_500;
            case 1000:
                return BANKNOTE_1000;
            case 5000:
                return BANKNOTE_5000;
            default:
                throw new ExceptionInvalidBanknote("Invalid value banknote: " + value);
        }
    }
}
