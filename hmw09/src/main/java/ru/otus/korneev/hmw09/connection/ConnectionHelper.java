package ru.otus.korneev.hmw09.connection;

import org.h2.tools.Server;

import java.sql.*;

public class ConnectionHelper {

    public static Connection getConnection() throws SQLException {
        Server.createWebServer("-web", "-webAllowOthers", "-webPort", "9090").start();
        DriverManager.registerDriver(new org.h2.Driver());
        Connection con = DriverManager.getConnection("jdbc:h2:mem:test", "tully", "tully");
        return con;
    }
}
