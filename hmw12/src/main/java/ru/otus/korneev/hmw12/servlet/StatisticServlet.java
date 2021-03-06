package ru.otus.korneev.hmw12.servlet;

import ru.otus.korneev.hmw11.Cache.CacheEngine;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class StatisticServlet extends HttpServlet {

    private final CacheEngine<Long, Object> cache;
    private final Map<String, Object> property;

    public StatisticServlet(final CacheEngine<Long, Object> cache,
                            final Map<String, Object> property) {
        this.cache = cache;
        this.property = property;
    }

    public void doGet(final HttpServletRequest request,
                      final HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    public void doPost(final HttpServletRequest request,
                       final HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("Max. elements: " + property.get("maxElements"));
        response.getWriter().println("<br>Life time, ms: " + property.get("lifeTimeMs"));
        response.getWriter().println("<br>Idle time, ms: " + property.get("idleTimeMs"));
        response.getWriter().println("<br>Is eternal: " + property.get("isEternal"));
        response.getWriter().println("<br>Hit count: " + cache.getHitCount());
        response.getWriter().println("<br>Miss count: " + cache.getMissCount());
        response.getWriter().println("<br><a href=/index.html> <b>Обратно</b></a>");
    }
}
