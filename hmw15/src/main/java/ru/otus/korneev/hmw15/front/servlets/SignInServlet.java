package ru.otus.korneev.hmw15.front.servlets;

import ru.otus.korneev.hmw12.service.UserProfile;
import ru.otus.korneev.hmw15.accountservice.MsgGetUserByLogin;
import ru.otus.korneev.hmw15.app.MessageSystemContext;
import ru.otus.korneev.hmw15.app.MsgToAccountService;
import ru.otus.korneev.hmw15.messageSystem.Address;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {

    private MessageSystemContext context;

    private Address address;

    private UserProfile user;

    private Boolean isNotFoundUser;

    public SignInServlet(final MessageSystemContext context,
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

        if (login.equals("") || pass.equals("")) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println(String.format("Не введен %s, <a href=/index.html> <b>Обратно</b></a>",
                    login.equals("") ? "login" : "password"));
            return;
        }
        isNotFoundUser = null;
        MsgToAccountService msg = new MsgGetUserByLogin(address, context.getAccountService(), login);
        context.getMessageSystem().sendMessage(msg);

        while (isNotFoundUser == null && user == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (user != null) {
            if (!(user.getLogin().equals(login) && user.getPass().equals(pass))) {
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().println("Данный пользователь не зарегистрирован, <a href=/index.html> <b>Обратно</b></a>");
            } else {
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println("Вы авторизованы <br><a href=/statistic> <b>Прейти к статистике</b></a><br><a href=/index.html> <b>Обратно</b></a>");
            }
        } else {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println("Данный пользователь не зарегистрирован, <a href=/index.html> <b>Обратно</b></a>");
        }
    }

    public void setUser(final UserProfile user) {
        this.user = user;
    }

    public void setNotFoundUser(Boolean notFoundUser) {
        isNotFoundUser = notFoundUser;
    }
}