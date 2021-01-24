package by.daryazalevskaya.finalproject.service.impl;

import by.daryazalevskaya.finalproject.dao.ContactDao;
import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.VacancyDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.model.Entity;
import by.daryazalevskaya.finalproject.service.ContactService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ContactServiceImpl extends ContactService {
    @Override
    public Integer addNewContact(Contact entity) throws DaoException, TransactionException {
        try {
            ContactDao contactDao = transaction.createDao(DaoType.CONTACT);
            return contactDao.create(entity);
        } catch (DaoException | InsertIdDataBaseException ex) {
            transaction.rollback();
            throw new DaoException(ex);
        }
    }

    @Override
    public Optional<Contact> read(Integer id) throws DaoException {
        if (id == null) {
            return Optional.empty();
        }
        ContactDao contactDao = transaction.createDao(DaoType.CONTACT);
        return contactDao.read(id);
    }

    @Override
    public void update(Contact entity) throws DaoException, TransactionException {
        try {
            ContactDao contactDao = transaction.createDao(DaoType.CONTACT);
            if (Objects.isNull(entity.getId()) && contactDao.read(entity.getId()).isEmpty()) {
                contactDao.create(entity);
            } else {
                contactDao.update(entity);
            }
        } catch (DaoException | InsertIdDataBaseException ex) {
            transaction.rollback();
            throw new DaoException(ex);
        }
    }


    @Override
    public void delete(Integer id) throws DaoException, TransactionException {
        try {
            ContactDao contactDao = transaction.createDao(DaoType.CONTACT);
            contactDao.delete(id);
        } catch (DaoException ex) {
            transaction.rollback();
            throw new DaoException(ex);
        }
    }

}
