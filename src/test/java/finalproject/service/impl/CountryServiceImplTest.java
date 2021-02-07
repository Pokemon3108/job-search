package finalproject.service.impl;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.impl.CountryDaoImpl;
import by.daryazalevskaya.finalproject.dao.transaction.Transaction;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionImpl;
import by.daryazalevskaya.finalproject.model.Country;
import by.daryazalevskaya.finalproject.model.type.DaoType;
import by.daryazalevskaya.finalproject.service.impl.CountryServiceImpl;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Optional;

public class CountryServiceImplTest {
    private CountryServiceImpl service=new CountryServiceImpl();

    @Mock
    private CountryDaoImpl dao=new CountryDaoImpl();

    @BeforeTest
    public void initDao() {
        MockitoAnnotations.openMocks(this);
        Transaction transaction= Mockito.mock(TransactionImpl.class);
        Mockito.when(transaction.createDao(DaoType.COUNTRY)).thenReturn(dao);
        service.setTransaction(transaction);
    }

    @Test
    public void testRead() throws DaoException {
        final Integer id=1;
        Country country=new Country(id);
        country.setName("Ukraine");
        Mockito.when(dao.read(id)).thenReturn(Optional.of(country));
        Assert.assertEquals(Optional.of(country), service.read(id));
    }

    @Test
    public void testReadEmpty() throws DaoException {
        final Integer id=null;
        Country country=new Country(id);
        country.setName("Ukraine");
        Mockito.when(dao.read(id)).thenReturn(Optional.of(country));
        Assert.assertEquals(Optional.empty(), service.read(id));
    }
}
