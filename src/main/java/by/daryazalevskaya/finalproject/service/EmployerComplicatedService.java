package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.Contact;

public abstract class EmployerComplicatedService extends BaseService {
    public  abstract void saveContact(Integer userId, Contact contact) throws TransactionException, DaoException;
}
