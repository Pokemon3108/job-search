package finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.impl.EmployeeDaoImpl;
import by.daryazalevskaya.finalproject.dao.impl.EmployerDaoImpl;
import by.daryazalevskaya.finalproject.model.employee.Employee;
import by.daryazalevskaya.finalproject.model.employee.JobPreference;
import by.daryazalevskaya.finalproject.model.employee.Resume;
import by.daryazalevskaya.finalproject.model.employer.Employer;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeDaoImplTest {
    private EmployeeDaoImpl dao = new EmployeeDaoImpl();

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

    @DataProvider(name = "employeeCreate")
    public Object[][] createEmployee() {
        final Integer userId = 1;
        Employee employee = new Employee(userId);
        employee.setResume(new Resume(1));
        return new Object[][]{{employee}};
    }


    @Test(dataProvider = "employeeCreate")
    public void createTest(Employee employee) throws DaoException {
        Integer id = dao.create(employee);
        Assert.assertNotNull(id);
    }

    @Test
    public void readTest() throws DaoException {
        final Integer userId=4;
        final Integer resumeId=4;
        Employee employee=new Employee(userId);
        employee.setResume(new Resume(resumeId));
        Assert.assertEquals(employee, dao.read(userId).get());
    }
}
