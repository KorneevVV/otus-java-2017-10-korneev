package ru.otus.korneev.hmw12.servlet;

import ru.otus.korneev.hmw12.service.AccountService;
import ru.otus.korneev.hmw12.service.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet  extends HttpServlet {

    private final AccountService accountService;

    public SignUpServlet(final AccountService accountService) {
        this.accountService = accountService;
    }

    public void doGet(final HttpServletRequest request,
                      final HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    public void doPost(final HttpServletRequest request,
                       final HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        response.setContentType("text/html;charset=utf-8");
        if (login == null || pass == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        final UserProfile user = new UserProfile(login, pass);
        accountService.addNewUser(user);

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("Вы успешно зарегистрированы");
    }
}
