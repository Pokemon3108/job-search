package finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.dao.impl.UserDaoImpl;
import by.daryazalevskaya.finalproject.dao.pool.ConnectionPool;
import by.daryazalevskaya.finalproject.model.User;
import by.daryazalevskaya.finalproject.model.type.Role;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.util.ResourceBundle;

@Log4j2
public class UserDaoImplTest {
    private UserDaoImpl userDao = new UserDaoImpl();

    private Integer id;

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
        userDao.setConnection(connection);

    }

    @BeforeMethod
    public void getUserIdByName() throws DaoException {
        final String username = "Kesha";
        this.id = userDao.read(username).getId();
    }


    @DataProvider(name = "user")
    public Object[][] createUser() {
        User user = User.builder().password("01234567890123456789")
                .role(Role.EMPLOYEE)
                .username("Kesha")
                .build();
        return new Object[][]{{user}};
    }

    @DataProvider(name = "deleteUser")
    public Object[][] createUserForDelete() {
        User user = User.builder().password("0a234cccbb1234567aa")
                .role(Role.EMPLOYEE)
                .username("Parrot")
                .build();
        return new Object[][]{{user}};
    }

    @DataProvider(name = "updatedUser")
    public Object[][] createUpdatedUser() {
        User user = User.builder().password("01234567890123456789")
                .role(Role.EMPLOYER)
                .username("Dasha")
                .build();
        return new Object[][]{{user}};
    }

    @Test(dataProvider = "user")
    public void createTest(User user) throws DaoException, InsertIdDataBaseException {
        id = userDao.create(user);
        Assert.assertNotNull(id);
    }

    @Test(dataProvider = "user")
    public void readNameTest(User user) throws DaoException {
        final String username = "Kesha";
        User userFromDB = userDao.read(username);
        user.setId(id);
        Assert.assertEquals(user, userFromDB);
    }

    @Test(dataProvider = "user")
    public void readIdTest(User user) throws DaoException {
        User userFromDB = userDao.read(id);
        user.setId(id);
        Assert.assertEquals(user, userFromDB);
    }

    @Test
    public void findAllTest() throws DaoException {
        Assert.assertTrue(userDao.findAll().size() != 0);
    }

    @Test(dataProvider = "updatedUser")
    public void updateTest(User user) throws DaoException {
        final String username = "Dasha";
        final String newUserName="Pokemon";
        User userFromDB=userDao.read(username);
        userFromDB.setUsername(newUserName);

        Integer id = userFromDB.getId();
        user.setId(id);

        userDao.update(user);
        User updatedUserFromDB = userDao.read(id);
        Assert.assertEquals(updatedUserFromDB, user);
    }

    @Test(dataProvider = "deleteUser")
    public void deleteTest(User user) throws DaoException {
        final String username =user.getUsername();
        Integer id = userDao.read(username).getId();
        userDao.delete(id);
        Assert.assertNull(userDao.read(id));
    }

}
