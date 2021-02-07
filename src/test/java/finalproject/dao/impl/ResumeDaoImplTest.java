package finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.IllegalOperationException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.impl.ResumeDaoImpl;
import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.model.employee.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ResumeDaoImplTest {
    private ResumeDaoImpl dao=new ResumeDaoImpl();

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

    @DataProvider(name = "resume")
    public Object[][] createResume() {
        Resume resume=Resume.builder()
                .employee(new Employee(4))
                .build();
        return new Object[][]{{resume}};
    }


    @Test(dataProvider = "resume")
    public void createTest(Resume resume) throws DaoException, InsertIdDataBaseException {
        Integer id = dao.create(resume);
        Assert.assertNotNull(id);
    }

    @DataProvider(name = "readResume")
    public Object[][] readResume() {
        final Integer id=1;
        Resume resume=Resume.builder()
                .contact(new Contact(2))
                .skills("Good")
                .jobPreference(new JobPreference(5))
                .personalInfo(new EmployeePersonalInfo(2))
                .employee(new Employee(1))
                .language(new EmployeeLanguage(1))
                .id(id)
                .build();
        return new Object[][]{{Optional.of(resume), id},
                {Optional.empty(), -1},
                {Optional.empty(), 999}
        };
    }

    @Test(dataProvider = "readResume")
    public void createForRead(Optional<Resume> resume, Integer id) throws DaoException {
        Assert.assertEquals(dao.read(id), resume);
    }

    @Test(expectedExceptions = IllegalOperationException.class)
    public void testUpdate() {
        dao.update(new Resume(1));
    }


    @Test
    public void testFindAll() throws DaoException {
        Assert.assertTrue(dao.findAll().size()!=0);
    }

    @Test
    public void testCreateContact() throws DaoException {
        Integer id=3;
        Contact contact=new Contact(4);
        Resume resume=new Resume(id);
        resume.setContact(contact);

        dao.createContact(resume);
        Optional<Resume> updatedResume=dao.read(id);
        Assert.assertEquals(updatedResume.get().getContact(), contact);
        Assert.assertEquals(updatedResume.get().getId(), id);
    }

    @Test
    public void testCreatePersonalInfo() throws DaoException {
        Integer id=3;
        EmployeePersonalInfo info=new EmployeePersonalInfo(3);
        Resume resume=new Resume(id);
        resume.setPersonalInfo(info);

        dao.createPersonalInfo(resume);
        Optional<Resume> updatedResume=dao.read(id);
        Assert.assertEquals(updatedResume.get().getPersonalInfo(), info);
        Assert.assertEquals(updatedResume.get().getId(), id);
    }

    @Test
    public void testUpdateSkills() throws DaoException {
        Integer id=3;
        String skills="New skills";
        Resume resume=new Resume(id);
        resume.setSkills(skills);

        dao.updateSkills(resume);
        Optional<Resume> updatedResume=dao.read(id);
        Assert.assertEquals(updatedResume.get().getSkills(), skills);
        Assert.assertEquals(updatedResume.get().getId(), id);
    }

    @Test
    public void testCreateJobPreference() throws DaoException {
        Integer id=3;
        JobPreference preference=new JobPreference(5);
        Resume resume=new Resume(id);
        resume.setJobPreference(preference);

        dao.createJobPreference(resume);
        Optional<Resume> updatedResume=dao.read(id);
        Assert.assertEquals(updatedResume.get().getJobPreference(), preference);
        Assert.assertEquals(updatedResume.get().getId(), id);
    }

    @Test
    public void testCreateEmployeeLanguage() throws DaoException {
        Integer id=3;
        EmployeeLanguage language=new EmployeeLanguage(4);
        Resume resume=new Resume(id);
        resume.setLanguage(language);

        dao.createEmployeeLanguage(resume);
        Optional<Resume> updatedResume=dao.read(id);
        Assert.assertEquals(updatedResume.get().getLanguage(), language);
        Assert.assertEquals(updatedResume.get().getId(), id);
    }
}
