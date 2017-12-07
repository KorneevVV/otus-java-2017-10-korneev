package ru.otus.korneev;

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
                .map(atm->{return atm.balance();})
                .reduce((atm1, atm2)->atm1+atm2).orElse(0);
    }

    public void getInitState(){
        listATM.forEach(atm -> atm.getState());
    }

}
