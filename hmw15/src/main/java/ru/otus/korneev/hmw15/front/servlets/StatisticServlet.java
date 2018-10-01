package ru.otus.korneev.hmw15.front.servlets;

import ru.otus.korneev.hmw15.app.MessageSystemContext;
import ru.otus.korneev.hmw15.messageSystem.Address;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StatisticServlet extends HttpServlet {

    private MessageSystemContext context;

    public StatisticServlet(final MessageSystemContext context, Address address) {
        this.context = context;
    }


    public void doGet(final HttpServletRequest request,
                      final HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    public void doPost(final HttpServletRequest request,
                       final HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        //todo
//        response.getWriter().println("<br>Hit count: " + cacheEngineImpl.getHitCount());
//        response.getWriter().println("<br>Miss count: " + cacheEngineImpl.getMissCount());
        response.getWriter().println("<br><a href=/index.html> <b>Обратно</b></a>");
    }
}
