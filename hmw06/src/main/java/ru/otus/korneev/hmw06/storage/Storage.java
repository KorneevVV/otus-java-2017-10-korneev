package ru.otus.korneev.hmw06.storage;

import ru.otus.korneev.hmw06.banknote.Banknote;
import ru.otus.korneev.hmw06.banknote.BanknoteType;
import ru.otus.korneev.hmw06.exception.ExceptionCashOut;
import ru.otus.korneev.hmw06.exception.ExceptionInvalidBanknote;

import java.util.*;

public class Storage {

    private List<CellsType> cells = new ArrayList<>();

    public Storage() {
        cells.addAll(Arrays.asList(CellsType.values()));
    }

    public int getBalance() {
        int balance = 0;
        for (CellsType cell : cells) {
            balance = balance + cell.getValue();
        }
        return balance;
    }

    public void cashIn(List<Banknote> banknotes) throws ExceptionInvalidBanknote {
        for (Banknote banknote : banknotes) {
            for (CellsType cell : cells) {
                if (cell.add(banknote)) {
                    break;
                }
            }
        }
    }

    public Collection<? extends Banknote> cashOut(final int amount) throws ExceptionCashOut {
        checkBalanceAndCountBanknote(amount);
        int tempAmount = amount;
        List<Banknote> res = new ArrayList<>();
        for (int i = cells.size() - 1; i >= 0; i--) {
            CellsType cell = cells.get(i);
            while (cell.getValue() > 0 && cell.getBanknoteType().getValue() <= tempAmount) {
                Banknote ban = cell.banknoteOut();
                if (ban != null) {
                    res.add(ban);
                    tempAmount = tempAmount - ban.getValue();
                }
            }
        }
        return res;
    }

    private void checkBalanceAndCountBanknote(final int amount) throws ExceptionCashOut {
        List<String> message = new ArrayList<>();
        if (amount > this.getBalance()) {
            message.add("Insufficient balance");
        }
        if (amount % BanknoteType.values()[0].getValue() != 0) {
            message.add("Amount must be a multiple " + BanknoteType.values()[0].getValue());
        }

        int tempAmount = amount;
        for (int i = cells.size() - 1; i >= 0; i--) {
            CellsType cell = cells.get(i);
            if (cell.getValue() != 0 && cell.getBanknoteType().getValue() <= tempAmount) {
                int count = amount / cell.getValue();
                if (count > 0) {
                    tempAmount = tempAmount - count * cell.getBanknoteType().getValue();
                }
            }
        }
        if (tempAmount != 0) {
            message.add("There is not enough banknotes of the necessary denomination");
        }

        if (!message.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (String msg : message) {
                sb.append(msg).append(". ");
            }
            throw new ExceptionCashOut(sb.toString());
        }
    }
}
