package ru.otus.korneev.hmw10.implementation.myORM.helpers;

import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {

    public static Connection getConnection() throws SQLException {
        try {

            Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8585").start();
        } catch (Exception e) {
            Server.createWebServer("-web", "-webAllowOthers", "-webPort", "9019").start();
        }
        DriverManager.registerDriver(new org.h2.Driver());
        return DriverManager.getConnection("jdbc:h2:mem:test", "tully", "tully");
    }
}
