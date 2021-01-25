package finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.NoEntityInDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.dao.impl.ContactDaoImpl;
import by.daryazalevskaya.finalproject.dao.pool.ConnectionPool;
import by.daryazalevskaya.finalproject.model.Contact;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.util.Optional;
import java.util.ResourceBundle;

public class ContactDaoImplTest {
    private ContactDaoImpl contactDao = new ContactDaoImpl();


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
        contactDao.setConnection(connection);

    }


    @DataProvider(name = "contact")
    public Object[][] createContact() {
        Contact contact = Contact.builder()
                .telephone("+375295987809")
                .email("pokemon@tut.by")
                .skype("popugich1456")
                .build();
        return new Object[][]{{contact}};
    }


    @Test(dataProvider = "contact")
    public void createTest(Contact contact) throws DaoException, InsertIdDataBaseException {
        Integer id = contactDao.create(contact);
        Assert.assertNotNull(id);
    }


    @DataProvider(name = "contactRead")
    public Object[][] createReadContact() {
        //should be in database before read test
        final int id=2;
        Contact contact = Contact.builder()
                .telephone("+375295395676")
                .email("parrot@gmail.com")
                .skype("parrot")
                .id(id)
                .build();
        return new Object[][]{{contact, id}};
    }

    @Test(dataProvider = "contactRead")
    public void readTest(Contact contact, int id) throws DaoException {
        Assert.assertEquals(contact, contactDao.read(id).get());
    }

    @Test
    public void findAllTest() throws DaoException {
        Assert.assertTrue(contactDao.findAll().size() != 0);
    }


    @DataProvider(name = "contactForDelete")
    public Object[][] createContactForDelete() {
        //should be in database before delete test
        final int id=3;
        Contact contact = Contact.builder()
                .telephone("+74956789898")
                .email("qwerty@outlook.com")
                .skype("pikachi34")
                .id(id)
                .build();
        return new Object[][]{{contact, id}};
    }


    @Test(dataProvider = "contactForDelete")
    public void deleteTest(Contact contact, int id) throws DaoException {
        Optional<Contact> contactFromDB = contactDao.read(id);
        contact.setId(contactFromDB.get().getId());
        contactFromDB.get().setTelephone("+375296637779");
        contactDao.delete(id);
        Assert.assertTrue(contactDao.read(id).isEmpty());
    }


    @DataProvider(name = "contactForUpdate")
    public Object[][] createContactForUpdate() {
        //should be in database before update test
        final int id=4;
        Contact contact = Contact.builder()
                .telephone("+375295989090")
                .email("qwerty@gmail.com")
                .skype("login1456")
                .id(id)
                .build();
        return new Object[][]{{contact, id}};
    }


    @Test(dataProvider = "contactForUpdate")
    public void updateTest(Contact contact, int id) throws DaoException, NoEntityInDataBaseException {
        final String newEmail = "qwerty@gmail.com";
        Contact contactFromDB = contactDao.read(id).orElseThrow(NoEntityInDataBaseException::new);
        contact.setId(contactFromDB.getId());
        contactFromDB.setEmail(newEmail);
        contactDao.update(contactFromDB);
        Assert.assertEquals(contactFromDB, contactDao.read(id).get());

    }

}
