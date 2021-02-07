package finalproject.service.impl;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.impl.ContactDaoImpl;
import by.daryazalevskaya.finalproject.dao.transaction.Transaction;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionImpl;
import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.model.type.DaoType;
import by.daryazalevskaya.finalproject.service.impl.ContactServiceImpl;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Optional;

public class ContactServiceImplTest {
    private ContactServiceImpl service=new ContactServiceImpl();

    @Mock
    private ContactDaoImpl dao=new ContactDaoImpl();

    @BeforeTest
    public void initDao() {
        MockitoAnnotations.openMocks(this);
        Transaction transaction= Mockito.mock(TransactionImpl.class);
        Mockito.when(transaction.createDao(DaoType.CONTACT)).thenReturn(dao);
        service.setTransaction(transaction);
    }

    @Test
    public void testRead() throws DaoException {
        final Integer id=2;
        Optional<Contact> contact= Optional.of(Contact.builder()
                .telephone("+375295395676")
                .id(id)
                .skype("parrot")
                .email("parrot@gmail.com")
                .build());
        Mockito.when(dao.read(2)).thenReturn(contact);
        Assert.assertEquals(contact, service.read(id));
    }

}
