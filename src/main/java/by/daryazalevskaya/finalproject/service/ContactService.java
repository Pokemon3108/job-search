package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.Contact;

import java.util.Optional;

public abstract class ContactService  extends  BaseService  {
    public abstract Integer addNewContact(Contact contact) throws DaoException, TransactionException;

    public abstract Optional<Contact> read(Integer id) throws DaoException;

    public abstract void update(Contact vacancy) throws DaoException, TransactionException;

    public abstract void delete(int id) throws DaoException, TransactionException;
}
