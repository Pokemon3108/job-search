package finalproject.service.impl;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.impl.CountryDaoImpl;
import by.daryazalevskaya.finalproject.dao.impl.EmployeePersonalInfoDaoImpl;
import by.daryazalevskaya.finalproject.dao.transaction.Transaction;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionImpl;
import by.daryazalevskaya.finalproject.model.Country;
import by.daryazalevskaya.finalproject.model.employee.EmployeePersonalInfo;
import by.daryazalevskaya.finalproject.model.type.DaoType;
import by.daryazalevskaya.finalproject.model.type.Gender;
import by.daryazalevskaya.finalproject.service.impl.EmployeePersonalInfoServiceImpl;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.Optional;

public class EmployeePersonalInfoServiceTest {
    private EmployeePersonalInfoServiceImpl service=new EmployeePersonalInfoServiceImpl();

    @Mock
    private EmployeePersonalInfoDaoImpl infoDao =new EmployeePersonalInfoDaoImpl();

    @Mock
    private CountryDaoImpl countryDao=new CountryDaoImpl();

    @BeforeTest
    public void initDao() {
        MockitoAnnotations.openMocks(this);
        Transaction transaction= Mockito.mock(TransactionImpl.class);
        Mockito.when(transaction.createDao(DaoType.EMPLOYEE_PERSONAL_INFO)).thenReturn(infoDao);
        Mockito.when(transaction.createDao(DaoType.COUNTRY)).thenReturn(countryDao);
        service.setTransaction(transaction);
    }

    @Test
    public void readTest() throws DaoException {
        final Integer countryId=90;
        Country country=new Country(countryId, "USA");

        final Integer id=3;
        EmployeePersonalInfo info=EmployeePersonalInfo.builder()
                .name("Mike")
                .gender(Gender.MALE)
                .country(country)
                .birthday(LocalDate.of(1999, 12,12))
                .id(3)
                .build();
        Mockito.when(infoDao.read(id)).thenReturn(Optional.of(info));
        Mockito.when(countryDao.read(countryId)).thenReturn(Optional.of(country));

        Assert.assertEquals(Optional.of(info), service.read(id));

    }

    @Test
    public void readEmptyTest() throws DaoException {
        final Integer countryId=90;
        Country country=new Country(countryId, "USA");

        final Integer id=null;
        Mockito.when(infoDao.read(id)).thenReturn(Optional.empty());
        Mockito.when(countryDao.read(countryId)).thenReturn(Optional.of(country));

        Assert.assertEquals(Optional.empty(), service.read(id));
    }
}
