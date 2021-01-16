package finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.CountryDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.dao.impl.ContactDaoImpl;
import by.daryazalevskaya.finalproject.dao.impl.CountryDaoImpl;
import by.daryazalevskaya.finalproject.dao.pool.ConnectionPool;
import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.model.Country;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

public class CountryDaoImplTest {

    private CountryDaoImpl countryDao = new CountryDaoImpl();

    @BeforeTest
    public void createConnection() throws PoolException {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String url = resource.getString("db.url");
        String user = resource.getString("db.user");
        String pass = resource.getString("db.password");
        String driver = resource.getString("db.driver");
        int poolSizeMax = Integer.parseInt(resource.getString("db.poolMaxSize"));
        int startPoolSize = Integer.parseInt(resource.getString("db.poolStartSize"));
        int timeout = Integer.parseInt(resource.getString("db.connectionTimeout"));

        ConnectionPool.getInstance().init(driver,
                url, user, pass, startPoolSize, poolSizeMax, timeout);
        Connection connection = ConnectionPool.getInstance().getConnection();
        countryDao.setConnection(connection);
    }

    @Test
    public void testFindAll() throws DaoException {
        final int size = 218;
        List<Country> countryList = countryDao.findAll();
        Assert.assertEquals(countryList.size(), size);
    }
}
