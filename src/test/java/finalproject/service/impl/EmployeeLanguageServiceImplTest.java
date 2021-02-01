package finalproject.service.impl;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.impl.EmployeeLanguageDaoImpl;
import by.daryazalevskaya.finalproject.dao.transaction.Transaction;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionImpl;
import by.daryazalevskaya.finalproject.model.employee.EmployeeLanguage;
import by.daryazalevskaya.finalproject.model.employee.Language;
import by.daryazalevskaya.finalproject.model.type.DaoType;
import by.daryazalevskaya.finalproject.model.type.LanguageLevel;
import by.daryazalevskaya.finalproject.service.impl.EmployeeLanguageServiceImpl;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Optional;

public class EmployeeLanguageServiceImplTest {
    private EmployeeLanguageServiceImpl service=new EmployeeLanguageServiceImpl();

    @Mock
    private EmployeeLanguageDaoImpl dao=new EmployeeLanguageDaoImpl();

    @BeforeTest
    public void initDao() {
        MockitoAnnotations.openMocks(this);
        Transaction transaction= Mockito.mock(TransactionImpl.class);
        Mockito.when(transaction.createDao(DaoType.EMPLOYEE_LANGUAGE)).thenReturn(dao);
        service.setTransaction(transaction);
    }

    @Test
    public void testRead() throws DaoException {
        final Integer catalogLangId=1;
        Language language=new Language(catalogLangId);
        language.setName("Russian");
        Mockito.when(dao.readLanguageFromCatalog(catalogLangId)).thenReturn(Optional.of(language));

        final Integer employeeLangId=1;
        EmployeeLanguage employeeLanguage=new EmployeeLanguage(employeeLangId);
        employeeLanguage.setLevel(LanguageLevel.B2);
        Mockito.when(dao.read(employeeLangId)).thenReturn(Optional.of(employeeLanguage));

        employeeLanguage.setName(language);

        Assert.assertEquals(Optional.of(employeeLanguage), service.read(employeeLangId));
    }

    @Test
    public void testReadEmpty() throws DaoException {
        final Integer catalogLangId=1;
        Language language=new Language(catalogLangId);
        language.setName("Russian");
        Mockito.when(dao.readLanguageFromCatalog(catalogLangId)).thenReturn(Optional.of(language));

        final Integer employeeLangId=1;
        Mockito.when(dao.read(employeeLangId)).thenReturn(Optional.empty());

        Assert.assertEquals(Optional.empty(), service.read(employeeLangId));
    }

}
