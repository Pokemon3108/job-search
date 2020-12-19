package by.daryazalevskaya.finalproject.controller;

import by.daryazalevskaya.finalproject.dao.pool.ConnectionPool;
import by.daryazalevskaya.finalproject.model.Contact;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Runner {
    public static void main(String[] args) {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String url = resource.getString("db.url");
        String user = resource.getString("db.user");
        String pass = resource.getString("db.password");
        String driver=resource.getString("db.driver");
        int poolSizeMax=Integer.parseInt(resource.getString("db.poolMaxSize"));
        int startPoolSize=Integer.parseInt(resource.getString("db.poolStartSize"));
        int timeout=Integer.parseInt(resource.getString("db.connectionTimeout"));

        ConnectionPool.getInstance().init(driver,
                url, user, pass, startPoolSize, poolSizeMax, timeout);
        Connection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        try {
            try {
                statement = connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ResultSet rs = null;
            try {
                rs = statement.executeQuery("SELECT * FROM contact");
                ArrayList<Contact> lst = new ArrayList<>();
                while (rs.next()) {
                    String name1 = rs.getString(1);
                    String name2 = rs.getString(2);
                    String name = rs.getString(3);
                    lst.add(new Contact(name1, name2, name));
                }
                if (lst.size() > 0) {
                    System.out.println(lst);
                } else {
                    System.out.println("Not found");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (rs != null) { // был ли создан ResultSet
                    try {
                        rs.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.err.println(
                            "ошибка во время чтения из БД");
                }
            }
        } finally {
            /*
             * закрыть Statement, если он был открыт или ошибка
             * произошла во время создания Statement
             */
            if (statement != null) { // для 2-го блока tryJDBC
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.err.println("Statement не создан");
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Сonnection close error: " + e);
                }
            }
        }

    }
}
