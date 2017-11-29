package ru.otus.korneev.hmw06;

import ru.otus.korneev.hmw06.banknote.Banknote;
import ru.otus.korneev.hmw06.exception.ExceptionCashOut;
import ru.otus.korneev.hmw06.exception.ExceptionInvalidBanknote;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ExceptionInvalidBanknote {

        ATM atm = new ATM();

        boolean isRunApplication = true;
        while (isRunApplication) {
            System.out.println("Enter the number of activity\n" +
                    "1. Deposit money to the ATM\n" +
                    "2. Get money from an ATM\n" +
                    "3. Get the ATM balance\n" +
                    "0. Exit application");
            Scanner in = new Scanner(System.in);
            int number = in.nextInt();
            switch (number) {
                case 0:
                    isRunApplication = false;
                    break;
                case 1:
                    depositMoney(atm, in);
                    break;
                case 2:
                    System.out.println("Enter amount");
                    try {
                        System.out.println(atm.cashOut(in.nextInt()));
                    } catch (ExceptionCashOut e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.println("Balance: " + atm.balance());
                    break;
                default:
                    System.out.println("Invalid choice, try again");
                    break;
            }
        }
    }

    private static void depositMoney(final ATM atm, final Scanner in) throws ExceptionInvalidBanknote {
        System.out.println("Insert one banknote (enter denomination 100, 500, 1000, 5000) or 0 for end");
        List<Banknote> banknotes = new ArrayList<>();
        boolean isInsert = true;
        while (isInsert) {
            int banknoteValue = in.nextInt();
            isInsert = banknoteValue != 0;
            if (!isInsert) {
                break;
            }
            try {
                banknotes.add(new Banknote(banknoteValue));
            } catch (ExceptionInvalidBanknote exceptionInvalidBanknote) {
                System.out.println("Invalid choice, try again");
            }
        }
        if (!banknotes.isEmpty()) {
            atm.cashIn(banknotes);
        }
    }
}
