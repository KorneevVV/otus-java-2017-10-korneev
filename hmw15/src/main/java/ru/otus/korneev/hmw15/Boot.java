package ru.otus.korneev.hmw15;

import ru.otus.korneev.hmw15.accountservice.AccountServiceImpl;
import ru.otus.korneev.hmw15.app.AccountService;
import ru.otus.korneev.hmw15.app.DBService;
import ru.otus.korneev.hmw15.app.FrontendService;
import ru.otus.korneev.hmw15.app.MessageSystemContext;
import ru.otus.korneev.hmw15.db.DBServiceImpl;
import ru.otus.korneev.hmw15.front.FrontendServiceImpl;
import ru.otus.korneev.hmw15.messageSystem.Address;
import ru.otus.korneev.hmw15.messageSystem.MessageSystem;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Boot {

    private final static Logger logger = Logger.getLogger(MessageSystem.class.getName());

    public static void main(String[] args) throws InterruptedException {
        MessageSystem messageSystem = new MessageSystem();
        MessageSystemContext context = new MessageSystemContext(messageSystem);

        Address accountServiceAddress = new Address("AccountService");
        context.setAccountService(accountServiceAddress);
        AccountService accountService = new AccountServiceImpl(context, accountServiceAddress);
        accountService.init();

        Address dbAddress = new Address("DB");
        context.setDbAddress(dbAddress);
        try {
            DBService dbService = new DBServiceImpl(context, dbAddress);
            dbService.init();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed load dbService", e);
        }


        Address frontAddress = new Address("Frontend");
        context.setFrontAddress(frontAddress);
        FrontendService frontendService = new FrontendServiceImpl(context, frontAddress);
        frontendService.init();

        messageSystem.start();

        //for test
//        frontendService.handleRequest("tully");
//        frontendService.handleRequest("sully");
//
//        frontendService.handleRequest("tully");
//        frontendService.handleRequest("sully");

        Thread.sleep(100000000);
        messageSystem.dispose();
    }
}
