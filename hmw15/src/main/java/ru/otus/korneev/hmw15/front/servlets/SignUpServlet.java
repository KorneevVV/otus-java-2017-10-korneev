package ru.otus.korneev.hmw15.front.servlets;

import ru.otus.korneev.hmw12.service.UserProfile;
import ru.otus.korneev.hmw15.app.AccountService;
import ru.otus.korneev.hmw15.app.MessageSystemContext;
import ru.otus.korneev.hmw15.app.MsgToAccountService;
import ru.otus.korneev.hmw15.messageSystem.Address;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {

    private MessageSystemContext context;

    private Address address;

    public SignUpServlet(final MessageSystemContext context,
                         final Address address) {
        this.context = context;
        this.address = address;
    }

    public void doGet(final HttpServletRequest request,
                      final HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    public void doPost(final HttpServletRequest request,
                       final HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        response.setContentType("text/html;charset=utf-8");
        if (login == null || pass == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        final UserProfile user = new UserProfile(login, pass);
        MsgToAccountService msg = new MsgToAccountService(address, context.getAccountService()) {
            @Override
            public void exec(final AccountService accountService) {
                accountService.addNewUser(user);
            }
        };
        context.getMessageSystem().sendMessage(msg);


        //todo проверить что реально зарегистрирован Дождаться ответа от сервера
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("Вы успешно зарегистрированы, <a href=/index.html> <b>Обратно</b></a>");
    }
}
