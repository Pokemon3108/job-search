package by.daryazalevskaya.finalproject.controller;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.dao.impl.UserDaoImpl;
import by.daryazalevskaya.finalproject.dao.pool.ConnectionPool;
import by.daryazalevskaya.finalproject.model.User;
import by.daryazalevskaya.finalproject.model.type.Role;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Runner {
    public static void main(String[] args) {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String url = resource.getString("db.url");
        String user = resource.getString("db.user");
        String pass = resource.getString("db.password");
        String driver = resource.getString("db.driver");
        int poolSizeMax = Integer.parseInt(resource.getString("db.poolMaxSize"));
        int startPoolSize = Integer.parseInt(resource.getString("db.poolStartSize"));
        int timeout = Integer.parseInt(resource.getString("db.connectionTimeout"));

        Statement statement = null;
        Connection connection = null;
        try {
            ConnectionPool.getInstance().init(driver,
                    url, user, pass, startPoolSize, poolSizeMax, timeout);
            connection = ConnectionPool.getInstance().getConnection();
            User user1=User.builder().password("01234567890123456789")
                    .role(Role.EMPLOYEE)
                    .username("Dasha")
                    .build();
            UserDaoImpl userDao=new UserDaoImpl();
            userDao.setConnection(connection);
            System.out.println(userDao.create(user1));
        } catch (PoolException | InsertIdDataBaseException | DaoException ex) {
            System.out.println(ex);
        }


    }
}
