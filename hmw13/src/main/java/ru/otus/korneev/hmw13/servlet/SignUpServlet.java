package ru.otus.korneev.hmw13.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.otus.korneev.hmw12.service.AccountService;
import ru.otus.korneev.hmw12.service.UserProfile;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {

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

        response.setContentType("text/html;charset=utf-8");
        if (login == null || pass == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        final UserProfile user = new UserProfile(login, pass);
        accountServiceImpl.addNewUser(user);

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("Вы успешно зарегистрированы, <a href=/index.html> <b>Обратно</b></a>");
    }
}
