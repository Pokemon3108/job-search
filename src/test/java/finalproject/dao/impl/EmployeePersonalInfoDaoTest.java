package finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.NoEntityInDataBaseException;
import by.daryazalevskaya.finalproject.dao.impl.EmployeePersonalInfoDaoImpl;
import by.daryazalevskaya.finalproject.model.Country;
import by.daryazalevskaya.finalproject.model.employee.EmployeePersonalInfo;
import by.daryazalevskaya.finalproject.model.type.Gender;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class EmployeePersonalInfoDaoTest {
    private EmployeePersonalInfoDaoImpl dao = new EmployeePersonalInfoDaoImpl();

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

    @DataProvider(name = "employeeInfo")
    public Object[][] createEmployeeInfo() {
        EmployeePersonalInfo info = EmployeePersonalInfo.builder()
                .birthday(LocalDate.of(1990, 11, 1))
                .country(new Country(13))
                .gender(Gender.FEMALE)
                .name("Larisa")
                .surname("Veshko")
                .build();
        return new Object[][]{{info}};
    }


    @Test(dataProvider = "employeeInfo")
    public void createTest(EmployeePersonalInfo info) throws DaoException, InsertIdDataBaseException {
        Integer id = dao.create(info);
        Assert.assertNotNull(id);
    }

    @DataProvider(name = "preferenceRead")
    public Object[][] createReadPreference() {
        final Integer id = 2;
        EmployeePersonalInfo info = EmployeePersonalInfo.builder()
                .birthday(LocalDate.of(2001, 8, 31))
                .country(new Country(13))
                .gender(Gender.FEMALE)
                .name("Darya")
                .surname("Zalevskaya")
                .id(id)
                .build();
        return new Object[][]{{info, id}};
    }

    @Test(dataProvider = "preferenceRead")
    public void readTest(EmployeePersonalInfo info, Integer id) throws DaoException {
        Assert.assertEquals(info, dao.read(id).get());
    }

    @Test
    public void updateTest() throws DaoException, NoEntityInDataBaseException {
        final Integer id = 3;
        final int countryId = 2;
        final LocalDate birthday = LocalDate.of(1956, 12, 23);
        EmployeePersonalInfo infoFromDB = dao.read(id).orElseThrow(NoEntityInDataBaseException::new);
        infoFromDB.setCountry(new Country(countryId));
        infoFromDB.setBirthday(birthday);

        dao.update(infoFromDB);
        Optional<EmployeePersonalInfo> updatedInfo = dao.read(id);
        Assert.assertEquals(updatedInfo.get(), infoFromDB);
    }

    @Test
    public void deleteTest() throws DaoException {
        final Integer id = 4;
        Optional<EmployeePersonalInfo> infoFromDB = dao.read(id);
        Assert.assertNotNull(infoFromDB.get());
        dao.delete(id);
        Assert.assertTrue(dao.read(id).isEmpty());
    }
}
