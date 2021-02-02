package finalproject.service.impl;

import by.daryazalevskaya.finalproject.dao.JobPreferenceDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.impl.JobPreferenceDaoImpl;
import by.daryazalevskaya.finalproject.dao.transaction.Transaction;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionImpl;
import by.daryazalevskaya.finalproject.model.Specialization;
import by.daryazalevskaya.finalproject.model.employee.JobPreference;
import by.daryazalevskaya.finalproject.model.type.DaoType;
import by.daryazalevskaya.finalproject.model.type.Schedule;
import by.daryazalevskaya.finalproject.service.impl.JobPreferenceServiceImpl;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Optional;

public class JobPreferenceServiceImplTest {
    private JobPreferenceServiceImpl service=new JobPreferenceServiceImpl();

    @Mock
    private JobPreferenceDao dao=new JobPreferenceDaoImpl();

    @BeforeTest
    public void initDao() {
        MockitoAnnotations.openMocks(this);
        Transaction transaction= Mockito.mock(TransactionImpl.class);
        Mockito.when(transaction.createDao(DaoType.JOB_PREFERENCE)).thenReturn(dao);
        service.setTransaction(transaction);
    }

    @Test
    public void readTest() throws DaoException {
        final Integer preferenceId=14;
        final Integer specId=13;
        final Specialization specialization=new Specialization(specId, "IT");
        JobPreference preference=JobPreference.builder()
                .position("Software developer")
                .schedule(Schedule.FULL_DAY)
                .id(preferenceId)
                .specialization(specialization)
                .build();
        Mockito.when(dao.read(preferenceId)).thenReturn(Optional.of(preference));
        Mockito.when(dao.readSpecializationById(specId)).thenReturn(Optional.of(specialization));
        Assert.assertEquals(Optional.of(preference), service.read(preferenceId));
    }

    @Test
    public void readEmptyTest() throws DaoException {
        final Integer preferenceId=null;
        final Integer specId=13;
        final Specialization specialization=new Specialization(specId, "IT");
        Mockito.when(dao.read(preferenceId)).thenReturn(Optional.empty());
        Mockito.when(dao.readSpecializationById(specId)).thenReturn(Optional.of(specialization));
        Assert.assertEquals(Optional.empty(), service.read(preferenceId));
    }

    @DataProvider(name="specId")
    public Object[][] createSpecId() {
        return new Object[][]{
                {"IT", 13},
                {"", null},
                {null, null}
        };
    }

    @Test(dataProvider = "specId")
    public void readIdBySpecializationNameTest(String specName, Integer id) throws DaoException {
        Mockito.when(dao.readIdBySpecialization(specName)).thenReturn(id);
        Assert.assertEquals(id, service.readIdBySpecializationName(specName));
    }

    @DataProvider(name="spec")
    public Object[][] createSpec() {
        return new Object[][]{
                {13, Optional.of(new Specialization(13, "IT"))},
                {null, Optional.empty()}
        };
    }

    @Test(dataProvider = "spec")
    public void readSpecializationByIdTest(Integer id, Optional<Specialization> specialization) throws DaoException {
        Mockito.when(dao.readSpecializationById(id)).thenReturn(specialization);
        Assert.assertEquals(specialization, service.readSpecializationById(id));
    }

}
