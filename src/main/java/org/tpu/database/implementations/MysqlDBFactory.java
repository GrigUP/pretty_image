package org.tpu.database.implementations;

import org.tpu.database.interfaces.DBFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlDBFactory implements DBFactory {
    private Connection connection = null;
    private final String dbURL = "jdbc:mysql://localhost:3306/website_db";
    private final String driverName = "com.mysql.cj.jdbc.Driver";
    private final String login = "root";
    private final String password = "32553255";


    public Connection connect()  {
        try {
            registerDriver(driverName);
            connection = DriverManager.getConnection(dbURL, login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void registerDriver(String driver) {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
