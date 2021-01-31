package finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.impl.VacancyDaoImpl;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class VacancyDaoImplTest {
    private VacancyDaoImpl dao = new VacancyDaoImpl();

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
    public void createConnection() throws SQLException {
        connection = DriverManager.getConnection(url, userName, password);
        dao.setConnection(connection);
    }

    @AfterMethod
    public void closeConnection() throws SQLException {
        connection.close();
    }

    @Test
    public void countTest() throws DaoException {
        Assert.assertTrue(dao.count() > 0);
    }

    @DataProvider(name = "employerVacancies")
    public Object[][] createEmployerVacancies() {
        return new Object[][]{
                {3, 2},
                {6, 2},
                {8, 4}
        };
    }

    @Test(dataProvider = "employerVacancies")
    public void testReadVacanciesByEmployerId(Integer employerId, int amount) throws DaoException {
        Assert.assertEquals(dao.readVacanciesByEmployerId(employerId).size(), amount);
    }

    @DataProvider(name = "employeeVacancies")
    public Object[][] createEmployeeVacancies() {
        return new Object[][]{
                {1, 2},
                {4, 1},
                {3, 0}
        };
    }

    @Test(dataProvider = "employeeVacancies")
    public void readEmployeeVacancyTest(Integer employeeId, int amount) throws DaoException {
        Assert.assertEquals(dao.readEmployeeVacancies(employeeId).size(), amount);
    }

    @DataProvider(name = "vacanciesBySpecializations")
    public Object[][] vacanciesAmountBySpec() {
        return new Object[][]{
                {12, 5},
                {2, 1},
                {1, 0}
        };
    }

    @Test(dataProvider = "vacanciesBySpecializations")
    public void countVacanciesBySpecializationIdTest(Integer specializationId, Integer amount) throws DaoException {
        Assert.assertEquals(dao.countVacanciesBySpecializationId(specializationId), amount);
    }


    @DataProvider(name = "vacanciesByCountry")
    public Object[][] vacanciesAmountByCountry() {
        return new Object[][]{
                {12, 5},
                {34, 1},
                {1, 0}
        };
    }

    @Test(dataProvider = "vacanciesByCountry")
    public void countVacanciesByCountryIdTest(Integer countryId, Integer amount) throws DaoException {
        Assert.assertEquals(dao.countVacanciesByCountryId(countryId), amount);
    }

    @DataProvider(name = "vacanciesByPosition")
    public Object[][] vacanciesAmountByPosition() {
        return new Object[][]{
                {"Teacher", 1},
                {"Head engineer", 2},
                {"Master", 0}
        };
    }

    @Test(dataProvider = "vacanciesByPosition")
    public void countVacanciesByPositionTest(String position, Integer amount) throws DaoException {
        Assert.assertEquals(dao.countVacanciesByPosition(position), amount);
    }


    @DataProvider(name = "vacanciesBySpecializationsAndCountry")
    public Object[][] vacanciesAmountBySpecAndCountry() {
        return new Object[][]{
                {12, 12, 4},
                {12, 8, 0}
        };
    }

    @Test(dataProvider = "vacanciesBySpecializationsAndCountry")
    public void countVacanciesBySpecializationIdAndCountryIdTest(Integer specializationId, Integer countryId, Integer amount) throws DaoException {
        Assert.assertEquals(dao.countVacanciesBySpecializationIdAndCountryId(specializationId, countryId), amount);
    }

    @DataProvider(name = "vacanciesByPositionAndCountry")
    public Object[][] vacanciesAmountByPositionAndCountry() {
        return new Object[][]{
                {"Head engineer", 12, 2},
                {"Doctor", 5, 1},
                {"House keeping manager", 5, 0}
        };
    }

    @Test(dataProvider = "vacanciesByPositionAndCountry")
    public void countVacanciesByPositionAndCountryIdTest(String position, Integer countryId, Integer amount) throws DaoException {
        Assert.assertEquals(dao.countVacanciesByPositionAndCountryId(position, countryId), amount);
    }

    @DataProvider(name = "vacanciesByPositionAndSpec")
    public Object[][] vacanciesByPositionAndSpecializationId() {
        return new Object[][]{
                {"Doctor", 12, 1},
                {"Head engineer", 12, 2},
                {"Builder", 11, 1},
                {"Doctor", 11, 0},

        };
    }

    @Test(dataProvider = "vacanciesByPositionAndSpec")
    public void countVacanciesByPositionAndSpecializationIdTest(String position, Integer specId, Integer amount) throws DaoException {
        Assert.assertEquals(dao.countVacanciesByPositionAndSpecializationId(position, specId), amount);
    }

    @DataProvider(name = "vacanciesBySpecializationsAndCountryAndPosition")
    public Object[][] vacanciesAmountBySpecAndCountryAndPosition() {
        return new Object[][]{
                {2, 12, "Teacher", 1},
                {12, 12, "Head engineer", 2},
                {2, 2, "Builder", 0}
        };
    }

    @Test(dataProvider = "vacanciesBySpecializationsAndCountryAndPosition")
    public void countVacanciesBySpecAndCountryAndPositionTest(Integer specializationId, Integer countryId, String position, Integer amount) throws DaoException {
        Assert.assertEquals(dao.countVacanciesBySpecializationIdAndCountryIdAndPosition(specializationId, countryId, position), amount);
    }
}
