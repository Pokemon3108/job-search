package finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.dao.impl.UserDaoImpl;
import by.daryazalevskaya.finalproject.dao.pool.ConnectionPool;
import by.daryazalevskaya.finalproject.model.User;
import by.daryazalevskaya.finalproject.model.type.Role;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.util.ResourceBundle;

public class UserDaoImplTest {
    private UserDaoImpl userDao=new UserDaoImpl();


    @BeforeTest
    public void createConnection() {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String url = resource.getString("db.url");
        String user = resource.getString("db.user");
        String pass = resource.getString("db.password");
        String driver = resource.getString("db.driver");
        int poolSizeMax = Integer.parseInt(resource.getString("db.poolMaxSize"));
        int startPoolSize = Integer.parseInt(resource.getString("db.poolStartSize"));
        int timeout = Integer.parseInt(resource.getString("db.connectionTimeout"));
        try {
            ConnectionPool.getInstance().init(driver,
                    url, user, pass, startPoolSize, poolSizeMax, timeout);
            Connection connection = ConnectionPool.getInstance().getConnection();
            userDao.setConnection(connection);
        } catch (PoolException e) {
            //can't be thrown in this test
        }
    }

    @Test
    public void createTest() {
        User user=User.builder().password("01234567890123456789")
                .role(Role.EMPLOYEE)
                .username("Kesha")
                .build();
        try {
            Assert.assertNotNull(userDao.create(user));
        } catch (InsertIdDataBaseException | DaoException e) {
           //can't be thrown in this test
        }
    }
}
