package by.daryazalevskaya.finalproject.controller;

import by.daryazalevskaya.finalproject.model.Contact;
import org.postgresql.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class Runner {
    public static void main(String[] args) {


        String url = "jdbc:postgresql://localhost:5432/job_search";
        Properties prop = new Properties();
        prop.put("user", "job_user");
        prop.put("password", "12345");
        prop.put("autoReconnect", "true");
        prop.put("characterEncoding", "UTF-8");
        prop.put("useUnicode", "true");
        Connection cn = null;
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try { // 1 блок
            cn = DriverManager.getConnection(url, prop);
            Statement st = null;
            try { // 2 блок
                st = cn.createStatement();
                ResultSet rs = null;
                try { // 3 блок
                    rs = st.executeQuery("SELECT * FROM contact");
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
                } finally { // для 3-го блока try
                    /*
                     * закрыть ResultSet, если он был открыт
                     * или ошибка произошла во время
                     * чтения из него данных
                     */
                    if (rs != null) { // был ли создан ResultSet
                        rs.close();
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
                if (st != null) { // для 2-го блока tryJDBC
                    st.close();
                } else {
                    System.err.println("Statement не создан");
                }
            }
        } catch ( SQLException e) { // для 1-го блока try
            System.err.println("DB connection error: " + e);
            /*
             * вывод сообщения о всех SQLException
             */
        } finally {
            /*
             * закрыть Connection, если он был открыт
             */
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    System.err.println("Сonnection close error: " + e);
                }
            }
        }
    }
}
