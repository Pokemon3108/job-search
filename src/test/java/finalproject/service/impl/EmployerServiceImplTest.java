package finalproject.service.impl;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.impl.ContactDaoImpl;
import by.daryazalevskaya.finalproject.dao.impl.CountryDaoImpl;
import by.daryazalevskaya.finalproject.dao.impl.EmployerDaoImpl;
import by.daryazalevskaya.finalproject.dao.transaction.Transaction;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionImpl;
import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.model.Country;
import by.daryazalevskaya.finalproject.model.employer.Employer;
import by.daryazalevskaya.finalproject.model.type.DaoType;
import by.daryazalevskaya.finalproject.service.impl.EmployerServiceImpl;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Optional;

public class EmployerServiceImplTest {
    private EmployerServiceImpl service=new EmployerServiceImpl();

    @Mock
    private EmployerDaoImpl employerDao =new EmployerDaoImpl();

    @Mock
    private CountryDaoImpl countryDao=new CountryDaoImpl();

    @Mock
    private ContactDaoImpl contactDao=new ContactDaoImpl();

    @BeforeTest
    public void initDao() {
        MockitoAnnotations.openMocks(this);
        Transaction transaction= Mockito.mock(TransactionImpl.class);
        Mockito.when(transaction.createDao(DaoType.EMPLOYER)).thenReturn(employerDao);
        Mockito.when(transaction.createDao(DaoType.COUNTRY)).thenReturn(countryDao);
        Mockito.when(transaction.createDao(DaoType.CONTACT)).thenReturn(contactDao);
        service.setTransaction(transaction);
    }

    @Test
    public void testRead() throws DaoException {
        final Integer countryId=90;
        Country country=new Country(countryId, "USA");

        final Integer contactId=56;
        Contact contact=Contact.builder()
                .email("email@gmail.com")
                .telephone("+375445677878")
                .build();

        final Integer employerId=23;
        Employer employer=Employer.builder()
                .companyName("Racket")
                .city("Minsk")
                .country(country)
                .contact(contact)
                .build();

        Mockito.when(employerDao.read(employerId)).thenReturn(Optional.of(employer));
        Mockito.when(contactDao.read(contactId)).thenReturn(Optional.of(contact));
        Mockito.when(countryDao.read(countryId)).thenReturn(Optional.of(country));

        Assert.assertEquals(Optional.of(employer), service.read(employerId));
    }

    @Test
    public void testReadEmpty() throws DaoException {
        final Integer countryId=90;
        Country country=new Country(countryId, "USA");

        final Integer contactId=56;
        Contact contact=Contact.builder()
                .email("email@gmail.com")
                .telephone("+375445677878")
                .build();

        final Integer employerId=23;

        Mockito.when(employerDao.read(employerId)).thenReturn(Optional.empty());
        Mockito.when(contactDao.read(contactId)).thenReturn(Optional.of(contact));
        Mockito.when(countryDao.read(countryId)).thenReturn(Optional.of(country));

        Assert.assertEquals(Optional.empty(), service.read(employerId));
    }

    @Test
    public void testReadEmptyContact() throws DaoException {
        final Integer countryId=90;
        Country country=new Country(countryId, "USA");

        final Integer employerId=23;
        Employer employer=Employer.builder()
                .companyName("Racket")
                .city("Minsk")
                .country(country)
                .build();

        final Integer contactId=34;

        Mockito.when(employerDao.read(employerId)).thenReturn(Optional.of(employer));
        Mockito.when(contactDao.read(contactId)).thenReturn(Optional.empty());
        Mockito.when(countryDao.read(countryId)).thenReturn(Optional.of(country));

        Assert.assertEquals(Optional.of(employer), service.read(employerId));
    }

    @Test
    public void isRepeatedCompanyNameTest() throws DaoException {
        final Integer userId=13;
        final String companyName="Hello";
        Mockito.when(employerDao.readUserIdByCompany(companyName)).thenReturn(userId);
        Assert.assertFalse(service.isRepeatedCompanyName(companyName,userId));
    }
}
