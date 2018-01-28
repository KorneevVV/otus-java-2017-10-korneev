package ru.otus.korneev.hmw12.servlet;

import ru.otus.korneev.hmw11.DBServiceWithCache.DBServiceImpWithCache;
import ru.otus.korneev.hmw12.service.AccountService;
import ru.otus.korneev.hmw12.service.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class SignInServlet extends HttpServlet {

    private final AccountService accountService;
    private final DBServiceImpWithCache dbService;
    private final Map<String, Object> property;

    public SignInServlet(final AccountService accountService, final DBServiceImpWithCache dbService,
                         final Map<String, Object> property) {
        this.accountService = accountService;
        this.dbService = dbService;
        this.property = property;
    }

    public void doGet(final HttpServletRequest request,
                      final HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(final HttpServletRequest request,
                       final HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        if (login == null || pass == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile profile = accountService.getUserByLogin(login);

        if (profile.getLogin().equals(login) && profile.getPass().equals(pass)) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);

            response.getWriter().println("Max. elements: " + property.get("maxElements"));
            response.getWriter().println("<br>Life time, ms: " + property.get("lifeTimeMs"));
            response.getWriter().println("<br>Idle time, ms: " + property.get("idleTimeMs"));
            response.getWriter().println("<br>Is eternal: " + property.get("isEternal"));
            response.getWriter().println("<br>Hit count: " + dbService.getHitCount());
            response.getWriter().println("<br>Miss count: " + dbService.getMissCount());
        } else {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println("Unauthorized");
        }
    }
}