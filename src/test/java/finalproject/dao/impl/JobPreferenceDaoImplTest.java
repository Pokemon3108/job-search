package finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.JobPreferenceDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.NoEntityInDataBaseException;
import by.daryazalevskaya.finalproject.dao.impl.JobPreferenceDaoImpl;
import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.model.Country;
import by.daryazalevskaya.finalproject.model.Specialization;
import by.daryazalevskaya.finalproject.model.User;
import by.daryazalevskaya.finalproject.model.employee.JobPreference;
import by.daryazalevskaya.finalproject.model.type.Currency;
import by.daryazalevskaya.finalproject.model.type.Role;
import by.daryazalevskaya.finalproject.model.type.Schedule;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class JobPreferenceDaoImplTest {

    private JobPreferenceDaoImpl dao = new JobPreferenceDaoImpl();

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

    @DataProvider(name = "jobPreference")
    public Object[][] createJobPreference() {
        JobPreference preference = JobPreference.builder()
                .currency(Currency.BYN)
                .position("Teacher")
                .schedule(Schedule.FULL_DAY)
                .specialization(new Specialization(1))
                .experience(5)
                .salary(400)
                .build();
        return new Object[][]{{preference}};
    }


    @Test(dataProvider = "jobPreference")
    public void createTest(JobPreference jobPreference) throws DaoException, InsertIdDataBaseException {
        Integer id = dao.create(jobPreference);
        Assert.assertNotNull(id);
    }

    @DataProvider(name = "preferenceRead")
    public Object[][] createReadPreference() {
        final Integer id = 5;
        JobPreference preference = JobPreference.builder()
                .id(id)
                .salary(234)
                .currency(Currency.USD)
                .schedule(Schedule.FULL_DAY)
                .experience(10)
                .specialization(new Specialization(13))
                .position("Java developer")
                .build();
        return new Object[][]{{preference, id}};
    }

    @Test(dataProvider = "preferenceRead")
    public void readTest(JobPreference preference, Integer id) throws DaoException {
        Assert.assertEquals(preference, dao.read(id).get());
    }


    @Test
    public void updateTest() throws DaoException, NoEntityInDataBaseException {
        final Integer id = 7;
        final int experience = 2;
        JobPreference jobPreferenceFromDB = dao.read(id).orElseThrow(NoEntityInDataBaseException::new);
        jobPreferenceFromDB.setExperience(experience);

        dao.update(jobPreferenceFromDB);
        Optional<JobPreference> updatedPreference = dao.read(id);
        Assert.assertEquals(updatedPreference.get(), jobPreferenceFromDB);
    }

    @Test
    public void deleteTest() throws DaoException {
        final Integer id = 7;
        Optional<JobPreference> preferenceFromDB = dao.read(id);
        Assert.assertNotNull(preferenceFromDB.get());
        dao.delete(id);
        Assert.assertTrue(dao.read(id).isEmpty());
    }

    @DataProvider(name = "specializationId")
    public Object[][] createSpecializationId() {
        return new Object[][]{
                {"Sales", 2},
                {"Qwert", null},
        };
    }

    @Test(dataProvider = "specializationId")
    public void testReadIdBySpecialization(String specialization, Integer id) throws DaoException {
        Assert.assertEquals(dao.readIdBySpecialization(specialization), id);
    }


    @DataProvider(name = "specialization")
    public Object[][] createSpecialization() {
        return new Object[][]{
                {-3, Optional.empty()},
                {2, Optional.of(new Specialization(2, "Sales"))},
                {100, Optional.empty()}
        };
    }

    @Test(dataProvider = "specialization")
    public void testReadSpecializationById(Integer id, Optional<Specialization> specialization) throws DaoException {
        Assert.assertEquals(dao.readSpecializationById(id), specialization);
    }

    @Test
    public void findAllSpecializations() throws DaoException {
        Assert.assertTrue(dao.findAll().size()!=0);
    }
}
