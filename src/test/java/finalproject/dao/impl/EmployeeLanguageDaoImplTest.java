package finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.NoEntityInDataBaseException;
import by.daryazalevskaya.finalproject.dao.impl.EmployeeLanguageDaoImpl;
import by.daryazalevskaya.finalproject.model.employee.EmployeeLanguage;
import by.daryazalevskaya.finalproject.model.employee.Language;
import by.daryazalevskaya.finalproject.model.type.LanguageLevel;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class EmployeeLanguageDaoImplTest {
    private EmployeeLanguageDaoImpl dao = new EmployeeLanguageDaoImpl();

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

    @DataProvider(name = "language")
    public Object[][] createEmployeeLanguage() {
        EmployeeLanguage language = new EmployeeLanguage(new Language(1), LanguageLevel.B1);
        return new Object[][]{{language}};
    }

    @Test(dataProvider = "language")
    public void createTest(EmployeeLanguage language) throws DaoException, InsertIdDataBaseException {
        Integer id = dao.create(language);
        Assert.assertNotNull(id);
    }

    @DataProvider(name = "languageRead")
    public Object[][] createReadEmployeeLanguage() {
        EmployeeLanguage employeeLanguage=EmployeeLanguage.builder()
                .level(LanguageLevel.B2)
                .name(new Language(2))
                .id(1)
                .build();
        return new Object[][]{{1, Optional.of(employeeLanguage)},
                {-1, Optional.empty()},
                {999, Optional.empty()}};
    }

    @Test(dataProvider = "languageRead")
    public void readTest(Integer id, Optional<EmployeeLanguage> language ) throws DaoException {
        Assert.assertEquals(language, dao.read(id));
    }

    @Test
    public void updateTest() throws DaoException, NoEntityInDataBaseException {
        final Integer id = 2;
        EmployeeLanguage employeeLanguageDB = dao.read(id).orElseThrow(NoEntityInDataBaseException::new);
        employeeLanguageDB.setLevel(LanguageLevel.A1);

        dao.update(employeeLanguageDB);
        Optional<EmployeeLanguage> updatedLanguage = dao.read(id);
        Assert.assertEquals(updatedLanguage.get(), employeeLanguageDB);
    }

    @Test
    public void deleteTest() throws DaoException {
        final Integer id = 3;
        Optional<EmployeeLanguage> employeeLanguageDB = dao.read(id);
        Assert.assertNotNull(employeeLanguageDB.get());
        dao.delete(id);
        Assert.assertTrue(dao.read(id).isEmpty());
    }

    @Test
    public void testFindAll() throws DaoException {
        Assert.assertTrue(dao.findAll().size()!=0);
    }

    @Test
    public void testFindAllLanguages() throws DaoException {
        final int size=7;
        List<Language> languages = dao.readAllLanguages();
        Assert.assertEquals(languages.size(), size);
    }

    @DataProvider(name = "languageCatalog")
    public Object[][] createReadLanguage() {
        return new Object[][]{{1, Optional.of(new Language(1, "Russian"))},
                {-1, Optional.empty()},
                {999, Optional.empty()}};
    }

    @Test(dataProvider = "languageCatalog")
    public void readCatalogLanguageTest(Integer id, Optional<Language> language ) throws DaoException {
        Assert.assertEquals(language, dao.readLanguageFromCatalog(id));
    }
}
