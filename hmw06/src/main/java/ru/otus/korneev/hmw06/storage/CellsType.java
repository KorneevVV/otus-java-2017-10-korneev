package ru.otus.korneev.hmw06.storage;

import ru.otus.korneev.hmw06.banknote.Banknote;
import ru.otus.korneev.hmw06.banknote.BanknoteType;

import java.util.ArrayList;
import java.util.List;

import static ru.otus.korneev.hmw06.banknote.BanknoteType.*;

public enum CellsType {
    CELL_100(BANKNOTE_100),
    CELL_500(BANKNOTE_500),
    CELL_1000(BANKNOTE_1000),
    CELL_5000(BANKNOTE_5000);

    CellsType(BanknoteType banknote) {
        this.banknoteType = banknote;
    }

    private BanknoteType banknoteType;

    private List<Banknote> banknotes = new ArrayList<>();

    private int value = 0;

    public int getValue() {
        return value;
    }

    public BanknoteType getBanknoteType() {
        return banknoteType;
    }

    public boolean add(final Banknote banknote) {
        if (banknoteType.equals(banknote.getType())) {
            value = value + banknote.getValue();
            banknotes.add(banknote);
            return true;
        }
        return false;
    }

    @org.jetbrains.annotations.Nullable
    @org.jetbrains.annotations.Contract(pure = true)
    public Banknote banknoteOut() {
        if (value != 0) {
            Banknote banknote = banknotes.get(0);
            banknotes.remove(0);
            value = value - banknote.getValue();
            return banknote;
        }
        return null;
    }
}
