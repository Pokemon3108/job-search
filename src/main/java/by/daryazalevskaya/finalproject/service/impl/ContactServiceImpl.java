package by.daryazalevskaya.finalproject.service.impl;

import by.daryazalevskaya.finalproject.dao.ContactDao;
import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.service.ContactService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ContactServiceImpl extends ContactService {
    @Override
    public Integer addNewEntity(Contact entity) throws DaoException, InsertIdDataBaseException {
        ContactDao contactDao = transaction.createDao(DaoType.CONTACT);
        return contactDao.create(entity);

    }

    @Override
    public Optional<Contact> read(Integer id) throws DaoException, PoolException {
        if (id==null) {
            return Optional.empty();
        }
        ContactDao contactDao=transaction.createDao(DaoType.CONTACT);
        return contactDao.read(id);
    }

    @Override
    public void update(Contact entity) throws DaoException, PoolException, InsertIdDataBaseException {
        ContactDao contactDao=transaction.createDao(DaoType.CONTACT);
        if (Objects.isNull(entity.getId()) && contactDao.read(entity.getId()).isEmpty()) {
            contactDao.create(entity);
        } else {
            contactDao.update(entity);
        }
    }

    @Override
    public void delete(int id) throws DaoException, PoolException {

    }

    @Override
    public List<Contact> findAll() throws DaoException, PoolException {
        return null;
    }
}
