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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.util.Optional;
import java.util.ResourceBundle;


public class UserDaoImplTest {
    private UserDaoImpl userDao = new UserDaoImpl();

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


    @DataProvider(name = "user")
    public Object[][] createUser() {
        User user = User.builder().password("$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.1111")
                .role(Role.EMPLOYEE)
                .email("zdashka@tut.by")
                .build();
        return new Object[][]{{user}};
    }


    @Test(dataProvider = "user")
    public void createTest(User user) throws DaoException, InsertIdDataBaseException {
        int id = userDao.create(user);
        Assert.assertNotNull(id);
    }


    @DataProvider(name = "userRead")
    public Object[][] createUserForRead() {
        final int id=1;
        User user = User.builder().password("01234567890123456789")
                .role(Role.EMPLOYEE)
                .email("pokemon")
                .id(id)
                .build();
        return new Object[][]{{user, id}};
    }

    @Test(dataProvider = "userRead")
    public void readNameTest(User user, int id) throws DaoException, NoEntityInDataBaseException {
        final String username = "pokemon";
        User userFromDB = userDao.read(username).orElseThrow(NoEntityInDataBaseException::new);
        Assert.assertEquals(user, userFromDB);
    }

    @Test(dataProvider = "userRead")
    public void readIdTest(User user, int id) throws DaoException, NoEntityInDataBaseException {
        Optional<User> userFromDB = userDao.read(id);
        Assert.assertEquals(user, userFromDB.orElseThrow(NoEntityInDataBaseException::new));
    }

    @Test
    public void findAllTest() throws DaoException {
        Assert.assertTrue(userDao.findAll().size() != 0);
    }


    @DataProvider(name = "updatedUser")
    public Object[][] createUpdatedUser() {
        final  int id=3;
        User user = User.builder().password("01234567890123456789")
                .role(Role.EMPLOYER)
                .email("Dasha")
                .id(id)
                .build();
        return new Object[][]{{user, id}};
    }

    @Test(dataProvider = "updatedUser")
    public void updateTest(User user, int id) throws DaoException, NoEntityInDataBaseException {
        final String email = "Dasha@tut.by";
        final String newEmail = "MyLogin@tut.by";
        User userFromDB = userDao.read(email).orElseThrow(NoEntityInDataBaseException::new);
        userFromDB.setEmail(newEmail);

        userDao.update(user);
        Optional<User> updatedUserFromDB = userDao.read(id);
        Assert.assertEquals(updatedUserFromDB.get(), user);
    }


    @Test
    public void deleteTest() throws DaoException {
        final int id=3;
        userDao.delete(id);
        Assert.assertTrue(userDao.read(id).isEmpty());
    }

}
