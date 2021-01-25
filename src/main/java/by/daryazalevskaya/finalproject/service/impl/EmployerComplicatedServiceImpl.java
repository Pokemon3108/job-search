package by.daryazalevskaya.finalproject.service.impl;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.service.ContactService;
import by.daryazalevskaya.finalproject.service.EmployerComplicatedService;
import by.daryazalevskaya.finalproject.service.EmployerService;

import java.util.Objects;

public class EmployerComplicatedServiceImpl extends EmployerComplicatedService {
    @Override
    public void saveContact(Integer userId, Contact contact) throws TransactionException, DaoException {
        ContactService contactService=new ContactServiceImpl();
        contactService.setTransaction(transaction);
        if (Objects.nonNull(contact.getId())) {
            contactService.update(contact);
        } else {
            Integer contactId = contactService.createContact(contact);
            contact.setId(contactId);

            EmployerService employerService = new EmployerServiceImpl();
            employerService.setTransaction(transaction);
            employerService.createContact(userId, contact);
        }
        transaction.commit();
    }
}
