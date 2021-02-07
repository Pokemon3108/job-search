package finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.IllegalOperationException;
import by.daryazalevskaya.finalproject.dao.impl.CountryDaoImpl;
import by.daryazalevskaya.finalproject.model.Country;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CountryDaoImplTest {

    private CountryDaoImpl countryDao = new CountryDaoImpl();

    private Connection connection;

    private String url;
    private String userName;
    private String password;

    @BeforeClass
    public void initParams() {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        url = resource.getString("db.url");
        userName = resource.getString("db.user");
        password = resource.getString("db.password");
    }

    @BeforeMethod
    public void createConnection() throws  SQLException {
        connection= DriverManager.getConnection(url, userName, password);
        countryDao.setConnection(connection);
    }

    @AfterMethod
    public void closeConnection() throws SQLException {
        connection.close();
    }

    @Test(expectedExceptions = IllegalOperationException.class)
    public void testCreate() {
        countryDao.create(new Country(1));
    }

    @DataProvider(name = "country")
    public Object[][] createCountry() {
        return new Object[][]{
                {300, Optional.empty()},
                {5, Optional.of(new Country(5, "Azerbaijan"))},
                {-10, Optional.empty()}
        };
    }

    @Test(dataProvider = "country")
    public void testRead(int id, Optional<Country> country) throws DaoException {
        Assert.assertEquals(countryDao.read(id), country);
    }

    @Test(expectedExceptions = IllegalOperationException.class)
    public void testUpdate() {
        countryDao.update(new Country(1));
    }

    @Test(expectedExceptions = IllegalOperationException.class)
    public void testDelete() {
        countryDao.delete(1);
    }


    @Test
    public void testFindAll() throws DaoException {
        final int size = 218;
        List<Country> countryList = countryDao.findAll();
        Assert.assertEquals(countryList.size(), size);
    }

}
