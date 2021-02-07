package finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.NoEntityInDataBaseException;
import by.daryazalevskaya.finalproject.dao.impl.EmployerDaoImpl;
import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.model.Country;
import by.daryazalevskaya.finalproject.model.employer.Employer;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class EmployerDaoImplTest {

    private EmployerDaoImpl dao = new EmployerDaoImpl();

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

    @DataProvider(name = "employerCreate")
    public Object[][] createEmployer() {
        final Integer userId = 2;
        Employer employer = new Employer(userId);
        return new Object[][]{{employer}};
    }


    @Test(dataProvider = "employerCreate")
    public void createTest(Employer employer) throws DaoException {
        Integer id = dao.create(employer);
        Assert.assertNotNull(id);
    }

    @DataProvider(name = "employerRead")
    public Object[][] createReadEmployer() {
        final Integer userId = 3;
        Employer employer = Employer.builder()
                .country(new Country(1))
                .companyName("Roboteka")
                .contact(new Contact(5))
                .city("Moscow")
                .id(userId)
                .build();
        return new Object[][]{{Optional.of(employer), userId}};
    }

    @Test(dataProvider = "employerRead")
    public void readTest(Optional<Employer> employer, Integer id) throws DaoException {
        Assert.assertEquals(employer, dao.read(id));
    }

    @Test
    public void updateTest() throws DaoException, NoEntityInDataBaseException {
        final Integer id = 6;
        final int countryId = 190;
        Employer employerFromDB = dao.read(id).orElseThrow(NoEntityInDataBaseException::new);
        employerFromDB.setCountry(new Country(countryId));

        dao.update(employerFromDB);
        Optional<Employer> updatedEmployer = dao.read(id);
        Assert.assertEquals(updatedEmployer.get(), employerFromDB);
    }

    @Test
    public void deleteTest() throws DaoException {
        final Integer id = 7;
        dao.delete(id);
        Assert.assertTrue(dao.read(id).isEmpty());
    }

    @Test
    public void findAllTest() throws DaoException {
        Assert.assertTrue(dao.findAll().size() != 0);
    }

    @Test
    public void testCreateContact() throws DaoException {
        final Integer userId = 8;
        final Integer contactId = 7;
        dao.createContact(userId, contactId);

        Employer employer = Employer.builder()
                .contact(new Contact(contactId))
                .city("Munchen")
                .companyName("CompSoft")
                .country(new Country(23))
                .id(userId)
                .build();

        Optional<Employer> updatedEmployer = dao.read(userId);
        Assert.assertEquals(updatedEmployer.get(), employer);
    }

    @DataProvider(name = "companyName")
    public Object[][] createCompanyName() {
        return new Object[][]{
                {"Roboteka", 3},
                {"empty", null}};
    }

    @Test(dataProvider = "companyName")
    public void testReadUserIdByCompany(String company, Integer id) throws DaoException {
        Assert.assertEquals(dao.readUserIdByCompany(company), id);
    }
}
