package ru.otus.korneev.hmw12.servlet;

import ru.otus.korneev.hmw10.dbService.DBService;
import ru.otus.korneev.hmw10.implementation.myORM.userDataSet.UserDataSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;


public class ImitateServlet extends HttpServlet {

    private final DBService dbService;
    private int id = 1;

    public ImitateServlet(final DBService dbService) {
        this.dbService = dbService;
    }

    public void doGet(final HttpServletRequest request,
                      final HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    public void doPost(final HttpServletRequest request,
                       final HttpServletResponse response) throws IOException {
        UserDataSet userActual1 = new UserDataSet();
        userActual1.setAge(22);
        userActual1.setName("Ivan1");
        userActual1.setSalary(BigDecimal.TEN);
        UserDataSet userActual2 = new UserDataSet();
        userActual2.setAge(23);
        userActual2.setName("Ivan2");
        userActual2.setSalary(BigDecimal.TEN);
        UserDataSet userActual3 = new UserDataSet();
        userActual3.setAge(24);
        userActual3.setName("Ivan3");
        userActual3.setSalary(BigDecimal.TEN);
        try {
            dbService.save(userActual1);
            dbService.save(userActual2);
            dbService.save(userActual3);
            dbService.load(id++, UserDataSet.class);
            dbService.load(id++, UserDataSet.class);
            dbService.load(id++, UserDataSet.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("В базу добавлено 3 записи, так же произведено чтение из базы. <br><a href=/index.html> <b>Обратно</b></a>");
    }
}