package ru.otus.korneev.hmw13.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.korneev.hmw12.service.AccountService;
import ru.otus.korneev.hmw12.service.UserProfile;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {

    @Autowired
    private AccountService accountServiceImpl;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
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

        UserProfile profile = accountServiceImpl.getUserByLogin(login);

        if (profile == null || !(profile.getLogin().equals(login) && profile.getPass().equals(pass))) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println("Данный пользователь не зарегистрирован, <a href=/index.html> <b>Обратно</b></a>");
        } else {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("Вы авторизованы <br><a href=/statistic> <b>Прейти к статистике</b></a><br><a href=/index.html> <b>Обратно</b></a>");
        }
    }
}