package ru.otus.korneev.hmw12;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.otus.korneev.hmw11.DBServiceWithCache.DBServiceImpWithCache;
import ru.otus.korneev.hmw12.service.AccountServiceImpl;
import ru.otus.korneev.hmw12.service.AccountService;
import ru.otus.korneev.hmw12.servlet.ImitateServlet;
import ru.otus.korneev.hmw12.servlet.SignInServlet;
import ru.otus.korneev.hmw12.servlet.SignUpServlet;
import ru.otus.korneev.hmw12.servlet.StatisticServlet;

import java.util.HashMap;
import java.util.Map;

public class Boot {

    private static final int MAX_ELEMENT = 2;
    private static final long LIFE_TIME_MS = 100_000;
    private static final long IDLE_TIME_MS = 0;
    private static final boolean IS_ETERNAL = false;

    public static void main(String[] args) throws Exception {
        Map<String, Object> property = new HashMap<>();
        DBServiceImpWithCache dbService = new DBServiceImpWithCache(MAX_ELEMENT, LIFE_TIME_MS, IDLE_TIME_MS, IS_ETERNAL);
        property.put("maxElements", MAX_ELEMENT);
        property.put("lifeTimeMs", LIFE_TIME_MS);
        property.put("idleTimeMs", IDLE_TIME_MS);
        property.put("isEternal", IS_ETERNAL);
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("public_html");
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        AccountService accountService = new AccountServiceImpl();
        context.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");
        context.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");
        context.addServlet(new ServletHolder(new ImitateServlet(dbService)), "/imitate");
        context.addServlet(new ServletHolder(new StatisticServlet(dbService, property)), "/statistic");
        Server server = new Server(8088);
        server.setHandler(new HandlerList(resource_handler, context));
        server.start();
        server.join();
    }
}
