package ru.otus.korneev.hmw07;

import ru.otus.korneev.hmw06.ATM;

import java.util.ArrayList;
import java.util.List;

public class Department {

    private final List<ATM> listATM = new ArrayList<>();

    public void addAtm(final ATM atm) {
        listATM.add(atm);
    }

    public List<ATM> getListATM() {
        return listATM;
    }

    public int getAllBalance() {
        return listATM.stream()
                .map(ATM::balance)
                .reduce((atm1, atm2)->atm1+atm2).orElse(0);
    }

    public void restoreInitState(){
        listATM.forEach(ATM::getState);
    }
}
