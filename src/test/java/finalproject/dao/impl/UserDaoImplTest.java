package finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.NoEntityInDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.dao.impl.UserDaoImpl;
import by.daryazalevskaya.finalproject.dao.pool.ConnectionPool;
import by.daryazalevskaya.finalproject.model.User;
import by.daryazalevskaya.finalproject.model.type.Role;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;


public class UserDaoImplTest {
    private UserDaoImpl userDao = new UserDaoImpl();

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
        connection= DriverManager.getConnection(url, userName, password);
        userDao.setConnection(connection);
    }

    @AfterMethod
    public void closeConnection() throws SQLException {
        connection.close();
    }

    @DataProvider(name = "user")
    public Object[][] createUser() {
        User user = User.builder().password("$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.1111")
                .role(Role.EMPLOYEE)
                .email("zdashka31@tut.by")
                .build();
        return new Object[][]{{user}};
    }


    @Test(dataProvider = "user")
    public void createTest(User user) throws DaoException, InsertIdDataBaseException {
        Integer id = userDao.create(user);
        Assert.assertNotNull(id);
    }


    @DataProvider(name = "userRead")
    public Object[][] createUserForRead() {
        final int id=1;
        User user = User.builder().password("01234567890123456789"+"1".repeat(44))
                .role(Role.EMPLOYEE)
                .email("pokemon31@gmail.com")
                .id(id)
                .build();
        return new Object[][]{{user,  id}};
    }

    @Test(dataProvider = "userRead")
    public void readNameTest(User user, int id) throws DaoException, NoEntityInDataBaseException {
        final String username = "pokemon31@gmail.com";
        User userFromDB = userDao.read(username).orElseThrow(NoEntityInDataBaseException::new);
        Assert.assertEquals(user, userFromDB);
    }

    @Test(dataProvider = "userRead")
    public void readIdTest(User user, int id) throws DaoException, NoEntityInDataBaseException {
        Optional<User> userFromDB = userDao.read(id);
        Assert.assertEquals(user, userFromDB.orElseThrow(NoEntityInDataBaseException::new));
    }


    @DataProvider(name = "updatedUser")
    public Object[][] createUpdatedUser() {
        final int id=2;
        final String email="Dasha@tut.by";
        return new Object[][]{{email, id}};
    }

    @Test(dataProvider = "updatedUser")
    public void updateTest(String email, int id) throws DaoException, NoEntityInDataBaseException {
        final String newEmail = "MyLogin@tut.by";
        User userFromDB = userDao.read(email).orElseThrow(NoEntityInDataBaseException::new);
        userFromDB.setEmail(newEmail);

        userDao.update(userFromDB);
        Optional<User> updatedUserFromDB = userDao.read(id);
        Assert.assertEquals(updatedUserFromDB.get(), userFromDB);
    }


    @Test
    public void deleteTest() throws DaoException {
        final int id=3;
        userDao.delete(id);
        Assert.assertTrue(userDao.read(id).isEmpty());
    }

    @Test
    public void findAllTest() throws DaoException {
        Assert.assertTrue(userDao.findAll().size() != 0);
    }

}
