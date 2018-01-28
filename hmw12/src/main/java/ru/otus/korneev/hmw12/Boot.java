package ru.otus.korneev.hmw12;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.otus.korneev.hmw11.DBServiceWithCache.DBServiceImpWithCache;
import ru.otus.korneev.hmw12.service.AccountService;
import ru.otus.korneev.hmw12.servlet.ImitateServlet;
import ru.otus.korneev.hmw12.servlet.SignInServlet;
import ru.otus.korneev.hmw12.servlet.SignUpServlet;

import java.util.HashMap;
import java.util.Map;

public class Boot {
    public static void main(String[] args) throws Exception {
        Map<String, Object> property = new HashMap<>();
        property.put("maxElements", 2);
        property.put("lifeTimeMs", 1000);
        property.put("idleTimeMs", 0);
        property.put("isEternal", false);
        DBServiceImpWithCache dbService = new DBServiceImpWithCache(2, 1000, 0, false);
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("public_html");
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        AccountService accountService = new AccountService();
        context.addServlet(new ServletHolder(new SignInServlet(accountService, dbService, property)), "/signin");
        context.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");
        context.addServlet(new ServletHolder(new ImitateServlet(dbService)), "/imitate");
        Server server = new Server(8088);
        server.setHandler(new HandlerList(resource_handler, context));
        server.start();
        server.join();
    }
}
