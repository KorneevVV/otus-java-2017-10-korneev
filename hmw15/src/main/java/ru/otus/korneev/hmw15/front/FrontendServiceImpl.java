package ru.otus.korneev.hmw15.front;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.otus.korneev.hmw12.service.UserProfile;
import ru.otus.korneev.hmw15.app.FrontendService;
import ru.otus.korneev.hmw15.app.MessageSystemContext;
import ru.otus.korneev.hmw15.db.MsgGetUserId;
import ru.otus.korneev.hmw15.front.servlets.ImitateServlet;
import ru.otus.korneev.hmw15.front.servlets.SignInServlet;
import ru.otus.korneev.hmw15.front.servlets.SignUpServlet;
import ru.otus.korneev.hmw15.front.servlets.StatisticServlet;
import ru.otus.korneev.hmw15.messageSystem.Address;
import ru.otus.korneev.hmw15.messageSystem.Message;
import ru.otus.korneev.hmw15.messageSystem.MessageSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FrontendServiceImpl implements FrontendService {
    private final static Logger logger = Logger.getLogger(MessageSystem.class.getName());
    private final Address address;
    private final MessageSystemContext context;
    private final Map<Integer, String> users = new HashMap<>();
    private SignInServlet servlet;

    public FrontendServiceImpl(MessageSystemContext context, Address address) {
        this.context = context;
        this.address = address;
    }

    public void init() {
        context.getMessageSystem().addAddressee(this);
        //todo start jetty

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("./hmw15/public_html");
        ServletContextHandler ctx = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servlet = new SignInServlet(context, address);
        ctx.addServlet(new ServletHolder(servlet), "/signin");
        ctx.addServlet(new ServletHolder(new SignUpServlet(context, address)), "/signup");
        ctx.addServlet(new ServletHolder(new ImitateServlet(context, address)), "/imitate");
        ctx.addServlet(new ServletHolder(new StatisticServlet(context, address)), "/statistic");
        Server server = new Server(8089);
        server.setHandler(new HandlerList(resource_handler, ctx));
        Thread thr = new Thread(() -> {
            try {
                server.start();
                server.join();
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Failed load Web Server", e);
            }
        });
        thr.start();
    }

    @Override
    public Address getAddress() {
        return address;
    }

    public void handleRequest(String login) {
        Message message = new MsgGetUserId(getAddress(), context.getDbAddress(), login);
        context.getMessageSystem().sendMessage(message);
    }

    public void addUser(int id, String name) {
        users.put(id, name);
        System.out.println("User: " + name + " has id: " + id);
    }

    @Override
    public void getUserFromAccountService(final UserProfile user) {
        if (user == null) {
            servlet.setNotFoundUser(Boolean.TRUE);
        } else {
            servlet.setUser(user);
            servlet.setNotFoundUser(Boolean.FALSE);

        }
    }

    @Override
    public MessageSystem getMS() {
        return context.getMessageSystem();
    }
}
