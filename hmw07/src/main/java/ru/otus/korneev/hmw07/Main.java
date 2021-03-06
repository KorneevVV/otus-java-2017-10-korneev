package ru.otus.korneev.hmw07;

import ru.otus.korneev.hmw06.ATM;
import ru.otus.korneev.hmw06.banknote.Banknote;
import ru.otus.korneev.hmw06.exception.ExceptionInvalidBanknote;

import java.util.Collections;
import java.util.Scanner;

import static ru.otus.korneev.hmw06.Main.fillingAtm;

public class Main {
    public static void main(String[] args) throws ExceptionInvalidBanknote {

        Department dep = new Department();

        boolean isRunApplication = true;
        while (isRunApplication) {
            System.out.println("Enter the number of activity\n" +
                    "1. Create new ATM\n" +
                    "2. Get all balance\n" +
                    "3. Get init state\n" +
                    "4. TEST add Banknote 100\n" +
                    "0. Exit application");
            Scanner in = new Scanner(System.in);
            int number = in.nextInt();
            switch (number) {
                case 0:
                    isRunApplication = false;
                    break;
                case 1:
                    ATM atm = new ATM();
                    fillingAtm(atm);
                    dep.addAtm(atm);
                    break;
                case 2:
                    System.out.println("Balance: " + dep.getAllBalance());
                    break;
                case 3:
                    dep.restoreInitState();
                    break;
                case 4:
                    dep.getListATM().get(0).cashIn(Collections.singletonList(new Banknote(100))); // change Balance for Test Memento()
                    break;
                default:
                    System.out.println("Invalid choice, try again");
                    break;
            }
        }
    }
}
